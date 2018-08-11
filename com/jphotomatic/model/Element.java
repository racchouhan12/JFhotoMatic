package com.jphotomatic.model;import java.awt.*;

public abstract class Element
{
     public static class Line extends Element
    {

        public Shape getShape()
        {
            return line;
        }

        public Rectangle getBounds()
        {
            return line.getBounds();/*the bounds of the area covered by this GraphicsConfiguration.*/
        }

        public void modify(Point point, Point point1)
        {
            line.x2 = point1.x;
            line.y2 = point1.y;
        }

        private java.awt.geom.Line2D.Double line;

        public Line(Point point, Point point1, Color color1)
        {
            super(color1);
            line = new java.awt.geom.Line2D.Double(point, point1);
        }
    }

    public Element(Color color1)
    {
        color = color1;
    }

    public Color getColor()
    {
        return color;
    }

    public abstract Shape getShape();

    public abstract Rectangle getBounds();

    public abstract void modify(Point point, Point point1);

    protected Color color;
}