import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MaxOrder{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(m);

        int[] visit = new int[n];
        ArrayList<Integer> price = new ArrayList<>();
        
        for (int i = 0; i < n; ++i) {
            int v = in.nextInt();
            int w = in.nextInt();
            price.add(v + 2*w);
            visit[i] = 0;
            if (minHeap.size() >= m) minHeap.poll();
            minHeap.offer(v + 2*w);
        }
        in.close();
        
        for (int i = 0; i < m; ++i) {
            int p = minHeap.poll();

            int idx = price.indexOf(p);
            if (visit[idx] == 0) {
                visit[idx] = 1;
            } else {
                for (int j = idx+1; j < n; ++j) {
                    if (price.get(j) == p && visit[j] == 0) {
                        visit[j] = 1;
                        idx = j;
                        break;
                    }
                }
            }

            if (i == 0)  System.out.print(idx+1);
            else  System.out.print(" " + (idx+1));
        }

        System.out.println();        
    }
}