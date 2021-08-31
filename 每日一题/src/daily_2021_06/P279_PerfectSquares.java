/**
 * 题目Id：279
 * 题目：完全平方数
 * 日期：2021-06-11 09:58:34
 */
//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 916 👎 0


package daily_2021_06;

//完全平方数

import java.util.Arrays;

public class P279_PerfectSquares {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P279_PerfectSquares().new Solution();
        int numSquares = solution.numSquares(256);
        System.out.println("numSquares = " + numSquares);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // O(n * sqrt(n))
    class Solution {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            // 最差情况全由1组成
            for (int i = 0; i < n + 1; i++) {
                dp[i] = i;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
