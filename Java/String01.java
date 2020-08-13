import java.util.*;


public class String01 {
    public static void main(String[] args) {
        System.out.println(solve("00000000000000000000"));
        System.out.println(solve("1111111111111111111"));
        System.out.println(solve("01010101001011101001010010001010101000101001010101"));
        System.out.println(solve("111010001111001001111101001010010001010101000101001010101"));
    }

    /**
     * 
     * @param str string字符串 初始字符串
     * @return string字符串
     */
    public static String solve (String str) {
        // write code here
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < str.length(); ++i) {
            char cur = str.charAt(i);
            if (stack.empty())  stack.push(cur);
            else {
                while (!stack.empty()) {
                    char prev = stack.peek();
                    if (prev == cur && prev == '0') {
                        stack.pop();
                        cur = '1';
                    } else if (prev == cur && prev == '1') {
                        stack.pop();
                        break;
                    } else {
                        stack.push(cur);
                        break;
                    }
                }
            }
        }
        
        StringBuffer buff = new StringBuffer(stack.size());
        while (!stack.empty()) {
            buff.insert(0, stack.pop());
        }
        
        
        return buff.toString();
    }
}