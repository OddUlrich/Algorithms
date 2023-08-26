import java.util.HashSet;
import java.util.Set;

public class SolutionLCP44 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int numColor(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Set<Integer> colorSet = new HashSet<>();
        assembleNewColor(root, colorSet);

        return colorSet.size();
    }

    private void assembleNewColor(TreeNode node, Set<Integer> colorSet) {
        if (node == null) {
            return;
        }
        colorSet.add(node.val);
        assembleNewColor(node.left, colorSet);
        assembleNewColor(node.right, colorSet);
    }
}
