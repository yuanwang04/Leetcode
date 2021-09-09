import java.util.*;

class IsMatchDP2 {  
 
   public static boolean isMatch(String s, String p) {
      List<String> token = new ArrayList<>();
      for (int i = 0; i < p.length(); i++) {
         if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
            token.add(p.substring(i, i + 2));
            i += 1;
         } else {
            token.add(p.substring(i, i+1));
         }
      }
      boolean[][] dp = new boolean[s.length() + 1][token.size() + 1];
      dp[0][0] = true;
      for (int pIDX = 1; pIDX < token.size() + 1; pIDX++) {
         // case1 p[:-1] match s[:] && p[-1] = x*
            boolean c1 = dp[0][pIDX-1] && token.get(pIDX - 1).length() == 2;
            dp[0][pIDX] = c1;
      }
      
      for (int sIDX = 1; sIDX < s.length() + 1; sIDX++) {
         for (int pIDX = 0; pIDX < token.size() + 1; pIDX++) {
            // case1 p[:-1] match s[:] && p[-1] = x*
            boolean c1 = pIDX - 1 >= 0 
               && dp[sIDX][pIDX-1] && token.get(pIDX - 1).length() == 2;
            // case2 p match s[:-1] && s[-1] match p[-1]
            boolean c2 = sIDX - 1 >= 0 && pIDX - 1 >= 0
               && dp[sIDX-1][pIDX]
               && (token.get(pIDX - 1).length() == 2)
               && (token.get(pIDX - 1).charAt(0) == s.charAt(sIDX - 1)
               || token.get(pIDX - 1).charAt(0) == '.');
            // case 3 p[:-1] match s[:-1]
            boolean c3 = sIDX - 1 >= 0 && pIDX - 1 >= 0
               && dp[sIDX - 1][pIDX - 1]
               && (token.get(pIDX - 1).charAt(0) == s.charAt(sIDX - 1)
                  || token.get(pIDX - 1).charAt(0) == '.');
            dp[sIDX][pIDX] = c1 || c2 || c3;
         }
      }
      
      return dp[s.length()][token.size()];
   }
   
   public static void main(String[] args) {
      System.out.println(isMatch("mississippi", "mis*is*p*."));
   }

}