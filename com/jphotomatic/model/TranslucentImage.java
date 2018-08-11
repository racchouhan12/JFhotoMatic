package com.jphotomatic.model;

import java.awt.image.*;
import java.awt.*;


public class TranslucentImage 
{
	public TranslucentImage()
	{
	}

	public static BufferedImage createTranslucentImage(BufferedImage loaded, float transperancy) 
	{
	        // Create the image using the    
	        BufferedImage aimg = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TRANSLUCENT);   
	        // Get the images graphics   
	        Graphics2D g = aimg.createGraphics();   
	        // Set the Graphics composite to Alpha   
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC/*OVER*/, transperancy));   
	        // Draw the LOADED img into the prepared reciver image   
	        g.drawImage(loaded, null, 0, 0);   
	        // let go of all system resources in this Graphics   
	        g.dispose();   
	        // Return the image

   
	        return aimg;   
	    }  



}