/**
 * 题目Id：879
 * 题目：盈利计划
 * 日期：2021-06-09 09:39:52
 */
//集团里有 n 名员工，他们可以完成各种各样的工作创造利润。 
//
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。 
//
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。 
//
// 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//输出：2
//解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
//总的来说，有两种计划。 
//
// 示例 2： 
//
// 
//输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//输出：7
//解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
//有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。 
// 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// 0 <= minProfit <= 100 
// 1 <= group.length <= 100 
// 1 <= group[i] <= 100 
// profit.length == group.length 
// 0 <= profit[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 99 👎 0


package daily_2021_06;

//盈利计划

public class P879_ProfitableSchemes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P879_ProfitableSchemes().new Solution();
//        int res = solution.profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3});
        int res = solution.profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 三维dp--看题解
    class Solution {
        public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
            int len = group.length, MOD = (int) 1e9 + 7;
            // dp[i][j][k] 表示在前 i 个工作中选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
            int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
            dp[0][0][0] = 1;
            for (int i = 1; i <= len; i++) { // 工作维度(候选物品)
                // 此工作所需员工,利润
                int memers = group[i - 1], earn = profit[i - 1];
                for (int j = 0; j <= n; j++) { // 员工人数维度(背包重量限制)
                    for (int k = 0; k <= minProfit; k++) { // 利润维度(背包重量限制)
                        // 无法开展当前工作--抄上一行
                        if (j < memers) {
                            dp[i][j][k] = dp[i - 1][j][k];
                        }
                        // 可以开展当前工作
                        else {
                            // 利润不满足
                            if (k < earn) {
                                dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - memers][0]) % MOD;
                            }
                            // 利润满足
                            else {
                                dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - memers][k - earn]) % MOD;
                            }
                            // 如下一句话可以代替上面--妙啊
                            // dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - memers][Math.max(0, k - earn)]) % MOD;

                        }
                    }
                }
            }
            int sum = 0;
            // 员工人数维度求和
            for (int j = 0; j <= n; j++) {
                sum = (sum + dp[len][j][minProfit]) % MOD;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
