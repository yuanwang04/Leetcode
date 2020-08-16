/*
Given an array of integer representing heights of bars,
calculates the largest area of possible rectangle formed by bars.

Solved using monotonically increasing stack,
change the height of previously removed bar to maintain the order.
Time complexity: O(N), space: O(N).
*/

import java.util.*;

public class LargestRectangle {

   public static void main(String[] args) {
      int[] n = new int[]{0, 2, 0};
      System.out.println(largestRectangleArea(n));
   }
   
   public static int largestRectangleArea(int[] heights) {
      if (heights.length == 0) 
         return 0;
      
      Stack<Integer> stack = new Stack<>();
      int max = heights[0];
      
      for (int i = 0; i < heights.length; i++) {
         if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
            stack.push(i);
         } else {
            int last = 0;
            while (!stack.isEmpty() &&
                  heights[stack.peek()] > heights[i]) {
               last = stack.pop();
               int h = heights[last];
               int w = i - last;
               max = Math.max(h * w, max);
            }
             // push back the last removed index, change the height
             // to current height
            stack.push(last);
            heights[last] = heights[i];
         }
      }
      
      while (!stack.isEmpty()) {
         int index =stack.pop();
         int h = heights[index];
         int w = heights.length - index;
         max = Math.max(h * w, max);
      }
      
      return max;
   }

}