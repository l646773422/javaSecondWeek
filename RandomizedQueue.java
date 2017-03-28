import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> 
{
    int queSize;
    Node tail;
    private class Node
    {
        Item item;
        Node next;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Node iter = tail;
        public Item next()
        {
            if(iter == null) throw new java.util.NoSuchElementException("queue is empty");
            Item item = iter.item;
            iter = iter.next;
            return item;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public boolean hasNext()
        {
            return iter.next != null;
        }
    }
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        tail = null;
        queSize = 0;
    }
    public boolean isEmpty()                 // is the queue empty?
    {
        return queSize == 0;
    }
    public int size()                        // return the number of items on the queue
    {
        return queSize;
    }
    public void enqueue(Item item)           // add the item
    {
        if ( item == null )throw new java.lang.NullPointerException();
        Node temp = new Node();
        temp.item = item;
        temp.next = tail;
        tail = temp;
        queSize++;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        StdRandom.setSeed(System.currentTimeMillis());
        int pos = StdRandom.uniform(0, queSize);
        Node temp, iter;
        Item item;
        if( pos == 0) 
        {
            item = tail.item;
            tail = tail.next;
            return item;
        }
        temp = tail;
        iter = tail.next;
        for( int i=0; i < pos - 1; i++)
        {
            iter = iter.next;
            temp = temp.next;
        }
        item = iter.item;
        temp.next = iter.next;
        iter = null;
        queSize--;
        return item;
    }
    public Item sample()                     // return (but do not remove) a random item
    {
        if( isEmpty() ) throw new java.util.NoSuchElementException("queue is empty");
        int pos = StdRandom.uniform(0, queSize);
        Node iter = tail;
        for(int i=0; i<pos; i++)
            iter = iter.next;
        return iter.item;
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        RandomizedQueue<Integer> ranque = new RandomizedQueue<Integer>();
        System.out.println("0size" + ranque.size());
        ranque.enqueue(15);
        System.out.println("1size" + ranque.size());
        ranque.enqueue(16);
        System.out.println("2size" + ranque.size());
        ranque.enqueue(17);
        System.out.println("3size" + ranque.size());
        ranque.enqueue(18);
        System.out.println("4size" + ranque.size());
        ranque.enqueue(5);
        System.out.println("5size" + ranque.size());
        ranque.enqueue(8);
        System.out.println("6size" + ranque.size());
        ranque.enqueue(35);
        System.out.println("7size" + ranque.size());
        
        for(int i: ranque)
            System.out.println(i);
    }
}