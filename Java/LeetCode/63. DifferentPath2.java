// Method 1: Recursion. (Time-consuming)
// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         if (obstacleGrid[0][0] == 0) {
//             return numOfPath(obstacleGrid, 0, 0);
//         } else {
//             return 0;
//         }
//     }

//     public int numOfPath(int[][] grid, int i, int j) {
//         int m = grid.length, n = grid[0].length;
        
//         // Reach the destination.
//         if (i == m - 1 && j == n - 1) { 
//             return 1;
//         }
       
//         int cntPath = 0;
//         // Go down.
//         if (i + 1 < m && grid[i + 1][j] == 0) {
//             cntPath +=  numOfPath(grid, i + 1, j);
//         }
//         // Go right.
//         if (j + 1 < n && grid[i][j + 1] == 0) {
//             cntPath +=  numOfPath(grid, i, j + 1);
//         }
//         return cntPath;
//     }
// }


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;    
        
        // Obestacle locates on the start or the destination.
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
 
        int[][] dp = new int[m][n];
 
        // Intialize the number of paths at the edges.
        dp[m-1][n-1] = 1;
        
        // The last column.
        for (int i = m - 2; i >= 0; --i) {
            if (obstacleGrid[i][n-1] == 0) {
                dp[i][n-1] = dp[i+1][n-1];
            }  else {
                dp[i][n-1] = 0;
            } // if
        } // for
 
        // The last row.
        for (int j = n - 2; j >= 0; --j) {
            if (obstacleGrid[m-1][j] == 0) {
                dp[m-1][j] = dp[m-1][j+1];
            }  else {
                dp[m-1][j] = 0;
            } // if
        } // for
 
        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }  else {
                    dp[i][j] = 0;
                } // if
            } // for
        } // for
 
        return dp[0][0];
    }
}
