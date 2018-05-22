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
  
  //merge sort
  public static <T extends Comparable<T>> void mergeSort(T[] a, T[] aux)
  {
    //Allocate the space one time only
    //Do not use Arrays.copyOf() (creates new array) in the recursive 
    //loop that will cause performance penalty. 
    
    //Compile error: generic array creation 
    //T[] extra = new T[a.length];
    
    recursive4MergeSort(a, aux, 0, a.length-1);
    assert isSorted(a);
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