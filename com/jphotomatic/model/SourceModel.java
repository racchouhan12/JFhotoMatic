package com.jphotomatic.model;/*It extends class Observable that enables to capture event when observed element changes.*/

import java.util.*;

public class SourceModel extends Observable
{

    public SourceModel()
    {
        elementList = new LinkedList();
    }

    public boolean remove(Element element)
    {
        boolean flag = elementList.remove(element);
        if(flag)
        {
            setChanged();
            notifyObservers(element.getBounds());
        }
        return flag;
    }

    public void add(Element element)
    {
        elementList.add(element);
        setChanged();
        notifyObservers(element.getBounds()); /*Returns a bounding Rectangle that completely encloses this Area.*/
        /* If this object has changed, as indicated by the hasChanged method, then notify all of its observers*/
    }

    public Iterator getIterator()
    {
        return elementList.listIterator();
    }

    protected LinkedList elementList;
}