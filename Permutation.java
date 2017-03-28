import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args)
    {
        if( args[0] == null ) throw new java.util.NoSuchElementException("want to output too many string");
        int k = Integer.parseInt( args[0] );
        RandomizedQueue<String> que = new RandomizedQueue<String>();
        
//        int i = 0;
//        while( !StdIn.isEmpty() && i < k )
//        {
//            que.enqueue( StdIn.readString() );
//            i++;
//        }
        while( !StdIn.isEmpty())
        {
            que.enqueue( StdIn.readString() );
        }
        
        if ( k > que.size() ) throw new java.util.NoSuchElementException("want to output too many string");
//         && !que.isEmpty()
        for(int iter=0;iter<k ;iter++)
        {
            System.out.println( que.dequeue() );
        }
        
        return;
    }
}