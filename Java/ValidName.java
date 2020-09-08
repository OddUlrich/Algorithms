import java.util.Scanner;

public class ValidName {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; ++i) {
            String name = in.next();

            if (isValid(name))  System.out.println("Accept");
            else  System.out.println("Wrong");
        }
        in.close();
    }

    public static boolean isValid(String s) {
        int len = s.length();
        if (len < 2)  return false;

        boolean hasDigit = false;

        char[] cArr = s.toCharArray();

        // Case 1.
        if (!Character.isLetter(cArr[0]))  return false;

        // Case 2 and 3.
        for (char c: cArr) {
            if (Character.isDigit(c))  hasDigit = true;
            if (!Character.isLetterOrDigit(c))  return false;
        }

        return hasDigit;
    } 
}