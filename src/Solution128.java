import java.util.*;

class Solution128 {
   public int longestConsecutiveInterval(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i : nums) {
         if (map.containsKey(i)) 
            continue;
         if (!map.containsKey(i-1) && !map.containsKey(i+1)) {
            map.put(i, i);
         } else if (map.containsKey(i-1) && !map.containsKey(i+1)) {
            int otherEnd = map.get(i-1);
            if (otherEnd <= i-1) {
               map.put(otherEnd, i);
               map.put(i, otherEnd);
               if (i-1 != otherEnd) {
                  map.remove(i-1);
               }
            }
         } else if (map.containsKey(i+1) && !map.containsKey(i-1)) {
            int otherEnd = map.get(i+1);
            if (otherEnd >= i+1) {
               map.put(otherEnd, i);
               map.put(i, otherEnd);
               if (i+1 != otherEnd) {
                  map.remove(i+1);
               }
            }
         } else {
            int leftOtherEnd = map.get(i-1);
            int rightOtherEnd = map.get(i+1);
            if (leftOtherEnd <= i-1 && rightOtherEnd >= i+1) {
               map.put(leftOtherEnd, rightOtherEnd);
               map.put(rightOtherEnd, leftOtherEnd);
               if (leftOtherEnd != i-1) {
                  map.remove(i-1);
               }
               if (rightOtherEnd != i+1) {
                  map.remove(i+1);
               }
            }
         }
      }
      int max = 0;
      for (int i : map.keySet()) {
         max = Math.max(max, Math.abs(i - map.get(i)) + 1);
      }
      return max;
   }
   
   public int longestConsecutiveSet(int[] num) {
      Set<Integer> set = new HashSet<>();
      for (int i : num) {
         set.add(i);
      }
      int max = 0;
     
      for (int i : set) {
         if (!set.contains(i-1)) {
            int curr = i;
            int streak = 1;
            while (set.contains(curr+1)) {
               curr++;
               streak++;
            }
            max = Math.max(max, streak);
         }
      }
      return max;
   }
}