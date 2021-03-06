/**
 * 题目Id：714
 * 题目：买卖股票的最佳时机含手续费
 * 日期：2021-04-26 10:03:17
 */
//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。 
//
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。 
//
// 返回获得利润的最大值。 
//
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。 
//
// 示例 1: 
//
// 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出: 8
//解释: 能够达到的最大利润:  
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8. 
//
// 注意: 
//
// 
// 0 < prices.length <= 50000. 
// 0 < prices[i] < 50000. 
// 0 <= fee < 50000. 
// 
// Related Topics 贪心算法 数组 动态规划 
// 👍 460 👎 0


package leetcode.editor.cn;

//买卖股票的最佳时机含手续费

import java.util.Arrays;

public class P714_BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P714_BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
        int res = solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit1(int[] prices, int fee) {
            int len = prices.length;
//            Sell----不持股（当天卖出）
//            S2----不持股（之前卖出）
//            Buy----持股（当天买入）
//            S1----持股（之前买入）
            int[] buy = new int[len];
            int[] s1 = new int[len];
            int[] sell = new int[len];
            int[] s2 = new int[len];

            buy[0] = s1[0] = -prices[0];
            s2[0] = sell[0] = 0;

            for (int i = 1; i < len; i++) {
                buy[i] = Math.max(s2[i - 1], sell[i - 1]) - prices[i];
                s1[i] = Math.max(s1[i - 1], buy[i - 1]);
                sell[i] = Math.max(buy[i - 1], s1[i - 1]) + prices[i] - fee;
                s2[i] = Math.max(s2[i - 1], sell[i - 1]);
            }
//            System.out.println("buy = " + Arrays.toString(buy));
//            System.out.println("s1 = " + Arrays.toString(s1));
//            System.out.println("sell = " + Arrays.toString(sell));
//            System.out.println("s2 = " + Arrays.toString(s2));

            return Math.max(sell[len - 1], s2[len - 1]);
        }

        public int maxProfit(int[] prices, int fee) {
//            dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，
//            dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; ++i) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[n - 1][0];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
