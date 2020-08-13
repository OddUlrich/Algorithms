import java.util.*;


public class ShareWater {
    public static void main(String[] args) {
        System.out.println(solve(3, 7, new int[]{9, 4, 9}));
        System.out.println(solve(2, 5, new int[]{4, 3}));
    }

    /**
     * 返回重新分配后，满足牛牛要求的水量的瓶子最多的数量
     * @param n int整型 瓶子的数量
     * @param x int整型 牛牛的对瓶中的水量要求
     * @param a int整型一维数组 每个瓶子中的含水量
     * @return int整型
     */
    public static int solve (int n, int x, int[] a) {
        // write code here
        List<Integer> list = new ArrayList<>();
        for (int num: a)  list.add(num);
        Collections.sort(list);
        
        int cnt = 0;
        int i = list.size() - 1;
        while (i >= 0 && cnt >= 0) {
            int del = list.get(i) - x;
            cnt += del;
            if (i == list.size() - 1 && cnt < 0)  return 0;
            --i;
        }
        if (i < 0 && cnt >= 0)  return list.size();
        else return ++i;
    }
}