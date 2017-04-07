import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.Arrays;
public class Point implements Comparable<Point> 
{
    private int m_x, m_y;
    private Point m_point;
    public void test()
    {
        m_x = 0;
    }
    private class slopeComparator implements Comparator<Point>
    {
        public int compare( Point p1, Point p2)
        {
            // m_point some question
            double result = m_point.slopeTo(p1) - m_point.slopeTo(p2);
            return result < 0 ? -1 : result > 0 ? 1 : 0 ;
        }
    }
    
    private static int Clip3 (int value, int min, int max)
    {
        return value > max ? max : value < min ? min : value;
    }
    
    public Point(int x, int y)                         // constructs the point (x, y)
    {
        m_x = Clip3( x, 0, 32767);
        m_y = Clip3( y, 0, 32767);
        m_point = this;
    }
    public   void draw()                               // draws this point
    {
        StdDraw.setPenRadius(0.014);
        StdDraw.point( m_x, m_y);
    }
    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        StdDraw.setPenRadius();
        StdDraw.line( m_x, m_y, that.m_x, that.m_y );
    }
    public String toString()                           // string representation
    {
        return " " + m_x + ", " + m_y + " ";
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
        if ( m_x == that.m_x) return 32767;
        return (double)(m_y - that.m_y) / (double)(m_x - that.m_x);
    }
    public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
    {
        return new slopeComparator();
    }
    
    public static void main(String[] args)
    {
        Point[] points = new Point[4];
        Point[] temp = new Point[3];
        points[0] = new Point(100, 100);
        points[1] = new Point(15000, 1000);
        points[2] = new Point(4100, 9000);
        points[3] = new Point(12000, 8000);
        
//        temp[0] = new Point(15000, 1000);
//        temp[1] = new Point(4100, 9000);
//        temp[2] = new Point(12000, 8000);
        for(int i=0;i<3;i++)
        {
            temp[i] = points[i+1];
        }
        System.out.println( "temp" );
        for( int k = 0; k < temp.length; k++ )
        {
            System.out.println( temp[k] );
        }
        Arrays.sort( temp, points[0].slopeOrder() );
//        Arrays.sort( temp);
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        System.out.println( "temp" );
        for( int k = 0; k < temp.length; k++ )
        {
            System.out.println( temp[k] );
            temp[k].draw();
        }
        System.out.println( "Points" );
        for( int k = 0; k < points.length; k++ )
        {
            System.out.println( points[k] );
        }

        StdDraw.show();
    }
}