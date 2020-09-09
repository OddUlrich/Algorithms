import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Grid {
    static int[] xDir = new int[]{0, 0, -1, 1};
    static int[] yDir = new int[]{-1, 1, 0, 0};
    static int xDes, yDes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int N = in.nextInt();
            int[][] grid = new int[2*N+1][2*N+1];
            int x = N, y = N;
            grid[y][x] = 1;
            
            for (int i = 0; i < N; ++i) {
                int dir = in.nextInt();
                int res = in.nextInt();
                if (res == -1)  continue;
                else {
                    x += xDir[dir];
                    y += yDir[dir];
                    grid[y][x] = 1;
                }
            }
            xDes = x;
            yDes = y;
            
            int cnt = bfs(grid, N, N);
            System.out.println(cnt-1);
        }
        in.close();
    }
    
    static int bfs(int[][] grid, int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        grid[y][x] = 0;
        q.offer(x);
        q.offer(y);
        int cnt = 0;
        // 从起点开始。
        while (q.size() > 0) {
            // cnt 代表层数，也代表步数。
            ++cnt;
            int size = q.size() / 2;
            
            for (int i = 0; i < size; ++i) {
                // 取出能成功走入的点。
                int pointX = q.poll();
                int pointY = q.poll();
                if (pointX == xDes && pointY == yDes) {
                    return cnt;
                }
                
                for (int idx = 0; idx < 4; ++idx) {
                    int tmpX = pointX + xDir[idx];
                    int tmpY = pointY + yDir[idx];
                    if (grid[tmpY][tmpX] == 1) {
                        q.offer(tmpX);
                        q.offer(tmpY);
                        grid[tmpY][tmpX] = 0;
                    } 
                }
            }
        }
        return cnt;
    }
    
}