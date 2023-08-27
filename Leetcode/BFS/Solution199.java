import java.util.*;

public class Solution199 {

    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> depthValueMap = new HashMap<>(16);

        // same step in both node queue and depth queue
        int maxDepth = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.offer(root);
        depthQueue.offer(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer curDepth = depthQueue.poll();

            if (node != null && curDepth != null) {
                // from the left to the right, overwrite the value within the same depth.
                depthValueMap.put(curDepth, node.val);

                nodeQueue.offer(node.left);
                nodeQueue.offer(node.right);
                depthQueue.offer(curDepth + 1);
                depthQueue.offer(curDepth + 1);

                maxDepth = Math.max(maxDepth, curDepth);
            }
        }

        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i <= maxDepth; i++) {
            valueList.add(depthValueMap.get(i));
        }

        return valueList;
    }
}
