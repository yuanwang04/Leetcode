/*
The demons had captured the princess and imprisoned her in the
bottom-right corner of a dungeon.
The dungeon consists of m x n rooms laid out in a 2D grid.
Our valiant knight was initially positioned in the top-left room
and must fight his way through dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer.
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons (represented by negative integers),
so the knight loses health upon entering these rooms;
other rooms are either empty (represented as 0) or contain magic orbs
that increase the knight's health (represented by positive integers).

To reach the princess as quickly as possible,
the knight decides to move only rightward or downward in each step.

Return the knight's minimum initial health so that he can rescue the princess.

Note that any room can contain threats or power-ups,
even the first room the knight enters and the bottom-right room
where the princess is imprisoned.

https://leetcode.com/problems/dungeon-game/
 */

public class DungeonGame {

    public static void main(String[] args) {
        DungeonGame solve = new DungeonGame();
        int[][] dungeon = new int[][]{{0}};
        System.out.println(solve.calculateMinimumHP(dungeon));
    }

    /**
     * Uses DP to solve.
     * dp[i][j] means the minimum of health to needed at [i][j] to go to the bottom right.
     * dp[i][j] = max(1, dungeon[i][j] - min(dp[i][j+1], dp[i][j-1]))
     * @param dungeon the map
     * @return the minimum health needed
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        dungeon[n-1][m-1] = Math.max(1, 1 - dungeon[n-1][m-1]);
        for (int j = m - 2; j >= 0; j--) {
            dungeon[n-1][j] = Math.max(1, dungeon[n-1][j+1] - dungeon[n-1][j]);
        }
        for (int i = n - 2; i >= 0; i--) {
            dungeon[i][m-1] = Math.max(1, dungeon[i+1][m-1] - dungeon[i][m-1]);
            for (int j = m - 2; j >= 0; j--) {
                dungeon[i][j] = Math.max(1, Math.min(dungeon[i][j+1], dungeon[i+1][j]) - dungeon[i][j]);
            }
        }

        return dungeon[0][0];
    }
}
