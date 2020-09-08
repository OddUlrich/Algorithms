import java.util.PriorityQueue;

class MaxK {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for (int num: nums) {
            if (q.size() == k) {
                if (num > q.peek()) {
                    q.poll();
                    q.offer(num);
                }
            } else  q.offer(num);
        }

        return q.poll();
    }
}