package com.jphotomatic.model;import java.util.Vector;

class Point_warper
{

    Point_warper()
    {
    }
    

    public Vector mesh_formation(CustomPoint apoint[], int i, int j, int k)/*4 corner points  identifier siw sih*/
    /*mshS = point_warper1.mesh_formation(apoint, i - 4, siw, sih);
        mshS = calc_m4(mshS);creates a Mesh when provided with the markers*/
    {
        CustomPoint apoint1[] = new CustomPoint[i];
        CustomPoint apoint2[] = new CustomPoint[i];
        for(int l = 0; l < i; l++)
        {
            apoint1[l] = apoint[l];/* points which have been marked*/
            apoint2[l] = apoint[l];
        }

        for(int i1 = 0; i1 < i; i1++)
        {
            for(int j1 = i1 + 1; j1 < i; j1++)
            {
                if(apoint1[i1].y > apoint1[j1].y)
                {
                    CustomPoint point1 = new CustomPoint();
                    point1 = apoint1[i1];/*Swap*/
                    apoint1[i1] = apoint1[j1];
                    apoint1[j1] = point1;
                }
                if(apoint2[i1].x > apoint2[j1].x)
                {
                    CustomPoint point2 = new CustomPoint();
                    point2 = apoint2[i1];
                    apoint2[i1] = apoint2[j1];
                    apoint2[j1] = point2;
                }
            }

        }

        Vector vector = new Vector(10, 2);
        int k1 = 0;
        int l1 = 0;
        int ai[] = new int[i];
        for(int i2 = 0; i2 < i; i2++)
            ai[i2] = 0;

        boolean flag = false;
        CustomPoint point3 = new CustomPoint();
        boolean flag1 = false;
        int j3 = 0;
        int k3 = 0;
        int l3 = i - 1;
        int i4 = i - 1;
        pt = new CustomPoint[3];
        int j4 = 0;
        do
        {
            if(k1 >= i)
                break;
            if(!flag)
            {
                switch(l1)
                {
                default:
                    break;

                case 0: // '\0'
                    if(j3 < i)
                    {
                        point3 = apoint1[j3++];
                        int j2 = point3.id;
                        if(point3.y < j / 2 && ai[j2] == 0)
                        {
                            flag = true;
                            ai[j2] = 1;
                            k1++;
                        }
                    }
                    break;

                case 1: // '\001'
                    if(i4 < 0)
                        break;
                    point3 = apoint2[i4--];
                    int k2 = point3.id;
                    if(point3.x > k / 2 && ai[k2] == 0)
                    {
                        flag = true;
                        ai[k2] = 1;
                        k1++;
                    }
                    break;

                case 2: // '\002'
                    if(l3 < 0)
                        break;
                    point3 = apoint1[l3--];
                    int l2 = point3.id;
                    if(point3.y > j / 2 && ai[l2] == 0)
                    {
                        flag = true;
                        ai[l2] = 1;
                        k1++;
                    }
                    break;

                case 3: // '\003'
                    if(k3 >= i)
                        break;
                    point3 = apoint2[k3++];
                    int i3 = point3.id;
                    if(point3.x < k / 2 && ai[i3] == 0)
                    {
                        flag = true;
                        ai[i3] = 1;
                        k1++;
                    }
                    break;
                }
                l1 = (l1 + 1) % 4;
            }
            if(flag)
            {
                if(j4 < 3)
                {
                    pt[j4] = point3;
                    j4++;
                    if(j4 == 3)
                        vector.addElement(new Mesh(pt[0], pt[1], pt[2], vector.size()));
                } else
                if(is_outside_to_all_mesh(point3, vector, vector.size()))
                {
                    vector.addElement(new Mesh(point3, pt[0], pt[1], vector.size()));
                } else
                {
                    Mesh mesh1 = (Mesh)vector.elementAt(mesh_index);
                    CustomPoint point4 = mesh1.m1;
                    CustomPoint point5 = mesh1.m2;
                    CustomPoint point11 = mesh1.m3;
                    vector.setElementAt(new Mesh(point4, point5, point3, mesh_index), mesh_index);
                    vector.addElement(new Mesh(point5, point11, point3, vector.size()));
                    vector.addElement(new Mesh(point11, point4, point3, vector.size()));
                }
                flag = false;
            }
        } while(true);
        int k4 = vector.size();
        for(int l4 = 0; l4 < k4; l4++)
        {
            CustomPoint point6 = new CustomPoint();
            CustomPoint point12 = new CustomPoint();
            CustomPoint point17 = new CustomPoint();
            Mesh mesh2 = (Mesh)vector.elementAt(l4);
            if(count_meshes_with_link(mesh2.m1, mesh2.m2, vector, k4) == 1)
            {
                CustomPoint point7 = mesh2.m1;
                CustomPoint point13 = mesh2.m2;
                CustomPoint point18 = mesh2.m3;
                CustomPoint point22 = nearest_point(point7, point13, point18, apoint, i);
                vector.addElement(new Mesh(point7, point13, point22, 1000));
            }
            if(count_meshes_with_link(mesh2.m2, mesh2.m3, vector, k4) == 1)
            {
                CustomPoint point8 = mesh2.m2;
                CustomPoint point14 = mesh2.m3;
                CustomPoint point19 = mesh2.m1;
                CustomPoint point23 = nearest_point(point8, point14, point19, apoint, i);
                vector.addElement(new Mesh(point8, point14, point23, 1000));
            }
            if(count_meshes_with_link(mesh2.m3, mesh2.m1, vector, k4) == 1)
            {
                CustomPoint point9 = mesh2.m3;
                CustomPoint point15 = mesh2.m1;
                CustomPoint point20 = mesh2.m2;
                CustomPoint point24 = nearest_point(point9, point15, point20, apoint, i);
                vector.addElement(new Mesh(point9, point15, point24, 1000));
            }
        }

label0:
        for(int i5 = 0; i5 < 4; i5++)
        {
            CustomPoint point10 = apoint[i + i5];
            CustomPoint point16 = apoint[i + (i5 + 1) % 4];
            int j5 = 0;
            do
            {
                if(j5 >= i)
                    continue label0;
                CustomPoint point21 = apoint[j5];
                if(count_meshes_with_link(point21, point10, vector, vector.size()) == 1 && count_meshes_with_link(point21, point16, vector, vector.size()) == 1)
                {
                    vector.addElement(new Mesh(point10, point16, point21, 1000));
                    continue label0;
                }
                j5++;
            } while(true);
        }

        return vector;
    }

