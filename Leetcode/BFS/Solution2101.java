import java.util.LinkedList;
import java.util.Queue;

public class Solution2101 {
    private int check(int[][] bombs, int i, int j) {
        double l1 = bombs[j][0] - bombs[i][0];
        double l2 = bombs[j][1] - bombs[i][1];
        double d = bombs[i][2];
        return l1 * l1 + l2 * l2 > d * d ? 0 : 1;
    }

    public int maximumDetonation(int[][] bombs) {
        int len = bombs.length;
        int[][] flag = new int[len][len];
        int num = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    flag[i][j] = 1;
                } else {
                    flag[i][j] = check(bombs, i, j);
                }
            }
        }

        for (int i = 0; i < len; i++) {
            int temp = 1;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(i);
            int[] v = new int[len];
            v[i] = 1;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int j = 0; j < len; j++) {
                    if (cur == j) {
                        continue;
                    }
                    if (flag[cur][j] == 1 && v[j] == 0) {
                        v[j] = 1;
                        temp++;
                        queue.offer(j);
                    }
                }
            }
            num = Math.max(num, temp);
            if (num == len) {
                break;
            }
        }
        return num;
    }

}
