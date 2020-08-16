/*

    Given an array of number, define the result of an interval as:
        the minimum in the interval * the sum of the interval;
    Return the largest result of the array.
    
    Solved by monotonically increase stack.

*/

import java.util.*;

public class LargestProductInterval {

   public static void main(String[] args) {
   
      int[] n = new int[]{3, 1, 6, 4, 5, 2};
      System.out.println(largestProductInterval(n));
   }
   
   public static int largestProductInterval(int[] n) {
      if (n.length == 0) 
         return 0;
      Stack<Integer> s = new Stack<>();
      int[] ns = new int[n.length];
      ns[0] = n[0];
      for (int i = 1; i < n.length; i++) {
         ns[i] = ns[i - 1] + n[i];
      }
      int max = 0;
      
      for (int i = 0; i < n.length; i++) {
         if (s.isEmpty() || n[i] >= n[s.peek()]) {
            s.push(i);
         } else {
            int last = 0;
            while (!s.isEmpty() &&
                  n[i] < n[s.peek()]) {
               last = s.pop();
               
               int sum = ns[i - 1];
               if (last != 0) {
                  sum -= ns[last - 1];
               }
               max = Math.max(max, sum * n[last]);
            }
            s.push(last);
            n[last] = n[i];
         
         }
      }
      return max;
   }

}
