public class Solve {
   
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


}