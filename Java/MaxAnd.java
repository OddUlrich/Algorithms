import java.util.Scanner;

public class MaxAnd{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (in.hasNext()) {
            int n = in.nextInt();
            int max = 0;
            for (int i = 0; i < n; ++i) {  
                int num = in.nextInt();
                max = Math.max(max, num);
            }
            System.out.println(max);
        }
        in.close();
    }
}