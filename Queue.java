/******************************************************************************
 *  Compilation:  javac -d . Queue.java
 *  Execution:    java -cp .  edu/princeton/cs/algs4/Queue < tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  Output:
 *  to be or not to be (2 left on queue)
 ******************************************************************************/

package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue <T> implements Iterable<T>
{
   private Node<T> first;
   private Node<T> last;
   private int n;   //size of the Queue
   
   //constructor
   public Queue() {
      first = null;  last = null;  n = 0;
   }   
   
   //linked list class (helper)
   private static class Node <T> {
      private T item;
      private Node<T>  next;
   }
   
   public boolean isEmpty() {
      return (first==null);
   }
   
   public int size()  { return n; }
   
   public T peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue is underflow");
      return first.item;
   }
   
   //put the item at the end of Queue
   public void enqueue(T item) {
      Node<T> tmp = last;   //save the last
      last = new Node<T>();
      last.item = item;
      last.next = null;
      if (isEmpty())  first = last;
      else            tmp.next = last;
      n++;
   }
   
   public T dequeue() {
      if (isEmpty()) throw new NoSuchElementException("Queue is underflow");
      T item = first.item;
      first = first.next;
      n--;
      if (isEmpty())  last=null; //avoid loitering
      return item;
   }
   
   public String toString() {
      StringBuilder s = new StringBuilder();  
      //"this" is Iterable, for-loop will invoke Iterable::Iterator::next()
      //which returns T   
      for (T item: this) {
         s.append(item);
         s.append(" ");
      }
      return s.toString();
   }
   
   //one abstract method in Iterable interface
   public Iterator<T> iterator() {
      return new ListIterator<T>(first);
   }
   
   private class ListIterator<T> implements Iterator<T> 
   {
      private Node<T> current;  //starting Node
      
      //constructor
      public ListIterator(Node<T> node) {
         current = node;
      }
      
      //not required to implement
      public void remove() { throw new UnsupportedOperationException(); }
      
      //two abstract methods in Iterator interface
      public boolean hasNext() { return (current != null); }
      public T next() {
         if (!hasNext()) { throw new NoSuchElementException(); }
         System.out.println("next() was called");
         T item = current.item;
         current = current.next;
         return item;
      }
   }

    public static void main(String[] args) 
    {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
        StdOut.println("Print (by toString) The items left on queue:" + queue);
        StdOut.println("Print (by iterator) The items left on queue:");
        Iterator<String> itr = queue.iterator();
        while (itr.hasNext()) {
           StdOut.println(" - next item: "+itr.next());
        }
    }   

}
