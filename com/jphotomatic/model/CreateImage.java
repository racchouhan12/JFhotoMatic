package com.jphotomatic.model;//Java Image Loader Class.Makes a BufferedImage from the JFileChooser's selected Image File.
import java.io.*;
import java.awt.image.*;
import java.awt.Image;
import javax.imageio.ImageIO;
public class CreateImage
{
	public CreateImage()
	{
	}

	public static BufferedImage CreateBImage(File ref) 
	{   
		BufferedImage bimg = null;   
        		try {     
	            		bimg = ImageIO.read(ref);   
	       		} catch (Exception e) {   
	            		e.printStackTrace();   
	        		}   
	        	return bimg;   
	}  
	public static BufferedImage CreateCanvasBImage(Image img)
	{
		BufferedImage cimg=null;
		try{
			cimg = new BufferedImage(img.getWidth(null),img.getHeight(null),1);
			}catch(Exception e){
			e.printStackTrace();
			}
		return cimg;
	}


} 