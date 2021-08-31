/**
 * 题目Id：剑指 Offer 29
 * 题目：顺时针打印矩阵
 * 日期：2021-06-17 09:53:59
 */
//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
//
//
// 示例 1：
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 限制：
//
//
// 0 <= matrix.length <= 100
// 0 <= matrix[i].length <= 100
//
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
// Related Topics 数组
// 👍 261 👎 0


package leetcode.editor.cn;

//顺时针打印矩阵

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 剑指Offer_29_顺时针打印矩阵 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_29_顺时针打印矩阵().new Solution();
        int[] ints = solution.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},

        });
        System.out.println("ints = " + Arrays.toString(ints));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 写出四个边界
    class Solution1 {
        public int[] spiralOrder(int[][] matrix) {
            if (matrix.length == 0) return new int[0];
            // 四个边界下标
            int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, idx = 0;
            int[] res = new int[(right + 1) * (bottom + 1)];
            while (true) {
                // 向右--上边界top+1
                for (int i = left; i <= right; i++) {
                    res[idx++] = matrix[top][i];
                }
                if (++top > bottom) break; // 先自增,再判断

                // 向下--右边界right-1
                for (int i = top; i <= bottom; i++) {
                    res[idx++] = matrix[i][right];
                }
                if (--right < left) break;

                // 向左--下边界bottom+1
                for (int i = right; i >= left; i--) {
                    res[idx++] = matrix[bottom][i];
                }
                if (--bottom < top) break;

                // 向上--左边界left+1
                for (int i = bottom; i >= top; i--) {
                    res[idx++] = matrix[i][left];
                }
                if (++left > right) break;
            }
            return res;

        }
    }

    // my--向四个方向打印
    class Solution {
        List<Integer> res = new ArrayList<>();
        int m, n;
        int[][] matrix;

        public int[] spiralOrder(int[][] matrix) {
            this.matrix = matrix;
            m = matrix.length;
            n = matrix[0].length;
            if (m == 0 || n == 0) return new int[0];

            // visited 判断
            boolean[][] visited = new boolean[m][n];
            int[] start = new int[]{0, 0};

            while (true) {
                int cur = res.size();
                // 四个方向打印--返回值是这个方向打印完后的起点位置
                start = printRight(start, visited);
                start = printDown(start, visited);
                start = printLeft(start, visited);
                start = printUp(start, visited);

                if (res.size() == cur) { // size不再增长--添加完毕
                    return res.stream().mapToInt(i -> i).toArray();
                }
            }
        }

        private int[] printUp(int[] start, boolean[][] visited) {
            int x = start[0], y = start[1];
            while (x >= 0 && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                x--;
            }
            x++;
            return new int[]{x, y + 1};
        }

        private int[] printLeft(int[] start, boolean[][] visited) {
            int x = start[0], y = start[1];
            while (y >= 0 && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                y--;
            }
            y++;
            return new int[]{x - 1, y};
        }

        private int[] printDown(int[] start, boolean[][] visited) {
            int x = start[0], y = start[1];
            while (x < m && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                x++;
            }
            x--;
            return new int[]{x, y - 1}; // 向上打印,终点时y-1,即向左走一格
        }

        private int[] printRight(int[] start, boolean[][] visited) {
            int x = start[0], y = start[1];
            while (y < n && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                y++;
            }
            y--; // 跳出while时,y出界一步
            return new int[]{x + 1, y}; // 向右打印,返回终点时,x+1即向下走一格
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
