import java.util.*;



public class CloseCircle {
    public static void main(String[] args) {
        int[] a = {2, 1, 1, 3, 2};
        int[] b = {30, 10, 20};

        System.out.println(solve(a.length, a));
        System.out.println(solve(b.length, b));
    }


    /**
     * ​返回按照这些花排成一个圆的序列中最小的“丑陋度”
     * @param n int整型 花的数量
     * @param array int整型一维数组 花的高度数组
     * @return int整型
     */
    public static int solve (int n, int[] array) {
        // write code here
        List<Integer> list = new ArrayList<>();
        
        for (int num: array) {
            list.add(num);
        }
        Collections.sort(list);
        
        int[] seq = new int[n];
        int mid = n / 2;
        
        int res = 0;
        seq[mid] = list.get(0);
        for (int i = 1; mid - i >= 0; ++i) {
            seq[mid - i] = list.get(2*i - 1);
            res = Math.max(res, seq[mid - i] - seq[mid - i + 1]);
            
            if (mid + i < n) {
                seq[mid + i] = list.get(2*i);
                res = Math.max(res, seq[mid + i] - seq[mid + i - 1]);
            }
        }
        res = Math.max(res, Math.abs(seq[0] - seq[n - 1]));
        return res;
    }
}