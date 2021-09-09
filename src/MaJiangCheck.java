/*

Checks if the current hand can win in Ma Jiang.

*/

import java.util.*;
import java.io.*;

public class MaJiangCheck {
   
   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(new File("input.txt"));
      int[] cards = new int[9];
      for (int i = 0; i < 13; i++) {
         int next = in.nextInt();
         cards[next - 1] ++;
      }
      String res = "";
      for (int i = 0; i < 9; i++) {
         cards[i] ++;
         if (tryPair(cards)) {
            res += " " + (i + 1);
         }
         cards[i] --;
      }
         
      if (res.isEmpty()) {
         System.out.println(0);
      } else {
         System.out.println(res.substring(1));
      }
   }
   
   public static boolean tryPair(int[] cards) {
      for (int i = 0; i < 9; i++) {
         if (cards[i] >= 2) {
            cards[i] -= 2;
            if (tryThree(cards, 12)) {
               cards[i] += 2;
               return true;
            } else {
               cards[i] += 2;
            }
            
         }
      }
      return false;
   }
   
   public static boolean tryThree(int[] cards, int remain) {
      if (remain == 0) 
         return true;
      
      // try 3 same first
      for (int i = 0; i < 9; i++) {
         if (cards[i] >= 3) {
            cards[i] -= 3;
            if (tryThree(cards, remain - 3)) {
               cards[i] += 3;
               return true;
            } else {
               cards[i] += 3;
            }
         }
      }
      
      // try continue of 3
      for (int i = 0; i < 7; i++) {
         if (cards[i] > 0 && cards[i + 1] > 0 && cards[i + 2] > 0) {
            cards[i]--;
            cards[i + 1]--;
            cards[i + 2]--;
            if (tryThree(cards, remain - 3)) {
               cards[i] ++;
               cards[i + 1] ++;
               cards[i + 2] ++;
               return true;
            } else {
               cards[i] ++;
               cards[i + 1] ++;
               cards[i + 2] ++;
            }
         }
      }
      
      return false;
   }
}