import java.util.Scanner;
import java.util.ArrayList;

public class SumTree {
    static ArrayList<Node> nodeList;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int Q = in.nextInt();
        nodeList = new ArrayList<>();
        int idx;
        long weight;
        // N nodes.
        for (int i = 1; i <= N; ++i) {
            idx = i;
            weight = in.nextLong();
            Node node = new Node(idx, weight);
            nodeList.add(node);
        }
        
        // N - 1 connections.
        for (int i = 1; i < N; ++i) {
            Node node = nodeList.get(in.nextInt() - 1);
            node.connectNode(nodeList.get(in.nextInt() - 1));
        }
        
        // Q operations.
        for (int i = 1; i <= Q; ++i) {
            int opt = in.nextInt();
            if (opt == 1) {
                int nodeIdx = in.nextInt();
                Node node = nodeList.get(nodeIdx - 1);
                long y = in.nextLong();
                node.addWeight(y);
            } else {
                int nodeIdx = in.nextInt();
                Node node = nodeList.get(nodeIdx - 1);
                System.out.println(node.squaredSum() % 23333);
                
            }
        }
        in.close();
    }
    
    
    public static class Node {
        int idx;
        long weight;
        ArrayList<Node> subNode;
        
        public Node(int idx, long weight) {
            this.idx = idx;
            this.weight = weight;
            subNode = new ArrayList<>();
        }
        
        void connectNode(Node node) {
            subNode.add(node);
        }
        
        void addWeight(long weight){
            this.weight += weight;
            for (Node node: subNode) {
                node.addWeight(weight);
            }
        }
        
        long squaredSum() {
            long sum = 0;
            sum += this.weight * this.weight;
            for (Node node: subNode) {
                sum += node.squaredSum();
            }
            return sum;
        }
    }
}