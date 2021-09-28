/*
Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

https://leetcode.com/problems/maximal-square/
 */

public class MaximalSquare {

    public static void main(String[] args) {
        MaximalSquare solve = new MaximalSquare();
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(solve.maximalSquare(matrix));
    }

    /**
     * Uses DP to solve.
     * DP[i][j] means the largest square length with right-bottom corner at (i, j).
     * DP[i][j] =
     *      if matrix[i][j] == 1:
     *          1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
     *      else
     *          0
     * @param matrix the matrix
     * @return the maximum area, length * length
     */
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    int l = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                    dp[i][j] = l + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max * max;
    }

}
