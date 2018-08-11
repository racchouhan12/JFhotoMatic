package com.jphotomatic.model;
/*It enables user to draw points or lines on source image.*/

import com.jphotomatic.constant.Constants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class SourceView extends JComponent
    implements Observer, Constants
{
    class MouseHandler extends MouseInputAdapter
    {

        public void mousePressed(MouseEvent mouseevent)
        {
            start = mouseevent.getPoint();
            int i = mouseevent.getModifiers();
            MouseEvent _tmp = mouseevent;
            if((i & 0x10) != 0)
            {
                g2D = (Graphics2D)getGraphics(); // Get graphics context
                g2D.setXORMode(getBackground());// Set XOR mode
                if(state == 0)
                    g2D.setPaint(theApp.getSourceWindow().getElementColor());// Set color
                else
                if(state == 1)
                    g2D.setPaint(theApp.getSourceWindow2().getElementColor());
                if(algo == 2)
                {
                    prev1 = new Point();
                    next1 = new Point();
                    prev2 = new Point();
                    next2 = new Point();
                    prev3 = new Point();
                    next3 = new Point();
                    prev4 = new Point();
                    next4 = new Point();
                    prev1.setLocation(start.x - 2, start.y - 2);
                    next1.setLocation(start.x + 2, start.y + 2);
                    prev2.setLocation(start.x - 2, start.y + 2);
                    next2.setLocation(start.x + 2, start.y - 2);
                    prev3.setLocation(start.x - 3, start.y - 2);
                    next3.setLocation(start.x + 1, start.y + 2);
                    prev4.setLocation(start.x - 3, start.y + 2);
                    next4.setLocation(start.x + 1, start.y - 2);
                    if(tempElement1 == null || tempElement2 == null)
                    {
                        tempElement1 = createElement(prev1, next1);
                        tempElement2 = createElement(prev2, next2);
                        tempElement3 = createElement(prev3, next3);
                        tempElement4 = createElement(prev4, next4);
                    } else
                    {
                        g2D.draw(tempElement1.getShape());
                        tempElement1.modify(prev1, next1);
                        g2D.draw(tempElement2.getShape());
                        tempElement2.modify(prev2, next2);
                        g2D.draw(tempElement3.getShape());
                        tempElement3.modify(prev3, next3);
                        g2D.draw(tempElement4.getShape());
                        tempElement4.modify(prev4, next4);
                    }
                    g2D.draw(tempElement1.getShape());
                    g2D.draw(tempElement2.getShape());
                    g2D.draw(tempElement3.getShape());
                    g2D.draw(tempElement4.getShape());
                    if(flag == 0)
                    {
                        flag = 1;
                        sCounter1++;
                    } else
                    {
                        flag = 0;
                        sCounter2++;
                    }
                    sCounter++;
                }
            }
        }

       

        public void mouseReleased(MouseEvent mouseevent)
        {
            int i = mouseevent.getModifiers();
            MouseEvent _tmp = mouseevent;
            if((i & 0x10) != 0)
            {
               
                if((tempElement1 != null || tempElement2 != null) && algo == 2)
                {
                    theApp.getSourceModel().add(tempElement1);
                    tempElement1 = null;
                    theApp.getSourceModel().add(tempElement2);
                    tempElement2 = null;
                    theApp.getSourceModel().add(tempElement3);
                    tempElement3 = null;
                    theApp.getSourceModel().add(tempElement4);
                    tempElement4 = null;
                }
                if(g2D != null)
                {
                    g2D.dispose();
                    g2D = null;
                }
                if(algo == 2 && state == 0)
                    objS.addSourcePoint(start.x, start.y);
               
               
                if(algo == 2 && state == 1)
                {
                    if(flag == 1)
                        objS.addSourcePoint(start.x, start.y);
                    else
                        objS.addDestinationPoint(start.x, start.y);
                } 
                start = last = null;
            }
        }

        private Element createElement(Point point, Point point1)
        {
            if(state == 0)
                return new Element.Line(point, point1, colors[sCounter]);
            if(state == 1 && algo == 2)
                if(flag == 0)
                    return new Element.Line(point, point1, colors[sCounter1]);
                else
                    return new Element.Line(point, point1, colors[sCounter2]);
            if(flag == 0)
                return new Element.Line(point, point1, colors[sCounter1]);
            else
                return new Element.Line(point, point1, colors[sCounter2]);
                
        }

        private Graphics2D g2D;
        private Point start;
        private Point prev1;
        private Point prev2;
        private Point prev3;
        private Point prev4;
        private Point last;
        private Point next1;
        private Point next2;
        private Point next3;
        private Point next4;
        private Element tempElement;
        private Element tempElement1;
        private Element tempElement2;
        private Element tempElement3;
        private Element tempElement4;
        private Element destElement;
        private Element destElement1;
        private Element destElement2;

        MouseHandler()
        {
        }
    }


    public SourceView(UserInterface gui_input1, Image image, int i, int j)/*this, imgS, algorithm, 0 in case of different source and dest
    (this, singleImg, algorithm, 1);in cae of single image*/
    {
        flag = 0;
        fcross = 0;
        colors = new Color[40];
        sCounter = 0;
        sCounter1 = 0;
        sCounter2 = 0;
        algo = i;
        imgS = image;
        state = j;
        theApp = gui_input1;
        colors[0] = new Color(0, 0, 255);
        colors[1] = new Color(0, 255, 0);
        colors[2] = new Color(255, 0, 0);
        colors[3] = new Color(128, 81, 108);
        colors[4] = new Color(24, 96, 98);
        colors[5] = new Color(255, 0, 255);
        colors[6] = new Color(0, 255, 255);
        colors[7] = new Color(126, 0, 0);
        colors[8] = new Color(38, 64, 62);
        colors[9] = new Color(208, 26, 255);
        colors[10] = new Color(100, 100, 255);
        colors[11] = new Color(99, 78, 82);
        colors[12] = new Color(200, 0, 130);
        colors[13] = new Color(50, 255, 255);
        colors[14] = new Color(234, 125, 164);
        colors[15] = new Color(0, 78, 130);
        colors[16] = new Color(0, 208, 130);
        colors[17] = new Color(200, 194, 70);
        colors[18] = new Color(79, 0, 130);
        colors[19] = new Color(50, 255, 255);
        colors[20] = new Color(0, 0, 126);
        colors[21] = new Color(0, 130, 130);
        colors[22] = new Color(130, 0, 130);
        colors[23] = new Color(249, 81, 108);
        colors[24] = new Color(0, 208, 130);
        colors[25] = new Color(79, 183, 232);
        colors[26] = new Color(255, 108, 255);
        colors[27] = new Color(128, 81, 108);
        colors[28] = new Color(249, 81, 108);
        colors[29] = new Color(169, 147, 99);
        colors[30] = new Color(130, 130, 0);
        colors[31] = new Color(200, 0, 130);
        colors[32] = new Color(0, 78, 130);
        colors[33] = new Color(234, 196, 82);
        colors[34] = new Color(105, 166, 164);
        colors[35] = new Color(98, 255, 197);
        colors[36] = new Color(105, 166, 55);
        colors[37] = new Color(208, 150, 255);
        colors[38] = new Color(255, 255, 0);
        colors[39] = new Color(255, 140, 194);
        MouseHandler mousehandler = new MouseHandler();
        addMouseListener(mousehandler);
        addMouseMotionListener(mousehandler);
        objS = new Display();
    }

    public void update(Observable observable, Object obj)
    {
        if(obj == null)
            repaint();
        else
            repaint((Rectangle)obj);
    }

    public void paint(Graphics g)
    {
        g.drawImage(imgS, 1, 1, this);
        Graphics2D graphics2d = (Graphics2D)g;
        Element element;
        for(Iterator iterator = theApp.getSourceModel().getIterator(); iterator.hasNext(); graphics2d.draw(element.getShape()))
        {
            element = (Element)iterator.next();
            graphics2d.setPaint(element.getColor());
        }

    }

    private UserInterface theApp;
    Image imgS;
    Image mark;
    int algo;
    int flag;
    int state;
    int fcross;
    Display objS;
    Color colors[];
    int p;
    int q;
    int sCounter;
    int sCounter1;
    int sCounter2;

}