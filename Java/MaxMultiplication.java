import java.util.Scanner;

public class MaxMultiplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        if (n <= 2) {
            System.out.println(0);
        }
        // max1 >= max2 >= max3; min1 <= min2.
        long max1 = 0, max2 = 0, max3 = 0;
        long min1 = 0, min2 = 0;
        
        for (int i = 0; i < n; ++i) {
            long val = in.nextLong();
            
            if (val > max3) {
                if (val > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = val;
                } else if (val > max2) {
                    max3 = max2;
                    max2 = val;
                } else {
                    max3 = val;
                }
            }
            
            if (val < min2) {
                if (val < min1) {
                    min2 = min1;
                    min1 = val;
                } else {
                    min2 = val;
                }
            }
        }
        in.close();
        System.out.println(Math.max(max1*max2*max3, max1*min1*min2));
    }
}