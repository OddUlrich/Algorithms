public class BlockMaze {
    public static void main(String[] args) {
        System.out.println(GetNumberOfPath(4, 4, 2, 2, 3, 3));
    }

    public static int GetNumberOfPath (int n, int m, int x0, int y0, int x1, int y1) {
        // write code here
        long mod = 1000000007L;
        long[][] dp = new long[n+1][m+1];
        dp[0][1] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (i >= x0 && i <= x1 && j >= y0 && j <= y0) {
                    continue;
                }
                
                if (i == x1 + 1 && j >= y0 && j <= y1)  dp[i][j] = dp[i][j-1];
                else if (i >= x0 && i <= x1 && j == y1 + 1)  dp[i][j] = dp[i-1][j];
                else  dp[i][j] = (dp[i][j-1] % mod) + (dp[i-1][j] % mod) % mod;
            }
        }
        return (int)(dp[n][m] % mod);
    }
}