    public boolean is_outside_to_all_mesh(CustomPoint point1, Vector vector, int i)
    /*true if CustomPoint p1 is outside else returns false.
Description: detects whether CustomPoint 'p1' is outside all meshes
*/
    {
        boolean flag = true;
        int j = point1.x;
        int k = point1.y;
        int l = 0;
        byte byte0 = 0;
        double d = 20000D;
        for(int i1 = 0; i1 < i; i1++)
        {
            Mesh mesh2 = (Mesh)vector.elementAt(i1);
            if(isInside3(mesh2.m1, mesh2.m2, mesh2.m3, point1))
            {
                flag = false;
                mesh_index = i1;
                break;
            }
            if(!isJustifies(mesh2.m1, mesh2.m2, mesh2.m3, point1))
            {
                int j1 = (mesh2.m1.x + mesh2.m2.x) / 2;
                int i2 = (mesh2.m1.y + mesh2.m2.y) / 2;
                double d1 = Math.sqrt((j1 - j) * (j1 - j) + (i2 - k) * (i2 - k));
                if(d1 < d && count_meshes_with_link(mesh2.m1, mesh2.m2, vector, vector.size()) == 1)
                {
                    d = d1;
                    l = i1;
                    byte0 = 12;
                }
            }
            if(!isJustifies(mesh2.m3, mesh2.m1, mesh2.m2, point1))
            {
                int k1 = (mesh2.m1.x + mesh2.m3.x) / 2;
                int j2 = (mesh2.m1.y + mesh2.m3.y) / 2;
                double d2 = Math.sqrt((k1 - j) * (k1 - j) + (j2 - k) * (j2 - k));
                if(d2 < d && count_meshes_with_link(mesh2.m3, mesh2.m1, vector, vector.size()) == 1)
                {
                    d = d2;
                    l = i1;
                    byte0 = 13;
                }
            }
            if(isJustifies(mesh2.m2, mesh2.m3, mesh2.m1, point1))
                continue;
            int l1 = (mesh2.m2.x + mesh2.m3.x) / 2;
            int k2 = (mesh2.m2.y + mesh2.m3.y) / 2;
            double d3 = Math.sqrt((l1 - j) * (l1 - j) + (k2 - k) * (k2 - k));
            if(d3 < d && count_meshes_with_link(mesh2.m2, mesh2.m3, vector, vector.size()) == 1)
            {
                d = d3;
                l = i1;
                byte0 = 23;
            }
        }

        if(flag)
        {
            Mesh mesh1 = (Mesh)vector.elementAt(l);
            if(byte0 == 12)
            {
                pt[0] = mesh1.m1;
                pt[1] = mesh1.m2;
            } else
            if(byte0 == 13)
            {
                pt[0] = mesh1.m1;
                pt[1] = mesh1.m3;
            } else
            {
                pt[0] = mesh1.m2;
                pt[1] = mesh1.m3;
            }
        }
        return flag;
    }

