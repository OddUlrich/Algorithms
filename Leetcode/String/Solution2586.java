import java.util.HashSet;
import java.util.Set;

class Solution2586 {
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> vowels = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            String word = words[i];
            if (vowels.contains(word.charAt(0)) && vowels.contains(word.charAt(word.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }
}
