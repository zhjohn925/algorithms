- Shellsort:
  
  Shellsort is a simple extension of insertion sort that gains speed by allowing
  exchanges of array entries that are far apart, to produce partially sorted
  arrays that can be efficiently sorted, eventually by insertion sort.
  
  The idea is to rearrange the array to give it the property that taking every 
  h-th entry yields a sorted subsequence. Such an array is said to be h-sorted.
  
  An h-sorted array is h independent sorted subsequences, interleaved together.
  By h-sorting for some large values of h, we can move items in the array long
  distances and thus make it easier to h-sort for smaller values of h. Using such
  a procedure for any sequence of values of h that ends in 1 will produce a sorted
  array: that is shellsort.
  
   
 - Example:  h = 4 ,  apply insertion sort in 4 independent subsequence
 
   L  E  E  A  M  H  L  E  P  S  O  L  T  S  X  R
   L --------- M --------- P --------- T
      E --------- H --------- S --------- S
         E --------- L --------- O --------- X
            A --------- E --------- L --------- R
            
  
 - JAVA
 
 public class Shell {
    public static void sort (Comparable[] a) 
    {
       int N = a.length;
       int h = 1;
       while (h<N/3) h = 3*h + 1;   //1, 4, 13, 40, 121, 364, 1093, ....
       while (h>=1) {
          //implement insertion sort
          for (int i=h; i<N; i++) {
             for (int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
                exch(a, j, j-h);
             }            
          } 
          h = h/3;        
       }
    }
 }      
       
            
            
 
     