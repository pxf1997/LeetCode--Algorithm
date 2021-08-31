/**
 * 题目Id：322
 * 题目：零钱兑换
 * 日期：2021-06-10 10:13:20
 */
//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 动态规划 
// 👍 1303 👎 0


package daily_2021_06;

//零钱兑换

import java.util.Arrays;

public class P322_CoinChange {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P322_CoinChange().new Solution();
//        int res = solution.coinChange(new int[]{1, 5, 10, 21}, 42);
        int res = solution.coinChange(new int[]{2}, 7);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 找零金额循环在外层,coins循环在内层
        public int coinChange1(int[] coins, int amount) {
            // dp[i] 金额为i时,所用硬币最少数
            int[] dp = new int[amount + 1];
//            Arrays.fill(dp, -1); // 这么写后面没法搞了
            Arrays.fill(dp, amount + 1); // 这么写后面没法搞了
            dp[0] = 0; //0元不用硬币
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i >= coin) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[amount] > amount ? -1 : dp[amount];
        }
        // coins循环在外层,金额循环在内层
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int coin : coins) {
                for (int i = coin; i < amount + 1; i++) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
