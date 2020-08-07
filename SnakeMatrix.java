/*
   Generates a snake matrix of the given size (odd).
*/

public class SnakeMatrix {
   
   public static void main(String[] args) {
      int[][] res = snakePointer(7);
   }
   
   public static int[][] snake(int n) {
      int[][] res = new int[n][n];
      
      res[n/2][n/2] = 1;
      int cnt = 2;
      
      for (int level = 1; level < n/2 + 1; level++) {
         for (int i = 0; i < level * 2; i++) {
            res[n/2 - i + level - 1][n/2 + level] = cnt;
            cnt++;
         }
         
         for (int i = 0; i < level * 2; i++) {
            res[n/2 - level][n / 2 + level - 1 - i] = cnt;
            cnt++;
         }
         
         for (int i = 0; i < level * 2; i++) {
            res[n / 2 - level + i + 1][n / 2 - level] = cnt;
            cnt++;
         }
         
         for (int i = 0; i < level * 2; i++) {
            res[n / 2 + level][n / 2 - level + i + 1] = cnt;
            cnt++;
         }
      }
      
      return res;
      
   }
   
   public static int[][] snakePointer(int n) {
      int[][] res = new int[n][n];
      int curX = n/2;
      int curY = n/2;
      int cnt = 1;
      int increment = 1;
      int[][] direction = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
      int curDir = 0;
      while (cnt <= n * n) {
         for (int i = 0; i < increment; i++) {
            res[curX][curY] = cnt;
            cnt++;
            curX += direction[curDir][0];
            curY += direction[curDir][1];
         }
         curDir++;
         curDir %= 4;
         if (cnt > n * n) 
            break;
         for (int i = 0; i < increment; i++) {
            res[curX][curY] = cnt;
            cnt++;
            curX += direction[curDir][0];
            curY += direction[curDir][1];
         }
         increment++;
         curDir++;
         curDir %= 4;
         
      }
      return res;
   }
   // more ways of generating different matrix
   public int[][] generateMatrix(int n) {
      int[][] result = new int[n][n];
      int c = 1;
      int level = 0;
      while (level < n/2) {
            //top
         for (int i = level; i < n-1-level; i++) {
            result[level][i] = c;
            c++;
         }
            //right
         for (int i = level; i < n-1-level; i++) {
            result[i][n-1-level] = c;
            c++;
         }
            //bot
         for (int i = n-level-1; i > level; i--) {
            result[n-1-level][i] = c;
            c++;
         }
            //left
         for (int i = n-level-1; i > level; i--) {
            result[i][level] = c;
            c++;
         }
         level++;
      }
      if (n%2 == 1) {
         result[n/2][n/2] = c;
      }
      return result;
        
   }


}