import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class InsertOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int N = in.nextInt();
        for (int i = 0; i < N; ++i) {
            list.add(in.nextInt());
        }
        int num = in.nextInt();
        in.close();
        list.add(num);
        Collections.sort(list);
        System.out.println(list.toString());
    }
}