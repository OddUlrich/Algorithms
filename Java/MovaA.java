public class MovaA {

    public static void main(String[] args) {
        String s = "abcavv";

        StringBuffer sBuff = new StringBuffer(s.length());
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'a') {
                cnt++;
            } else {
                sBuff.append(s.charAt(i));
            }
        }

        for (int i = 0; i < cnt; ++i) {
            sBuff.append('a');
        }

        System.out.println(sBuff.toString());
    }
}