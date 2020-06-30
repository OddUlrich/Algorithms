import java.util.Scanner;

/**
 * @link: https://www.nowcoder.com/practice/42852fd7045c442192fa89404ab42e92?tpId=137&&tqId=33895&rp=1&ru=/ta/exam-bytedance&qru=/ta/exam-bytedance/question-ranking
 */

public class StringRevise {

    static String revise (String inStr) {
        StringBuffer str = new StringBuffer();

        int p = 0;
        while (p < inStr.length()) {
            str.append(inStr.charAt(p));
            
            int len = str.length();
            if (len >= 3) { // 检查连续3个字符重复。
                if (str.charAt(len-1) == str.charAt(len-2) && str.charAt(len-2) == str.charAt(len-3)) {
                    str.deleteCharAt(len-1);
                }
            }

            len = str.length();
            if (len >= 4) { // 检查AABB型重复。
                // AAAA型重复已在上一个条件被排查。
                if (str.charAt(len-1) == str.charAt(len-2) && str.charAt(len-3) == str.charAt(len-4)) {
                    str.deleteCharAt(len-1);
                }
            }

            p++;    // 遍历指针向前一位。
        }

        return str.toString();
    }

    public static void main (String[] argv) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for (int line = 0; line < n; line++) {
            String inStr = in.next();
            String outStr = revise(inStr);
            System.out.println(outStr);
        }
    }
    
}