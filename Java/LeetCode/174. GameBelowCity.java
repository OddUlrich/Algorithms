public class GameBelowCity {
    public static void main(String[] args) {
        int[][] a = new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};

        System.out.println(calculateMinimumHP(a));

    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = Math.max(1 - dungeon[m-1][n-1], 1);

        // Initialize the last column.
        for (int i = m-2; i >= 0; --i) {
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1 - dungeon[i][n-1]);
            // dungeon is a large positive and make dp negative.
            dp[i][n-1] = Math.max(dp[i][n-1], 1);
        }

        // Initialize the last row.
        for (int j = n-2; j >= 0; --j) {
            dp[m-1][j] = Math.max(dp[m-1][j+1] - dungeon[m-1][j], 1 - dungeon[m-1][j]);
            // dungeon is a large positive and make dp negative.
            dp[m-1][j] = Math.max(dp[m-1][j], 1);
        }

        for (int i = m-2; i >= 0; --i) {
            for (int j = n-2; j >= 0; --j) {      
                int maxBelow = Math.max(dp[i+1][j] - dungeon[i][j], 1 - dungeon[i][j]);
                maxBelow = Math.max(maxBelow, 1);
                int maxRight = Math.max(dp[i][j+1] - dungeon[i][j], 1 - dungeon[i][j]);
                maxRight = Math.max(maxRight, 1);

                dp[i][j] = Math.min(maxRight, maxBelow);
            }
        }

        return dp[0][0];
    }
}