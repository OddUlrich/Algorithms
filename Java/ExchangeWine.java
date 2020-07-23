
public class ExchangeWine {
    public static void main(String[] args) {
        int[] a = new int[]{9, 3, 15, 4, 5, 5, 2, 3};

        for (int i = 0; i < a.length; i += 2) {
            System.out.println(numWaterBottles(a[i], a[i+1]));
        }
        
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        if (numBottles < numExchange) {
            return numBottles;
        }
        int wine = numBottles / numExchange;
        int rest = numBottles % numExchange;

        return numBottles + wine + Exchange(wine + rest, numExchange);
    }

    public static int Exchange(int bottles, int numExchange) {
        if (bottles < numExchange) {
            return 0;
        }

        int wine = bottles / numExchange;
        int rest = bottles % numExchange;
        return wine + Exchange(wine + rest, numExchange);
    }
}