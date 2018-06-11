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
     
     
- Mergesort (Top-Down):
  1. Combining two ordered arrays to make one larger ordered array. To sort 
     an array, divide it into two halves, sort the two halves (recursively), 
     and then merge the results.   
  2. ~ NlogN  
     
- Mergesort (Bottom-up)
  1. Bottom-up mergesort consists of a sequence of passes over the whole array,
     doing sze-by-sze merges, starting with sze equal to 1 and doubling sze
     on each pass (loop). The final subarray is of size (sze) only when the 
     array size is an even multiple of sze (otherwise it is less than sze).
     
- Quicksort
  1. is a divide-and-conquer method for sorting. It works by partitioning an array
     into two subarrays, then sorting the subarrays independently.
  2. Quicksort is complementary to Mergesort:
     for Mergesort, we break the array into two subarrays to be sorted and then
     combine the ordered subarrays to make the whole ordered array;
     for Quicksort, we rearrange the array such that, when the two subarrays are
     sorted, the whole array is ordered. 
     for Mergesort, the array is divided in half; for Quicksort, the position of
     the partition depends on the contents of the array.
  3. In the first instance, we do the two recursive calls before working on the 
     whole array; in the second instance, we do the two recursive calls after
     working on the whole array.
  4. The crux of the method is the partitioning process, which rearrages the array
     to make the following three conditions hold:
     - The entry a[j] is in its final place in the array, for some j.
     - No entry in a[lo..j-1] is greater than a[j]
     - No entry in a[j+1..hi] is less than a[j]
     We achieve a complete sort by partitioning, then recursively applying the method.
  5. The inner loop of quicksort (in the partitioning method) increments an index and
     compares an array entry against a fixed value. This simplicity is one factor that
     makes quicksort quick.  For example, mergesort and shellsort are typically slower
     than quicksort because they also do data movement within their inner loops.
  6. improved version for quicksort with three partitions, recursive function the 
     middle partition has the same key (v)  
        a[lo..lt-1] < v=a[lt..gt] < a[gt+1, hi]
     This sort code partitions to put keys equals to the partitions element in place 
     and thus does not have to include those keys in the subarrays for the recursive 
     calls. It is good for the amount of keys are same in the data 
  7. Cutoff to insertion sort: (another improvement)
     - Quicksort is slower than insertion sort for tiny subarrays
     - Replace the statement 
      if (hi <= lo) return;   to
      if (hi <= lo + M) { Insertion.sort(a, lo, hi); return; }      
     The optimum value of the cutoff M is system-dependent, but any value between
     5 and 15 is likely to work well in most situations. 
     
     
     
     
     
     
     
  
     
   