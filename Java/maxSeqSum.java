public class maxSeqSum {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5};
        
        int n = a.length;
        int x = 10;
        
        // Solve.
        if (n == 0) {
            // return 0;
        } 
        if (n == 1 && a[0] == x) {
            // return 1;
        } else {
            // return 0;
        }

        int max = 0;
        int[][] mSum = new int[n+1][n+1];
        for (int idx = 1; idx < n; ++idx) {
            mSum[1][idx] = mSum[1][idx-1] + a[idx-1];
            if (mSum[1][idx] == x) {
                max = Math.max(max, idx);
            }
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                mSum[i][j] = mSum[i-1][j] - a[i-2];
                if (mSum[i][j] == x) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

    }
}