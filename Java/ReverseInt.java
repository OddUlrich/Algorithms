import java.util.Scanner;

public class ReverseInt {    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int res = 0;
        
        in.close();
        while (n > 0) {
            res = res*10 + n%10;
            n /= 10;
        }
        System.out.printf("%04d", res);
    }
}