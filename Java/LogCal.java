public class LogCal {
    public static void main(String[] args) {
        double a, b;
        double c, d;

        a = (4.0*Math.E - Math.sqrt(1 + 8*Math.E)) / 8 / Math.E;
        b = (-1.0 + Math.sqrt(1 + 8*Math.E)) / 4 / Math.E;
        System.out.println(a*Math.log(a) + b*Math.log(b));

        c = 1.0 / Math.E;
        d = 1.0 - 2.0/Math.E;
        System.out.println(c*Math.log(c) + d*Math.log(d));
    }
}