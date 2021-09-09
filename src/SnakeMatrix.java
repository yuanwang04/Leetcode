/*
   Generates a snake matrix of the given size (odd).
*/

import java.util.*;

public class SnakeMatrix {
   
   public static void main(String[] args) {
      int[][] res = snakePointer(5);
      System.out.println(printMatrix(res));
   }
   
   // print the matrix from outer layer to inner layer,
   // clockwise. 
   public static ArrayList<Integer> printMatrix(int [][] matrix) {
      ArrayList<Integer> res = new ArrayList<>();
      if (matrix.length == 0 || matrix[0].length == 0) {
         return res;
      }
        
      int[] pointer = new int[]{0, 0};
      int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
      int currDir = 0;
      int xMax = matrix[0].length - 1;
      int yMax = matrix.length - 1;
      int xMin = 0;
      int yMin = 1;
      int size = (xMax + 1) * (yMax + 1);
      while (res.size() < size) {
         res.add(matrix[pointer[0]][pointer[1]]);
         pointer[0] += dirs[currDir][0];
         pointer[1] += dirs[currDir][1];
         if (currDir == 0 && pointer[1] == xMax) {
            xMax--;
            currDir = 1;
         } else if (currDir == 1 && pointer[0] == yMax) {
            yMax--;
            currDir = 2;
         } else if (currDir == 2 && pointer[1] == xMin) {
            xMin++;
            currDir = 3;
         } else if (currDir == 3 && pointer[0] == yMin) {
            yMin++;
            currDir = 0;
         }
      }
        
      return res;
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