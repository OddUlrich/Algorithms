import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class APlusB{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        
        if (n % 2 != 0)  System.out.println(0);
        
        int a = n / 100 / 2;
        if (a == 0)  System.out.println(0);
        
        // Save value abc.
        ArrayList<Integer> res = new ArrayList<>();
        int b;
        int[] cs = new int[]{n % 10 / 2, (n % 10 + 10) / 2};
        
        for (int c: cs) {
            int acc = 100*a + 10*c + c;
            if (c != a) {
                b = (n - acc - 100*a - c) / 10;
                if (b != a && b != c && (n-acc)/100 == a) {
                    res.add(n - acc);
                }
            }
        }
        
        if (res.size() == 0)  System.out.println(0);
        else {
            System.out.println(res.size());
            Collections.sort(res);
            for (int abc: res) {
                System.out.println("" + abc + " " + (n - abc));
            }
        }
    }
}