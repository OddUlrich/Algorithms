import java.util.Scanner;
import java.util.ArrayList;

/**
 * @link: https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71?tpId=137&&tqId=33901&rp=1&ru=/ta/exam-bytedance&qru=/ta/exam-bytedance/question-ranking
 */
public class RobotJump {
    
    public static void main (String[] argv) {
        Scanner in = new Scanner(System.in);
        int n;
        ArrayList<Integer> lst = new ArrayList<>();
        
        
        n = Integer.parseInt(in.nextLine());
        String[] heights = in.nextLine().split("\\s+");
        
        for (int i = 0; i < n; i++) {
            lst.add(Integer.parseInt(heights[i]));
        }

        // System.out.println("Read the input heights");
        // System.out.println(lst);
        
        // 从E_(i+1) = 2*E_i - H_(i+1)可知，E_i = (E_(i+1) + H_(i+1)) / 2
        // 这里除以2去ceiling，要保证不能出现负能量值。
        int energy = 0;
        for (int i = lst.size() - 1; i >= 0; i--) {
            energy = (energy + lst.get(i) + 1) / 2;
        }
        
        System.out.println(energy);
    }
}
