public class ResetStr {
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] cs = new char[n];

        for (int i = 0; i < n; ++i) {
            cs[indices[i]] = s.charAt(i);
        }

        return String.valueOf(data);
    }
}