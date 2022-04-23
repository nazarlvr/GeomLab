import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Canvas extends JFrame implements MouseListener, KeyListener {
    ///huy
    int x, y;
    ArrayList<Point> points;
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
        if (points.size() > 0) {
            if (f)
            {
                graphics.setColor(Color.black);
            }
            else
            {
                graphics.setColor(Color.red);
            }
            graphics.setStroke(new BasicStroke(3));
            for (Point point : points) {
                graphics.fillOval(point.x - 2, point.y - 2, 4, 4);
            }

            for (int i = 0; i < points.size() - 1; ++i) {
                graphics.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
            }
            if (f) {
                graphics.drawLine(points.get(0).x, points.get(0).y, points.get(points.size() - 1).x, points.get(points.size() - 1).y);
            }
            graphics.setColor(Color.BLUE);
            graphics.drawLine(7, 35, 2000, 35);
            if (flag) {
                graphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
                graphics.setColor(Color.blue);
                for (Point point : points) {
                    graphics.drawLine(point.x, point.y, point.x, 35);
                }
            }
        }
    }
    public static void main(String[] args) {
        Canvas demo = new Canvas();
        demo.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!f) {
            x = e.getX() + 7;
            y = e.getY() + 33;
            if (y > 37)
            this.points.add(new Point(x, y));
        }
        this.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        for (Point point : points ) {
            System.out.print( point.x + " " + point.y + " ");
        }
            System.out.println();
        if (points.size() > 0) {
            f = true;
        }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            f = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            points.clear();
            f = false;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}