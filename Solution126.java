import java.util.*;

public class Solution126 {
   Map<String, List<String>> neighbors = new HashMap<>();
   Map<String, Integer> distance = new HashMap<>();
   Map<String, List<String>> prev = new HashMap<>();
   List<List<String>> result = new ArrayList<>();
   
   public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      
      // build neighbors, distance, explore queue
      for (String key : wordList) {
         List<String> linked = new ArrayList<>();
         neighbors.put(key, linked);
         for (String str2 : wordList) {
            if (countDifference(key, str2) == 1) {
               linked.add(str2);
            }
         }
         distance.put(key, Integer.MAX_VALUE);
         prev.put(key, new ArrayList<>());
      }
      neighbors.put(endWord, new ArrayList<>());
      
      
      if (!distance.containsKey(endWord)) {
         return result;
      }
      
      // build queue
      Queue<String> q = new LinkedList<>();
      for (String str : wordList) {
         if (countDifference(beginWord, str) == 1) {
            q.add(str);
            distance.put(str, 1);
            prev.get(str).add(beginWord);
         }
      }
      
      // search
      while (!q.isEmpty()) {
         String curr = q.remove();
         int currDis = distance.get(curr);
         int leastRemain = countDifference(curr, endWord);
         if (currDis + leastRemain > distance.get(endWord)) {
            continue;
         }
         List<String> next = neighbors.get(curr);
         for (String nextStr : next) {
            if (distance.get(nextStr) == Integer.MAX_VALUE) {
               q.add(nextStr);
            }
            if (currDis + 1 < distance.get(nextStr)) {
               prev.put(nextStr, new ArrayList<>());
               prev.get(nextStr).add(curr);
               distance.put(nextStr, currDis + 1);
            } else if (currDis + 1 == distance.get(nextStr)) {
               prev.get(nextStr).add(curr);
            }
         }
      }
      
      // backtrace
      
      List<String> current = new LinkedList<>();
      current.add(endWord);
      backtraceTransformation(current, endWord, beginWord);
      
      return result;
   }
   
   public int countDifference(String str1, String str2) {
      if (str1.length() != str2.length()) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            count++;
         }
      }
      return count;
   }
   
   public void backtraceTransformation(List<String> curr, 
                                       String word, String beginWord) {
      if (word.equals(beginWord)) {
         result.add(new ArrayList<>(curr));
      } else {
         List<String> prevWords = prev.get(word);
         for (String str : prevWords) {
            curr.add(0, str);
            backtraceTransformation(curr, str, beginWord);
            curr.remove(0);
         }
      }
   }
   
   public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      Queue<String> explore = new LinkedList<>();
      Map<String, Integer> dist = new HashMap<>();
      for (String str : wordList) {
         if (countDifference(beginWord, str) == 1) {
            explore.add(str);
            dist.put(str, 1);
         } else {
            dist.put(str, Integer.MAX_VALUE);
         }
      }
        
      if (!dist.containsKey(endWord)) 
         return 0;
        
      boolean found = false;
        
      while (!explore.isEmpty() && !found) {
         String curr = explore.remove();
         int distance = dist.get(curr);
         if (distance >= dist.get(endWord)) {
            break;
         }
         for (String str : wordList) {
            if (countDifference(curr, str) == 1) {
               if (str.equals(endWord)) {
                  found = true;
                  dist.put(endWord, distance + 1);
                  break;
               } else if (dist.get(str) > distance + 1) {
                  dist.put(str, distance + 1);
                  explore.add(str);
               }
            }
         }
      }
        
      return dist.get(endWord);
    
   }
}