import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
public class BruteCollinearPoints 
{
    private Point[] arrPoint;
    private LineSegment[] arrLine;
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.NullPointerException("null array");
        for(int i = 0; i < points.length; i++)
            for(int j = i+1; j < points.length; j++)
            {
                if ( points[i].compareTo( points[j] ) == 0 ) throw new java.lang.IllegalArgumentException("have same points");
            }
        arrPoint = points;
    }
    public           int numberOfSegments()        // the number of line segments
    {
        return arrLine.length;
    }
    public LineSegment[] segments()                // the line segments
    {
        return arrLine;
    }
    public static void main(String[] args) 
    {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) 
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) 
        {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) 
        {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}