import edu.princeton.cs.algs4.StdDraw;
public class LineSegment 
{
    Point pStart, pEnd;
    public LineSegment(Point p, Point q)        // constructs the line segment between points p and q
    {
        pStart = p;
        pEnd = q;
    }
    public   void draw()                        // draws this line segment
    {
        pStart.draw();
        pEnd.draw();
        pStart.drawTo(pEnd);
    }
    public String toString()                    // string representation
    {
        return "( " + pStart.toString() + " )" + " -> " + "( " + pEnd.toString() + " )";
    }
    public static void main(String[] args)
    {
        Point p1, p2;
        p1 = new Point(20,20);
        p2 = new Point(15000,15000);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        LineSegment test = new LineSegment(p1, p2);
        test.draw();
        System.out.println( test );
        
        StdDraw.show();
    }
}