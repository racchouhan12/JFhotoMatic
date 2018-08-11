package com.jphotomatic.model;

import java.io.File;
import java.io.PrintStream;
import javax.swing.ImageIcon;

public class Utils
{
 	public static final String jpeg = "jpeg";
    public static final String jpg = "jpg";
    public Utils()
    {
    }
    
    /*      Get the extension of a file.     */

    public static String getExtension(File file)
    {
        String s = null;
        String s1 = file.getName();
        int i = s1.lastIndexOf('.');
        if(i > 0 && i < s1.length() - 1)
            s = s1.substring(i + 1).toLowerCase();
        return s;
    }

 /* Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String s)
    {
        java.net.URL url = (Utils.class).getResource(s);
        if(url != null)
        {
            return new ImageIcon(url);
        } else
        {
            System.err.println("Couldn't find file: " + s);
            return null;
        }
    }
}