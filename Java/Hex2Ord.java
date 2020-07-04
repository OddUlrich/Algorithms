import java.util.Scanner;

public class Hex2Ord {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String hex = in.nextLine().substring(2);
            int num = 0;

            int len = hex.length();
            for (int i = len-1; i >= 0; i--) {
                char c = hex.charAt(i);
                if (c >= '0' && c <= '9') {
                    num += (c - '0') * Math.pow(16, len-1-i);
                } else if (c >= 'A' && c <= 'F') {
                    num += (c - 'A' + 10) * Math.pow(16, len-1-i);
                } else {
                    num = 0;
                }
            }
            System.out.println(num);
        }

    }
}