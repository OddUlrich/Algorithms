import java.util.Scanner;

public class SparklingBottle {
    public static int blankWithFull(int nBlank) {
        int ret;

        if (nBlank <= 1) {
            ret = 0;
        } else if (nBlank == 2) {
            ret = 1;
        } else {
            ret = nBlank / 3 + blankWithFull(nBlank % 3 + nBlank / 3);
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (!in.hasNext("0")) {
            int n = in.nextInt();
            System.out.println(blankWithFull(n));
        }
        in.close();
    }
}