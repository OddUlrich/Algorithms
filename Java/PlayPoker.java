public class PlayPoker {

    public static void main(String[] args) {
        String a = "3C8D6H3D";

        System.out.println(Orderofpoker(a));
    }

    public static String Orderofpoker (String x) {
        // write code here
        int len = x.length();
        String s = x;

        StringBuffer res = new StringBuffer(len);
        while (len > 0) {
            if (isPrime(len/2)) {
                res.append(s.substring(0, 2));
                s = s.substring(2);
            } else {
                res.append(s.substring(len - 2, len));
                s = s.substring(0, len - 2);
            }
            len -= 2;
        }
        return res.toString();
    }

    public static boolean isPrime(int n) {
        boolean flag = true;
        if (n < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); ++i) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}