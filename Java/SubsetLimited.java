import java.util.*;

public class SubsetLimited {
    /**
     * 返回找到能够满足游戏胜利条件的子集，如果有多个子集满足条件，返回字典序最小的即可。
     * @param n int整型 代表数字的数量
     * @param k int整型 代表子集的大小
     * @param s int整型一维数组 代表数字数组
     * @return int整型一维数组
     */
    public int[] solve (int n, int k, int[] s) {
        // write code here
        int[] res = new int[k];
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num: s) {
            if (set.contains(num)) {
                set.remove(num);
                minHeap.offer(num);
            } else {
                set.add(num);
            }
        }
        if (minHeap.size() < k)  return null;
        for (int i = 0; i < k; ++i)  res[i] = minHeap.poll();
        
        return res;
    }
}