import java.util.*;

public class NumSplit {

    public int numSplits(String s) {
        int cnt = 0;
        int n = s.length();
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (!set1.contains(c)) {
                set1.add(c);
                ++cnt;
            }
            nums1[i] = cnt;
        }
        cnt = 0;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (!set2.contains(c)) {
                set2.add(c);
                ++cnt;
            }
            nums2[i] = cnt;
        }

        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums1[i] == nums2[i + 1]) {
                ++res;
            }
        }
        return res;

    }
}