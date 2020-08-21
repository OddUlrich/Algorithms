import java.util.*;

/*
 * public class Point {
 *   int x;
 *   int y;
 * }
 */

public class RoomChase {
    static List<List<Integer>> edge;
    static int max;
    
    public static void main(String[] args) {
        int[][] edge = new int[][] {{1, 2}, {2, 3}, {3, 4}, {2, 5}};
        System.out.println(solve(5, 2, edge));
    }

    /**
     * 牛牛经过的房间数。
     * @param n int整型 
     * @param x int整型 
     * @param Edge Point类一维数组 
     * @return int整型
     */
    public static int solve (int n, int x, Point[] Edge) {
        // write code here
        if (x == 1)  return 0;
        edge = new ArrayList<>(n);
        max = 0;
        
        for (Point e: Edge) {
            List<Integer> u = edge.get(e.x);
            
            if (u == null)  u = new ArrayList<>();
            u.add(e.y);
            edge.set(e.x, u);

            u = edge.get(e.y);
            if (u == null)  u = new ArrayList<>();
            u.add(e.x);
            edge.set(e.y, u);
        }
        
        dfs(1, 0, 0);
        
        return max;
    }
    
    public static void dfs(int x, int prev, int cnt) {
        List<Integer> e = edge.get(x);
        
        if (x != 1 && e.size() == 1) {
            max = Math.max(cnt + 1, max);
            return;
        }
        
        for (int v: e) {
            if (v != prev)  dfs(v, x, cnt+1);
        }
    }
}