class PalindromePartition {

    String s;
    int[][] dp;
    
    public static void main(String[] args) {
        PalindromePartition p = new PalindromePartition();
        System.out.println(p.minCut("aab"));
    
    }
    
    public int minCut(String s) {
        this.s = s;
        dp = new int[s.length() + 1][s.length() + 1];
        buildDP();
        return dp[0][s.length()];
    }
    
    public void buildDP() {
        for (int init = 0; init < 2; init++) {
            for (int start = 0; start + init <= s.length(); start++) {
                dp[start][start + init] = 0;
            }
        }
        
        for (int step = 2; step <= s.length(); step++) {
            for (int start = 0; start + step <= s.length(); start++) {
                if (dp[start+1][start+step-1] == 0 &&
                   s.charAt(start) == s.charAt(start+step-1)) {
                    // is a palindrome
                    dp[start][start+step] = 0;
                } else {
                    // try all cut
                    int min = s.length();
                    for (int mid = start + 1; mid < start + step; mid++) {
                        min = Math.min(min, dp[start][mid] + dp[mid][start + step]);
                    }
                    dp[start][start + step] = min + 1;
                }
            }
        }
    }


}