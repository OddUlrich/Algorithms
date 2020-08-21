import java.util.Scanner;

public class StringDigit{
        public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            int len = s.length();
            int i = 0, j = -1;
            
            while (i < len && j <= len) {
                if (Character.isDigit(s.charAt(i))) {
                    j = i + 1; 
                    while (j < len && Character.isDigit(s.charAt(j))) ++j;
                    System.out.print("" + Long.parseLong(s.substring(i, j)) + " ");
                    i = j;
                } else {
                    ++i;
                }
            }
            
            System.out.println();
        }
        in.close();
    }
}