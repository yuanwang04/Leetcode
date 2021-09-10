/*
You are given an integer n.
You have an n x n binary grid grid with all values initially 1's
except for some indices given in the array mines.
The ith element of the array mines is defined as
mines[i] = [xi, yi] where grid[xi][yi] == 0.

Return the order of the largest axis-aligned plus sign
of 1's contained in grid. If there is none, return 0.

An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1
along with four arms of length k - 1 going up, down, left, and right,
and made of 1's.
Note that there could be 0's or 1's beyond the arms of the plus sign,
only the relevant area of the plus sign is checked for 1's.

https://leetcode.com/problems/largest-plus-sign/
 */

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LargestPlusSign {

    public static void main(String[] args) {
        LargestPlusSign solve = new LargestPlusSign();
        int[][] mines = new int[][]{{4, 2}};
        int n = 5;
        System.out.println(solve.orderOfLargestPlusSign(n, mines));
    }

    /**
     * Uses DP to solve. DP matrix for four directions: left, right, up, down.
     * left[i][j] means how many steps you can go at (i, j) to the left.
     * Solution is the max at each point.
     * Time Complexity: O(N^2)
     * @param n     matrix size
     * @param mines 0s' positions
     * @return the order of the sign
     */
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Pos> mineSet = new HashSet<>();
        for (int[] mine: mines) {
            mineSet.add(new Pos(mine[0], mine[1]));
        }

        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];

        // dp initialize
        for (int i = 0; i < n; i++) {
            if (!mineSet.contains(new Pos(i, 0))) {
                left[i][0] = 1;
            }
            if (!mineSet.contains(new Pos(i, n-1))) {
                right[i][n-1] = 1;
            }
            if (!mineSet.contains(new Pos(0, i))) {
                up[0][i] = 1;
            }
            if (!mineSet.contains(new Pos(n-1, i))) {
                down[n-1][i] = 1;
            }
        }

        // dp left
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (!mineSet.contains(new Pos(i, j))) {
                    left[i][j] = left[i][j-1] + 1;
                } else {
                    left[i][j] = 0;
                }
            }
        }
        // dp right
        for (int i = 0; i < n; i++) {
            for (int j = n-2; j >= 0; j--) {
                if (!mineSet.contains(new Pos(i, j))) {
                    right[i][j] = right[i][j+1] + 1;
                } else {
                    right[i][j] = 0;
                }
            }
        }
        // dp up
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!mineSet.contains(new Pos(i, j))) {
                    up[i][j] = up[i-1][j] + 1;
                } else {
                    up[i][j] = 0;
                }
            }
        }
        // dp down
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (!mineSet.contains(new Pos(i, j))) {
                    down[i][j] = down[i + 1][j] + 1;
                } else {
                    down[i][j] = 0;
                }
            }
        }

        // go over the matrix
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result,
                        Math.min(Math.min(up[i][j], down[i][j]), Math.min(left[i][j], right[i][j])));
            }
        }
        return result;
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
