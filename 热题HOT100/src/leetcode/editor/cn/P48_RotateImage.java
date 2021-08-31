/**
 * 题目Id：48
 * 题目：旋转图像
 * 日期：2021-08-23 16:51:00
 */
//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：[[1]]
// 
//
// 示例 4： 
//
// 
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
// 
//
// 
//
// 提示： 
//
// 
// matrix.length == n 
// matrix[i].length == n 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
// Related Topics 数组 数学 矩阵 👍 974 👎 0


package leetcode.editor.cn;

//旋转图像

import util.dp_util;

public class P48_RotateImage {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P48_RotateImage().new Solution();
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        dp_util.print_2D(matrix);
        System.out.println();

        solution.rotate(matrix);

        dp_util.print_2D(matrix);
        System.out.println();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 使用额外空间,不原地修改!
    class Solution {
        int m, n;

        public void rotate(int[][] matrix) {
            m = matrix.length;
            n = matrix[0].length;
            int[][] temp = new int[m][n];
            // 分析旋转 i行j列 --> 倒数i列 j行
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    temp[j][m - 1 - i] = matrix[i][j];
                }
            }
            // 浅拷贝,在函数里修改matrix指向的堆中地址(无效!)
            matrix = temp;

            // 深拷贝
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    matrix[i][j] = temp[i][j];
//                }
//            }
            //dp_util.print_2D(temp);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
