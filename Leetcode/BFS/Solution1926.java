import java.util.LinkedList;
import java.util.Queue;

public class Solution1926 {


    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};


    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{entrance[0], entrance[1], 0});
        visited[entrance[0]][entrance[1]] = true;

        while (!q.isEmpty()) {

            int[] b = q.poll();
            if (b[2] != 0 && isEdge(b[0], b[1], n, m)) {
                return b[2];
            }

            // Four direction
            for (int i = 0; i < 4; i++) {
                int x = b[0] + dx[i];
                int y = b[1] + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == '.' && !visited[x][y]) {
                    visited[x][y] = true;
                    q.offer(new int[]{x, y, b[2] + 1});
                }
            }
        }

        return -1;

    }

    private boolean isEdge(int x, int y, int n, int m) {
        return x == 0 || x == n - 1 || y == 0 || y == m -1;
    }
}
