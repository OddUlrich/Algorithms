import java.util.*;

class Solution590_2 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                res.add(node.val);
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
