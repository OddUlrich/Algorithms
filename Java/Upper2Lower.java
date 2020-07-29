import java.util.Scanner;

public class Upper2Lower {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNext()) {
            String s = in.next();
            char c = s.charAt(0);
            System.out.println((char)(c + 32));
        }
        in.close();
    }
}