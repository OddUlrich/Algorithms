public class Solution1315 {
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, 1, 1);
    }

    private int dfs(TreeNode root, int gp, int p) {
        if (root == null) return 0;
        return (gp % 2 == 0 ? root.val : 0) + dfs(root.left, p, root.val) + dfs(root.right, p, root.val);
    }

}
