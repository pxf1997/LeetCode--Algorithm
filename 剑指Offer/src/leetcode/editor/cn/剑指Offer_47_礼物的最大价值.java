/**
 * 题目Id：剑指 Offer 47
 * 题目：礼物的最大价值
 * 日期：2021-06-15 23:19:46
 */
//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
//
//
//
// 示例 1:
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
//
//
//
// 提示：
//
//
// 0 < grid.length <= 200
// 0 < grid[0].length <= 200
//
// Related Topics 动态规划
// 👍 148 👎 0


package leetcode.editor.cn;

//礼物的最大价值

import static util.dp_util.print_2D;

public class 剑指Offer_47_礼物的最大价值 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_47_礼物的最大价值().new Solution();
        int maxValue = solution.maxValue(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        });
        System.out.println("maxValue = " + maxValue);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--回溯+记录路径path 看下长宽范围 0-200 乘起来就是40000 怕是不行哟
    // 分析--dp[i][j]逻辑为考虑到横纵坐标[i,j]处可得到的最大值
    class Solution {
        public int maxValue(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];
            // 第一行 第一列
            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int i = 1; i < n; i++) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // dp[i-1][j-1] 左上--不考虑,必然小
                    // dp[i-1][j] 左
                    // dp[i][j-1] 上
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            print_2D(dp);
            return dp[m - 1][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
