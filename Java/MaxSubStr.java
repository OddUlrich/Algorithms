import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class MaxSubStr {
    public static void main(String[] args) {
        String[] s = new String[]{"adefaddaccc", "abbaccd"};

        for (int i = 0; i < s.length; ++i) {
            System.out.println(maxNumOfSubstrings(s[i]).toString());
        }
    }

    public static List<String> maxNumOfSubstrings(String s) {
        List<String> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();

        int i = 0;
        while (i < n) {
            char c = s.charAt(i);

            // Save the existing character.
            if (map.containsKey(c)) {
                ++i;
                continue;
            } else {
                map.put(c, 1);
            }

            // Locate the same character.
            int j = i+1;
            while (j < n) {
                if (s.charAt(j) == c) {
                    ++j;
                } else {
                    break;
                }
            }

            if (j == n) {
                res.add(s.substring(i, j));
                break;
            } else {
                if (!s.substring(j).contains(""+c)) {
                    res.add(s.substring(i, j));
                    i = j;
                } else {
                    ++i;
                }
            }
        }

        if (res.size() == 0) {
            res.add(s);
        }

        return res;
    }

}