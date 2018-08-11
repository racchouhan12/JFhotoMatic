package com.jphotomatic.layouts;
import java.awt.*;

public class detailsLayout
    implements LayoutManager
{

    public detailsLayout()
    {
    }

    public void addLayoutComponent(String s, Component component)
    {
    }

    public void removeLayoutComponent(Component component)
    {
    }

    public Dimension preferredLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        Insets insets = container.getInsets();
        /*Determines the insets of this container, which indicate the size of the container's border.*/
        dimension.width = 799 + insets.left + insets.right;
        dimension.height = 549 + insets.top + insets.bottom;
        
        return dimension;
    }

    public Dimension minimumLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        return dimension;
    }

    public void layoutContainer(Container container)
    {
        Insets insets = container.getInsets();
        Component component = container.getComponent(0);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 25, 300, 40);
        component = container.getComponent(1);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 90, 500, 25);
        component = container.getComponent(2);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 130, 150, 25);
        component = container.getComponent(3);
        if(component.isVisible())
            component.setBounds(insets.left + 170, insets.top + 130, 150, 25);
        component = container.getComponent(4);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 170, 450, 25);
        component = container.getComponent(5);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 210, 150, 25);
        component = container.getComponent(6);
        if(component.isVisible())
            component.setBounds(insets.left + 170, insets.top + 210, 150, 25);
        component = container.getComponent(7);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 250, 598, 25);
        component = container.getComponent(8);
        if(component.isVisible())
            component.setBounds(insets.left + 600, insets.top + 250, 30, 25);
        component = container.getComponent(9);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 300, 500, 25);
        component = container.getComponent(10);
        if(component.isVisible())
            component.setBounds(insets.left + 510, insets.top + 300, 30, 25);
        component = container.getComponent(11);
        if(component.isVisible())
            component.setBounds(insets.left + 20, insets.top + 350, 450, 25);
        component = container.getComponent(12);
        if(component.isVisible())
            component.setBounds(insets.left + 220, insets.top + 400, 500, 25);
        component = container.getComponent(13);
        if(component.isVisible())
            component.setBounds(insets.left + 220, insets.top + 425, 120, 25);
        component = container.getComponent(14);
        if(component.isVisible())
            component.setBounds(insets.left + 220, insets.top + 450, 120, 25);
      
    }
}