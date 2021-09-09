/*

    One swap can swap two adjacent characters in the string.
    Given a string and n maximum swaps, return the length of the
    longest continuous character. 

*/

import java.util.*;

public class MaxLengthOfNSwap {

   
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      String str = in.next();
      int n = in.nextInt();
      
      List<Integer>[] charCount = new List[26];
      for (int i = 0; i < 26; i++) {
         charCount[i] = new ArrayList<>();
      }
      for (int i = 0; i < str.length(); i++) {
         charCount[str.charAt(i) - 'a'].add(i);
      }
      
      int res = 0;
      for (int i = 0; i < 26; i++) {
         res = Math.max(res, dpSolve(charCount[i], n));
      }
      
      System.out.println(res);
      System.out.println(solve(str, n));
   }
   
   public static int solve(String str, int n) {
      int i = 0;
      int max = 0;
      while (i < str.length()) {
         int l = i;
         int r = i;
         while (r < str.length() - 1 && 
               str.charAt(r) == str.charAt(r + 1)) {
            r++;
         }
         int nextI = r + 1;
         int res = r - l + 1;
         char c = str.charAt(i);
         List<Integer> leftPossible = new ArrayList<>();
         List<Integer> rightPossible = new ArrayList<>();
         // check possible steps
         int step = 0;
         
         while (step < n && (l > 0 || r < str.length() - 1)) {
            if (l > 0) { // check to the left
               l--;
               if (str.charAt(l) == c) {
                  leftPossible.add(step);
               }
            }
            if (r < str.length() - 1) { //check to the right
               r++;
               if (str.charAt(r) == c) {
                  rightPossible.add(step);
               }
            }
            step++; // increment step
         }
         res += checkPossible(leftPossible, rightPossible, n);
         
         max = Math.max(max, res);
         i = nextI;
      }
      return max;
   }
   
   private static int checkPossible(List<Integer> left, List<Integer> right, int remain) {
      
      int res = 0;
      while (remain > 0 && 
            ((!left.isEmpty() && left.get(0) <= remain) ||
             (!right.isEmpty() && right.get(0) <= remain))) {
         
         res++; // must be some possible switch to make
         
         List<Integer> removingList = right; // default remove from right
         if (!left.isEmpty() && left.get(0) <= remain &&
            !right.isEmpty() && right.get(0) <= remain) { // left AND right has something
            if (left.get(0) <= right.get(0)) { // remove from left, else remove from right;
               removingList = left;
            }
         } else if (!left.isEmpty() && left.get(0) <= remain) {
            removingList = left;
         }
         
         // updating the list and remain
         remain -= removingList.get(0);
         removingList.remove(0);
         for (int i = 0; i < removingList.size(); i++) {
            removingList.set(i, removingList.get(i) - 1);
         }
      }
   
      return res;
      
   }
   
   
   // checking one character at a time using DP.
   // dp[i][j]: swaps needed to move ith to jth character together
   // dp[i][j] = dp[i+1][j-1] + (pos[j] - pos[i] + 1) - (j - i - 1)
   public static int dpSolve(List<Integer> position, int n) {
      int size = position.size();
      int[][] dp = new int[size][size];
      for (int i = 1; i < size; i++) {
         for (int j = 0; j < size - i; j++) {
            int left = j;
            int right = j + i;
            dp[left][right] = dp[left + 1][right - 1] +
                                 position.get(right) - position.get(left) - 1 -
                                 (right - left - 1);
         }
      }
      int max = 0;
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            if (dp[i][j] <= n) {
               max = Math.max(j - i + 1, max);
            }
         }
      }
      return max;
   }
   
}
