/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Arrau2BST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return solve(nums, 0, nums.length - 1);
    }

    public TreeNode solve(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = solve(nums, left, mid - 1);
        root.right = solve(nums, mid + 1, right);
        return root;
    }
}