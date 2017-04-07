import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdDraw;
public class Test
{
    private static int  Clip3 (int value, int min, int max)
    {
        return value > max ? max : value < min ? min : value;
    }
    
    public static void main(String[] args)
    {
//        Draw d = new Draw("test");
//        d.setCanvasSize(200, 200);
//        d.setPenRadius(0.02);
//        d.setPenColor(Draw.BLACK);
//        double x,y;
//        x = 100.0;
//        y = 100.0;
//        d.point(.2,.4);
//        d.setPenRadius();
//        d.circle(0.5,0.5,0.3);
        
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.enableDoubleBuffering();
////        StdDraw.setCanvasSize(200, 200);
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        StdDraw.setPenRadius(0.014);
////        StdDraw.point(100, 100);
//        
//        StdDraw.point(1000.2,1000.4);
//        StdDraw.point(15000.5,15000.5);
//        
//        StdDraw.setPenRadius();
//        StdDraw.line(1000.2,1000.4,15000.5,15000.5);
//        StdDraw.show();
        Point tst1 = new Point(2,3);
        Point tst2 = new Point(1,2);
        tst1 = tst2;
        System.out.println(tst1);
        System.out.println(tst1);
        System.out.println(tst1 == tst2);
        tst2.test();
        System.out.println(tst1);
        System.out.println(tst1);
        System.out.println(tst1 == tst2);
        
    }
}