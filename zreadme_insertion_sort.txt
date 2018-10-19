- Insertion Sort
  Idea: Save the current item, and move larger items on its left to the right. 
        Then insert the current item to the vacated position.
        
  Demo:    32, 36, 18, 56, 21
  
  step 0:      36,                //start with item 1, item 1 as current item  
           32,     18, 56, 21     //hole in item 1 position 
  step 1:  32, 36, 18, 56, 21     //insert (36) to the same hole (index 1)
  
  step 2:          18,            //item 2 as current item
           32, 36,     56, 21     //hole in item 2 position
  step 3:          18,
               32, 36, 56, 21     //move larger items(32,36) to the right
  step 4:  18, 32, 36, 56, 21     //insert current item to the vacated position (index 0)
  
  step 5:              56,        //item 3 as current item
           18, 32, 36,     21     //hole in item 3 position 
  step 6:  18, 32, 36, 56, 21     //insert (56) to the same hole
  
  step 7:                  21     //item 4 as current item
           18, 32, 36, 56,      
           18,     32, 36, 56     //move larger items(32,36,56) to the right
  step 8:  18, 21, 32, 36, 56     //insert current item(21) to the vacated position(index 1)            
                          
        
  https://www.khanacademy.org/computing/computer-science/algorithms/insertion-sort/a/insertion-sort      

- Unlike that of selection sort, the running time of insertion sort depends 
  on the initial order of the items in the input. 
  For example, if the array is large and its entries are already in order (or nearly
  in order), then insertion sort is much faster.

- Insertion sort uses ~ (N^2)/4 compares and ~ (N^2)/4 exchanges to sort a randomly
  ordered array of length N with distinct keys, on the average.
  
  The worst case is ~ (N^2)/2 compares and ~ (N^2)/2 exchanges, and the best 
  case is N-1 compares and 0 exchanges. 
  
- Code:
  
  //Java 
  //jz prefers to python implementation in below
  public class Insertion
  {
     public static void sort(Comparable[] a) 
     {
        int N = a.length;
        for (int i = 1; i < N; i++) {
           //jz thinks it may save some loops (compare) if 
           //using while loop  
           for (int j=i; j>0 && less(a[j], a[j-1]); j--) {
              exch(a, j, j-1);
           }
        } 
     }
  }
  
  //Python
  //The below implemented in python looks more meaningful to insertion sort 
  def insertionSort(alist):
     for index in range(1, len(alist)):
        #working the current item
        #its position as hole (by saving the current itme value) 
        currentValue = alist[index]
        position = index
        while position>0 and alist[position-1]>currentValue:
           #move larger items than the current value to the right
           alist[position] = alist[position-1]
           position = position-1
        #now locate the hole, and put current item into the hole    
        alist[postion] = currentValue
        
  alist = [54, 26, 93, 17, 77, 31, 44, 55, 20]
  insertionSort(alist)
  print(alist)
  


  