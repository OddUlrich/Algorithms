import java.util.Scanner;

public class ArrIdx {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextLong();
        }
        in.close();
        
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[j] > a[i]) {
                    b[i] = j+1;
                    break;
                }
            }
        }
        System.out.print(b[0]);
        for (int i = 1; i < n - 1; ++i) {
            System.out.print(" " + b[i]);
        }
        
    }
}