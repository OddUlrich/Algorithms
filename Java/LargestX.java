import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @link: https://www.nowcoder.com/practice/089dbc5ec7ac468589ce143d43248949?tpId=137&&tqId=33890&rp=1&ru=/ta/exam-bytedance&qru=/ta/exam-bytedance/question-ranking
 */

public class LargestX {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        Map<Integer, Integer> xyMap = new HashMap<>();
        ArrayList<Integer> xList = new ArrayList<>();

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            xList.add(x);
            xyMap.put(x, in.nextInt());
        }

        // 排序。
        Collections.sort(xList);

        // x最大的点必然是“最大的”。
        ArrayList<Integer> retList = new ArrayList<>();
        int size = xList.size();
        retList.add(xList.get(size-1));

        // 从x第二大的点开始判断。
        for (int i = size - 2; i >= 0; i--) {
            // 当前点的x比上一个“最大点”小，对应的y必然较大，才为“最大点”。
            int tmpSize = retList.size();
            if (xyMap.get(xList.get(i)) >= xyMap.get(retList.get(tmpSize - 1))) {
                retList.add(xList.get(i));
            }
        }

        // 输出结果。
        for (int i = retList.size() - 1; i >= 0; i--) {
            System.out.print(retList.get(i));
            System.out.println(" " + xyMap.get(retList.get(i)));
        }
    }
}