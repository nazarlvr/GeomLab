package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Canvas extends JFrame implements MouseListener, KeyListener {
    ///huy
    ArrayList<Point> points;
    Polygon p1;
    Polygon p2;

    boolean f, flag;
    public Canvas() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(550, 300);
        panel.addMouseListener(this);
        panel.addKeyListener(this);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        points = new ArrayList<Point>();
        f = false;
        flag = true;
    }
    public void paint(Graphics gp) { super.paint(gp); Graphics2D graphics = (Graphics2D) gp;
        //if (points.size() > 0) {
            /*if (f)
            {
                graphics.setColor(Color.black);
            }
            else
            {
                graphics.setColor(Color.red);
            }*/
            graphics.setColor(Color.BLACK);
            graphics.setStroke(new BasicStroke(3));
            for (Point point : points) {
                graphics.fillOval(point.x - 2, point.y - 2, 4, 4);
            }

            if (points.size() > 1)
            {
                for (int i = 0; i < points.size() - 1; ++i) {
                    graphics.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
                }
            }

            if (p1 != null)
            {
                graphics.setColor(Color.RED);

                for (int i = 0; i < p1.segments.size(); ++i) {
                    Segment s = p1.segments.get(i);
                    graphics.drawLine(s.p1.x, s.p1.y, s.p2.x, s.p2.y);
                    graphics.drawString("" + i, (s.p1.x + s.p2.x)/2,(s.p1.y + s.p2.y)/2);
                }

                graphics.fillOval(p1.segments.get(p1.v).p1.x - 5, p1.segments.get(p1.v).p1.y - 5, 10, 10);
            }

            if (p2 != null)
            {
                graphics.setColor(Color.BLUE);

                for (int i = 0; i < p2.segments.size(); ++i) {
                    Segment s = p2.segments.get(i);
                    graphics.drawLine(s.p1.x, s.p1.y, s.p2.x, s.p2.y);
                    graphics.drawString("" + i, (s.p1.x + s.p2.x)/2,(s.p1.y + s.p2.y)/2);
                }

                graphics.fillOval(p2.segments.get(p2.v).p1.x - 5, p2.segments.get(p2.v).p1.y - 5, 10, 10);
            }

            if (p1 != null && p2 != null && f)
            {
                graphics.setColor(Color.MAGENTA);
                ArrayList<Segment> segments = Variant27.Intersection(p1, p2);
                System.out.println("Segments " + segments.size());

                for (Segment s : segments)
                {
                    graphics.drawLine(s.p1.x, s.p1.y, s.p2.x, s.p2.y);
                }
            }

            /*else
            {
                Polygon p = new Polygon(points);

                for (int i = 0; i < p.segments.size(); ++i) {
                    Segment s = p.segments.get(i);
                    graphics.drawLine(s.p1.x, s.p1.y, s.p2.x, s.p2.y);
                    graphics.drawString("" + i, (s.p1.x + s.p2.x)/2,(s.p1.y + s.p2.y)/2);
                }
            }*/

            /*graphics.setColor(Color.BLUE);
            graphics.drawLine(7, 35, 2000, 35);
            if (flag) {
                graphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
                graphics.setColor(Color.blue);
                for (Point point : points) {
                    graphics.drawLine(point.x, point.y, point.x, 35);
                }
            }*/


            if (this.points.size() >= 4)
            {
                Segment s = new Segment(this.points.get(0), this.points.get(1));
                Segment l = new Segment(this.points.get(2), this.points.get(3));
                Point p = s.Intersection(l);

                if (p != null)
                    graphics.fillOval(p.x - 2, p.y - 2, 4, 4);
            }
        //}
    }
    public static void main(String[] args) {
        Canvas demo = new Canvas();
        demo.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!f)
        {
            int x = e.getX() + 7;
            int y = e.getY() + 33;

            if (y > 37)
                if (this.points.isEmpty())
                {
                    this.points.add(new Point(x, y));
                }
                else if (this.points.get(this.points.size() - 1).distanceSq(x, y) > 10*10)
                {
                    Point p = this.points.get(this.points.size() - 1);

                    if (Math.abs(p.x - x) > Math.abs(p.y - y))
                        this.points.add(new Point(x, p.y));
                    else
                        this.points.add(new Point(p.x, y));
                }

        }
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
        /*for (Point point : points ) {
            System.out.print( point.x + " " + point.y + " ");
        }
            System.out.println();*/
        if (p1 != null && p2 != null)
        {
            f = true;
        }
        else if (points.size() > 1)
        {
            points.add(new Point(points.get(0).x, points.get(points.size()-1).y));

            if (p1 == null) {
                p1 = new Polygon(points);
                f = false;
            }
            else if (p2 == null)
            {
                p2 = new Polygon(points);
                f = false;
            }

            points.clear();
        }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (f == true)
                f = false;
            else if (points.size() > 0)
                points.clear();
            else if (p2 != null)
                p2 = null;
            else if (p1 != null)
                p1 = null;
        }
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            if (points.size() > 0)
                points.remove(points.size() - 1);
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}