package com.jphotomatic.shape;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
public class ShearImage
{
	public ShearImage(){}

	public static BufferedImage Shear(BufferedImage img,double sx,double sy) {   
	        int w = img.getWidth();   
	        int h = img.getHeight();   
	        BufferedImage dimg = new BufferedImage(w, h, img.getType());   
	        AffineTransform trans=new AffineTransform();
		trans.setToShear(sx,sy);
    		AffineTransformOp op = new AffineTransformOp(trans, null);
		op.filter(img,dimg);
	        BufferedImage cimg = new BufferedImage(dimg.getWidth(),dimg.getHeight(), dimg.getType());   
	        Graphics2D g = cimg.createGraphics();   

g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
	        g.drawImage(dimg, null, 0,0);
                        g.dispose();
	        return dimg;   
	    }  


}