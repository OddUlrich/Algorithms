import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class msgCnt{

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> cntList = new ArrayList<>();

        String inputStr = in.nextLine();
        in.close();
        String[] repoAndLine = inputStr.split("\\s+");
        for (int i = 0; i < repoAndLine.length / 2; i++) {
            String[] repo = repoAndLine[2*i].split("\\\\");
            String line = repoAndLine[2*i+1];
            String name = repo[repo.length - 1];

            String key = name + "-" + line;
            if (nameList.contains(key)) {
                int idx = nameList.indexOf(key);
                int val = cntList.get(idx);
                cntList.set(idx, val + 1);
            } else {
                nameList.add(key);
                cntList.add(1);
            }
        }

        ArrayList<Integer> retList = new ArrayList<>();
        retList = (ArrayList) cntList.clone();
        Collections.sort(retList);
        int len = retList.size();
        for (int i = 0; i < 8; i++) {
            int val = retList.get(len - i - 1);
            int idx = cntList.indexOf(val);
            String file = nameList.get(idx);

            String name = file.split("-")[0];
            String line = file.split("-")[1];

            if (name.length() > 16) {
                System.out.println(name.substring(name.length() - 16 - 1) + " " + line + " " + val);
            } else {
                System.out.println(name + " " + line + " " + val);
            }

            nameList.remove(idx);
            cntList.remove(idx);

            if (nameList.isEmpty()) {
                break;
            }
        }
    }
}