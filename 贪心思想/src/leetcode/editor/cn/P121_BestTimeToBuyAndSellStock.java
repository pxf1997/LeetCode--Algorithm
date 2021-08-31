/**
 * 题目Id：121
 * 题目：买卖股票的最佳时机
 * 日期：2021-04-18 22:38:13
 */
//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 104 
// 
// Related Topics 数组 动态规划 
// 👍 1575 👎 0


package leetcode.editor.cn;

//买卖股票的最佳时机

import java.util.concurrent.ForkJoinPool;

public class P121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P121_BestTimeToBuyAndSellStock().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        动态规划
//        前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len == 0 || len == 1) return 0;
//            记录最小价格
            int minSoFar = prices[0];
            int max = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] < minSoFar) minSoFar = prices[i];
//                梳理逻辑，上一行发生条件：i日股价是之前最低的，故不可能在i日卖出股票，
//                max = Math.max(max, prices[i]-minSoFar);
                else max = Math.max(max, prices[i]-minSoFar);
            }
            return max;

        }

        //        不用DP--极其低效，放弃
        public int maxProfit_Bad(int[] prices) {
            int res = 0;
            for (int i = 0; i < prices.length; i++) {
                int buyIn = prices[i];
                for (int j = i + 1; j < prices.length; j++) {
                    int sellOut = prices[j];
                    int profit = sellOut - buyIn;
                    if (profit > res) res = profit;
                }

            }
            return res;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
