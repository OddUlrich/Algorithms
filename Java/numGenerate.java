import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class numGenerate {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int N = in.nextInt();
            ArrayList<Integer> lst = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int num = in.nextInt();
                if (!lst.contains(num)) {
                    lst.add(num);
                }
            }
            Collections.sort(lst);
            for (int n: lst) {
                System.out.println(n);
            }
        }
    }
}