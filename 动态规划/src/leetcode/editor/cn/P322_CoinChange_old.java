/**
 * 题目Id：322
 * 题目：零钱兑换
 * 日期：2021-04-22 15:36:52
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
// 👍 1220 👎 0


package leetcode.editor.cn;

//零钱兑换

import java.util.Arrays;

public class P322_CoinChange_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P322_CoinChange_old().new Solution();
        int res = solution.coinChange(new int[]{2},3);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        注意：如果没有1元硬币--找不开
        public int coinChange1(int[] coins, int amount) {
            int len = coins.length;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
//                int min = i;//默认有1元硬币
                int min = amount + 1;
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        min = Math.min(min, dp[i - coin] + 1);
                    }
                }
                dp[i] = min;
            }
//            return dp[amount];
//            考虑找不开--失败
            return dp[amount] > amount ? -1 : dp[amount];
        }

        public int coinChange(int[] coins, int amount) {
            if (amount == 0 || coins == null) return 0;
            int[] dp = new int[amount + 1];
            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) { //将逆序遍历改为正序遍历
                    if (i == coin) {
                        dp[i] = 1;
                    } else if (dp[i] == 0 && dp[i - coin] != 0) {
                        dp[i] = dp[i - coin] + 1;

                    } else if (dp[i - coin] != 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
            return dp[amount] == 0 ? -1 : dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
