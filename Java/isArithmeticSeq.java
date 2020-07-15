import java.util.Scanner;
import java.util.ArrayList;

public class isArithmeticSeq{
    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            int val = in.nextInt();
            insertOrder(lst, val);
        }
        
        int d = lst.get(1) - lst.get(0);
        for (int i = 1; i < lst.size() - 1; ++i) {
            if (lst.get(i+1) - lst.get(i) != d) {
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println("Possible");
    }
    
    public static void insertOrder(ArrayList<Integer> lst, int num) {
        int left = 0, right = lst.size() - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num < lst.get(mid)) {
                right = mid - 1;
            } else if (num > lst.get(mid)) {
                left = mid + 1;
            } else {
                lst.add(mid, num);
                return;
            }
        }
        lst.add(left, num);
    }
}