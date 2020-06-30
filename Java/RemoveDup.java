import java.util.ArrayList;
import java.util.Scanner;

public class RemoveDup {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        String inStr = in.nextLine();
        
        StringBuffer ret = new StringBuffer();
        char[] chs = inStr.toCharArray();
        
        ArrayList<Character> lst = new ArrayList<>();
        for (char c: chs) {
            if (!lst.contains(c)) {
                ret.append(c);
                lst.add(c);
            }
        }
        System.out.println(ret);

    }
}