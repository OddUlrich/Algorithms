import java.util.Scanner;
import java.util.ArrayList;


public class GradeCal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] staStr = in.nextLine().split("\\s+");
        String[] inStr = in.nextLine().split("\\s+");

        ArrayList<String> staStrLst = new ArrayList<>();
        ArrayList<String> inStrLst = new ArrayList<>();

        // Original score.
        int score = staStr.length;
        for (String sta: staStr) {
            staStrLst.add(sta);
        }
        for (int i = 0; i < inStr.length; i++) {
            String inWord = inStr[i];

            // Skip the matched words.
            if (staStrLst.contains(inWord)) {
                staStrLst.remove(inWord);
                continue;
            }

            inStrLst.add(inWord);
        }
        
        score = -2 * staStrLst.size() - inStrLst.size();
        System.out.println(score);
    }
}