package util;

/**
 * @author pxf
 * @create 2021-05-26 14:23
 */
public class 矩阵相关 {
    // 坐标合法
    boolean inArea(int x, int y, int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    //
}
