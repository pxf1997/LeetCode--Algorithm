/**
 * 题目Id：309
 * 题目：最佳买卖股票时机含冷冻期
 * 日期：2021-04-25 15:57:03
 */
//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 动态规划 
// 👍 756 👎 0


package leetcode.editor.cn;

//最佳买卖股票时机含冷冻期

public class P309_BestTimeToBuyAndSellStockWithCooldown_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P309_BestTimeToBuyAndSellStockWithCooldown_old().new Solution();
        int[] prices = {1, 2, 3, 0, 2};
        int res = solution.maxProfit(prices);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //        看题解
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int len = prices.length;
//             f[i][0]: 手上持有股票的最大收益
//             f[i][1]: 手上不持有股票，并且处于冷冻期中(当天卖了，第二天是冻结期)的累计最大收益
//             f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
//            这里的「处于冷冻期」指的是在第 i 天结束之后的状态。也就是说：如果第 i 天结束之后处于冷冻期，那么第 i+1 天无法买入股票。
            int[][] f = new int[len][3];
            f[0][0] = -prices[0];
//            dp_util.print_DP_2(f);
//            System.out.println();

            for (int i = 1; i < len; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);

                f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);

            }
//            dp_util.print_DP_2(f);
//            System.out.println();

            return Math.max(f[len - 1][1], f[len - 1][2]);


        }
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }

            int[][] dp = new int[len][3];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;

            // dp[i][0]: 手上不持有股票，并且今天不是由于卖出股票而不持股，我们拥有的现金数
            // dp[i][1]: 手上持有股票时，我们拥有的现金数
            // dp[i][2]: 手上不持有股票，并且今天是由于卖出股票而不持股，我们拥有的现金数
            for (int i = 1; i < len; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][2] = dp[i - 1][1] + prices[i];
            }
//            dp_util.print_DP_2(dp);
//            System.out.println();

            return Math.max(dp[len - 1][0], dp[len - 1][2]);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)


}
