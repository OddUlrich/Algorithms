import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        in.close();
        
        int i = 0;
        int j = s.length() - 1;
        int skip = -1;  // 删除的字符位置。
        String res = s;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                ++i;
                --j;
            } else {
                // 之前已经删除过字符。
                if (skip != -1) {
                    res = "false";
                    break;
                }
                
                // 已经到达中间点。
                if (i + 1 == j) {
                    res = s.substring(0, i) + s.substring(j);  // 跳过 i。
                    break;
                } else {
                    // 优先考虑删左边，且要看似可行。
                    if (s.charAt(i+1) == s.charAt(j)) {
                        skip = i;
                        i = i + 2;
                        j = j - 1;
                    } else if (s.charAt(i) == s.charAt(j-1)) {
                        skip = j;
                        i = i + 1;
                        j = j - 2;
                    } else {
                        res = "false";
                        break;
                    }
                }
            }
        }
        System.out.println(res);
    }
}