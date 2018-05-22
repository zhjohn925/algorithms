- Selection sort
  1. First, find the smallest item in the array and exchange it with the first entry.
     Then, find the next smallest item and exchange it with the second entry.
     Continue in this way until the entire array is sorted. 
     This method is called selection sort because it works by repeatedly selecting
     the smallest remaining item.
  2. Selection sort uses ~(N^2)/2 compares and N exchanges to sort an array 
     of length N.
     
     
- Insertion sort
  1. Make space to insert the current item by moving large items one position 
     to the right, before inserting the current item into the vacated position.
  2. Insertion sort uses ~(N^2)/4 compares and ~(N^2)/4 exchanges to sort
     a randomly ordered array of length N.  The worst case is ~(N^2)/2 compares 
     and ~(N^2)/2 exchanges (ie. the array with inverted order); the best case
     is N-1 compares and 0 exchanges (ie. the array is already sorted)
  3. Insertion sort is an efficient method for a partially sorted array; selection
     is not.  Partially sorted:
     - An array where each entry is not far from its final position
     - A small array appended to a large sorted array
     - An array with only a few entries that are not in place
  4. It is not difficult to speed up insertion sort substantially, by shortening
     its inner loop to move the larger entries to the right one position rather
     than doing full exchanges (thus cutting the number of array access in half)
     

- Shellsort
  1. is a simple extension of insertion sort that gains speed by
     allowing exchanges of array entries that are far apart, to produce
     partially sorted arrays that can be efficiently sorted, eventually by 
     insertion sort.
  2. The idea is to rearrange the array to give it the property that taking
     every h-th entry (starting anywhere) yields a sorted subsequence. 
     Such an array is said to be h-sorted. 
     Put another way, an h-sorted array is h independent sorted subsequences,
     interleaved together. By h-sorting for some large values of h, we can
     move items in the array long distances and thus make it easier to h-sort
     for smaller values of h.
     Using such a procedure for any sequence of values of h that ends in 1 will 
     produce a sorted array: that is shellsort.
     
     
     
   