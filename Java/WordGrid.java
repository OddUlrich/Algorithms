import java.util.Scanner;

public class WordGrid{
    static boolean[][] visited;
    static int[] xDir = new int[]{0, 1, 0, -1};
    static int[] yDir = new int[]{-1, 0, 1, 0};
    static char[][] grid = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        in.close();
        
        int col = grid.length;
        int row = grid[0].length;
        visited = new boolean[col][row];
        
        // 遍历网格，找首字母。
        int len = 0;  // 探索到的合法长度。
        for (int i = 0; i < col; ++i) {
            len = 0;
            for (int j = 0; j < row; ++j) {
                len = dfs(j, i, 0, s);
                if (len == s.length())  break;
            }
            if (len == s.length())  break;
        }
        if (len == s.length())  System.out.println("true");
        else  System.out.println("false");
    }

    /**
    *  Param x: 水平位置，从左到右为0 ~ m-1。
    *  Param y: 垂直位置，从上到下为0 ~ n-1。
    *  Param idx: 当前寻找的字符在给定字符串的索引位置。
    *  Param s: 目标字符串。
    *
    *  return res: 匹配成功的长度。
    */
    static int dfs(int x, int y, int idx, String s) {
        if (grid[y][x] != s.charAt(idx))  return idx;
        else {
            // 已经匹配整个字符串。
            if (idx + 1 == s.length())  return idx + 1;
        }

        int res = 0;
        // 遍历四个方向。
        for (int i = 0; i < 4; ++i) {
            // Marked.
            visited[y][x] = true;
            
            int tmpX = x + xDir[i];
            int tmpY = y + yDir[i];
            if (tmpX >= 0 && tmpX < grid[0].length
                && tmpY >= 0 && tmpY < grid.length
                && !visited[tmpY][tmpX])  res = Math.max(res, dfs(tmpX, tmpY, idx+1, s));
            if (res == s.length())  break;
            // Unmarked.
            visited[y][x] = false;
        }
        return res;
    }
}