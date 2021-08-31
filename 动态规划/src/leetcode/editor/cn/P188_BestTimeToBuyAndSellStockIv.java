/**
 * 题目Id：188
 * 题目：买卖股票的最佳时机 IV
 * 日期：2021-04-26 13:51:45
 */
//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 490 👎 0


package leetcode.editor.cn;

//买卖股票的最佳时机 IV

import java.util.Arrays;

public class P188_BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P188_BestTimeToBuyAndSellStockIv().new Solution();
        int res = solution.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int k, int[] prices) {
//            dp定义----对于数组 prices[0..i] 中的价格而言，
//            buy[i][j]进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
//            sell[i][j] 表示恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
            int len = prices.length;
            if (prices.length == 0) {
                return 0;
            }
            k = Math.min(k, len / 2); // len天最多交易 len/2次
            int[][] buy = new int[len][k + 1];
            int[][] sell = new int[len][k + 1];

            buy[0][0] = -prices[0];
            sell[0][0] = 0;
            for (int i = 1; i <= k; i++) { //非法情况 只有一天，交易 i>=1 次
                buy[0][i] = sell[0][i] = -10000;
            }
//           sell[i][j] 的状态转移方程中包含 buy[i−1][j−1]，
//           在 j=0 时其表示不合法的状态，因此在 j=0 时，我们无需对 sell[i][0]进行转移，让其保持值为 0 即可。


            for (int i = 1; i < len; i++) {
                buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);//j=0--交易0次
                for (int j = 1; j <= k; j++) {
                    buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                    sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
                }
            }
//            dp_util.print_DP_2(buy);
//            System.out.println();
//            dp_util.print_DP_2(sell);
//            System.out.println();


            return Arrays.stream(sell[len - 1]).max().getAsInt(); // sell的 len-1 行（包含所有日期）最大值----交易次数为[0,k]

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
