/*
  Use DP to check if one string is the scramble of the other
  scramble means changing the swapping the tree view of the string.
 */

import java.util.*;

public class IsScramble {
   Map<String, Boolean> cache87 = new HashMap<>();
    
   public boolean isScramble(String s1, String s2) {
        
      cache87 = new HashMap<>();
      return recurse(s1, s2);
   }
    
   public boolean recurse(String s1, String s2) {
      if (s1.length() != s2.length()) 
         return false;
      if (s1.length() <= 1) 
         return s1.equals(s2);
        
      if (cache87.containsKey(s1 + " " + s2) ||
           cache87.containsKey(s2 + " " + s1)) {
         return cache87.getOrDefault(s1 + " " + s2, false) ||
                cache87.getOrDefault(s2 + " " + s1, false);
      }
        
      int l = s1.length();
      int[] n1 = new int[26];
      int[] n2 = new int[26];
      for (int i = 0; i < l; i++) {
         n1[s1.charAt(i) - 'a']++;
         n2[s2.charAt(i) - 'a']++;
      }
      for (int i = 0; i < 26; i++) {
         if (n1[i] != n2[i]) {
            cache87.put(s1 + " " + s2, false);
            return false;
         }
      }
        
      for (int i = 1; i < l; i++) {
         if (recurse(s1.substring(0, i), s2.substring(0, i)) &&
               recurse(s1.substring(i), s2.substring(i))) {
            cache87.put(s1 + " " + s2, true);
            return true;
         } else if (recurse(s1.substring(0, i), s2.substring(l-i)) &&
                      recurse(s1.substring(i), s2.substring(0, l-i))) {
            cache87.put(s1 + " " + s2, true);
            return true;
         }
      
      }
      return false;
   }
}