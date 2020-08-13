import java.util.*;


public class commute {
    public static void main(String[] args) {
        System.out.println(icecream(2, 3, 10, new int[]{10, 30, 40}).toString());
    }
    /**
     * 两个数表示答案
     * @param n int整型 一次运输的冰激凌数量
     * @param m int整型 总冰激凌数
     * @param t int整型 一次运输的时间
     * @param c int整型一维数组 表示每个冰激凌制作好时间<1e4
     * @return int整型一维数组
     */
    public static int[] icecream (int n, int m, int t, int[] c) {
        // write code here
        int[] res = new int[2];
        List<Integer> time = new ArrayList<>();
        for (int i = 0; i < c.length; ++i)  time.add(c[i]);
        Collections.sort(time);
        
        if (n >= m) {
            res[0] = time.get(m-1) + t;
            res[1] = 1;
        } else {
            int commute = m / n;
            
            int tt = 0;
            int i;
            
            for (i = 0; i < commute; ++i) {
                if (i == 0)  tt = 2*tt + time.get(n-1);
                else {
                    tt = 2*t + Math.max(time.get((i+1)*n - 1), tt);
                }
            }
            
            if (m % n != 0) {
                tt = t + Math.max(time.get(m-1), tt);
                ++commute;
            } else {
                tt -= t;
            }
            res[0] = tt;
            res[1] = commute;
        }
        
        
        return res;
    }
}