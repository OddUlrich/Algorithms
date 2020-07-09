import java.util.Scanner;

public class AddUp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();
        
        String s1 = s.split("\\s+")[0];
        String s2 = s.split("\\s+")[1];
        
        int a = Integer.parseInt(s1.substring(2), 16);
        int b = Integer.parseInt(s2.substring(1), 8);
        System.out.println(a + b);
    }
}