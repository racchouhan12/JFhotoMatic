package com.jphotomatic.shape;

import java.awt.*;
import java.awt.image.*;

public class Scale 
{
	public Scale()
	{}
	public static BufferedImage Resize(BufferedImage img, int newW, int newH) {   
	        int w = img.getWidth();   
	        int h = img.getHeight();   
	        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());   
	        Graphics2D g = dimg.createGraphics();   
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
	        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);   
	        g.dispose();   
	        return dimg;   
	    }  


}