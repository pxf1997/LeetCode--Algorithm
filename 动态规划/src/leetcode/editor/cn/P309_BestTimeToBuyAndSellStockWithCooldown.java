/**
 * 题目Id：309
 * 题目：最佳买卖股票时机含冷冻期
 * 日期：2021-04-26 11:13:14
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

import java.util.Arrays;

public class P309_BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P309_BestTimeToBuyAndSellStockWithCooldown().new Solution();
        int[] prices = {1, 2, 3, 0, 2};
        int res = solution.maxProfit(prices);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
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
                buy[i] = s2[i - 1] - prices[i];
                s1[i] = Math.max(s1[i - 1], buy[i - 1]);
                sell[i] = Math.max(buy[i - 1], s1[i - 1]) + prices[i];
                s2[i] = Math.max(s2[i - 1], sell[i - 1]);
            }
//            System.out.println("buy = " + Arrays.toString(buy));
//            System.out.println("s1 = " + Arrays.toString(s1));
//            System.out.println("sell = " + Arrays.toString(sell));
//            System.out.println("s2 = " + Arrays.toString(s2));

            return Math.max(sell[len - 1], s2[len - 1]);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
