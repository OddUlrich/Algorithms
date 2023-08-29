import java.util.ArrayList;
import java.util.List;

public class Solution958 {

    public boolean isCompleteTree(TreeNode root) {

        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(root, 1));

        int idx = 0;
        while (idx < nodeList.size()) {
            Node node = nodeList.get(idx);
            if (node != null && node.treeNode != null) {
                nodeList.add(new Node(node.treeNode.left, node.val * 2));
                nodeList.add(new Node(node.treeNode.right, node.val * 2 + 1));
            }
            idx++;
        }

        return nodeList.get(idx - 1).val == nodeList.size();
    }

    private class Node {
        public TreeNode treeNode;
        public int val;

        public Node(TreeNode treeNode, int val) {
            this.treeNode = treeNode;
            this.val = val;
        }
    }

}
