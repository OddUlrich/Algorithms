import java.util.Scanner;
import java.util.HashMap;
import java.util.Stack;

public class Storage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        HashMap<Integer, Stack<Integer>> store = new HashMap<>();
        
        for (int i = 1; i <= N; ++i) {
            Stack<Integer> stack = new Stack();
            stack.push(in.nextInt());
            store.put(i, stack);
        }
        for (int i = 0; i < M; ++i) {
            int K = in.nextInt();
            in.nextLine();
            int left = 0, right = 0;
            int total = 0;
            // 第 i 个人的操作过程。
            for (int j = 0; j < K; ++j) {
                String[] op = in.nextLine().split("\\s++");
                if (op[0].equals("left")) {
                    if (op[1].equals("take")) {
                        stack<Integer> tmpStack = store.get(Integer.parseInt(op[2]));
                        if (tmpStack.size() == 1)  left = tmpStack.peek();
                        else  left = tmpStack.pop();
                    }
                    else if (op[1].equals("return")) {
                        stack<Integer> tmpStack = store.get(Integer.parseInt(op[2]));
                        tmpStack.push(left);
                        left = 0;
                    }
                    else if (op[1].equals("keep")) {
                        total += price[left];
                        left = 0;
                    }
                } else if (op[0].equals("right")) {
                    if (op[1].equals("take")) {
                        stack<Integer> tmpStack = store.get(Integer.parseInt(op[2]));
                        if (tmpStack.size() == 1)  right = tmpStack.peek();
                        else  right = tmpStack.pop();
                    }
                    else if (op[1].equals("return")) {
                        stack<Integer> tmpStack = store.get(Integer.parseInt(op[2]));
                        tmpStack.push(right);
                        right = 0;
                    }
                    else if (op[1].equals("keep")) {
                        total += price[right];
                        right = 0;
                    }
                }
            }
            
            // 计算费用并输出结果。
            total += left + right;
            System.out.println(total);
        }
        in.close();
    }
}