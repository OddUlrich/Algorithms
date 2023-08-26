public class SolutionOffer27 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        return mirrorSubTree(root);
    }

    private TreeNode mirrorSubTree(TreeNode node) {

        if (node == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(node.val);
        newRoot.left = mirrorSubTree(node.right);
        newRoot.right = mirrorSubTree(node.left);

        return newRoot;
    }

}
