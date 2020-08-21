import java.util.Scanner;

public class FabSnake{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        
        if (n == 1) {
            System.out.println(1);
            return;
        }
        
        long[] fab = new long[n*n];
        fab[0] = 1;
        fab[1] = 1;
        for (int i = 2; i < fab.length; ++i) fab[i] = fab[i-1] + fab[i-2];
        
        long[][] res = new long[n][n];
        int[] dirX = new int[]{1, 0, -1, 0};
        int[] dirY = new int[]{0, 1, 0, -1};
        int level = 0;
        int i = 0, j = 0, d = 0;  // i is row, j is column, d is direction index.
        
        for (int cnt = fab.length - 1; cnt >= 0; --cnt) {
            res[i][j] = fab[cnt];
            
            // Update direction.
            if ((d == 0 && j == n-1 - level)
                || (d == 1 && i == n-1 - level)
                || (d == 2 && j == level))
                ++d;
            else if (d == 3 && i == level + 1) {
                d = 0;
                ++level;
            }

            // Update location.
            i += dirY[d];
            j += dirX[d];
        }
        
        // Print out the result.
        for (int p = 0; p < n; ++p) {
            for (int q = 0; q < n; ++q) {
                if (q == 0)  System.out.print(res[p][q]);
                else System.out.print(" " + res[p][q]);
            }
            System.out.println();
        }
    }
}