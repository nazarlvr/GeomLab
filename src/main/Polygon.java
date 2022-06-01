package main;

import java.awt.*;
import java.util.ArrayList;

public class Polygon
{
    public ArrayList<Segment> segments;
    public int v;

    public Polygon(ArrayList<Point> points)
    {
        this.v = 0;

        for (int i = 1; i < points.size(); ++i)
            if (points.get(i).x < points.get(this.v).x || (points.get(i).x == points.get(this.v).x && points.get(i).y < points.get(this.v).y))
                this.v = i;

        int xb = points.get(this.v).x, yb = points.get(this.v).y, xa, ya, xc, yc;

        if (this.v == 0)
        {
            xa = points.get(points.size()-1).x;
            ya = points.get(points.size()-1).y;
        }
        else
        {
            xa = points.get(this.v-1).x;
            ya = points.get(this.v-1).y;
        }

        if (this.v == points.size()-1)
        {
            xc = points.get(0).x;
            yc = points.get(0).y;
        }
        else
        {
            xc = points.get(this.v+1).x;
            yc = points.get(this.v+1).y;
        }

        int d = (xb - xa) * (yc - ya) - (xc - xa) * (yb - ya);

        ArrayList<Point> p = new ArrayList<Point>();

        if (d < 0)
        {
            for (int i = points.size() - 1; i >= 0; --i)
                p.add(points.get(i));
        }
        else
        {
            p = points;
        }

        this.segments = new ArrayList<Segment>();

        for (int i = 1; i < p.size(); ++i)
        {
            this.segments.add(new Segment(p.get(i-1), p.get(i)));
        }

        this.segments.add(new Segment(p.get(p.size()-1), p.get(0)));

        System.out.println(this.v);
    }

    public ArrayList<Point> getPoints(int b, int e)
    {
        ArrayList<Point> p = new ArrayList<Point>();

        if (segments.size() > 0)
        {
            for (int i = b; i != e; i = (i + 1) % segments.size())
            {
                p.add(segments.get(i).p2);
            }
        }


        /*if (b < e)
        {
            for (int i = b; i < e; ++i)
                p.add(segments.get(i).p2);
        }
        else if (e < b)
        {
            for (int i = e; i > b; --i)
                p.add(segments.get(i).p1);
        }*/

        return p;
    }
}
