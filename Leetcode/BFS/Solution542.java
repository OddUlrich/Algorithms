import java.util.LinkedList;
import java.util.Queue;

public class Solution542 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int[][] updateMatrix(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    res[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {

            int[] loc = q.poll();
            int x = loc[0];
            int y = loc[1];
            for (int idx = 0; idx < 4; idx++) {
                int newX = x + dx[idx];
                int newY = y + dy[idx];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    res[newX][newY] = res[x][y] + 1;
                    q.offer(new int[]{newX, newY});
                }
            }
        }

        return res;

    }

}
