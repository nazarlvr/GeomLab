package main;

import java.awt.*;
import java.util.ArrayList;

public class Variant19
{
    public ArrayList<Segment> segments;

    public Variant19(ArrayList<Point> points)
    {
        this.segments = new ArrayList<Segment>();
        this.addSegment(new Segment(points.get(0), points.get(points.size()-1)));

        for (int i = 1; i < points.size(); ++i)
        {
            this.addSegment(new Segment(points.get(i-1), points.get(i)));
        }
    }

    public void addSegment(Segment s)
    {
        if (s.p1.x == s.p2.x)
            return;

        if (this.segments.isEmpty())
            this.segments.add(s);
        else
        {
            int i = 0;
            int il = 0;
            int ir = this.segments.size()-1;

            while (il <= ir)
            {
                if (this.segments.get(il).p1.x + this.segments.get(il).p2.x > s.p1.x + s.p2.x)
                {
                    i = il;
                    break;
                }

                if (this.segments.get(ir).p1.x + this.segments.get(ir).p2.x < s.p1.x + s.p2.x)
                {
                    i = ir+1;
                    break;
                }

                if (this.segments.get((il+ir)/2).p1.x + this.segments.get((il+ir)/2).p2.x > s.p1.x + s.p2.x)
                {
                    ir = (il+ir)/2;
                }
                else
                    il = (il+ir+1)/2;
            }

            while (i < this.segments.size()-1 && Math.min(this.segments.get(i).p1.x, this.segments.get(i).p2.x) < Math.max(s.p1.x, s.p2.x))
            {
                if (Math.min(this.segments.get(i).p1.y, this.segments.get(i).p2.y) < Math.min(s.p1.y, s.p2.y) && Math.min(this.segments.get(i).p1.x, this.segments.get(i).p2.x) < Math.min(s.p1.x, s.p2.x) && Math.max(this.segments.get(i).p1.x, this.segments.get(i).p2.x) > Math.max(s.p1.x, s.p2.x))
                {
                    return;
                }

                else if (Math.min(this.segments.get(i).p1.y, this.segments.get(i).p2.y) > Math.min(s.p1.y, s.p2.y) && Math.min(this.segments.get(i).p1.x, this.segments.get(i).p2.x) > Math.min(s.p1.x, s.p2.x) && Math.max(this.segments.get(i).p1.x, this.segments.get(i).p2.x) < Math.max(s.p1.x, s.p2.x))
                {
                    //this.segments.
                }
            }
        }
    }
}
