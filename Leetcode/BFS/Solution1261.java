import java.util.HashSet;
import java.util.Set;

public class Solution1261 {

    class FindElements {

        Set<Integer> valSet = new HashSet<>();


        public FindElements(TreeNode root) {
            if (root != null) {
                init(root, 0);
            }
        }

        private void init(TreeNode node, int val) {
            node.val = val;
            valSet.add(val);
            if (node.left != null) {
                init(node.left, 2 * val + 1);
            }
            if (node.right != null) {
                init(node.right, 2 * val + 2);
            }
        }


        public boolean find(int target) {
            return valSet.contains(target);
        }
    }
}
