import java.util.Scanner;
import java.util.HashSet;

public class Notice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] teams = new int[m][];
        for (int i = 0; i < m; i++) {
            int len = sc.nextInt();
            int[] team = new int[len];
            for (int j = 0; j < len; j++) {
                team[j] = sc.nextInt();
            }
            teams[i] = team;
        }
        sc.close();
        HashSet<Integer> knows = new HashSet<>();
        knows.add(0);
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < teams[i].length; j++) {
                    if (knows.contains(teams[i][j])) {
                        for (int j2 = 0; j2 < teams[i].length; j2++) {
                            knows.add(teams[i][j2]);
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(knows.size());
    }
}