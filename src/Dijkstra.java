import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int startY = in.nextInt();
        int startX = in.nextInt();
        int endY = in.nextInt();
        int endX = in.nextInt();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        Dijkstra d = new Dijkstra();
        int len = d.solve(map, startX, startY, endX, endY, n);

    }

    public int solve(char[][] map, int startX, int startY, int endX, int endY, int n) {
        Queue<Point> q = new LinkedList<>();

        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        q.add(new Point(startX, startY, 0));

        while (!q.isEmpty()) {
            Point p = q.remove();
            if (p.x == endX && p.y == endY) {
                return p.length;
            }
            if (visited[p.x][p.y]) {
                continue;
            }
            // not visited
            visited[p.x][p.y] = true;
            // x - 1
            if (p.x - 1 >= 0 && !visited[p.x - 1][p.y] && map[p.x - 1][p.y] != '#' && map[p.x - 1][p.y] != '@') {
                q.add(new Point(p.x - 1, p.y, p.length + 1));
            }
            if (p.x + 1 < n && !visited[p.x + 1][p.y] && map[p.x + 1][p.y] != '#' && map[p.x + 1][p.y] != '@') {
                q.add(new Point(p.x + 1, p.y, p.length + 1));
            }
            if (p.y - 1 >= 0 && !visited[p.x][p.y - 1] && map[p.x][p.y - 1] != '#' && map[p.x][p.y - 1] != '@') {
                q.add(new Point(p.x, p.y - 1, p.length + 1));
            }
            if (p.y + 1 < n && !visited[p.x][p.y + 1] && map[p.x][p.y + 1] != '#' && map[p.x][p.y + 1] != '@') {
                q.add(new Point(p.x, p.y + 1, p.length + 1));
            }
        }

        return -1;
    }

    private static class Point {
        int x;
        int y;
        int length;

        public Point(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }



}
