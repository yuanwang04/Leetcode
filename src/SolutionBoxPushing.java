import java.util.*;

public class SolutionBoxPushing {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();
      int m = in.nextInt();
      char[][] map = new char[n][m];
      int startX = 0;
      int startY = 0;
      int boxX = 0;
      int boxY = 0;
      for (int i = 0; i < n; i++) {
         String s = in.nextLine();
         for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            map[i][j] = c;
            if (c == 'S') {
               startX = i;
               startY = j;
            }
            if (c == '0') {
               boxX = i;
               boxY = j;
            }
         }
      }
      // int n = 3;
      // int m = 6;
      // char[][] map = new char[][]
         // {{'.','S','#','.','.','E'},
         // {'.','#','.','0','.','.'},
         // {'.','.','.','.','.','.'}};
      // int startX = 0;
      // int startY = 1;
      // int boxX = 1;
      // int boxY = 3;
      System.out.println(solve(map, startX, startY,
                             boxX, boxY, n, m));
   }
   
   public static class Node {
      int px, py;
      int bx, by;
      int step;
      public Node(int px, int py, int bx, int by) {
         this.px = px;
         this.py = py;
         this.bx = bx;
         this.by = by;
      }
   }
   
   public static int solve(char[][] map, int startX, int startY,
                          int boxX, int boxY, int n, int m) {
      Node start = new Node(startX, startY, boxX, boxY);
      boolean[][][][] walked = new boolean[n][m][n][m];
      int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
      walked[startX][startY][boxX][boxY] = true;
      
      Queue<Node> q = new LinkedList<>();
      q.add(start);
      
      while (!q.isEmpty()) {
         Node curr = q.remove();
         int newBx = curr.bx;
         int newBy = curr.by;
         for (int[] dir : direction) {
            if (curr.px + dir[0] == curr.bx &&
              curr.py + dir[1] == curr.by) {
               newBx += dir[0];
               newBy += dir[1];
            }
            Node next = new Node(curr.px + dir[0], curr.py + dir[1],
                               newBx, newBy);
            if (next.px >= n || next.px < 0 || next.py >= m || next.py < 0 ||
               newBx >= n || newBx < 0 || newBy >= m || newBx < 0 ||
               map[next.px][next.py] == '#' || map[newBx][newBy] == '#') {
               continue;
            }
            if (!walked[next.px][next.py][newBx][newBy]) {
               q.add(next);
               next.step = curr.step + 1;
               if (map[newBx][newBy] == 'E') {
                  return next.step;
               }
               walked[next.px][next.py][newBx][newBy] = true;
            }
         }
      }
      return -1;
   }
}