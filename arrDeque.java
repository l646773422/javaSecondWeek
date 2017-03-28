import java.util.Iterator;
// 为什么要用数组做呢。作死
// 感觉应该用链表。
public class arrDeque<Item> implements Iterable<Item> 
{
    private int head, tail;
    private int N;
    private Item[] arr;
    private boolean hasItem;
    
    public class DequeIterator implements Iterator<Item>
    {
        int iter = tail;
        public Item next()
        {
            Item temp = arr[iter];
            iter = ( iter + 1) % N;
            return temp;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public boolean hasNext()
        {
            return (iter != head) && hasItem;
        }
    }
    
    private void resize(int newSize)
    {
        int iter = tail;
        int hPos = size();
        Item[] temp = (Item[]) new Object[newSize];
        int i = 0;
        while( iter != head )
        {
            temp[i] = arr[iter];
            arr[iter] = null;
            iter = (iter + 1)%N;
            i++;
        }
        // update all data
        tail = 0;
        head = hPos;
        N = newSize;
        arr = temp;
        System.out.println(true);
    }
    
    public arrDeque()                           // construct an empty deque
    {
        hasItem = false;
        N = 2;
        arr = (Item[]) new Object[N];
        tail = 0;
        head = 0;
    }
    
    public boolean isEmpty()                 // is the deque empty?
    {
        return (tail + 1) == head;
    }
    
    public int size()                        // return the number of items on the deque
    {
        return head>tail ? (head - tail) : hasItem ? (N + head - tail) : 0;
//        return head>tail ? (head - tail + 1) : ( N + head - tail);
    }
    
    public void addFirst(Item item)          // add the item to the front
    {
        if ( item == null )throw new java.lang.NullPointerException();
        if ( size() >= ( N / 2) ) resize( N * 2);
        arr[head] = item;
        head = ( head + 1) % N;
        hasItem = true;
    }
    
    public void addLast(Item item)           // add the item to the end
    {
        if ( item == null )throw new java.lang.NullPointerException();
        if ( size() >= ( N / 2) ) resize( N * 2);
        tail = (tail - 1 + N) % N;
        arr[tail] = item;
        hasItem = true;
    }
    
    public Item removeFirst()                // remove and return the item from the front
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        head = (head - 1 + N)%N;
        Item temp = arr[head];
        arr[head] = null;
        if( tail == head ) hasItem = false;
        return temp;
    }
    
    public Item removeLast()                 // remove and return the item from the end
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        Item temp = arr[tail];
        arr[tail] = null;
        tail = (tail + 1)%N;
        if( tail == head ) hasItem = false;
        return temp;
    }
    
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    
    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<Integer> que = new Deque<Integer>();
        System.out.println("0size" + que.size());
        que.addFirst(17);
        System.out.println("1size" + que.size());
        que.addFirst(18);
        System.out.println("2size" + que.size());
        que.addFirst(19);
        System.out.println("3size" + que.size());
        que.addLast(20);
        System.out.println("4size" + que.size());
        que.addLast(21);
        System.out.println("5size" + que.size());
        que.removeFirst();
        System.out.println("4size" + que.size());
        que.removeFirst();
        System.out.println("3size" + que.size());
        que.removeLast();
        System.out.println("2size" + que.size());
        for(int res: que)
            System.out.printf("%3d", res);
        System.out.printf("\n");
    }
}
//    int head, tail;
//    int N;
//    Item[] arr;
//    
//    public class DequeIterator implements Iterator<Item>
//    {
//        private int i = 0, j = N - 1;
//        // initial the front, back flags
//        private boolean front = i==head;
//        private boolean back  = j==tail;
//        
//        public Item next()
//        {
//            //todo
//            //throw a java.util.NoSuchElementException if there are no more items
//            if(!front)
//            {
//                Item item = arr[i];
//                i++;
//                if( i == head ) front = true;
//            }
//            else
//            {
//                Item item = arr[j];
//                j--;
//                if( j == tail) back = true;
//            }
//        }
//        public void remove()
//        {
//            throw new java.lang.UnsupportedOperationException();
//            return;
//        }
//        public boolean hasNext()
//        {
//            return !(front && back);
//        }
//    }
//    
//    private void resize(int newSize)
//    {
//        int i=0;
//        int j=0, k=newSize-1;
//        Item[] temp = (Item[]) new Object[newSize];
//        for(; i<head; i++)
//        {
//            temp[i] = arr[i];
//            arr[i] = null;
//        }
//        for(; j>tail; j--,k--)
//        {
//            temp[k] = arr[j];
//            arr[j] = null;
//        }
//        // update all data
//        tail = k;
//        N = newSize;
//        arr = temp;
//    }
//    
//    public Deque()                           // construct an empty deque
//    {
//        N = 2;
//        head = 0;
//        tail = N - 1;
//        arr = (Item[]) new Object[N];
//        
//    }
//    public boolean isEmpty()                 // is the deque empty?
//    {
//        return head == 0 && tail == ( N - 1 );
//    }
//    public int size()                        // return the number of items on the deque
//    {
//        return head + ( N - tail - 1);
//    }
//    public void addFirst(Item item)          // add the item to the front
//    {
//        if ( item == null )throw new java.lang.NullPointerException();
//        if ( size() >= ( N / 2) ) resize( N * 2);
//        
//        arr[head] = item;
//        head++;
//        
//    }
//    public void addLast(Item item)           // add the item to the end
//    {
//        if ( item == null )throw new java.lang.NullPointerException();
//        if ( size() >= ( N - 1) ) resize( N * 2);
//        
//        arr[tail] = item;
//        tail--;
//    }
//    public Item removeFirst()                // remove and return the item from the front
//    {
//        if( isEmpty() ) throw new java.util.NoSuchElementException("the queue is empty");
//        if( head == 0 ) throw new java.util.NoSuchElementException("");
//    }
//    public Item removeLast()                 // remove and return the item from the end
//    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
//    {
//        return new DequeIterator();
//    }
//    public static void main(String[] args)   // unit testing (optional)
//    {
//        return ;
//    }
