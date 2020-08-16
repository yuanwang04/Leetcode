/*
    Compute the length of the largest triangle in the given 
    map of hexagon. 
*/

import java.util.*;
   

public class LargestTriangleInHex {

   public static void main(String[] args) {
   
      LargestTriangleInHex test = new LargestTriangleInHex();
      int res = test.largestSubTriangle(4, 
                             new int[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0});
      System.out.println(res);
   }

   public int largestSubTriangle (int a, int[] maps) {
      // write code here
      List<Integer>[] myMap = createMap(a, maps);
      int res = findDownward(myMap, a);
      myMap = reverseMap(myMap, a);
      res = Math.max(res, findDownward(myMap, a));
      
      return res;
   }
   
   private List<Integer>[] reverseMap(List<Integer>[] map, int a) {
      for (int i = 0; i < a; i++) {
         List<Integer> temp = map[i];
         map[i] = map[2 * a - 1 - i];
         map[2 * a - 1 - i] = temp;
      }
      return map;
   }
   
   private int findDownward(List<Integer>[] map, int a) {
      int max = 0;
      List<Integer>[] dp = new List[map.length];
      //first row
      dp[0] = new ArrayList<>();
      for (int i = 0; i < a; i++) {
         int val = map[0].get(i * 2 + 1);
         max = Math.max(max, val);
         dp[0].add(val);
      }
      // to mid-row
      for (int i = 1; i < a; i++) {
         List<Integer> currMapRow = map[i];
         dp[i] = new ArrayList<>();
         dp[i].add(map[i].get(1));
         for (int j = 1; j < a + i - 1; j++) {
            int leftUp = dp[i - 1].get(j - 1);
            int rightUp = dp[i - 1].get(j);
            int up = map[i - 1].get(j * 2);
            int curr = map[i].get(j * 2 + 1);
            int val = curr * (1 + up * (Math.min(leftUp, rightUp)));
            max = Math.max(max, val);
            dp[i].add(val);
         }
         dp[i].add(map[i - 1].get(map[i - 1].size() - 1));
      }
      // first low below mid
      dp[a] = new ArrayList<>();
      dp[a].add(map[a].get(0));
      for (int i = 1; i < a * 2 - 1; i++) {
         int leftUp = dp[a - 1].get(i - 1);
         int rightUp = dp[a - 1].get(i);
         int up = map[a - 1].get(i * 2);
         int curr = map[a].get(i * 2);
         int val = curr * (1 + up * (Math.min(leftUp, rightUp)));
         max = Math.max(max, val);
         dp[a].add(val);
      }
      dp[a].add(map[a].get(map[a].size() - 1));
      // rows below mid
      for (int i = 1; i < a; i++) {
         dp[a + i] = new ArrayList<>();
         for (int j = 0; j < a * 2 - i; j++) {
            int leftUp = dp[a + i - 1].get(j);
            int rightUp = dp[a + i - 1].get(j + 1);
            int up = map[a + i - 1].get(j * 2 + 1);
            int curr = map[a + i].get(j * 2);
            int val = curr * (1 + up * (Math.min(leftUp, rightUp)));
            max = Math.max(max, val);
            dp[a + i].add(val);
         }
      }
      
      return max;
      
   }
   
   private List<Integer>[] createMap(int a, int[] maps) {
      List<Integer>[] myMap = new List[a * 2];
      int pointer = 0;
      // create upper
      for (int i = 0; i < a; i++) {
         myMap[i] = new ArrayList<>();
         for (int j = 0; j < (a + i) * 2 + 1; j++) {
            myMap[i].add(maps[pointer]);
            pointer++;
         }
      }
      for (int i = 0; i < a; i++) {
         myMap[a + i] = new ArrayList<>();
         for (int j = 0; j < a * 4 - 1 - i * 2; j++) {
            myMap[a + i].add(maps[pointer]);
            pointer++;
         }
      }
      return myMap;
   }
}