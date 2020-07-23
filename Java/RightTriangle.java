import java.util.Scanner;

public class RightTriangle {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        for (int i = 1; i <= n; ++i) {
            int cnt = n;
            while (cnt > i) {
                --cnt;
                System.out.print("  ");
            }
            while (cnt > 0) {
                --cnt;
                System.out.print("* ");
            }
            System.out.print("\n");
        }
        in.close();
    }
}