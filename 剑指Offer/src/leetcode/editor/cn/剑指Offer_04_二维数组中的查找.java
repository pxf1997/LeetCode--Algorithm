/**
 * 题目Id：剑指 Offer 04
 * 题目：二维数组中的查找
 * 日期：2021-06-09 15:38:44
 */
//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。
//
//
//
// 示例:
//
// 现有矩阵 matrix 如下：
//
//
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
//
//
// 给定 target = 5，返回 true。
//
// 给定 target = 20，返回 false。
//
//
//
// 限制：
//
// 0 <= n <= 1000
//
// 0 <= m <= 1000
//
//
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
// Related Topics 数组 双指针
// 👍 344 👎 0


package leetcode.editor.cn;

//二维数组中的查找

public class 剑指Offer_04_二维数组中的查找 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_04_二维数组中的查找().new Solution();
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };
        int target = 21;
        boolean b = solution.findNumberIn2DArray(matrix, target);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
            int m = matrix.length, n = matrix[0].length;
            int row = 0, col = n - 1;
            while (inArea(row, col, matrix)) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) {
                    row++;
                } else {
                    col--;
                }
            }
            return false;
        }

        boolean inArea(int x, int y, int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            return x >= 0 && x < m && y >= 0 && y < n;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
