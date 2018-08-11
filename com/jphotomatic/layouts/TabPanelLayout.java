package com.jphotomatic.layouts;

import java.awt.*;

public class TabPanelLayout implements LayoutManager
{

    public TabPanelLayout()
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
        dimension.width = 200;
        dimension.height =700;
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
        component.setBounds(insets.left + 0, insets.top + 50, 200, 340);//TabbedPane
        component = container.getComponent(1);
        if(component.isVisible())
        component.setBounds(insets.left + 0, insets.top + 400, 130, 40);//apply changes JButton
        component = container.getComponent(2);
        if(component.isVisible())
        component.setBounds(insets.left + 0, insets.top + 443, 180, 62);//imgInfo JLabel
        component = container.getComponent(3);
        if(component.isVisible())
        component.setBounds(insets.left + 0, insets.top + 500, 180, 30);//imgIcon JLabel
        component = container.getComponent(4);
        if(component.isVisible())
        component.setBounds(insets.left + 0, insets.top + 507, 160, 40);//imgsize JLabel

       
    }
}