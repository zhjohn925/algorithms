//ThreeSumFast.java

- Fast algorithm for 2-sum
  Q: to find the pairs of a[i]+a[j]=0 in an array (size of N)
  A: - Slow algorithm by using tow for-loop.
       Order of growth of running time: N^2. 
  B: - Fast algorithm by sorting the array and then, for every entry a[i]
       in the array, do a binary search for -a[i] with rank() in 
       BinarySearch. If the result is an index j with j>i, we increment
       the count. 
       Order of growth of running time: NlogN.

- Fast algorithm for 3-sum
  Q: to find the triple a[i]+a[j]+a[k]=0 in an array (size of N)
  A: - Slow algorithm by using three for-loop.
       Order of growth of running time: N^3.
  B: A pair a[i] and a[j] is part of a triple that sums to 0 if and 
     only if the value -(a[i]+a[j]) is in the array (and not a[i] or a[j]).
     Order of growth of running time: N^2LogN.
  