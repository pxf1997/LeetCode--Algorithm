/**
 * 题目Id：518
 * 题目：零钱兑换 II
 * 日期：2021-06-10 10:12:58
 */
//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
//
//
//
//
//
//
// 示例 1:
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2:
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
//
//
// 示例 3:
//
// 输入: amount = 10, coins = [10]
//输出: 1
//
//
//
//
// 注意:
//
// 你可以假设：
//
//
// 0 <= amount (总金额) <= 5000
// 1 <= coin (硬币面额) <= 5000
// 硬币种类不超过 500 种
// 结果符合 32 位符号整数
//
// 👍 437 👎 0


package daily_2021_06;

//零钱兑换 II

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.dp_util.print_2D;

public class P518_CoinChange2 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P518_CoinChange2().new Solution();
//        int res = solution.change(5, new int[]{1, 2, 5});
        int res = solution.change(200, new int[]{3, 5, 7, 8, 9, 10, 11});
        System.out.println("res = " + res);
    }

    // 一维dp--理解为对二维dp的状态压缩
    class Solution {
        // 外层循环coin--正确
        public int change(int amount, int[] coins) {
            // dp[i]-- i块钱的找零方案个数
            // dp[i]初始为0，状态转移为 dp[i] += dp[i-coin]
            int[] dp = new int[amount + 1];
            dp[0] = 1;

            for (int coin : coins) { // 每个coin对结果进行更新--类似背包问题的'物品维度'
                for (int i = coin; i <= amount; i++) {
                    dp[i] = dp[i] + dp[i - coin];
                }
                System.out.println("考虑硬币金额:" + coin);
                System.out.println("dp = " + Arrays.toString(dp));
                System.out.println();
            }
            return dp[amount];
        }

        // 外层循环amount--错误!
        public int change2(int amount, int[] coins) {
            // dp[i]-- i块钱的找零方案个数
            // dp[i]初始为0，状态转移为 dp[i] += dp[i-coin]
            int[] dp = new int[amount + 1];
            dp[0] = 1;

            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i >= coin) {
                        dp[i] = dp[i] + dp[i - coin];
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[amount];
        }
    }



    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 二维dp
    class Solution2 {
        public int change(int amount, int[] coins) {
            // dp[i][j]--考虑到coins下标i-1,可以凑成金额j的方案数
            int[][] dp = new int[coins.length + 1][amount + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= coins.length; i++) {
                int coin = coins[i - 1];
                for (int j = 0; j <= amount; j++) {
                    dp[i][j] = dp[i - 1][j]; // 默认抄上一行,不使用新加入的硬币
                    if (j >= coin) {
                        int k = 1; // 此硬币用了k个
                        while (j - k * coin >= 0) {
                            dp[i][j] += dp[i - 1][j - k * coin];
                            k++;
                        }
                    }
                }
            }
            print_2D(dp);
            return dp[coins.length][amount];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    // 回溯法--组合求和--超时
    class Solution3 {
        List<List<Integer>> res = new ArrayList<>();

        public int change(int amount, int[] coins) {
            Arrays.sort(coins);
            List<Integer> path = new ArrayList<>();
            backtracking(coins, amount, 0, path);
            System.out.println("res = " + res);
            return res.size();
        }

        //做减法
        private void backtracking(int[] candidates, int target, int beginIndex, List<Integer> path) {
            if (target == 0) {
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
            }
            for (int i = beginIndex; i < candidates.length; i++) {
                // 剪枝
                if (target - candidates[i] < 0) {
                    break;
                }
                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "  剩余:" + (target - candidates[i]));

                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i
                backtracking(candidates, target - candidates[i], i, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }
        }
    }
}
