
public class MaxHexInStr {
    public static int solve (String s) {
        int num = 0;
        int bitCnt = 0;
        int max = 0;
        
        // 从后往前遍历字符串。
        for (int i = s.length()-1; i >= 0; i--) {
            // 检查是否已达到 int 型上限，即 32 bits。
            if (bitCnt == 32) {
                bitCnt--;
                num /= 16;
            }

            // 解析字符。
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int dig = Integer.parseInt(s.substring(i, i+1));
                num += dig * Math.pow(16, bitCnt);
                bitCnt++;
            } else if (c >= 'A' && c <= 'F') {
                int dig = c - 'A' + 10;
                num += dig * Math.pow(16, bitCnt);
                bitCnt++;
            } else {
                num = 0;
                bitCnt = 0;
            }

            // 更新最大值
            max = Math.max(max, num);
        }
        return max;
    }
    public static void main(String[] argv) {
        String s = "012345BZ16";
        System.out.println(solve(s));
    }
}