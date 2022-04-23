package main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Segment
{
    Point p1, p2;

    public Segment(Point a, Point b)
    {
        p1 = a;
        p2 = b;
    }

    public Point Intersection(Segment s)
    {
        int t = (p1.x - s.p1.x)*(s.p1.y - s.p2.y) - (p1.y - s.p1.y)*(s.p1.x - s.p2.x);
        int d = (p1.x - p2.x)*(s.p1.y - s.p2.y) - (p1.y - p2.y)*(s.p1.x - s.p2.x);

        if (d == 0 || (d < 0 && t < d) || (d > 0 && t > d))
            return null;

        Point p = new Point(p1.x + t * (p2.x - p1.x) / d, p1.y + t * (p2.y - p1.y) / d);
        return p;
    }
}
