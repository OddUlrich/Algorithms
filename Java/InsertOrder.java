import java.util.Scanner;

public class InsertOrder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        
        if (n == 0) {
            in.nextLine();
        } else {
            for (int i = 0; i < n; ++i) {
                arr[i] = in.nextInt();
            }
        }
        
        int num = in.nextInt();
        boolean bPrinted = false;
        
        in.close();
        
        if (n == 0) {
            System.out.print(num);
            return;
        }
        
        if (num <= arr[0]) {
            System.out.print(num);
            bPrinted = true;
        } else {
            System.out.print(arr[0]);
        }
        for (int i = 1; i < n; ++i) {
            if (!bPrinted && num <= arr[i]) {
                System.out.print(" " + num);
                bPrinted = true;
            }
            
            System.out.print(" " + arr[i]);
        }
        if (!bPrinted) {
            System.out.print(" " + num);
        }
    }
}