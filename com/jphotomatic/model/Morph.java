package com.jphotomatic.model;import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

class Morph
{

    double incrx[];
    double incry[];
    double sourceImageWeight;
    double destinationImageWeight;
    Vector mshS;
    Vector mshD;
    int sih;
    int siw;
    int pixelsS[];
    int dih;
    int diw;
    int pixelsD[];
    String fimage;
    BufferedImage bimg[];
    Morph()
    {
    }

    public Image[] mainMorph(Image image, Image image1, CustomPoint apoint[], CustomPoint apoint1[], int i, int j, String s)
    {
    	/*called from display imgS, imgD, ps, pd, noOfSMarkers + 4, NUM_FRAMES, fimage*/
        Image aimage[] = new Image[j + 2];
        bimg = new BufferedImage[j + 2];
        fimage = s;
        siw = image.getWidth(null);
        sih = image.getHeight(null);
        pixelsS = new int[siw * sih];
        try
        {
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, siw, sih, pixelsS, 0, siw);
            pixelgrabber.grabPixels();
        }
        catch(InterruptedException interruptedexception) { }
        if(image1 != null)
        {
            diw = image1.getWidth(null);
            dih = image1.getHeight(null);
            pixelsD = new int[diw * dih];
            aimage[j + 1] = image1;
            try
            {
                PixelGrabber pixelgrabber1 = new PixelGrabber(image1, 0, 0, diw, dih, pixelsD, 0, diw);
                pixelgrabber1.grabPixels();
            }
            catch(InterruptedException interruptedexception1) { }/*taking cormer CustomPoint in to account for deatination */
            apoint1[i - 4] = new CustomPoint(0, 0, i - 4);
            apoint1[i - 3] = new CustomPoint(diw - 1, 0, i - 3);
            apoint1[i - 2] = new CustomPoint(diw - 1, dih - 1, i - 2);
            apoint1[i - 1] = new CustomPoint(0, dih - 1, i - 1);
        } else
        {
            apoint1[i - 4] = new CustomPoint(0, 0, i - 4);
            apoint1[i - 3] = new CustomPoint(siw - 1, 0, i - 3);
            apoint1[i - 2] = new CustomPoint(siw - 1, sih - 1, i - 2);
            apoint1[i - 1] = new CustomPoint(0, sih - 1, i - 1);
        }
        aimage[0] = image;/* source image*/
        if(fimage != null)
        {
            for(int k = 0; k < j + 2; k++)
                bimg[k] = new BufferedImage(siw, sih, 1);

            for(int l = 0; l < siw * sih; l++)
            {
                bimg[0].setRGB(l % siw, l / siw, pixelsS[l]);/*Sets a pixel in this BufferedImage to the specified RGB value.*/
                if(image1 != null)
                    bimg[j + 1].setRGB(l % siw, l / siw, pixelsD[l]);
            }

            try
            {
                ImageIO.write(bimg[0], "JPG", new File(fimage + 0 + ".jpg"));
            }
            catch(IOException ioexception) { }
            if(image1 != null)
                try
                {
                    ImageIO.write(bimg[j + 1], "JPG", new File(fimage + (j + 1) + ".jpg"));
                }
                catch(IOException ioexception1) { }
        }
        apoint[i - 4] = new CustomPoint(0, 0, i - 4);
        apoint[i - 3] = new CustomPoint(siw - 1, 0, i - 3);
        apoint[i - 2] = new CustomPoint(siw - 1, sih - 1, i - 2);
        apoint[i - 1] = new CustomPoint(0, sih - 1, i - 1);
        Point_warper point_warper1 = new Point_warper();
        mshS = point_warper1.mesh_formation(apoint, i - 4, siw, sih);
        mshS = calc_m4(mshS);
        mshD = destMesh(apoint1, mshS);
        calcIncr(apoint, apoint1, i, j);
        for(int i1 = 1; i1 <= j; i1++)
        {
            destinationImageWeight = 2D * (double)i1 * (1.0D / (double)(j + 1));
            sourceImageWeight = 2D - destinationImageWeight;
            Vector vector = interpolateMesh(i1, mshS);
            aimage[i1] = interImage(image, image1, siw, sih, mshS, mshD, vector, i1);
        }

