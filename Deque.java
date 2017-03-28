import java.util.Iterator;
// use list to realize the deque
public class Deque<Item> implements Iterable<Item> 
{
    private class Node
    {
        Item item;
        Node next;
        Node front;
    }
    private Node head, tail;
    private int listSize;
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node iter = tail;
        public Item next()
        {
            if ( !hasNext() ) throw new java.util.NoSuchElementException();
            Item temp = iter.item;
            iter = iter.next;
            return temp;
            
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public boolean hasNext()
        {
            return iter!=head;
        }
    }
    
    public Deque()                           // construct an empty deque
    {
        head = null;
        tail = null;
        listSize = 0;
    }
    
    public boolean isEmpty()                 // is the deque empty?
    {
        return listSize == 0;
    }
    
    public int size()                        // return the number of items on the deque
    {
        return listSize;
    }
    
    public void addFirst(Item item)          // add the item to the front
    {
        Node temp = new Node();
        temp.item = item;
        temp.front = head;
        if(head == null) {
            tail = temp;
        }
        else head.next = temp;
        temp.next = null;
        head = temp;
        listSize++;
    }
    
    public void addLast(Item item)           // add the item to the end
    {
        Node temp = new Node();
        temp.item = item;
        temp.next = tail;
        if(tail == null){
            head = temp;
        }
        else tail.front = temp;
        temp.front = null;
        tail = temp;
        listSize++;
    }
    
    public Item removeFirst()                // remove and return the item from the front
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        Item item = head.item;
        if(listSize == 1)
        {
            head = null;
            tail = null;
        }
        else 
        {
            head = head.front;
            head.next = null;
        }
        listSize--;
        return item;
    }
    
    public Item removeLast()                 // remove and return the item from the end
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        Item item = tail.item;
        if(listSize == 1)
        {
            head = null;
            tail = null;
        }
        else 
        {
            tail = tail.next;
            tail.front = null;
        }
        listSize--;
        return item;
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
        
        for(int res: que)
            System.out.printf("%3d", res);
        System.out.printf("\n");
    }
}