package com.jphotomatic.model;

import java.awt.*;
import java.awt.image.*;
public class UndoCollection 
{
BufferedImage[] undoImage=new BufferedImage[3];
	public UndoCollection(){}
	public void setImageInUndoCollection(BufferedImage img)
	{
		if(undoImage[0]==null)
		undoImage[0]=img;
		else if(undoImage[1]==null)
		undoImage[1]=img;
		else if(undoImage[2]==null)
		undoImage[2]=img;

	}
	public void removeImageFromUndoCollection(int ino)
	{
		if(ino==0)
		undoImage[0]=null;
		else if(ino==1)
		undoImage[1]=null;
		else if(ino==2)
		undoImage[2]=null;
		
	}
	public BufferedImage getImageFromUndoCollection(int i)
	{
		if(i==0)
		return undoImage[0];
		else if(i==1)
		return undoImage[1];
		else if(i==2)
		return undoImage[2];
		else
		return null;
	}
}