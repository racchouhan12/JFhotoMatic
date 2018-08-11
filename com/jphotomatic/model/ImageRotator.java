package com.jphotomatic.model;import java.awt.*;
import java.awt.image.*;

public class ImageRotator
{
	public ImageRotator(){}

	public static BufferedImage Rotate(BufferedImage img, int angle) {   
	        int w = img.getWidth();   
	        int h = img.getHeight();   
	        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getType());   
	        Graphics2D g = dimg.createGraphics();   
	        g.setColor(new Color(0.0f,0.0f,0.0f,0.0f));  
	        g.rotate(Math.toRadians(angle), w/2, h/2);
	        g.drawImage(img, null, 0, 0);
   	        g.dispose();
	        return dimg;   
	    }  


}