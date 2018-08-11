package com.jphotomatic.model;import java.awt.*;
import java.awt.image.*;


public class HVFlip
{
	public HVFlip(){}

	public static BufferedImage HorizontalFlip(BufferedImage img) {   
	        int w = img.getWidth();   
	        int h = img.getHeight();   
	        BufferedImage dimg = new BufferedImage(w, h, img.getType());   
	        Graphics2D g = dimg.createGraphics();   
	        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);   
	        g.dispose();   
	        return dimg;   
	    }  

	public static BufferedImage VerticalFlip(BufferedImage img) {   
	        int w = img.getWidth();   
	        int h = img.getHeight();   
	        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());   
	        Graphics2D g = dimg.createGraphics();   
	        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);   
	        g.dispose();   
	        return dimg;   
	    }  

	
}