import java.util.Scanner;

public class LuckyPocket {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long m = in.nextLong();
        long n = in.nextLong();
        long T = in.nextLong();
        
        for (long i = 0; i < T; ++i) {
            long pos = in.nextLong();
            
            if (pos > m) {
                System.out.println(0);
                continue;
            }
            
            if (n < 2*n/m) {
                if (pos == n) {
                    System.out.println(m);
                } else {
                    System.out.println(0);
                }
                continue;
            }
            
            System.out.println(n/m);
        }
        in.close();
    }
}