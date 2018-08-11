package com.jphotomatic.layouts;
import java.awt.*;

public class outputLayout
    implements LayoutManager
{

    public outputLayout()
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
            component.setBounds(insets.left + 300, insets.top + 5, 450, 60);
        component = container.getComponent(1);
        if(component.isVisible())
            component.setBounds(insets.left + 50, insets.top + 90, 150, 40);
        component = container.getComponent(2);
        if(component.isVisible())
            component.setBounds(insets.left + 325, insets.top + 90, 200, 40);
        component = container.getComponent(3);
        if(component.isVisible())
            component.setBounds(insets.left + 595, insets.top + 90, 160, 40);
        component = container.getComponent(4);
        if(component.isVisible())
            component.setBounds(insets.left + 40, insets.top + 420, 150, 50);
        component = container.getComponent(5);
        if(component.isVisible())
            component.setBounds(insets.left + 590, insets.top + 420, 170, 50);
        component = container.getComponent(6);
        if(component.isVisible())
            component.setBounds(insets.left + 350, insets.top + 460, 100, 50);
        component = container.getComponent(7);
        if(component.isVisible())
            component.setBounds(insets.left + 285, insets.top + 160, 230, 230);
    }
}