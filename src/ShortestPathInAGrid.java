/*
You are given an m x n integer matrix grid where each cell
is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right
from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0)
to the lower right corner (m - 1, n - 1) given that you can eliminate
at most k obstacles. If it is not possible to find such walk return -1.

https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInAGrid {

    public static void main(String[] args) {
        ShortestPathInAGrid solve = new ShortestPathInAGrid();
        int[][] grid = new int[][]{
                {0,1,1},
                {1,1,0},
                {1,0,0},
        };
        System.out.println(solve.shortestPath(grid, 1));
    }

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        if (m == 0) {
            return -1;
        }
        int n = grid[0].length;
        if (n == 0) {
            return -1;
        }
        if (m == 1 && n == 1) {
            return 0;
        }
        if (k == 0 && grid[0][0] == 1) {
            return -1;
        }

        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        if (grid[0][0] == 0) {
            q.add(new int[]{0, 0, k, 0});
        } else {
            q.add(new int[]{0, 0, k-1, 0});
        }

        while (!q.isEmpty()) {
            int[] move = q.remove();
            if (move[0] == m - 1 && move[1] == n - 1) {
                return move[3];
            }
            visited[move[0]][move[1]][move[2]] = true;
            // try up
            if (move[0] > 0) {
                if (grid[move[0] - 1][move[1]] == 0 && !visited[move[0]-1][move[1]][move[2]]) {
                    q.add(new int[]{move[0] - 1, move[1], move[2], move[3] + 1});
                } else if (move[2] > 0 && !visited[move[0]-1][move[1]][move[2]-1]) {
                    q.add(new int[]{move[0] - 1, move[1], move[2] - 1, move[3] + 1});
                }
            }
            // try down
            if (move[0] < m - 1) {
                if (grid[move[0] + 1][move[1]] == 0 && !visited[move[0]+1][move[1]][move[2]]) {
                    q.add(new int[]{move[0] + 1, move[1], move[2], move[3] + 1});
                } else if (move[2] > 0 && !visited[move[0]+1][move[1]][move[2]-1]) {
                    q.add(new int[]{move[0] + 1, move[1], move[2] - 1, move[3] + 1});
                }
            }
            // try left
            if (move[1] > 0) {
                if (grid[move[0]][move[1] - 1] == 0 && !visited[move[0]][move[1]-1][move[2]]) {
                    q.add(new int[]{move[0], move[1] - 1, move[2], move[3] + 1});
                } else if (move[2] > 0 && !visited[move[0]][move[1]-1][move[2]-1]) {
                    q.add(new int[]{move[0], move[1] - 1, move[2] - 1, move[3] + 1});
                }
            }
            // try right
            if (move[1] < n - 1) {
                if (grid[move[0]][move[1] + 1] == 0 && !visited[move[0]][move[1]+1][move[2]]) {
                    q.add(new int[]{move[0], move[1] + 1, move[2], move[3] + 1});
                } else if (move[2] > 0 && !visited[move[0]][move[1]+1][move[2]-1]) {
                    q.add(new int[]{move[0], move[1] + 1, move[2] - 1, move[3] + 1});
                }
            }
        }

        return -1;
    }
}
