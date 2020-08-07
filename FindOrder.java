/*
    Given a number of courses, an array of prerequisites
    (as the first number needs be taken before the second),
    return an array of order to take all courses.
    Return empty array if impossible.
    
    Solved by finding the topological order.
*/
    
import java.util.*;

public class FindOrder {
   public int[] findOrder(int numCourses, int[][] prerequisites) {
      boolean[] seen = new boolean[numCourses];
      int[] result = new int[numCourses];
      int n = 0;
      Map<Integer, List<Integer>> edgeFrom = new HashMap<>();
        
      for (int i = 0; i < prerequisites.length; i++) {
         edgeFrom.putIfAbsent(prerequisites[i][0], new ArrayList<>());
         edgeFrom.get(prerequisites[i][0]).add(prerequisites[i][1]);
      }
        
      for (int i = 0; i < numCourses; i++) {
         if (!seen[i]) {
            n = dfs(i, seen, edgeFrom, result, n);
         }
      }
        
      return result;
   }
    
   public int dfs(int i, boolean[] seen, Map<Integer, List<Integer>> edgeFrom, int[] result, int n) {
      if (!seen[i]) {
         seen[i] = true;
         List<Integer> next = edgeFrom.getOrDefault(i, null);
         if (next != null) {
            for (int course : next) {
               n = dfs(course, seen, edgeFrom, result, n);
            }
         }
         result[n] = i;
         n++;
      } 
      return n;
   }

}