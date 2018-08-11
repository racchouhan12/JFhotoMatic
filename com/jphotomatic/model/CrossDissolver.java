package com.jphotomatic.model;//1st algo for morphing
// Source File Name:   CrossDissolver.java

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class CrossDissolver
{
    Image img[];
    Image scaledImage;
    PixelGrabber pg;
    int siw;
    int sih;
    int diw;
    int dih;
    int iw;
    int ih;
    int pixelsS[];
    int pixelsD[];
    int temp[][];
    int rs;
    int gs;
    int bs;
    int as;
    int rd;
    int gd;
    int bd;
    int ad;
    int r[];
    int g[];
    int b[];
    int a[];
    int newImage[];
    BufferedImage bimg[];
    int larger;
    CrossDissolver()
    {
    }

    public Image[] CrossDissolve(Image image, Image image1, int i, String s)
    {
        img = new Image[i + 2];                   /* i here is number of frames*/
        bimg = new BufferedImage[i + 2];
	/**	Reading the first image 	**/
        try
        {
            siw = image.getWidth(null);//src image width
            sih = image.getHeight(null);//src image height
            pixelsS = new int[siw * sih];//int array showing pixels
            pg = new PixelGrabber(image, 0, 0, siw, sih, pixelsS, 0, siw);

/*
 PixelGrabber(imageObject, int left,int top,int width,int height,int pixel[],int offset,int scanline width)   
Create a PixelGrabber object to grab the (x, y, w, h) rectangular
 section of pixels from the specified image into the given array.
 The pixels are stored into the array in the default RGB ColorModel.
 The RGB data for pixel (i, j) where (i, j) is inside the rectangle
 (x, y, w, h) is stored in the array at
 pix[(j - y) * scansize + (i - x) + offset].
*/   
            pg.grabPixels();
        }
        catch(InterruptedException interruptedexception) { }
	/**	Reading the second image 	**/
        try
        {
            diw = image1.getWidth(null);
            dih = image1.getHeight(null);
            pixelsD = new int[diw * dih];
            pg = new PixelGrabber(image1, 0, 0, diw, dih, pixelsD, 0, diw);
            pg.grabPixels();
        }
        catch(InterruptedException interruptedexception1) { }

	/* 	Adjusting the size of the images	*/
        if(siw * sih >= diw * dih)
        {
            larger = 0;
            iw = siw;
            ih = sih;
            scaledImage = image1.getScaledInstance(iw, ih, 1/* OR SCALE_DEFAULT --Use the default image-scaling algorithm*/);
/*Creates a scaled version of this image. A new Image object is returned which will render the image at the specified width and height by default. The new Image object may be loaded asynchronously even if the original source image has already been loaded completely. If either the width or height is a negative number then a value is substituted to maintain the aspect ratio of the original image dimensions. */

            try
            {
                newImage = new int[iw * ih];
                pg = new PixelGrabber(scaledImage, 0, 0, iw, ih, newImage, 0, iw);
	/*Create a PixelGrabber object to grab the (x, y, w, h) rectangular section of pixels from the specified image into the given array.*/
                pg.grabPixels();
            }

            catch(InterruptedException interruptedexception2) { }
        } 
	else
        {
            larger = 1;
            iw = diw;
            ih = dih;
            scaledImage = image.getScaledInstance(iw, ih, 1);
            try
            {
                newImage = new int[iw * ih];
                pg = new PixelGrabber(scaledImage, 0, 0, iw, ih, newImage, 0, iw);
                pg.grabPixels();
            }
            catch(InterruptedException interruptedexception3) { }
        }
        a = new int[iw * ih];//alpha
        r = new int[iw * ih];//red
        g = new int[iw * ih];//green
        b = new int[iw * ih];//blue
        img[0] = image;  /*source image*/
        temp = new int[i + 1][iw * ih];
        for(int j = 0; j < i + 2; j++)
            bimg[j] = new BufferedImage(iw, ih, 1);    

                /*public BufferedImage(int width,int height, int imageType)
Constructs a BufferedImage of one of the predefined image types. The ColorSpace for the image is the default sRGB space.
1 OR TYPE_INT_RGB -- to represent an image with 8-bit RGB color components packed into integer pixels. The image has a DirectColorModel without alpha
*/
        for(int k = 0; k < i + 1; k++)
        {
            for(int i1 = 0; i1 < iw * ih; i1++)
            {
                int j1;
                if(larger == 0)
                    j1 = pixelsS[i1];
                else
                    j1 = newImage[i1];
                int k1 = 0xff & j1 >> 24;
                int l1 = 0xff & j1 >> 16;
                int i2 = 0xff & j1 >> 8;
                int j2 = 0xff & j1;
                if(k == 0)
                    bimg[0].setRGB(i1 % iw, i1 / iw, k1 << 24 | l1 << 16 | i2 << 8 | j2);

/*public void setRGB(int x,int y,int rgb)*/

                if(larger == 1)
                    j1 = pixelsD[i1];
                else
                    j1 = newImage[i1];
                int k2 = 0xff & j1 >> 24;
                int l2 = 0xff & j1 >> 16;
                int i3 = 0xff & j1 >> 8;
                int j3 = 0xff & j1;
                k1 += ((k + 1) * (k2 - k1)) / (i + 1);
                l1 += ((k + 1) * (l2 - l1)) / (i + 1);
                i2 += ((k + 1) * (i3 - i2)) / (i + 1);
                j2 += ((k + 1) * (j3 - j2)) / (i + 1);
                temp[k][i1] = k1 << 24 | l1 << 16 | i2 << 8 | j2;
                bimg[k + 1].setRGB(i1 % iw, i1 / iw, temp[k][i1]);
            }

        }

        for(int l = 0; l < i + 1; l++)
        {
            img[l + 1] = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw, ih, temp[l], 0, iw));
/*Constructs an ImageProducer object which uses an array of integers
 in the default RGB ColorModel to produce data for an Image object.
public MemoryImageSource(int w,int h,int[] pix,int off,int scan)
Parameters:w - the width of the rectangle of pixels
h - the height of the rectangle of pixels
pix - an array of pixels
off - the offset into the array of where to store the 
        first pixelscan - the distance from one row of pixels to the next in
        the array*/
            if(s == null)
                continue;
            try
            {
                ImageIO.write(bimg[l], "JPG", new File(s + l + ".jpg")); // Write generated image to a file
            }
            catch(IOException ioexception1) { }
        }

        if(s != null)
            try
            {
                ImageIO.write(bimg[i + 1], "JPG", new File(s + (i + 1) + ".jpg"));
            }
            catch(IOException ioexception) { }
        return img;
    }
}





/*  Notes*/


/*public void writeInt(int v)
              throws IOException
Writes an int value, which is comprised of four bytes, to the output stream.
 The byte values to be written, in the order shown, are: 


 (byte)(0xff & (v >> 24))
 (byte)(0xff & (v >> 16))
 (byte)(0xff & (v >>    8))
 (byte)(0xff & v)
 
The bytes written by this method may be read by the readInt method of interface
 DataInput , which will then return an int equal to v. 


Parameters:
v - the int value to be written. 
Throws: 
IOException - if an I/O error occurs.
*/

/*// Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
         PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        */