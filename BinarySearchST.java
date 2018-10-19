
package edu.princeton.cs.algs4;

public class BinarySearchST<Key extends Comparable<Key>, Value>
{
   //pair of key and value,  keep in order
   private Key[] keys;
   private Value[] vals;
   private int N;
   
   @SuppressWarnings("unchecked")
   public BinarySearchST(int capacity) {
      keys = (Key[]) new Comparable[capacity];
      vals = (Value[]) new Object[capacity];
   }
   
   public int size() {
      return N;
   }
   
   //number of keys less than the input key 
   //the rank() code uses binary search to complete the 
   //symbol-table (ST) implementation
   //option 1: non-recursive better exposes the structure of the 
   //          algorithm (binary search).  
   //option 2: call recursive ie. return rank(key, 0, N-1);
   //The below uses option 1
   public int rank(Key key) {
      int lo = 0, hi = N-1;
      while (lo <= hi) {
         int mid = lo+(hi-lo)/2;
         int cmp = key.compareTo(keys[mid]);
         if (cmp < 0)  hi = mid-1;
         else if (cmp > 0) lo = mid+1;
         else return mid;  //search hit
      }
      //search miss: keys[lo] is smallest but greater than the input key, OR
      //             lo = N if the input key > keys[N-1] (the last key in the list)
      return lo;
   }
   
   //recursive implementation
   public int rank(Key key, int lo, int hi) {
      if (hi < lo) return lo;
      
      int mid = lo + (hi-lo)/2;
      int cmp = key.compareTo(keys[mid]);
      if (cmp < 0) 
         return rank(key, lo, mid-1);
      else if (cmp > 0) 
         return rank(key, mid+1, hi);
      else 
         return mid;   
   }
   
   public Value get(Key key) {
      if (isEmpty()) return null;
      int i = rank(key);
      if (i<N && keys[i].compareTo(key)==0) {
         return vals[i];
      } else {
         return null;
      }
   }
   
   public void put(Key key, Value val) {
      int i = rank(key);
      //search hit
      if (i<N && keys[i].compareTo(key)==0) {
         vals[i] = val; return;
      }
      //search miss, shift the key to the bottom from i
      for (int j=N; j>i; j--) {
         keys[j] = keys[j-1];
         vals[j] = vals[j-1];
      }
      keys[i] = key;
      vals[i] = val;
      N++;
   }
   
   public boolean isEmpty()  {  return (N==0); } 
   
   public boolean contains(Key key) {
      int i = rank(key);
      if (i<N && keys[i].compareTo(key)==0) {
         return true;
      }
      return false;
   }
   
   public Key min() {
      //if (isEmpty()) return null;
      return keys[0];
   }
   
   public Key max() {
      //if (isEmpty()) return null;
      return keys[N-1];
   }
   
   //key of rank k
   public Key select(int k) {
      return keys[k];
   }
   
   //smallest key greater than or equal to the input key
   public Key ceiling(Key key) {
      int i = rank(key);
      return keys[i];
   }
   
   //largest key less than or equal to the input key
   public Key floor(Key key) {
      int i = rank(key); 
      if (i<N && keys[i].compareTo(key)==0) {
         //the input key is in the list
         return key;  
      } else {
         //the input key is not in the list
         //keys[i] is smallest but greater than the input key 
         if (i<N && i>0) {
            return keys[i-1];
         }
      }
      return null;
   }
      
   public void delete(Key key) {
      if (N<2) {
         N = 0;  
      } else {
        int i = rank(key);
        //search hit
        if (i<N && keys[i].compareTo(key)==0) {
           for (int j=i; j<N-1; j++) {
              keys[j] = keys[j+1];
           }
           N--;
        }
      }
   }
   
   
   //Queue implements Iterable interface
   public Iterable<Key> keys(Key lo, Key hi) {
      Queue<Key> q = new Queue<Key>();
      for (int i=rank(lo); i<rank(hi); i++) {
         q.enqueue(keys[i]);
      }
      if (contains(hi)) {
         q.enqueue(keys[rank(hi)]);
      }
      return q;
   }
   
}