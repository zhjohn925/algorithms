package edu.princeton.cs.algs4;

import java.util.List;
import java.util.ArrayList;

//Index priority-queue
public class IndexMinPQ<Key extends Comparable<Key>>
{
  private int N;     //number of elements in PQ
  private int[] pq;  //binary heap using 1-based indexing
  private int[] qp;  //inverse: qp[pq[i]]=pq[qp[i]]=i
  private List<Key> keys;  //items with priorities
  
  public IndexMinPQ(int maxN) {
    //the first element (0) is unused in priority queue
    keys = new ArrayList<Key>(maxN+1);
    pq = new int[maxN+1];
    qp = new int[maxN+1];
    for (int i=0; i<maxN+1; i++) {
      qp[i] = -1;      
    }
  }

  public boolean isEmpty() {
    return (N==0);
  }  
  
  public boolean contains(int k) {
    return (qp[k] != -1);
  }
  
  public void insert(int k, Key key) {
    N++;
    qp[k] = N;
    pq[N] = k;
    keys.set(k, key);
    //swim(N);
  }
  
  public Key min() {
    return keys.get(pq[1]);
  }
  
  public int delMin() {
    //the element 1 is minium (element 0 is unused) 
    int indexOfMin = pq[1];
    //exch(1, N);
    keys.remove(pq[N]);
    //sink(1);
    qp[pq[N]] = -1;
    N--;
    return indexOfMin;
  }

}