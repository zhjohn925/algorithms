
package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.List;

public class MaxPQ <Key extends Comparable<Key>>
{
  //The priority queue is maintained in a heap-ordered 
  //complete binary tree in the list pq, with element 0 
  //unused and the N keys in elements (1..N)
  private List<Key> pq;
  private int N = 0;

  public MaxPQ(int maxN) {
    //Constructs an empty list with the specified initial capacity.
    pq = new ArrayList<Key>(maxN+1);
  }
  
  public boolean isEmpty() 
  { return N==0; }
  
  public int size() 
  { return N;  }
  
  public void insert(Key v)
  {
    pq.add(v);
    N++;
    //swim(N);
  }
  
  //element 0 is not used
  //- element 1 is max, switch the last element(N) 
  //  in element 1 position. then delete the last
  //- now the last element (smallest Node) in Node 1 
  //  which violates heap order (the key is less than
  //  its child)
  //- apply sink() down
  public Key delMax()
  {
    Key max = pq.get(1);
    exch(1, N);
    pq.remove(N);
    N--;   //reduce size
    sink(1);
    return max;
  }
  
  //Bottom-up reheapify (swim)
  //Node 5 violates heap order (larger key than parent)
  //            1(S)
  //        /           \
  //      2(P)          3(R)
  //    /     \         /   \    
  //  4(G)      5(T)   6(O)  7(A)
  //  /   \     /   \   
  // 8(E) 9(I) 10(H) 11(G) 
  //
  //swim() up:  Node 5->2->1
  //            1(T)
  //        /           \
  //      2(S)          3(R)
  //    /     \         /   \    
  //  4(G)      5(P)   6(O)  7(A)
  //  /   \     /   \   
  // 8(E) 9(I) 10(H) 11(G)   
  
  //Bottom-up reheapify (swim)
  //each Node k, has parent node k/2 if any; and
  //has child node 2k and 2k+1 if any.
  private void swim(int k)
  {
    //compare with parent node
    while (k>1 && less(k/2, k)) {
      exch(k/2, k);
      k = k/2;
    }
  }
  
  
  //Top-down reheapify (sink)
  //Node 5 violates heap order (key is less than its child)
  //            1(T)
  //        /           \
  //      2(H)          3(R)
  //    /     \         /   \    
  //  4(P)      5(S)   6(O)  7(A)
  //  /   \     /   \   
  // 8(E) 9(I) 10(N) 11(G) 
  //
  //sink() down:  Node 2->5->10
  //            1(T)
  //        /           \
  //      2(S)          3(R)
  //    /     \         /   \    
  //  4(G)      5(N)   6(O)  7(A)
  //  /   \     /   \   
  // 8(E) 9(I) 10(H) 11(G)  
  
  //Top-down reheapify (sink)
  private void sink(int k)
  {
    while (2*k <= N) {
      int j = 2*k;
      //compare two child nodes to determine
      //which way to sink 
      if (j<N && less(j, j+1)) j++;
      //compare with the larger child
      if (!less(k, j)) break;
      exch(k,j);
      k = j;
    }
  }
  
 
  private boolean less(int i, int j)
  {
    return pq.get(i).compareTo(pq.get(j)) < 0; 
  }
 
  private void exch(int i, int j)
  {
    Key e = pq.get(i);
    pq.set(i, pq.get(j));
    pq.set(j, e);
  }
  
  public static void main(String[] args)
  {
    //int i = (new MaxPQ(5)).getPqSize();
    //System.out.println("pq.size()="+i);
  }
}