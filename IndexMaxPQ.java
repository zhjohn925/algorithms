package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> 
{
  private int n;       //number of elements in PQ
  private int[] pq;    //binary heap using 1-based indexing
  private int[] qp;    //inverse of pq - qp[pq[i]] = pq[qp[i]] = i
  private Key[] keys;  //keys[i] = priority of i

  public IndexMaxPQ(int maxN) {
    if (maxN<0) throw new IllegalArgumentException();
    n = 0;
    keys = (Key[]) new Comparable[maxN+1];
    pq = new int[maxN+1];
    qp = new int[maxN+1];
    for (int i=0; i<=maxN; i++) {
      qp[i] = -1;
    }
  }
  
  public boolean isEmpty() {
    return (n==0);
  }

  public boolean contains(int i) {
    return qp[i] != -1;
  }

  public int size()  { return n; }

  public void insert(int i, Key key) {
    if (contains(i)) throw new IllegalArgumentException("index is already in the PQ");
    n++;
    qp[i] = n;  pq[n] = i;
    keys[i] = key;
    swim(n);
  }

  public int maxIndex() {
    if (n==0) throw new NoSuchElementException("Priority queue underflow");
    return pq[1];
  }

  public Key maxKey() {
    if (n==0) throw new NoSuchElementException("Priority queue underflow");
    return keys[pq[1]];
  }

  public int delMax() {
    if (n==0) throw new NoSuchElementException("Priority queue underflow");
    int min = pq[1];
    exch(1, n--);
    sink(1);
    assert pq[n+1]==min;
    qp[min] = -1;
    keys[min] = null;  //to help with garbage collection
    pq[n+1] = -1;
    return min;
  }

  public Key keyOf(int i) {
    if (!contains(i)) throw new NoSuchElementException("index is not in the PQ");
    else return keys[i];
  }

  public void changeKey(int i, Key key) {
    if (!contains(i)) throw new NoSuchElementException("index is not in the PQ");
    keys[i] = key;
    swim(qp[i]);
    sink(qp[i]);
  }

  //@deprecated Replaced by {@code changeKey(int, Key)}.
  @Deprecated
  public void change(int i, Key key) {
    changeKey(i, key);
  }

  /*********************************************************
   * General helper functions
   * Note: 
   *   pq is index priority queue (holding keys' indice).
   *   pq[0] is not used.
   *********************************************************/

  //the function compares two keys with indice pq[i] and pq[j]
  private boolean less(int i, int j) {
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
  }

  //the function exchanges index (pq[i]) with index (pq[j])
  private void exch(int i, int j) {
    int swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  private void swim(int k) {
    while (k>1 && less(k/2, k)) {
      exch(k, k/2);
      k = k/2;
    }
  }

  private void sink(int k) {
    while (2*k <= n) {
      int j = 2*k;
      if (j<n && less(j, j+1)) j++;
      if (!less(k,j)) break;
      exch(k, j);
      k = j;
    }
  }


  public Iterator<Integer> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Integer> {
    private IndexMaxPQ<Key> copy;
    public HeapIterator() {
      copy = new IndexMaxPQ<Key>(pq.length -1);
      for (int i=1; i<=n; i++) {
        //copy.insert(pq[i], keys[pq[i]]);
      }
    }
    public boolean hasNext() {
      return false; //!copy.isEmpty();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Integer next() {
      if (!hasNext()) throw new NoSuchElementException();
      return 0; //copy.delMax();
    }
  }
  
  public static void main(String[] args)
  {

  }
}