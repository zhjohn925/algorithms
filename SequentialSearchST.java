/***************************************************
* Compilation:javac -d . SequentialSearchST.java
****************************************************/

package edu.princeton.cs.algs4;

import java.util.List;
import java.util.ArrayList;

//Sequential search in an unordered linked list
//ST - symbol table - dictionary - pair of <key, value>

public class SequentialSearchST<Key, Value>
{
   private Node first;
   
   private class Node {
      Key key;
      Value val;
      Node next;
      public Node(Key key, Value val, Node next) {
         this.key=key; this.val=val; this.next=next;
      }
   }
   
   public Value get(Key key) {
      //search for key, return associated value
      for (Node x=first; x!=null; x=x.next) {
         if (key.equals(x.key)) {
            return x.val;
         }
      }
      return null;
   }
   
   public void put(Key key, Value val) {
      //search for key, update value if found; grow table if new
      for (Node x=first; x!=null; x=x.next) {
         if (key.equals(x.key)) {
           //search hit, update the value
           x.val = val;
           return;
         }
      }
      //search miss: insert it at the beginning of the list
      first = new Node(key, val, first);
   }
   
   public int size() {
      int sze = 0;
      for (Node x=first; x!=null; x=x.next) {
         sze++;
      }
      return sze;
   }
   
   public Iterable<Key> keys() {
      List<Key> list = new ArrayList<>();
      for (Node x=first; x!=null; x=x.next) {
         list.add(x.key);
      }
      return list;
   }
   
   public boolean contains(Key key) {
      for (Node x=first; x!=null; x=x.next) {
         if (key.equals(x.key)) {
            return true;
         }
      }
      return false;
   }
   
}