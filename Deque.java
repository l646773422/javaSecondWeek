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
        private Node iter = head;
        public Item next()
        {
            if ( !hasNext() ) throw new java.util.NoSuchElementException();
            Item temp = iter.item;
            iter = iter.front;
            return temp;
            
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public boolean hasNext()
        {
            return iter!=null;
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
        if ( item == null )throw new java.lang.NullPointerException();
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
        if ( item == null )throw new java.lang.NullPointerException();
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
        for(int i=0; i<10; i++)
            que.addFirst(i);
        que.addFirst(1);
        que.addLast(2);
        System.out.println("size is : " + que.size());
        for(int res: que)
            System.out.printf("%3d", res);
        System.out.printf("\n");
    }
}