import java.util.LinkedList;
import java.util.Queue;

public class Solution994 {
    public int orangesRotting(int[][] grid) {

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        int fresh = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                } else if (grid[i][j] == 1) {
                    fresh++;
                } else {
                    visited[i][j] = true;
                }
            }
        }

        int round = 0;
        while (!q.isEmpty()) {
            int[] pos = q.poll();

            round = pos[2];

            for (int i = 0; i < 4; i++) {
                if (pos[0] + dx[i] >= 0 && pos[0] + dx[i] < m
                    && pos[1] + dy[i] >= 0 && pos[1] + dy[i] < n
                    && !visited[pos[0] + dx[i]][pos[1] + dy[i]]) {
                    visited[pos[0] + dx[i]][pos[1] + dy[i]] = true;
                    fresh--;
                }
            }
        }

        if (fresh > 0) {
            return -1;
        } else {
            return round;
        }
    }
}
