package com.jphotomatic.model;class Mesh
{
    CustomPoint m1;
    CustomPoint m2;
    CustomPoint m3;
    CustomPoint m4;
    int id;/*Id: A unique identifier*/
    Mesh()
    {
        m1 = new CustomPoint();
        m2 = new CustomPoint();
        m3 = new CustomPoint();
        m4 = new CustomPoint();
    }

    Mesh(CustomPoint point1, CustomPoint point2, CustomPoint point3)
    {
        m1 = point1;
        m2 = point2;
        m3 = point3;
        m4 = new CustomPoint();
    }

    Mesh(CustomPoint point1, CustomPoint point2, CustomPoint point3, int i)
    {
        m1 = point1;
        m2 = point2;
        m3 = point3;
        id = i;
        m4 = new CustomPoint();
    }
}