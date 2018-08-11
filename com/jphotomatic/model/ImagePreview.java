package com.jphotomatic.model;//Accessory--image preview in filechoosers
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.*;

public class ImagePreview extends JComponent
    implements PropertyChangeListener
{

    ImageIcon thumbnail;
    File file;

    public ImagePreview(JFileChooser jfilechooser)
    {
        thumbnail = null;
        file = null;
        setPreferredSize(new Dimension(100, 50));
        jfilechooser.addPropertyChangeListener(this);
    }

    public void loadImage()
    {
        if(file == null)
        {
            thumbnail = null;
            return;
        }
        ImageIcon imageicon = new ImageIcon(file.getPath());/*Creates an ImageIcon from the specified file.*/
        if(imageicon != null)
            if(imageicon.getIconWidth() > 90)
                thumbnail = new ImageIcon(imageicon.getImage().getScaledInstance(90, -1, 1));
            else
                thumbnail = imageicon;
    }

    public void propertyChange(PropertyChangeEvent propertychangeevent)
    {
        boolean flag = false;
        String s = propertychangeevent.getPropertyName();
        if("directoryChanged".equals(s))
        {
            file = null;
            flag = true;
        } else
        if("SelectedFileChangedProperty".equals(s))
        {
            file = (File)propertychangeevent.getNewValue();
            flag = true;
        }
        if(flag)
        {
            thumbnail = null;
            if(isShowing())
            {
                loadImage();
                repaint();
            }
        }
    }

    protected void paintComponent(Graphics g)
    {
        if(thumbnail == null)
            loadImage();
        if(thumbnail != null)
        {
            int i = getWidth() / 2 - thumbnail.getIconWidth() / 2;
            int j = getHeight() / 2 - thumbnail.getIconHeight() / 2;
            if(j < 0)
                j = 5;
            if(i < 5)
                i = 5;
            thumbnail.paintIcon(this, g, i, j);
        }
    }
}