import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
public class FastCollinearPoints 
{
    private Point[] arrPoints;
    private int lineSegmentCount;
    private LineSegment[] arrLine;
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) throw new java.lang.NullPointerException("null array");
        for(int i = 0; i < points.length; i++)
            for(int j = i+1; j < points.length; j++)
            {
                if ( points[i].compareTo( points[j] ) == 0 ) throw new java.lang.IllegalArgumentException("have same points");
            }
        arrPoints = points;
        lineSegmentCount = 0;
    }
    public           int numberOfSegments()        // the number of line segments
    {
        return arrLine.length;
    }
    public LineSegment[] segments()                // the line segments
    {
        // declare a new array, store sorted points
        Point[] tempPoints = new Point[ arrPoints.length - 1 ];
        double[] tempSlope = new double[ arrPoints.length - 1 ];
        for( int i = 0; i < arrPoints.length; i++)
        {
            // for each point, traverse all other points to find same slope to define a line;
            // initialize the array
            for( int j = 0; j < tempPoints.length; j++)
            {
                if ( i > j)tempPoints[j] = arrPoints[j];
                else tempPoints[j] = arrPoints[j+1]; 
            }
            // according to the slope, sort the points array
            Arrays.sort( tempPoints, arrPoints[i].slopeOrder() );

            // calculate all slope
            for( int k = 0; k < tempPoints.length; k++ )
            {
                tempSlope[k] = arrPoints[i].slopeTo( tempPoints[k] );
            }

            int slopeCounter = 0;
            for( int k = 0; k < tempSlope.length - 1; k++)
            {
                // check whether have over 3 same slope
                // two slope close enough mean
                if( (tempSlope[k] - tempSlope[k+1]) < 0.0001 )
                    slopeCounter++;
                else 
                {
                    if (slopeCounter >= 2)
                        // find a lineSegment
                        break;
                    slopeCounter = 0;
                }
                // how to record
            }
        }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) 
        {
            System.out.println(segment);
            segment.draw();
        }
        System.out.println(collinear.segments());
        StdDraw.show();
    }
}