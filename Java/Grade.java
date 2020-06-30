import java.util.Scanner;

public class Grade {

    static int findMax (int[] grades, int start, int end) {
        int max = grades[start];
        for (int i = start+1; i <= end; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        } 
        return max;
    }

    public static void main (String[] argv) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        int[] grades = new int[N];
        for (int i = 0; i < N; i++) {
            grades[i] = in.nextInt();
        }

        for (int i = 0; i < M; i++) {
            String str = in.next();
            
            if (str.equals("Q")) {
                int max = findMax(grades, in.nextInt()-1, in.nextInt()-1);
                System.out.println(max);
            } else if (str.equals("U")) {
                int idx = in.nextInt()-1;
                int num = in.nextInt();
                grades[idx] = num;
            } else {
                continue;
            }
        }

        in.close();
    }
}