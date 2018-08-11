package com.jphotomatic.model;

/*u, v: special parameters that uniquely identify a CustomPoint in a quadrilateral
 *: It performs Bilinear Transformation*/
class UV
{
    double u;
    double v;
    UV()
    {
        u = 0.0D;
        v = 0.0D;
        u = 0.0D;
        v = 0.0D;
    }

    UV(double d, double d1)
    {
        u = 0.0D;
        v = 0.0D;
        u = d;
        v = d1;
    }

    public UV calculate_uv(CustomPoint point1, CustomPoint point2, CustomPoint point3, CustomPoint point4, CustomPoint point5)
    {
        double d = point1.x - point2.x;
        double d1 = -point2.x + point3.x;
        double d2 = -point2.x + point5.x;
        double d3 = ((point2.x - point3.x) + point4.x) - point5.x;
        double d4 = point1.y - point2.y;
        double d5 = -point2.y + point3.y;
        double d6 = -point2.y + point5.y;
        double d7 = ((point2.y - point3.y) + point4.y) - point5.y;
        double d8 = d;
        double d9 = d1;
        double d10 = d2;
        d8 = d - (d3 / d7) * d4;
        d9 = d1 - (d3 / d7) * d5;
        d10 = d2 - (d3 / d7) * d6;
        double d11 = 0.0D;
        double d12 = d5;
        double d13 = -d4;
        if(d10 == 0.0D);
        d11 = (-d9 * d7) / d10;
        d12 = (d5 - (d9 * d6) / d10) + (d8 * d7) / d10;
        d13 = (d8 * d6) / d10 - d4;
        double d14 = 0.0D;
        double d16 = 0.0D;
        double d18 = 0.0D;
        double d19 = 0.0D;
        double d20 = Math.sqrt(d12 * d12 - 4D * d11 * d13);
        if(d11 == 0.0D)
        {
            u = -d13 / d12;
            v = (d8 - u * d9) / d10;
        } else
        {
            double d15 = (-d12 + d20) / (2D * d11);
            double d17 = (-d12 - d20) / (2D * d11);
            if(d10 != 0.0D)
            {
                d18 = (d8 - d15 * d9) / d10;
                d19 = (d8 - d17 * d9) / d10;
            }
            if(d15 >= 0.0D && d17 >= 0.0D)
            {
                if(d18 <= 1.0D)
                {
                    u = d15;
                    v = d18;
                } else
                if(d19 <= 1.0D)
                {
                    u = d17;
                    v = d19;
                }
            } else
            if(d15 >= 0.0D)
            {
                u = d15;
                v = d18;
            } else
            {
                u = d17;
                v = d19;
            }
        }
        UV uv1 = new UV(u, v);
        return uv1;
    }

    public CustomPoint estimate_point(UV uv1, CustomPoint point1, CustomPoint point2, CustomPoint point3, CustomPoint point4)
    {
        u = uv1.u;
        v = uv1.v;
        CustomPoint point5 = new CustomPoint();
        point5.x = (int)((1.0D - u) * (1.0D - v) * (double)point1.x + u * (1.0D - v) * (double)point2.x + u * v * (double)point3.x + (1.0D - u) * v * (double)point4.x);
        point5.y = (int)((1.0D - u) * (1.0D - v) * (double)point1.y + u * (1.0D - v) * (double)point2.y + u * v * (double)point3.y + (1.0D - u) * v * (double)point4.y);
        return point5;
    }
}