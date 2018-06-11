/****************************************************************************************
 *-Compare different Sorting algorithms
 * Compilation:javac -d . SortCompare.java
 * Execution: java -cp . edu/princeton/cs/algs4/SortCompare Insertion Selection 1000 100
 *            java -cp . edu/princeton/cs/algs4/SortCompare Insertion Shell 1000 100 
 *            java -ea -cp . edu/princeton/cs/algs4/SortCompare Insertion MergeTD 10000 10
 *            java -ea -cp . edu/princeton/cs/algs4/SortCompare Insertion Quick 10000 10
 *            java -ea -cp . edu/princeton/cs/algs4/SortCompare MergeBU Quick 10000 10
 *            java -ea -cp . edu/princeton/cs/algs4/SortCompare Shell Quick 100000 10
 *            add -ea option to enable assertion ie. assert isSorted(a)
 ****************************************************************************************/


package edu.princeton.cs.algs4;

public class SortCompare
{
  //return time of sorting a[] with the given algorithm
  public static double time(String algorithm, Double[] a) {
    
    Stopwatch timer = new Stopwatch();
    if (algorithm.equals("Insertion")) {
      Sorting.insertionSort(a);
    } else if (algorithm.equals("Selection")) {
      Sorting.selectionSort(a);
    } else if (algorithm.equals("Shell")) {
      Sorting.shellSort(a);
    } else if (algorithm.equals("MergeTD")) {
      Sorting.mergeTDSort(a);   
    } else if (algorithm.equals("MergeBU")) {
      Sorting.mergeBUSort(a);   
    } else if (algorithm.equals("Quick")) {
      Sorting.quickSort(a); 
    } else if (algorithm.equals("Quick3")) {
      Sorting.quickSort3(a);                      
    }
    return timer.elapsedTime();
  }
  
  //T is number of random arrays (each has size of N)
  public static double timeRandomInput(String algorithm, int N, int T) {
    double total = 0.0;
    Double[] a = new Double[N];
    for (int t=0; t<T; t++) {
      for (int i=0; i<N; i++) {
        a[i] = StdRandom.uniform();
      }
      StdRandom.shuffle(a);
      total += time(algorithm, a);
    }
    return total;
  }
  
  public static void main(String[] args)
  {
    String algorithm1 = args[0];
    String algorithm2 = args[1];
    int N = Integer.parseInt(args[2]);
    int T = Integer.parseInt(args[3]);
    double t1 = timeRandomInput(algorithm1, N, T);
    double t2 = timeRandomInput(algorithm2, N, T);
    StdOut.printf("For %d random doubles\n %s is", N, algorithm1);
    StdOut.printf(" %.3f times faster than %s\n", t2/t1, algorithm2);
    StdOut.printf(" %s = %.3f, %s = %.3f \n", algorithm1, t1, algorithm2, t2);   
  }
}