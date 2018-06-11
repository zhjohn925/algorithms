/****************************************************************************************
 *  Compilation:  javac -d . Sorting.java
 *  Execution:    java -ea -cp .  edu/princeton/cs/algs4/Sorting < tiny.txt
 *       Note:    -ea to enable assertion for assert statement
 *  Data files:   curl -O https://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                curl -O https://algs4.cs.princeton.edu/21elementary/words3.txt
 ****************************************************************************************/


package edu.princeton.cs.algs4;

import java.util.Arrays;

public class Sorting
{
  //Can not declare an array as generic type; there is no way
  //to allocate space for generic type.

  // Selection Sort:
  // array implements Comparable interface 
  public static <T extends Comparable<T>> void selectionSort(T[] a)
  {
    int N = a.length;
    for (int i=0; i<N; i++) {
      int min = i;
      for (int j=i+1; j<N; j++) {
        if (less(a[j], a[min])) {
          min = j;
        }
      }
      exch(a, i, min);
    }
  }
  
  // Insertion sort
  public static <T extends Comparable<T>> void insertionSort(T[] a)
  {
    int N = a.length;
    for (int i=1; i<N; i++) {
      // insert a[i] among a[i-1], a[i-2], a[i-3] ..., a[0]
      // in book:
      //for (int j=i; j>0 && less(a[j], a[j-1]); j--) {
      //  exch(a, j, j-1);
      //}
      for (int j=i; j>0; j--) {
        if (less(a[j], a[j-1])) {
          exch(a, j, j-1);
        } else {
          // no need to continue since the previous
          // elements were sorted
          break;
        }
      }
    }
    //assert isSorted(a);
  }
  
  //Shellsort
  //is a simple extension of insertion sort that gains speed by
  //allowing exchanges of array entries that are far apart, to produce
  //partially sorted arrays that can be efficiently sorted, eventually by 
  //insertion sort.
  public static <T extends Comparable<T>> void shellSort(T[] a)
  {
    int N = a.length;
    int h = 1;
    while (h < N/3) h = 3*h + 1;  //1,4,13,40....
    while (h>=1) {
      for (int i=h; i < N; i++) {
        // in book:
        //for (int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
        //  exch(a, j, j-h);
        //}
        for (int j=i; j>=h; j-=h) {
          if (less(a[j], a[j-h])) {
            exch(a, j, j-h);
          } else {
            // no need to continue since the previous
            // elements were sorted          
            break;
          }
        }        
      }
      h = h/3;
    } 
    //assert isSorted(a);
  }
  
  //Top-Down merge sort
  public static <T extends Comparable<T>> void mergeTDSort(T[] a)
  {
    int N = a.length;
    //Allocate the space one time only, but here also do unnecessary copy 
    //Do not use Arrays.copyOf() (creates new array) in the recursive 
    //loop that will cause performance penalty. 
    T[] aux = Arrays.copyOf(a, N);
    //Compile error: generic array creation 
    //T[] extra = new T[a.length];
    
    recursive4MergeSort(a, aux, 0, N-1);
    assert isSorted(a);
  }
  
  //Bottom-Up merge sort
  public static <T extends Comparable<T>> void mergeBUSort(T[] a)
  {
    int mid, hi, N = a.length;
    //Allocate the space one time only, but here also do unnecessary copy 
    T[] aux = Arrays.copyOf(a, N);
    //Compile error: generic array creation 
    //T[] extra = new T[a.length];
    
    for (int size = 1; size<N; size = size+size) {
      for (int lo=0; lo<N-size; lo+=(size+size)) {
        mid = lo+size-1;
        hi = Math.min(lo+size+size-1, N-1);
        merge(a, aux, lo, mid, hi);
      }
    }
    
    assert isSorted(a);
  }
    
  //Quicksort with two partitions
  public static <T extends Comparable<T>> void quickSort(T[] a)
  {
    int N = a.length;
    qsort(a, 0, N-1);
    assert isSorted(a);
  }
  
 
  //Quicksort with three partitions
  public static <T extends Comparable<T>> void quickSort3(T[] a)
  {
    int N = a.length;
    qsort3(a, 0, N-1);
    assert isSorted(a);
  }
  
