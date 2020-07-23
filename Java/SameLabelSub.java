public class SameLabelSub {
    public static void main(String[] args) {

    }

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] res = new int[n];
        int[][] edgeMap = new int[n][n];

        // Create connection projection.
        for (int i = 0; i < n - 1; ++i) {
            edgeMap[edges[i][0]][edges[i][1]] = 1;
        }
        
        for (int i = 0; i < n; ++i) {
            res[i] = 1 + countSub(i, labels.charAt(i), edgeMap, labels);
        }
        return res;
    }
    public static int countSub(int node, char c, int[][] edgeMap, String labels) {
        int cnt = 0;
        int n = edgeMap.length;
        
        for (int i = 1; i < n; ++i) {
            if (edgeMap[node][i] == 1) {
                if (labels.charAt(i) == c) {
                    cnt++;  
                }
                cnt += countSub(i, c, edgeMap, labels);
            }
        }

        return cnt;
    }
}