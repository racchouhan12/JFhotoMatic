package com.jphotomatic.model;
import java.util.*;

public class DestinationModel extends Observable
{

    public DestinationModel()
    {
        
        elementList = new LinkedList();
    }

    public boolean remove(Element element)
    {
        boolean flag = elementList.remove(element);
        if(flag)
        {
            setChanged();
            /* Marks this Observable object as having been changed; 
            the hasChanged method will now return true.*/
            notifyObservers(element.getBounds());
            /*If this object has changed, as indicated by the hasChanged method,
              then notify all of its observers and then call the clearChanged method 
              to indicate that this object has no longer changed.*/
        }
        return flag;
    }

    public void add(Element element)
    {
        elementList.add(element);
        setChanged();
        notifyObservers(element.getBounds());
    }

    public Iterator getIterator()
    {
        return elementList.listIterator();
    }
    protected LinkedList elementList;
    
}