/**
 * 题目Id：剑指 Offer 10- II
 * 题目：青蛙跳台阶问题
 * 日期：2021-06-15 11:35:07
 */
//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
// 输入：n = 2
//输出：2
//
//
// 示例 2：
//
// 输入：n = 7
//输出：21
//
//
// 示例 3：
//
// 输入：n = 0
//输出：1
//
// 提示：
//
//
// 0 <= n <= 100
//
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
//
//
// Related Topics 递归
// 👍 169 👎 0


package leetcode.editor.cn;

//青蛙跳台阶问题

import java.util.Arrays;

public class 剑指Offer_10_II_青蛙跳台阶问题 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_10_II_青蛙跳台阶问题().new Solution();
        int res = solution.numWays(7);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 常规dp
    class Solution {
        public static final int MOD = (int) (1e9 + 7);

        public int numWays(int n) {
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 2;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                // 跳一级 or 跳两级
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }

    }

    // 二维测试
    class Solution2 {
        public static final int MOD = (int) (1e9 + 7);

        // 外层循环--台阶(容量)  内层循环--step(物品)
        // 题目逻辑含义--排列
        public int numWays1(int n) {
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 2;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            int[] steps = new int[]{1, 2};

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < steps.length; j++) {
                    int step = steps[j];
                    if (i >= step) {
                        dp[i] = (dp[i] + dp[i - step]) % MOD;
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }

        // 交换内外层循环--变成组合
        public int numWays(int n) {
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 2;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            int[] steps = new int[]{1, 2};

            for (int step : steps) {
                for (int i = 1; i <= n; i++) {
                    if (i >= step) {
                        dp[i] = (dp[i] + dp[i - step]) % MOD;
                    }
                }
                System.out.println("step = " + step + "  dp = " + Arrays.toString(dp));
            }
            return dp[n];
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}