    public boolean isInside3(CustomPoint point1, CustomPoint point2, CustomPoint point3, CustomPoint point4)
    /*true if pt is outside region pqr else returns false.
Description: detects whether CustomPoint 'pt' is inside the region pqr.
*/
    {
        boolean flag = true;
        if(!isJustifies(point1, point2, point3, point4))
            flag = false;
        else
        if(!isJustifies(point2, point3, point1, point4))
            flag = false;
        else
        if(!isJustifies(point3, point1, point2, point4))
            flag = false;
        return flag;
    }

    public boolean isJustifies(CustomPoint point1, CustomPoint point2, CustomPoint point3, CustomPoint point4)
    /*true if r and pt are to the same side of line 'pq' else returns false.
Description: detects whether points 'r' and 'pt' are to the same side of line 'pq'.
*/
    {
        boolean flag = true;
        int i = point1.x;
        int j = point1.y;
        int k = point2.x;
        int l = point2.y;
        double d;
        double d1;
        if(j - l == 0)
        {
            int k1 = point3.y;
            d = k1 - j;
            k1 = point4.y;
            d1 = k1 - j;
        } else
        if(i - k == 0)
        {
            int i1 = point3.x;
            d = i1 - i;
            i1 = point4.x;
            d1 = i1 - i;
        } else
        {
            double d2 = (double)(j - l) / (double)(i - k);
            int j1 = point3.x;
            int l1 = point3.y;
            d = (double)(l1 - j) - d2 * (double)(j1 - i);
            j1 = point4.x;
            l1 = point4.y;
            d1 = (double)(l1 - j) - d2 * (double)(j1 - i);
        }
        if(d1 * d < 0.0D)
            flag = false;
        return flag;
    }

    public int count_meshes_with_link(CustomPoint point1, CustomPoint point2, Vector vector, int i)
    /*counts meshes containing link 'p1p2' . It is used to detect boundry links of meshes (if count is 1 it is boundry link)*/
    {
        int j = 0;
        for(int k = 0; k < i && j < 2; k++)
        {
            Mesh mesh1 = (Mesh)vector.elementAt(k);
            if(mesh1.m1.x == point1.x && mesh1.m1.y == point1.y && mesh1.m2.x == point2.x && mesh1.m2.y == point2.y || mesh1.m2.x == point1.x && mesh1.m2.y == point1.y && mesh1.m1.x == point2.x && mesh1.m1.y == point2.y)
            {
                j++;
                continue;
            }
            if(mesh1.m2.x == point1.x && mesh1.m2.y == point1.y && mesh1.m3.x == point2.x && mesh1.m3.y == point2.y || mesh1.m3.x == point1.x && mesh1.m3.y == point1.y && mesh1.m2.x == point2.x && mesh1.m2.y == point2.y)
            {
                j++;
                continue;
            }
            if(mesh1.m1.x == point1.x && mesh1.m1.y == point1.y && mesh1.m3.x == point2.x && mesh1.m3.y == point2.y || mesh1.m3.x == point1.x && mesh1.m3.y == point1.y && mesh1.m1.x == point2.x && mesh1.m1.y == point2.y)
                j++;
        }

        return j;
    }

    public CustomPoint nearest_point(CustomPoint point1, CustomPoint point2, CustomPoint point3, CustomPoint apoint[], int i)
/*nearest corner CustomPoint
Description: identifies corner CustomPoint of Image that is nearest to link 'p1p2'
*/  {
        int j = (point1.x + point2.x) / 2;
        int k = (point1.y + point2.y) / 2;
        double d = 20000D;
        CustomPoint point4 = new CustomPoint();
        if(!isJustifies(point1, point2, point3, apoint[i]))
        {
            int l = apoint[i].x;
            int l1 = apoint[i].y;
            double d2 = Math.sqrt((l - j) * (l - j) + (l1 - k) * (l1 - k));
            if(d2 < d)
            {
                point4 = apoint[i];
                d = d2;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 1]))
        {
            int i1 = apoint[i + 1].x;
            int i2 = apoint[i + 1].y;
            double d3 = Math.sqrt((i1 - j) * (i1 - j) + (i2 - k) * (i2 - k));
            if(d3 < d)
            {
                point4 = apoint[i + 1];
                d = d3;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 2]))
        {
            int j1 = apoint[i + 2].x;
            int j2 = apoint[i + 2].y;
            double d4 = Math.sqrt((j1 - j) * (j1 - j) + (j2 - k) * (j2 - k));
            if(d4 < d)
            {
                point4 = apoint[i + 2];
                d = d4;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 3]))
        {
            int k1 = apoint[i + 3].x;
            int k2 = apoint[i + 3].y;
            double d5 = Math.sqrt((k1 - j) * (k1 - j) + (k2 - k) * (k2 - k));
            if(d5 < d)
            {
                point4 = apoint[i + 3];
                double d1 = d5;
            }
        }
        return point4;
    }

    CustomPoint pt[];
    int mesh_index;
}