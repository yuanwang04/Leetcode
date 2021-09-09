import java.util.*;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("babcaaccabab", 12));
    }

    public static int getLongestPalindrome(String A, int n) {
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n][n];
        int max = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i + 1 < n; i++) {
            if (A.charAt(i) == A.charAt(i+1)) {
                dp[i][i+1] = 2;
                max = 2;
            }
        }

        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                if (A.charAt(i) == A.charAt(i + len) && dp[i + 1][i + len - 1] > 0) {
                    dp[i][i + len] = dp[i + 1][i + len - 1] + 2;
                    max = Math.max(dp[i][i + len], max);
                }
            }
        }

        return max;
    }
}
