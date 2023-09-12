import java.util.ArrayList;
import java.util.List;

public class Solution1992 {
    public int[][] findFarmland(int[][] land) {
        List<int[]> res = new ArrayList();
        int m = land.length, n = land[0].length;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(land[i][j] == 0){
                    continue;
                }

                int row = i, col = j;
                // 向下探索矩形农场组行
                while(row + 1 < m && land[row+1][j] == 1) row++;
                // 向右探索矩形农场组的列
                while(col + 1 < n && land[i][col+1] == 1) col++;

                res.add(new int[]{i,j, row, col});

                // 探索到的矩形农场组中所有块置为0，避免后续的遍历
                for(int x = i; x <= row; x++){
                    for(int y = j; y <= col; y++){
                        land[x][y] = 0;
                    }
                }
            }
        }

        return res.toArray(new int[0][0]);

    }

}
