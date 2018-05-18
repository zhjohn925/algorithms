/******************************************************************************
 *  Compilation:  javac -d . TwoSumFast.java
 *  Execution:    java  -cp .  edu/princeton/cs/algs4/TwoSumFast input.txt
 *  Dependencies: In.java StdOut.java Stopwatch.java
 *  Data files:   https://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *******************************************************************************/

package edu.princeton.cs.algs4;

import java.util.Arrays;

public class TwoSumFast
{
  public static int count(int[] a) {
    Arrays.sort(a);
    int N = a.length;
    int cnt = 0;
    for (int i=0; i<N; i++) {
      if (BinarySearch.rank(-a[i], a) > i) {
        cnt++;
      }
    }
    return cnt;
  }
  
  public static void main(String[] args)
  {
    int[] a = In.readInts(args[0]);
    Stopwatch timer = new Stopwatch();
    int cnt = count(a);
    StdOut.println("elapsed time = " + timer.elapsedTime());
    StdOut.println(cnt);
  }

}