import java.util.Scanner;

public class FloatBase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float num = in.nextFloat();
        System.out.println((int) num % 10);
    }
}