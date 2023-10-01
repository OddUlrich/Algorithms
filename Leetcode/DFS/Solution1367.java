import java.util.ArrayList;
import java.util.List;

public class Solution1367 {
    int n;
    boolean res = false;
    int[] next;

    public boolean isSubPath(ListNode head, TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        n = list.size();
        next = new int[n];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < n - 1) {
            if (j < 0 || list.get(i) == list.get(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        dfs(root, list, 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> list, int i) {
        if (res) {
            return;
        }
        if (i == n) {
            res = true;
            return;
        }
        if (node == null) {
            return;
        }
        if (i < 0 || node.val == list.get(i)) {
            dfs(node.left, list, i + 1);
            dfs(node.right, list, i + 1);
        } else {
            dfs(node, list, next[i]);
        }
    }

}
