/**
 * 题目Id：1269
 * 题目：停在原地的方案数
 * 日期：2021-05-13 10:15:16
 */
//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。 
//
// 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。 
//
// 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。 
//
// 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。 
//
// 
//
// 示例 1： 
//
// 输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
// 
//
// 示例 2： 
//
// 输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
// 
//
// 示例 3： 
//
// 输入：steps = 4, arrLen = 2
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= steps <= 500 
// 1 <= arrLen <= 10^6 
// 
// Related Topics 动态规划 
// 👍 93 👎 0


//停在原地的方案数

import util.dp_util;

public class P1269_NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1269_NumberOfWaysToStayInTheSamePlaceAfterSomeSteps().new Solution();
        int ways = solution.numWays(10, 4);
        System.out.println("ways = " + ways);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // my思路:向左走left步 向右走right步 不动n步   满足left=right  left+right+n=steps // 不能越界
        // 求方案数---想到dp了没有？？
        public int numWays(int steps, int arrLen) {
            final int MODULO = 1000000007;
            // dp[i][j] 表示在 i 步操作之后，指针位于下标 j 的方案数 i∈[0,steps] j∈[0,len-1]

            // 一共执行 steps 步操作，指针所在下标一定不会超过 steps，可以将 j 缩小范围
            int maxCol_Index = Math.min(arrLen - 1, steps);

            //行下标=步数 共steps+1行  共maxCol+1列
            int[][] dp = new int[steps + 1][maxCol_Index + 1];

            // 当没有进行任何操作时，指针一定位于下标 0
            dp[0][0] = 1;
            for (int i = 1; i <= maxCol_Index; i++) {
                dp[0][i] = 0;
            }

            for (int i = 1; i <= steps; i++) {
                for (int j = 0; j <= maxCol_Index; j++) {
                    dp[i][j] = dp[i - 1][j]; //这步不动
                    // 第一列和最后一列为判断边界，需要特殊关注
                    // 第一列j=0时，j-1情况排除，直观理解:你不能从dp表外面走进来
                    // 最后一列j=maxCol_Index时，j+1情况排除，直观理解:你不能从dp表外面走进来
                    if (j - 1 >= 0) {
                        dp[i][j] += dp[i - 1][j - 1]; //这步向右走,下标+1
                        dp[i][j] %= MODULO;
                    }
                    if (j + 1 <= maxCol_Index) {
                        dp[i][j] += dp[i - 1][j + 1]; //这步向左走,下标-1
                        dp[i][j] %= MODULO;
                    }

                }
            }
            dp_util.print_2D(dp);

            return dp[steps][0] ;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
