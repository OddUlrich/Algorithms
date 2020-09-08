import java.util.PriorityQueue;
import java.util.Comparator;


class MaxKPoint {
    public static void main(String[] args) {
        int[][] points = new int[][]{{3,3},{5,-1},{-2,4}};
        int k = 2;
        System.out.println(kClosest(points, k));
    }

    public static int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<double[]> maxHeap = new PriorityQueue<double[]>(new Comparator<double[]>() {
            public int compare(double[] p1, double[] p2) {
                return (int) (p2[0] - p1[0]);
            }
        });

        for (int[] point: points) {
            double newDis = Math.pow(point[0], 2) + Math.pow(point[1], 2);
            if (maxHeap.size() == K) {
                double oldDis = maxHeap.peek()[0];
                if (newDis < oldDis) {
                    maxHeap.poll();
                    maxHeap.offer(new double[]{newDis, point[0], point[1]});
                }
            } else  maxHeap.offer(new double[]{newDis, point[0], point[1]});
        }

        for (int i = 0; i < K; ++i) {
            double[] node = maxHeap.poll();
            res[i][0] = (int)node[1];
            res[i][1] = (int)node[2];
        }

        return res;
    }
}