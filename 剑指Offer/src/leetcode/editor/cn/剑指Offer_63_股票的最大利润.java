/**
 * 题目Id：剑指 Offer 63
 * 题目：股票的最大利润
 * 日期：2021-06-15 20:06:55
 */
//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？ 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 10^5 
//
// 
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-s
//tock/ 
// Related Topics 动态规划 
// 👍 129 👎 0


package leetcode.editor.cn;

//股票的最大利润

import java.util.Arrays;

public class 剑指Offer_63_股票的最大利润 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_63_股票的最大利润().new Solution();
        int maxProfit = solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
//        int maxProfit = solution.maxProfit(new int[]{7, 6, 4, 3, 1});
        System.out.println("maxProfit = " + maxProfit);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--限定买卖该股票一次
    // 进阶--hold sell双状态数组(持股or当天卖出)

    class Solution {
        // 一维dp压缩为一个变量
        public int maxProfit1(int[] prices) {
            int len = prices.length;
            if (len <= 1) return 0;
            // min到下标i为止的最小价格
            int min = Integer.MAX_VALUE;
            // profit到下标i为止的最大收益
            int profit = 0;
            for (int i = 0; i < prices.length; i++) {
                min = Math.min(min, prices[i]);
                profit = Math.max(profit, prices[i] - min);
            }
            return profit;
        }

        // 一维dp
        // dp[i]逻辑为--考虑到prices下标i处,可获得最大利润
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len <= 1) return 0;
            int[] dp = new int[len];
            dp[0] = 0;
            int min = prices[0];
            for (int i = 1; i < len; i++) {
                // 前i日最大利润=max( 前(i−1)日最大利润, 第i日价格−前i日最低价格 )
                min = Math.min(min, prices[i]);
                dp[i] = Math.max(dp[i - 1], prices[i] - min);
            }
            System.out.println("dp = " + Arrays.toString(dp));

            return dp[len - 1];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
