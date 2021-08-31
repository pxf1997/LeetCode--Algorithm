/**
 * 题目Id：322
 * 题目：零钱兑换
 * 日期：2021-06-24 14:08:47
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
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1340 👎 0


package leetcode.editor.cn;

//零钱兑换

import java.util.Arrays;

public class P322_CoinChange {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P322_CoinChange().new Solution();
        int res = solution.coinChange(new int[]{1, 2, 5, 10, 21}, 22);
//        int res = solution.coinChange(new int[]{2}, 3);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 遍历coin在内层
        public int coinChange1(int[] coins, int amount) {
            // dp[i]--兑换i元钱,最小硬币数量
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 0; i <= amount; i++) {
                for (int coin : coins) {
                    if (i >= coin) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
                // 输出每轮更新结果!
                System.out.print("外层遍历 i = " + i);
                System.out.println("  dp = " + Arrays.toString(dp));
            }
            return dp[amount] == (amount + 1) ? -1 : dp[amount];
        }

        // 遍历coin在外层,如何理解--
        // 每遍历到一个coin值(面值coin的硬币加入考虑),对dp表进行更新
        public int coinChange(int[] coins, int amount) {
            // dp[i]--兑换i元钱,最小硬币数量
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE / 10); // 技巧--标记"找不开"
            dp[0] = 0;
            for (int coin : coins) {
                for (int i = 0; i <= amount; i++) {
                    if (i >= coin) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
                // 输出每轮更新结果!
                System.out.print("外层遍历 coin = " + coin);
                System.out.println("  dp = " + Arrays.toString(dp));
            }
            return dp[amount] == Integer.MAX_VALUE / 10 ? -1 : dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
