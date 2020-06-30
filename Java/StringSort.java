import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class StringSort {
    public static void main (String[] argv) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        ArrayList<String> strLst = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            strLst.add(in.next());
        }
        in.close();
        
        Collections.sort(strLst);
        String[] strArr = new String[N];

        for (int i = 0; i < N; i++) {
            strArr[i] = strLst.get(i);
        }
        String ret = String.join(" ", strArr);
        System.out.println(ret);
    }
}