  //for quicksort with two partitions, recursive function
  //there is j so that  a[lo..j-1]<=a[j]<=a[j+1..hi]
  private static <T extends Comparable<T>> void qsort(T[] a, int lo, int hi)
  {
    if (hi <= lo) return;    
    int j = partition(a, lo, hi);
    qsort(a, lo, j-1);   //sort left part
    qsort(a, j+1, hi);   //sort right part
  }
  
  
  //improved version for quicksort with three partitions, recursive function
  //the middle partition has the same key (v)
  // a[lo..lt-1] < v=a[lt..gt] < a[gt+1, hi]
  //This sort code partitions to put keys equals to the partitions element in
  //place and thus does not have to include those keys in the subarrays for 
  //the recursive calls.
  private static <T extends Comparable<T>> void qsort3(T[] a, int lo, int hi)
  {
    if (hi <= lo) return;
    int lt = lo, i=lo+1, gt=hi;
    T v = a[lo];
    while (i<=gt) {
      int cmp = a[i].compareTo(v);
      if      (cmp<0) exch(a, lt++, i++);
      else if (cmp>0) exch(a, i, gt--);
      else            i++;  //when equal, no exch 
    }
    // Now a[lo..lt-1] < v=a[lt..gt] < a[gt+1..hi]
    // a[lt..gt] is not in part of recursive
    qsort3(a, lo, lt-1);
    qsort3(a, gt+1, hi);
  }
  
  
  // for merge sort:
  private static <T extends Comparable<T>> 
  void recursive4MergeSort(T[] a, T[] aux, int lo, int hi)
  {
    if (hi <= lo) return;
    int mid = lo + (hi-lo)/2;
    //sort the first half
    recursive4MergeSort(a, aux, lo, mid);
    //sort the second half
    recursive4MergeSort(a, aux, mid+1, hi);
    //merge two sorted halves
    merge(a, aux, lo, mid, hi);
  }
  
  
  // for merge sort:
  // a[] has two sorted halves
  // To merge two sorted halves into one array for merge sort
  public static <T extends Comparable<T>> 
  void merge (T[] a, T[] aux, int lo, int mid, int hi)
  {
    int first_half_index  = lo;         //first half
    int second_half_index = mid+1;      //second half
     
    // copy a[lo..hi] to aux
    for (int i=lo; i<(hi+1); i++) {
      aux[i] = a[i];
    }
    
    //Do not use copyOfRange() which create new array causing performance penalty
    //T[] aux = Arrays.copyOfRange(a, lo, hi+1);
     
    for (int i=lo; i<(hi+1); i++) {      
      if (first_half_index>mid) {      
        //the first half is exhausted, get entry from the second half 
        //compile warning: [unchecked] unchecked cast
        a[i] = aux[second_half_index++];
      } else if (second_half_index>hi) {
        //the second half is exhausted, get entry from the first half
        //compile warning: [unchecked] unchecked cast
        a[i] = aux[first_half_index++];
      } else if (less(aux[first_half_index], aux[second_half_index])) {
        //the first half is smaller, copy entry from the first half
        //compile warning: [unchecked] unchecked cast
        a[i] = aux[first_half_index++];
      } else {
        //the second half is smaller, copy entry from the second half
        //compile warning: [unchecked] unchecked cast
        a[i] = aux[second_half_index++]; 
      }
    }    
  }
  
  //For quicksort
  private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi)
  {
    int toRight = lo, toLeft = hi+1;
    T v = a[lo];
    while (true) {
      //Scan to the right, find entry is larger than v
      while (less(a[++toRight], v))   if (toRight==hi) break;
      //Scan to the left, find entry is smaller than v
      while (less(v, a[--toLeft]))  if (toLeft==lo) break;
      //Stop when both are intersecting
      if (toRight>=toLeft) break;
      //switch the smaller to left, the larger to the right
      exch(a, toRight, toLeft);
    }
    
    //Move v into the partition point (index = toLeft), now
    //with a[lo..toLeft-1] <= a[toLeft]<=a[toLeft+1..hi]
    exch(a, lo, toLeft);
   
    return toLeft;
  }
  

  // check if the elements are in incremental order 
  public static <T extends Comparable<T>> boolean isSorted(T[] a) {
    for (int i=1; i<a.length; i++) {
      if (less(a[i], a[i-1])) return false;
    }
    return true;
  }
  
  private static <T extends Comparable<T>> boolean less(T v, T w) {
    return (v.compareTo(w) < 0);
  }
  
  // switch the element i and j in the array
  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j]; a[j] = t; 
  }
  
  // display the array
  private static <T extends Comparable<T>> void show (T[] a) {
    for (int i=0; i<a.length; i++) {
      StdOut.print(a[i]+" ");
    }
    StdOut.println();
  }
  
  public static void main(String[] args) 
  {
    //Compile warning: uses or overrides a deprecated API.
    String[] a = In.readStrings();
    
    //This creates new array. Do not use this in the recursive
    //loop (will cause performance penalty 
    String[] b = Arrays.copyOf(a, a.length);
    
    selectionSort(a);
    // assertion is disabled by default. use -ea to enable
    // assertion in run time0
    assert isSorted(a);
    show(a); 
        
    insertionSort(b);
    assert isSorted(b);
    show(b); 
  }
}