import java.util.LinkedList;
import java.util.Queue;

public class Solution695 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] != 1) {
                    continue;
                }

                int cur = 0;
                Queue<Integer> qi = new LinkedList<>();
                Queue<Integer> qj = new LinkedList<>();
                qi.offer(i);
                qj.offer(j);
                while (!qi.isEmpty() && !qj.isEmpty()) {
                    int curI = qi.poll();
                    int curJ = qj.poll();
                    if (curI < 0 || curJ < 0 || curI >= grid.length || curJ >= grid[0].length || grid[curI][curJ] != 1) {
                        continue;
                    }

                    cur++;
                    grid[curI][curJ] = 0;

                    for (int idx = 0; idx < 4; ++idx) {
                        qi.offer(curI + dx[idx]);
                        qj.offer(curJ + dy[idx]);
                    }
                }
                maxArea = Math.max(maxArea, cur);
            }
        }
        return maxArea;
    }
}