import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> 
{
    private int m_x, m_y;
    
    private static int Clip3 (int value, int min, int max)
    {
        return value > max ? max : value < min ? min : value;
    }
    
    public Point(int x, int y)                         // constructs the point (x, y)
    {
        m_x = Clip3( x, 0, 32767);
        m_y = Clip3( y, 0, 32767);
    }
    public   void draw()                               // draws this point
    {
        StdDraw.setPenRadius(0.014);
        StdDraw.point( m_x, m_y);
    }
    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        StdDraw.line( m_x, m_y, that.m_x, that.m_y );
    }
    public String toString()                           // string representation
    {
        return "" + m_x + ", " + m_y;
    }

    public int compareTo(Point that)                   // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        if ( m_y < that.m_y ) return -1;
        if ( m_y > that.m_y ) return  1;
        if ( m_x < that.m_x ) return -1;
        if ( m_x > that.m_x ) return  1;
        return 0;
    }
    public double slopeTo(Point that)                  // the slope between this point and that point
    {
        return 0;
    }
//    public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
//    {
//        
//    }
    public static void main(String[] args)
    {
        Point p1, p2;
        p1 = new Point(100, 100);
        p2 = new Point(15000, 15000);
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        p1.draw();
        System.out.println(p1);
        p2.draw();
        p1.drawTo(p2);
        StdDraw.show();
    }
}