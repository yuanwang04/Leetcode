import java.util.*;

public class SolutionAli {

    private static int MODULO = 1000000009;


    public static void main(String[] args) {
//         int[] input = new int[]{4, 7, 9, 10};
//         int k = 2;
//         System.out.println(solve(input, k, 0, input.length - 1));
// 
//         int[] workers = new int[]{1, 1, 1};
//         int[] profits = new int[]{1, 2, 3};
//         int numWorkers = 3;
//         int minProfit = 2;
//         System.out.println(solve2(workers, profits, minProfit, numWorkers));
        int U = 3;
        int L = 1;
        int[] C = new int[]{2,0,1,1};
        System.out.println(solveMatrix2(U, L, C));
    }

    public static int solve(int[] input, int k, int left, int right) {
        if (input.length == 0) {
            return k;
        }

        int offset = input[left];
        if (input[right] - offset - (right-left) < k) {
            return offset + (right - left) + k;
        }

        if (left + 1 == right) {
            return input[left] + k;
        }

        int mid = (right + left) / 2;
        int missingOnLeft = input[mid] - (mid - left) - offset;
        if (missingOnLeft < k) {
            return solve(input, k - missingOnLeft, mid, right);
        } else {
            return solve(input, k, left, mid);
        }
    }

    public static int solve2(int[] workers, int[] profits, int minProfit, int workerNum) {
        int[][][] dp = new int[workers.length + 1][workerNum + 1][minProfit + 1];

        for (int j = 0; j <= workerNum; j++) {
            dp[0][j][0] = 1;
        }

        for (int i = 1; i <= workers.length; i++) {
            for (int j = 0; j <= workerNum; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    int ways = dp[i-1][j][k];
                    if (j >= workers[i-1]) {
                        ways += dp[i-1][j - workers[i-1]][Math.max(0, k - profits[i-1])];
                    }
                    dp[i][j][k] = ways % MODULO;
                }
            }
        }

        return dp[workers.length][workerNum][minProfit];
    }
    
    public static String solveMatrix(int U, int L, int[] C) {
        // write your code in Java SE 8
        if (C.length == 0) {
            return "IMPOSSIBLE";
        }
        
        int[][][][] backptr = new int[C.length][U+1][L+1][2];
        for (int u = 0; u < U+1; u++) {
            for (int l = 0; l < L+1; l++) {
                backptr[0][u][l][0] = u <= 1 && l <= 1 && u + l == C[0] ? 0 : -1;
                backptr[0][u][l][1] = u <= 1 && l <= 1 && u + l == C[0] ? 0 : -1;
                
            }
        }
        
        for (int i = 1; i < C.length; i++) {
            for (int u = 0; u < U+1; u++) {
                for (int l = 0; l < L+1; l++) {
                    if (C[i] == 0) {
                        if (backptr[i-1][u][l][0] != -1) {
                            backptr[i][u][l][0] = u;
                            backptr[i][u][l][1] = l;
                        } else {
                            backptr[i][u][l][0] = -1;
                            backptr[i][u][l][1] = -1;
                        }
                    } else if (C[i] == 1) {
                        if (u - 1 >= 0 && backptr[i-1][u-1][l][0] != -1) {
                            backptr[i][u][l][0] = u - 1;
                            backptr[i][u][l][1] = l;
                        } else if (l - 1 >= 0 && backptr[i-1][u][l-1][0] != -1) {
                            backptr[i][u][l][0] = u;
                            backptr[i][u][l][1] = l - 1;
                        } else {
                           backptr[i][u][l][0] = -1;
                           backptr[i][u][l][1] = -1;
                        }
                    } else {  // C[i] == 2
                        if (u - 1 >= 0 && l - 1 >= 0 
                        && backptr[i-1][u-1][l-1][0] != -1) {
                            backptr[i][u][l][0] = u - 1;
                            backptr[i][u][l][1] = l - 1;
                        } else {
                            backptr[i][u][l][0] = -1;
                            backptr[i][u][l][1] = -1;
                        }
                    }
                }
            }
        }

        if (backptr[C.length - 1][U][L][0] == -1) {
            return "IMPOSSIBLE";
        }

        StringBuilder upper = new StringBuilder();
        StringBuilder lower = new StringBuilder();
        int u = U;
        int l = L;
        for (int i = C.length - 1; i > 0; i--) {
            int[] ptr = backptr[i][u][l];
            upper.insert(0, ptr[0] == u ? '0' : '1');
            lower.insert(0, ptr[1] == l ? '0' : '1');
            u = ptr[0];
            l = ptr[1];
        }
        upper.insert(0, u);
        lower.insert(0, l);

        String result = upper.toString() + "," + lower.toString();
        return result;
    }
    
    
    public static String solveMatrix2(int U, int L, int[] C) {
    
        int sum = 0;
        int zeros = 0;
        int twos = 0;
        for (int i = 0; i < C.length; i++) {
            sum += C[i];
            zeros += C[i] == 0 ? 1 : 0;
            twos += C[i] == 2 ? 1 : 0;
        }
        int ones = C.length - zeros - twos;
        
        if (U + L == sum && U <= C.length - zeros && U >= twos
            && L <= C.length && L >= twos && U - twos + L - twos == ones) {
            int u = U - twos;
            StringBuilder upper = new StringBuilder();
            StringBuilder lower = new StringBuilder();
            for (int i = 0; i < C.length; i++) {
                if (C[i] == 0) {
                    upper.append(0);
                    lower.append(0);
                } else if (C[i] == 2) {
                    upper.append(1);
                    lower.append(1);
                } else if (u > 0) {
                    upper.append(1);
                    lower.append(0);
                    u--;
                } else {
                    upper.append(0);
                    lower.append(1);
                }
            }
            return upper.toString() + "," + lower.toString();
        } else {
            return "IMPOSSIBLE";
        }
    }
}


