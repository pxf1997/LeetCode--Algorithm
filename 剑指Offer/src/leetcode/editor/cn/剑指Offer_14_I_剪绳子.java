/**
 * 题目Id：剑指 Offer 14- I
 * 题目：剪绳子
 * 日期：2021-06-15 17:14:04
 */
//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。
//
// 示例 1：
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1
//
// 示例 2:
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
//
// 提示：
//
//
// 2 <= n <= 58
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划
// 👍 248 👎 0


package leetcode.editor.cn;

//剪绳子

import java.util.Arrays;

public class 剑指Offer_14_I_剪绳子 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_14_I_剪绳子().new Solution();
        int res = solution.cuttingRope(58);
        System.out.println("res = " + res);
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    // dp
    class Solution1 {
        public int cuttingRope(int n) {
            // 特殊情况直接返回
            if (n <= 1) return -1;
            if (n == 2) return 1;
            if (n == 3) return 2;

            // dp[i]--最大乘积值--允许不分段
            int[] dp = new int[n + 1];

            // 初始化


            dp[0] = -1; // dp[0] 无意义
            dp[1] = 1;
            dp[2] = 2; // 2 和 3 按dp的逻辑应当不分段(题目实际上不允许)
            dp[3] = 3; // dp定义与实际题目逻辑有所不同
            for (int i = 4; i <= n; i++) {
                int max = dp[i - 1];
                for (int j = 1; j < i; j++) {
                    // 之前问题的解 dp[j] * 本段的长度 i-j
                    max = Math.max(max, dp[j] * (i - j));
                }
                dp[i] = max;
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }
    }

    // 数学
    class Solution {
        // 尽可能的多使用3
        public int cuttingRope(int n) {
            if (n == 2) return 1;
            if (n == 3) return 2;

            int res = 1;
            while (n > 4) {
                res *= 3;
                n -= 3;
            }
            return res * n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
