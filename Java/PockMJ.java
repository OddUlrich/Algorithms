import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @link: https://www.nowcoder.com/practice/448127caa21e462f9c9755589a8f2416?tpId=137&&tqId=33897&rp=1&ru=/ta/exam-bytedance&qru=/ta/exam-bytedance/question-ranking
 */

public class PockMJ {
    final private static int[] NUMS = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * 给定当前牌（剩余牌），判断是否能胡。
     * 
     * @param list
     * @return true / false
     */
    private static boolean isWon (ArrayList<Integer> lst) {
        int len = lst.size();

        // 所有牌都按规则正确移除。
        if (len == 0) {
            return true;
        }

        // 从小到大排序。
        Collections.sort(lst);
        int firstNum = lst.get(0);
        int cntFirst = cntExisted(lst, firstNum);

        ArrayList<Integer> list = (ArrayList<Integer>) lst.clone();
        // 1. 第一个牌出现3次以上，去掉该牌后能胡牌。
        if (cntFirst >= 3) {
            list.remove(list.indexOf(firstNum));
            list.remove(list.indexOf(firstNum));
            list.remove(list.indexOf(firstNum));
            if (isWon(list)) return true;
        }

        // 2. 没出现雀头，且第一个牌出现两次，将其作为雀头，剩下的牌能胡牌。
        list = (ArrayList<Integer>) lst.clone();
        if (len % 3 != 0 && cntFirst >= 2) {
            list.remove(list.indexOf(firstNum));
            list.remove(list.indexOf(firstNum));
            if (isWon(list)) return true;
        }

        // 3. 第一个牌能和其他牌组成顺子，去掉该牌后能胡牌。
        list = (ArrayList<Integer>) lst.clone();
        if (list.contains(firstNum+1) && list.contains(firstNum+2)) {
            list.remove(list.indexOf(firstNum));
            list.remove(list.indexOf(firstNum+1));
            list.remove(list.indexOf(firstNum+2));
            if (isWon(list)) return true;
        }

        return false;
    }

    /**
     * 统计某数字在列表中出现的次数。
     * @param list
     * @param num
     * @return cnt
     */
    private static int cntExisted (ArrayList<Integer> list, int num) {
        int cnt = 0;
        for (int i: list) {
            if (i == num) {
                cnt ++;
            }
        }
        return cnt;
    }

    public static void main(String[] argv) {
        int[] numbers = {3, 3, 3, 4, 4, 4, 6, 6, 6, 8, 8, 9, 9};
        ArrayList<Integer> nums = new ArrayList<>();
        for (int num: numbers) {
            nums.add(num);
        }
        
        // Scanner in = new Scanner(System.in);
        // String[] numsStr = in.nextLine().split("\\s+");
        // ArrayList<Integer> nums = new ArrayList<>();

        // for (String numStr: numsStr) {
        //     nums.add(Integer.parseInt(numStr));
        // }

        // System.out.println(nums);

        ArrayList<Integer> res = new ArrayList<>();
            
        // 尝试添加每一个剩下的牌，看是否能胡牌。
        for (int i: NUMS) {
            // 如果当前牌已经出现了4次，则不考虑该数。
            if (cntExisted(nums, i) == 4) {
                continue;
            }
        
            ArrayList<Integer> tmp = (ArrayList<Integer>) nums.clone();
            tmp.add(i);

            if (isWon(tmp)) {
                res.add(i);
            }
        }

        for (int n: res) {
            System.out.print("" + n + " ");
        }
    }
}