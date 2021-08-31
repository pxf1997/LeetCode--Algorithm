/**
 * 题目Id：123
 * 题目：买卖股票的最佳时机 III
 * 日期：2021-04-26 11:39:26
 */
//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 105 
// 
// Related Topics 数组 动态规划 
// 👍 752 👎 0


package leetcode.editor.cn;

//买卖股票的最佳时机 III

import java.util.Arrays;

public class P123_BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P123_BestTimeToBuyAndSellStockIii().new Solution();
        int res = solution.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit_1(int[] prices) {
            int firstBuy = Integer.MIN_VALUE, firstSell = 0;
            int secondBuy = Integer.MIN_VALUE, secondSell = 0;
            for (int curPrice : prices) {
                if (firstBuy < -curPrice) {
                    firstBuy = -curPrice;
                }
                if (firstSell < firstBuy + curPrice) {
                    firstSell = firstBuy + curPrice;
                }
                if (secondBuy < firstSell - curPrice) {
                    secondBuy = firstSell - curPrice;
                }
                if (secondSell < secondBuy + curPrice) {
                    secondSell = secondBuy + curPrice;
                }
            }
            return secondSell;
        }

        public int maxProfit(int[] prices) {
//            状态分析：
//            未进行过任何操作；不考虑
//            只进行过一次买操作；buy1
//            进行了一次买操作和一次卖操作，即完成了一笔交易；sell1
//            在完成了一笔交易的前提下，进行了第二次买操作；buy2
//            完成了全部两笔交易。sell2

//            状态转移：
//            buy1[i] = Math.max(buy1[i - 1], -prices[i]);
//            sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
//            buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
//            sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);

//            边界条件：无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零。
//            buy1=-prices[0];
//            buy2即为在同一天买入并且卖出后再以prices[0] 的价格买入股票:buy2=-prices[0];

            int len = prices.length;
            int[] buy1 = new int[len];
            int[] sell1 = new int[len];
            int[] buy2 = new int[len];
            int[] sell2 = new int[len];

            buy1[0] = buy2[0] = -prices[0];
            sell1[0] = sell2[0] = 0;

            for (int i = 1; i < len; i++) {
                buy1[i] = Math.max(buy1[i - 1], -prices[i]);
                sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
                buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
                sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
            }
            System.out.println("buy1 = " + Arrays.toString(buy1));
            System.out.println("sell1 = " + Arrays.toString(sell1));
            System.out.println("buy2 = " + Arrays.toString(buy2));
            System.out.println("sell2 = " + Arrays.toString(sell2));
            return Math.max(sell1[len - 1], sell2[len - 1]);
/*            int n = prices.length;
            int buy1 = -prices[0], sell1 = 0;
            int buy2 = -prices[0], sell2 = 0;
            for (int i = 1; i < n; ++i) {
                buy1 = Math.max(buy1, -prices[i]);
                sell1 = Math.max(sell1, buy1 + prices[i]);
                buy2 = Math.max(buy2, sell1 - prices[i]);
                sell2 = Math.max(sell2, buy2 + prices[i]);
            }
            return sell2;*/
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
