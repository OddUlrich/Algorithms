import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Solution1379 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        if (original == null) {
            return null;
        }
        if (cloned == null) {
            return original;
        }

        Queue<TreeNode> originQueue = new LinkedList<>();
        Queue<TreeNode> clonedQueue = new LinkedList<>();

        originQueue.offer(original);
        clonedQueue.offer(cloned);

        while (!originQueue.isEmpty() && !clonedQueue.isEmpty()) {

            TreeNode originNode = originQueue.poll();
            TreeNode clonedNode = clonedQueue.poll();
            if (originNode.val == target.val) {
                return clonedNode;
            } else {
                if (originNode.left != null && clonedNode.left != null) {
                    originQueue.offer(originNode.left);
                    clonedQueue.offer(clonedNode.left);
                }
                if (originNode.right != null && clonedNode.right != null) {
                    originQueue.offer(originNode.right);
                    clonedQueue.offer(clonedNode.right);
                }
            }
        }

        return null;
    }
}