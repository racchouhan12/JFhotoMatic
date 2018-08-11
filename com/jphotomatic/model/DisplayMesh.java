package com.jphotomatic.model;import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class DisplayMesh extends JInternalFrame
{

    public DisplayMesh()
    {
        flag = 0;
    }

    public DisplayMesh(Image image)
    {
        super("Source Image", true, true, true, true);
        flag = 0;
        source = new ImageIcon(image);
        slabel = new JLabel();
        getContentPane().add(new JScrollPane(slabel));
    }

    public DisplayMesh(Image image, int i)
    {
        super("Destination Image", true, true, true, true);
        flag = 0;
        dest = new ImageIcon(image);
        dlabel = new JLabel();
        getContentPane().add(new JScrollPane(dlabel));
    }

    public void displayS()
    {
        slabel.setIcon(source);
        flag = 0;
        repaint(15, 160, 230, 230);
    }

    public void displayD()
    {
        dlabel.setIcon(dest);
        flag = 1;
        repaint(555, 160, 230, 230);
    }

    public void setS(Vector vector)
    {
        mshS = vector;
    }

    public void setD(Vector vector)
    {
        mshD = vector;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.red);
        if(flag == 0)
        {
            for(int i = 0; i < mshS.size(); i++)
            {
                Mesh mesh1 = (Mesh)mshS.elementAt(i);
                g.drawLine(mesh1.m1.x + 5, mesh1.m1.y + 42, mesh1.m2.x + 5, mesh1.m2.y + 42);
                g.drawLine(mesh1.m1.x + 5, mesh1.m1.y + 42, mesh1.m3.x + 5, mesh1.m3.y + 42);
                g.drawLine(mesh1.m3.x + 5, mesh1.m3.y + 42, mesh1.m2.x + 5, mesh1.m2.y + 42);
            }

        } else
        {
            for(int j = 0; j < mshD.size(); j++)
            {
                Mesh mesh2 = (Mesh)mshD.elementAt(j);
                g.drawLine(mesh2.m1.x + 5, mesh2.m1.y + 42, mesh2.m2.x + 5, mesh2.m2.y + 42);
                g.drawLine(mesh2.m1.x + 5, mesh2.m1.y + 42, mesh2.m3.x, mesh2.m3.y + 42);
                g.drawLine(mesh2.m3.x + 5, mesh2.m3.y + 42, mesh2.m2.x + 5, mesh2.m2.y + 42);
            }

        }
    }

    ImageIcon source;
    ImageIcon dest;
    JLabel slabel;
    JLabel dlabel;
    static Vector mshS;
    static Vector mshD;
    int flag;
}