import java.util.ArrayList;
import java.util.List;

class Solution1443 {


    List<Boolean> hasApple;
    List<Integer>[] nexts;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.hasApple = hasApple;
        nexts = new ArrayList[n];// 相邻的节点
        for (int i = 0; i < n; i++) {
            nexts[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            nexts[edges[i][0]].add(edges[i][1]);
            nexts[edges[i][1]].add(edges[i][0]);
        }
        boolean[] been = new boolean[n];// 到过的节点
        been[0] = true;
        // 收集到0节点所需时间
        int ans = this.f(0, been);
        return ans == -1 ? 0 : ans;
    }

    private int f(int index, boolean[] been) {
        int cur = 0;
        List<Integer> next = nexts[index];
        for (int nxt : next) {
            if (!been[nxt]) {
                been[nxt] = true;
                int has = this.f(nxt, been);
                if (has != -1) {
                    cur += has + 2;
                }
            }
        }
        if (this.hasApple.get(index)) {
            return cur;
        } else {
            return cur == 0 ? -1 : cur;
        }
    }

}
