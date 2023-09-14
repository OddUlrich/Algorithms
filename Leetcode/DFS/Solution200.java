public class Solution200 {
    int m, n;
    int cnt;

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    ++cnt;
                    dfs(i, j, grid);
                }
            }
        }

        return cnt;
    }

    public void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (grid[i][j] == '0' || grid[i][j] == '2') return;

        grid[i][j] = '2';
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
    }
}
