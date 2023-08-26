# LeetCode 之网格图







## 螺旋行走

```java
public long[][] snake(long[] fab) {
    long[][] res = new long[n][n];	// 二维网格图。
    int[] dirX = new int[]{1, 0, -1, 0};  // 水平方向的步长。
    int[] dirY = new int[]{0, 1, 0, -1};  // 垂直方向的步长。
    int level = 0;  // 内卷的厚底（已走的圈数）。
    int i = 0, j = 0, d = 0;  // i,j表示行列，d表示当前行走方向的索引。

    for (int cnt = fab.length - 1; cnt >= 0; --cnt) {
        res[i][j] = fab[cnt];  // 写入目标值。

        // 更新方向。
        if ((d == 0 && j == n-1 - level)
            || (d == 1 && i == n-1 - level)
            || (d == 2 && j == level))
            ++d;
        else if (d == 3 && i == level + 1) {  // 最后一步被上一圈起点占领，所以这里 level + 1判断。
            d = 0;
            ++level;
        }

        // 更新网格位置。
        i += dirY[d];
        j += dirX[d];
    }
    return res;
}
```

