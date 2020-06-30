import java.util.Scanner;

public class SpyPosition {
    static int calCombine (int num) {

        if (num == 0) {
            return 0;
        }
        // 只有3个间谍，也就是相当于num个里取3个的组合数。
        return num * (num-1) * (num-2) / 6;
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);

        String[] numAndDist = in.nextLine().split("\\s+");
        int num = Integer.parseInt(numAndDist[0]); 
        int maxDist = Integer.parseInt(numAndDist[1]);;
        String[] loc = in.nextLine().split("\\s+");

        if (num <= 2) { // 建筑物数量不足3，不满足3个卧底不能埋伏在同一地点的条件。
            System.out.println("0");
            return;
        }

        int sum = 0;
        int lastLoc = 0;
        int curLoc = 0;
        for (int i = 0; i < num-2; i++) {
            int maxNum = 0;

            for (int k = i+2; k < num; k++) {
                int len = Integer.parseInt(loc[k]) - Integer.parseInt(loc[i]);
                if (len <= maxDist && k-i+1 > maxNum) {
                    maxNum = k-i+1;
                    curLoc = k;
                }
            }
            if (curLoc > lastLoc) {
                // 组合运算。
                sum += calCombine(maxNum);
                lastLoc = curLoc;
            }
        }
        System.out.println(sum);
    }
}