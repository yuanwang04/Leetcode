/*
   Travelling Salesman Problem (TSP):
   Given a set of points and their distances to each other,
   calculate the shortest path to travel all points.
   
   Solved with DP:
       i, V, s, C:
       from node i, travel all nodes in V, back to s.
       c[a,b]: cost of a to b
       dp[i, V] = {
                   V empty: C[i, s];
                   else: min(C[i, k] + dp[k, V-k]), k in V
                  }
*/
import java.util.*;
import java.io.*;

public class TSP {
   
   public static Map<String, Integer> cache = new HashMap<>();
   
   public static void main(String[] args) throws FileNotFoundException {
      Scanner in = new Scanner(new File("input.txt"));
      int n = in.nextInt();
      int[][] cost = new int[n][n];
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            cost[i][j] = in.nextInt();
         }
         list.add(i);
      }
      list.remove(0);
      int res = solve(cost, 0, list);
      System.out.println(res);
   }
   
   public static int solve(int[][] cost, int i, List<Integer> v) {
      String key = i + ":" + v.toString();
      if (cache.containsKey(key)) 
         return cache.get(key);
      int res = 0;
      if (v.isEmpty()) {
         cache.put(key, cost[0][i]);
         return cost[0][i];
      } else {
         int min = Integer.MAX_VALUE;
         for (int index = 0; index < v.size(); index++) {
            int k = v.get(index);
            v.remove(index);
            min = Math.min(min, cost[i][k] + solve(cost, k, v));
            v.add(index, k);
         }
         cache.put(key, min);
         return min;
      }
          
   }

}