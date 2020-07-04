import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class Regroup {
    final static String HIGH = "bdfhkl";
    final static String MID = "aceimnorstuvwxz";
    final static String LOW = "gjpqy";
    
    public static void sortAndPrint(ArrayList<Character> lst) {
        if (lst.size() == 0) {
            System.out.println("null");
        } else {
            Collections.sort(lst);
            StringBuffer buf = new StringBuffer();

            for (char c: lst) {
                buf.append(c);
            }
            System.out.println(buf);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inStr = in.nextLine();
        in.close();

        ArrayList<Character> highLst = new ArrayList<>();
        ArrayList<Character> midLst = new ArrayList<>();
        ArrayList<Character> lowLst = new ArrayList<>();

        // Group the input characters.
        for (int i = 0; i < inStr.length(); i++) {
            String s = inStr.substring(i, i+1);

            if (HIGH.contains(s)) {
                highLst.add(s.charAt(0));
            } else if (MID.contains(s)) {
                midLst.add(s.charAt(0));
            } else if (LOW.contains(s)) {
                lowLst.add(s.charAt(0));
            }
        } 

        // Print out the sorted results.
        sortAndPrint(highLst);
        sortAndPrint(midLst);
        sortAndPrint(lowLst);
    }
}