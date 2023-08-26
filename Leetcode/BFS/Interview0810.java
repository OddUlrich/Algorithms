import java.util.LinkedList;
import java.util.Queue;

public class Interview0810 {

    public class Node {
        public int r;
        public int c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (image == null) {
            return null;
        }

        int rc = image.length;
        int cc = image[0].length;
        int originColor = image[sr][sc];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sr, sc));

        while (!q.isEmpty()) {
            Node node = q.poll();
            image[node.r][node.c] = newColor;

            if (node.r - 1 >= 0 && image[node.r - 1][node.c] == originColor) {
                q.offer(new Node(node.r - 1, node.c));
            }
            if (node.r + 1 < rc && image[node.r + 1][node.c] == originColor) {
                q.offer(new Node(node.r + 1, node.c));
            }
            if (node.c - 1 >= 0 && image[node.r][node.c - 1] == originColor) {
                q.offer(new Node(node.r, node.c - 1));
            }
            if (node.c + 1 < cc && image[node.r][node.c + 1] == originColor) {
                q.offer(new Node(node.r, node.c + 1));
            }
        }

        return image;
    }


}
