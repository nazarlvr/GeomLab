package main;

import java.awt.*;
import java.util.ArrayList;

public class Variant27
{
    public ArrayList<Segment> segments;

    public Variant27(Polygon p1, Polygon p2)
    {
        this.segments = Variant27.Intersection(p1, p2);
    }

    public static ArrayList<Segment> Intersection(Polygon p1, Polygon p2)
    {
        if (p1.segments.get(p1.v).p1.x > p2.segments.get(p2.v).p1.x ||
                (p1.segments.get(p1.v).p1.x == p2.segments.get(p2.v).p1.x && p1.segments.get(p1.v).p1.y > p2.segments.get(p2.v).p1.y))
            return Variant27.Intersection(p2, p1);

        ArrayList<Segment> segments = new ArrayList<Segment>();

        boolean in = false;
        int v = p1.v;
        int v1 = -1;
        int v2 = -1;

        do
        {
            Segment segment = p1.segments.get(v);
            ArrayList<Point> points = new ArrayList<Point>();
            //Point fintersection = null;
            //boolean fint = v2 == -1;

            for (int i = 0; i < p2.segments.size(); ++i)
            {
                Point intersection = segment.Intersection(p2.segments.get(i));

                if (intersection != null)
                {
                    points.add(intersection);

                    if (v2 == -1)
                    {
                        v1 = v;
                        v2 = i;
                    }

                    /*if (fint)
                    {
                        if (fintersection == null || intersection.distanceSq(segment.p1) < fintersection.distanceSq(segment.p1))
                        {
                            fintersection = intersection;
                            v2 = i;
                        }
                    }*/
                }
            }

            points.sort( (Point a, Point b) -> Double.compare(Segment.lenSqr(a, segment.p1), Segment.lenSqr(b, segment.p1)) );

            if (in)
            {
                if (points.isEmpty())
                {
                    segments.add(segment);
                }
                else
                {
                    segments.add(new Segment(segment.p1, points.get(0)));

                    for (int i = 2; i < points.size(); i += 2)
                        segments.add(new Segment(points.get(i-1), points.get(i)));

                    if (points.size() % 2 == 0) {
                        segments.add(new Segment(points.get(points.size() - 1), segment.p2));
                    }

                    in = points.size() % 2 == 0;
                }
            }
            else
            {
                if (!points.isEmpty())
                {
                    for (int i = 1; i < points.size(); i += 2)
                        segments.add(new Segment(points.get(i-1), points.get(i)));

                    if (points.size() % 2 == 1) {
                        segments.add(new Segment(points.get(points.size() - 1), segment.p2));
                    }

                    in = points.size() % 2 == 1;
                }
            }

            ++v;
            v %= p1.segments.size();
        } while (v != p1.v);

        if (v2 != -1)
        {
            in = false;
            Point intersection = p1.segments.get(v1).Intersection(p2.segments.get(v2));

            for (Segment s : p1.segments)
            {
                Point point = s.Intersection(p2.segments.get(v2));

                if (point != null && Segment.lenSqr(p2.segments.get(v2).p1, point) > Segment.lenSqr(p2.segments.get(v2).p1, intersection))
                    in = !in;
            }

            ++v2;
            v2 %= p2.segments.size();
            v = v2;

            do
            {
                Segment segment = p2.segments.get(v);
                ArrayList<Point> points = new ArrayList<Point>();

                for (int i = 0; i < p1.segments.size(); ++i)
                {
                    intersection = segment.Intersection(p1.segments.get(i));

                    if (intersection != null)
                    {
                        points.add(intersection);
                    }
                }

                points.sort( (Point a, Point b) -> Double.compare(Segment.lenSqr(a, segment.p1), Segment.lenSqr(b, segment.p1)) );

                if (in)
                {
                    if (points.isEmpty())
                    {
                        segments.add(segment);
                    }
                    else
                    {
                        segments.add(new Segment(segment.p1, points.get(0)));

                        for (int i = 2; i < points.size(); i += 2)
                            segments.add(new Segment(points.get(i-1), points.get(i)));

                        if (points.size() % 2 == 0) {
                            segments.add(new Segment(points.get(points.size() - 1), segment.p2));
                        }

                        in = points.size() % 2 == 0;
                    }
                }
                else
                {
                    if (!points.isEmpty())
                    {
                        for (int i = 1; i < points.size(); i += 2)
                            segments.add(new Segment(points.get(i-1), points.get(i)));

                        if (points.size() % 2 == 1) {
                            segments.add(new Segment(points.get(points.size() - 1), segment.p2));
                        }

                        in = points.size() % 2 == 1;
                    }
                }

                ++v;
                v %= p2.segments.size();
            } while (v != v2);
        }



        /*boolean vp;

        int v = p2.v;

        //if (vp)
            //v = p1.v;

        Point st = null;
        int v1=0,v2=0;
        int vs = v;
        boolean vps = vp;
        boolean intersection = false;

        do
        {
            if (vp)
            {

            }
        } while (v != vs || vp != vps);

        if (vp)
        {

            do
            {
                for (int j = 0; j < p2.segments.size(); ++j)
                {
                    Point in = p1.segments.get(v).Intersection(p2.segments.get(j));

                    if (in != null)
                    {

                        if (st == null)
                        {
                            st = in;
                            v1 = v;
                            v2 = j;


                        }
                        else
                        {
                            ArrayList<Point> points = new ArrayList<Point>();
                            points.add(st);

                            ArrayList<Point> points1 = p1.getPoints(v1, v);
                            System.out.println("Psize1 " + points1.size());
                            for (Point pt : points1)
                                System.out.print(pt);
                            System.out.println();
                            points.addAll(points1);

                            points.add(in);

                            ArrayList<Point> points2 = p2.getPoints(j, v2);
                            System.out.println("Psize2 " + points2.size());
                            for (Point pt : points2)
                                System.out.print(pt);
                            System.out.println();
                            points.addAll(points2);

                            Polygon polygon = new Polygon(points);

                            System.out.println("Polygon Segments " + polygon.segments.size());
                            polygons.add(polygon);

                            st = null;
                            intersection = true;
                        }

                        System.out.println("Intersecton " + v + " " + j + " " + in);
                    }
                }

                ++v;
                v %= p1.segments.size();
            } while (v != p1.v);

            //if (!intersection)
            //{
                //for (Segment s : p1.segments)
                //{
                    //if (p2.segments.get(0).IntersectionLine(s) != null)
                //}
            //}
        }*/



        return segments;
    }
}
