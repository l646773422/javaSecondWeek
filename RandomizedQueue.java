import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private int queSize;
    private Node tail;
    private class Node
    {
        Item item;
        Node next;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Node iter = tail;
        RandomizedQueueIterator()
        {
            // to init queSize of iters and random output them
        }
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
            if(queSize==0) return false;
            return  (iter != null);
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
//        StdRandom.setSeed(System.currentTimeMillis());
        int pos = StdRandom.uniform(0, queSize);
        Node temp, iter;
        Item item;
        if( pos == 0) 
        {
            item = tail.item;
            tail = tail.next;
            queSize--;
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
//        iter = null;
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
//        RandomizedQueue<String> ranque = new RandomizedQueue<String>();
        RandomizedQueue<Integer> ranque = new RandomizedQueue<Integer>();
        
            ranque.enqueue(1);
            ranque.enqueue(2);
            ranque.enqueue(3);
            ranque.enqueue(4);
        
        for(int i: ranque)
            System.out.printf("%3d",i);
    }
}