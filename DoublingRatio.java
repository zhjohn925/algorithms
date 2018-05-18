/****************************************************************************************
 *  Compilation:  javac -d . DoublingRatio.java
 *  Execution:    java  -cp .  edu/princeton/cs/algs4/DoublingRatio
 * - Doubling ratio experiments:
 *   1. This code is a simple and effective shortcut for predicting performance, and
 *   for determining the approximate order of growth of the running time of any 
 *   program.
 *   2. Run until the ratios approach a limit 2^b  (ie.  8 for b=3)
 *   3. This test is not effective if the ratios do not approach a limiting value,
 *   but they do for many, many programs, implying the following conclusions:
 *      - The order of growth of the running time is approximately N^b
 *   4. I got the below results:
 *      reaching the ratio limit of 8 (2^3), this can concludes ThreeSum aligorithm
 *      has order of growth N^3. and also predicts time = 213.8 * 8 when N=32000 and
 *      so on. 
 *      $ java  -cp .  edu/princeton/cs/algs4/DoublingRatio 
 *             250     0.0   1.3
 *             500     0.0   5.6
 *            1000     0.1   2.1
 *            2000     0.4   7.1
 *            4000     3.3   7.8
 *            8000    25.1   7.7
 *           16000   213.8   8.5
 * - Why does the ratio approach a constant ?
 *   Proposition C. (Doubling ratio) If T(N) ~ a(N^b)lgN then the ratio T(2N)/T(N) ~ 2^b
 *   Proof:  T(2N)/T(N) = a((2N)^b)lg(2N)/a(N^b)lgN
 *                      = 2^b(1 + lg2/lgN) 
 *                      ~ 2^b
 ****************************************************************************************/

package edu.princeton.cs.algs4;

public class DoublingRatio 
{
  public static double timeTrial(int N)
  {
    int MAX = 1000000;
    int[] a = new int[N];
    for (int i=0; i<N; i++) {
      a[i] = StdRandom.uniform(-MAX, MAX);
    } 
    Stopwatch timer = new Stopwatch();
    //Apply algorithm in ThreeSum.java
    int cnt = ThreeSum.count(a);
    return timer.elapsedTime();
  }
  
  public static void main(String[] args)
  {
    double prev = timeTrial(125);
    for (int N=250; true; N += N) {
      double time = timeTrial(N);
      StdOut.printf("%6d %7.1f ", N, time);
      StdOut.printf("%5.1f\n", time/prev);
      prev = time;
    }
  }
}