        return aimage;
    }

    public Image interImage(Image image, Image image1, int i, int j, Vector vector, Vector vector1, Vector vector2, 
            int k)/*generates an intermediate image.*/
    {
        UV uv1 = new UV();
        boolean flag = false;
        int i1 = 0;
        int ai[] = new int[i * j];
        for(int j2 = 0; j2 < j; j2++)
        {
            for(int k2 = 0; k2 < i; k2++)
            {
                CustomPoint point1 = new CustomPoint(k2, j2);
                int l2 = k2 + i * j2;
                int i3 = identify_mesh(point1, vector2);
                int l;
                if(i3 != -1)
                {
                    Mesh mesh1 = (Mesh)vector2.elementAt(i3);
                    Mesh mesh2 = (Mesh)vector.elementAt(i3);
                    Mesh mesh3 = (Mesh)vector1.elementAt(i3);
                    UV uv2 = uv1.calculate_uv(point1, mesh1.m1, mesh1.m2, mesh1.m3, mesh1.m4);
                    CustomPoint point2 = uv1.estimate_point(uv2, mesh2.m1, mesh2.m2, mesh2.m3, mesh2.m4);
                    CustomPoint point3 = uv1.estimate_point(uv2, mesh3.m1, mesh3.m2, mesh3.m3, mesh3.m4);
                    if(point2.x + i * point2.y < j * i && point2.x + i * point2.y > 0)
                        l = pixelsS[point2.x + i * point2.y];
                    else
                        l = pixelsS[l2];
                    if(image1 != null)
                        if(point3.x + i * point3.y < j * i && point3.x + i * point3.y > 0)
                            i1 = pixelsD[point3.x + i * point3.y];
                        else
                            i1 = pixelsD[l2];
                } else
                {
                    l = pixelsS[l2];
                    if(image1 != null)
                        i1 = pixelsD[l2];
                }
                if(image1 != null)
                {
                    int j3 = l;
                    int k3 = 0xff & j3 >> 24;
                    int l3 = 0xff & j3 >> 16;
                    int i4 = 0xff & j3 >> 8;
                    int j4 = 0xff & j3;
                    j3 = i1;
                    int k4 = 0xff & j3 >> 24;
                    int l4 = 0xff & j3 >> 16;
                    int i5 = 0xff & j3 >> 8;
                    int j5 = 0xff & j3;
                    int j1 = (int)((double)k3 * sourceImageWeight + (double)k4 * destinationImageWeight) / 2;
                    int k1 = (int)((double)l3 * sourceImageWeight + (double)l4 * destinationImageWeight) / 2;
                    int l1 = (int)((double)i4 * sourceImageWeight + (double)i5 * destinationImageWeight) / 2;
                    int i2 = (int)((double)j4 * sourceImageWeight + (double)j5 * destinationImageWeight) / 2;
                    ai[l2] = j1 << 24 | k1 << 16 | l1 << 8 | i2;
                } else
                {
                    ai[l2] = l;
                }
                if(fimage != null)
                    bimg[k].setRGB(l2 % i, l2 / i, ai[l2]);
            }

        }

        Image image2 = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(i, j, ai, 0, i));
        if(fimage != null)
            try
            {
                ImageIO.write(bimg[k], "JPG", new File(fimage + k + ".jpg"));
            }
            catch(IOException ioexception) { }
        return image2;
    }

    public Vector interpolateMesh(int i, Vector vector)/*generates an intermediate Mesh*/
    {
        Vector vector1 = new Vector(vector.size(), 2);
        for(int j = 0; j < vector.size(); j++)
        {
            Mesh mesh1 = new Mesh();
            Mesh mesh2 = (Mesh)vector.elementAt(j);
            mesh1.m1.x = mesh2.m1.x + (int)(incrx[mesh2.m1.id] * (double)i);
            mesh1.m1.y = mesh2.m1.y + (int)(incry[mesh2.m1.id] * (double)i);
            mesh1.m2.x = mesh2.m2.x + (int)(incrx[mesh2.m2.id] * (double)i);
            mesh1.m2.y = mesh2.m2.y + (int)(incry[mesh2.m2.id] * (double)i);
            mesh1.m3.x = mesh2.m3.x + (int)(incrx[mesh2.m3.id] * (double)i);
            mesh1.m3.y = mesh2.m3.y + (int)(incry[mesh2.m3.id] * (double)i);
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            mesh1.id = mesh2.id;
            vector1.addElement(mesh1);
        }

        return vector1;
    }

    public Vector calc_m4(Vector vector)
    {
        for(int i = 0; i < vector.size(); i++)
        {
            Mesh mesh1 = (Mesh)vector.elementAt(i);
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            vector.setElementAt(mesh1, i);
        }

        return vector;
    }

    public Vector destMesh(CustomPoint apoint[], Vector vector)
    {
        Vector vector1 = new Vector(vector.size(), 2);
        for(int i = 0; i < vector.size(); i++)
        {
            Mesh mesh1 = new Mesh();
            Mesh mesh2 = (Mesh)vector.elementAt(i);
            mesh1.m1 = apoint[mesh2.m1.id];
            mesh1.m2 = apoint[mesh2.m2.id];
            mesh1.m3 = apoint[mesh2.m3.id];
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            vector1.addElement(mesh1);
        }

        return vector1;
    }

    public int identify_mesh(CustomPoint point1, Vector vector)
    /*: -1 if 'CustomPoint p' is outside to all meshes otherwise returns index of Mesh that encloses the 'CustomPoint p' .
Description: checks whether 'CustomPoint p' is inside any Mesh.
*/
    {
        int i = -1;
        Point_warper point_warper1 = new Point_warper();
        for(int j = 0; j < vector.size(); j++)
        {
            Mesh mesh1 = (Mesh)vector.elementAt(j);
            if(!point_warper1.isInside3(mesh1.m1, mesh1.m2, mesh1.m3, point1))
                continue;
            if(i == -1)
            {
                i = j;
                continue;
            }
            if(mesh1.id != 1000)
                i = j;
        }

        return i;
    }

    public void calcIncr(CustomPoint apoint[], CustomPoint apoint1[], int i, int j)
    {
        incrx = new double[i];
        incry = new double[i];
        for(int k = 0; k < i; k++)
        {
            incrx[k] = (double)(apoint1[k].x - apoint[k].x) * (1.0D / (double)(j + 1));
            incry[k] = (double)(apoint1[k].y - apoint[k].y) * (1.0D / (double)(j + 1));
        }

    }

}