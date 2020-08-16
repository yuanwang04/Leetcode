/*
Arrange n numbers in lexiographically, 
output the mth number. 
*/

import java.io.*;
import java.util.*;

public class NArrangeM {
   
   static int cnt = 0;
   static int res = 0;
   static int n = 0, m = 0;
   
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      n = in.nextInt();
      m = in.nextInt();
      System.out.println(solve(n, m));
   }
   
   public static int solve(int n, int m) {
      for (int i = 1; i <= 9; i++) {
         backtrace(i);
      }
      return res;
   }
   
   public static void backtrace(int curr) {
      if (curr <= n && cnt < m) {
         res = curr;
         cnt++;
         for (int i = 0; i <= 9; i++) {
            backtrace(curr * 10 + i);
         }
      }
   }
}