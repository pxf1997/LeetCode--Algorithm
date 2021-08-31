/**
 * 题目Id：240
 * 题目：搜索二维矩阵 II
 * 日期：2021-07-06 15:54:46
 */
//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 分治 矩阵 
// 👍 661 👎 0


package leetcode.editor.cn;

//搜索二维矩阵 II

public class P240_SearchA2dMatrixIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P240_SearchA2dMatrixIi().new Solution();
        int target = 20;
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };

        boolean b = solution.searchMatrix(matrix, target);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--利用矩阵元素分布规律,类似二分法
    // 从右上角开始搜索
    class Solution {
        int m, n;

        public boolean searchMatrix(int[][] matrix, int target) {
            m = matrix.length;
            n = matrix[0].length;
            int row = 0, col = n - 1;
            while (inArea(row, col)) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) { // 比目标小,找下一行,下移
                    row++;
                } else { // 比目标大,找左一列,左移
                    col--;
                }
            }
            return false;
        }

        private boolean inArea(int row, int col) {
            return row >= 0 && row < m && col >= 0 && col < n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
