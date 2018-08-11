package com.jphotomatic.layouts;
import java.awt.*;

public class singleLayout
    implements LayoutManager
{

    public singleLayout()
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
            component.setBounds(insets.left + 120, insets.top + 30, 150, 40);
        component = container.getComponent(1);
        if(component.isVisible())
            component.setBounds(insets.left + 505, insets.top + 30, 200, 40);
        component = container.getComponent(2);
        if(component.isVisible())
            component.setBounds(insets.left + 90, insets.top + 440, 150, 50);
        component = container.getComponent(3);
        if(component.isVisible())
            component.setBounds(insets.left + 515, insets.top + 440, 100, 50);
    }
}