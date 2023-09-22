import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1306 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (arr[index] == 0) {
                return true;
            }
            int[] nextIndices = {index - arr[index], index + arr[index]};
            for (int nextIndex : nextIndices) {
                if (nextIndex >= 0 && nextIndex < n && !visited[nextIndex]) {
                    visited[nextIndex] = true;
                    queue.offer(nextIndex);
                }
            }
        }
        return false;
    }

}
