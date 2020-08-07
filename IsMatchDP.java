import java.util.*;

/*
*
* Given a string and a pattern, return if the pattern matches the string.
* '*' matches 0 or 1 character; '.' match any 1 character
*
*/


public class IsMatchDP {

   public boolean isMatchDP(String s, String p) {
      int n1 = s.length();
      int n2 = p.length();
      boolean[][] dp = new boolean[n1+1][n2+1];
      dp[0][0] = true;
      for (int i = 1; i <= n1; i++) {
         dp[i][0] = dp[i-1][0] && s.charAt(i-1) == '*';
      }
      for (int j = 1; j <= n2; j++) {
         dp[0][j] = dp[0][j-1] && p.charAt(j-1) == '*';
      }
      for (int diag = 1; diag <= Math.min(n1, n2); diag++) {
         dp[diag][diag] = check(dp, s, p, diag, diag);
         for (int i = diag + 1; i <= n1; i++) {
            dp[i][diag] = check(dp, s, p, i, diag);
         }
         for (int j = diag + 1; j <= n2; j++) {
            dp[diag][j] = check(dp, s, p, diag, j);
         }
      }
      return dp[n1][n2];
   }

   public boolean check(boolean[][] dp, String s, String p, int i, int j) {
      return (dp[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1)
                                || s.charAt(i-1) == '?'
                                || s.charAt(i-1) == '*'
                                || p.charAt(j-1) == '?'
                                || p.charAt(j-1) == '*'))
            || (dp[i-1][j] && (s.charAt(i-1) == '*'
                              || (p.charAt(j-1) == '*')))
            || (dp[i][j-1] && (p.charAt(j-1) == '*'
                              || (s.charAt(i-1) == '*')));

   }
}
