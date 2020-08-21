import java.util.*;

public class Step {
    public static void main(String[] args) {
        int[] first = new int[]{1,2,3,4,5};
        int[] second = new int[]{5,4,3,2,1};
        System.out.println(arrange(first, second));
    }

    public static int arrange (int[] firstRow, int[] secondRow) {
        // write code here
        int cnt = 0;
        int n = firstRow.length;
        int i;
        for (i = 0; i < n - 1; ++i) {
            if (firstRow[i] < firstRow[i+1] || secondRow[i] > secondRow[i+1]) {
                if (firstRow[i] < secondRow[i+1] && secondRow[i] > firstRow[i+1]){
                    int tmp = firstRow[i];
                    firstRow[i] = secondRow[i];
                    secondRow[i] = tmp;
                    ++cnt;
                } else if (firstRow[i] == secondRow[i])  continue;
                else  return -1;
            }
        }
        --i;
        if (firstRow[i] < firstRow[i+1] || secondRow[i] > secondRow[i+1]) {
            if (firstRow[i] < secondRow[i+1] && secondRow[i] > firstRow[i+1]){
                int tmp = firstRow[i];
                firstRow[i] = secondRow[i];
                secondRow[i] = tmp;
                ++cnt;
            } else if (firstRow[i] != secondRow[i])  return -1;
        }
        return cnt;
    }

}