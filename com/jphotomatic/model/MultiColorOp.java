package com.jphotomatic.model;import java.awt.*;
import java.awt.color.*;
import java.awt.image.*;

public class MultiColorOp
{
	public MultiColorOp()
	{}
	public static BufferedImage GrayColorEff(BufferedImage src)
	{
		int w=src.getWidth();
		int h=src.getHeight();
		BufferedImage bi=new BufferedImage(w,h,src.getType());
		Graphics2D g2=bi.createGraphics();
		g2.drawImage(src,0,0,null);
	
		ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace
        .getInstance(ColorSpace.CS_GRAY), null);
    		bi=colorConvert.filter(src, bi);
		return bi;
		
	}
	public static BufferedImage PYCCEff(BufferedImage src)
	{
		int w=src.getWidth();
		int h=src.getHeight();
		BufferedImage bi=new BufferedImage(w,h,src.getType());
		Graphics2D g2=bi.createGraphics();
		g2.drawImage(src,0,0,null);
	
		ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace
        .getInstance(ColorSpace.CS_PYCC), null);
    		bi=colorConvert.filter(src, bi);
		return bi;
		
	}


}