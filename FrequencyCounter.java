/**********************************************************************************
* Compilation:javac -d . FrequencyCounter.java
* Execution:  java -cp . edu/princeton/cs/algs4/FrequencyCounter 8 < tale.txt
***********************************************************************************/

package edu.princeton.cs.algs4;

public class FrequencyCounter
{
   public static void main(String[] args) 
   {
      int minlen = Integer.parseInt(args[0]);
      
      //<Key, Value> - <String, Integer> - Value as number of occurrence 
      SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
      
      while (!StdIn.isEmpty()) {
         String word = StdIn.readString();
         if (word.length() < minlen) continue;   //ignore the short keys
         if (!st.contains(word)) {
            st.put(word, 1);
         } else {
            st.put(word, st.get(word)+1);
         }   
      }
      
      //find a key with the highest frequency count
      String max = "";
      st.put(max, 0);
      for (String word: st.keys()) {
         if (st.get(word) > st.get(max)) {
            max = word;
         }
      }
      
      StdOut.println(max+" "+st.get(max));
   }
}