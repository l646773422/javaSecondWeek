import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

import javax.sound.sampled.Line;
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

//        LineSegment[] tempLineArr = new LineSegment[ arrPoints.length / 4 + 1 ];
        LineSegment[] tempLineArr = new LineSegment[ arrPoints.length + 1 ];
        Point[] tempPoints = new Point[ arrPoints.length - 1 ];
        double[] tempSlope = new double[ arrPoints.length - 1 ];
        int pointCounter;

        for( int i = 0; i < arrPoints.length; i++)
        {
            boolean hasLine = false;
//            double[] lineSlope = new double[5];
            double lineSlope ;
            int lineSlopeIndex=-1;
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
                if( Math.abs( (tempSlope[k] - tempSlope[k+1]) )  < 0.0001 )
                {
                    slopeCounter++;
                    if (slopeCounter >= 2)
                    {
                        // find a lineSegment
                        lineSlopeIndex = k;
                        hasLine = true;
                    }
                }
                else 
                {
                    slopeCounter = 0;
                }
                // how to record
            }
            if (hasLine)
            {
                // find the
                if(lineSlopeIndex != -1) lineSlope = tempSlope[lineSlopeIndex];
                else return null;
                Arrays.sort( tempPoints );
                Point begin=null, end=null;
                for(int iter = 0;iter<tempPoints.length; iter++)
                {
                    if( arrPoints[i].slopeTo( tempPoints[iter] ) - lineSlope < 0.0001 )
                    {
                        begin = arrPoints[pointIndex( tempPoints[iter] )];
                        break;
                    }
                }
                for(int iter = tempPoints.length-1;iter>0; iter--)
                {
                    if( arrPoints[i].slopeTo( tempPoints[iter] ) - lineSlope <  0.0001 )
                    {
                        end = arrPoints[pointIndex( tempPoints[iter] )];
                        break;
                    }
                }
                // if no same line in the array

                //
                if( begin!=null && end!=null )
                {
                    LineSegment line = new LineSegment(begin, end);
                    tempLineArr[lineSegmentCount] = line;
                    lineSegmentCount++;
                }
            }
        }

        // finally get the arr
        if (lineSegmentCount != 0)
        {
            arrLine = new LineSegment[lineSegmentCount];
            for(int i=0 ; i < lineSegmentCount; i++)
            {
                arrLine[i] = tempLineArr[i];
            }
        }
        return arrLine;
    }
    private int pointIndex(Point point)
    {

        for( int i = 0; i < arrPoints.length; i++)
        {
            if(point == arrPoints[i]) return i;
        }
        return -1;
    }
//    private boolean OnLine()
//    {
//
//    }
    public static void main(String[] args) 
    {
        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++)
//        {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }

        int n = 6;
        Point[] points = new Point[n];
        points[0] = new Point(100, 100);
        points[1] = new Point(500, 500);
        points[2] = new Point(700, 700);
        points[3] = new Point(13000, 1000);
        points[4] = new Point(9000, 9000);
        points[5] = new Point(5400, 18000);

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
//        collinear.segments();
        for (LineSegment segment : collinear.segments())
        {
            System.out.println(segment);
            segment.draw();
        }
//        System.out.println(collinear.segments());
        StdDraw.show();
    }
}