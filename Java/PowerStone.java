import java.util.Scanner;

public class PowerStone{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; ++i) {
            long n = in.nextLong();
            if (solve(n, true))  System.out.println("Alan");
            else  System.out.println("Frame");
        }
    }
    
    public static boolean solve(long n, boolean flag) {
        if (n == 1)  return flag;
        long e = (long)(Math.log(n) / Math.log(2));
        long l = (long)Math.pow(2, e);
        if (l == n)  return flag;
        else {
            return solve((long)Math.pow(2, Math.log(n-1)), !flag);
        }
    }
}