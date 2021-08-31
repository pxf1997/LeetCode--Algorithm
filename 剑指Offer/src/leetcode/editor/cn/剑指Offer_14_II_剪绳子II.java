/**
 * 题目Id：剑指 Offer 14- II
 * 题目：剪绳子 II
 * 日期：2021-06-15 17:53:50
 */
//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1]
// 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘
//积是18。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
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
//
//
// 提示：
//
//
// 2 <= n <= 1000
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划
// 👍 117 👎 0


package leetcode.editor.cn;

//剪绳子 II

import java.math.BigInteger;
import java.util.Arrays;

public class 剑指Offer_14_II_剪绳子II {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_14_II_剪绳子II().new Solution();
        int res = solution.cuttingRope(1000);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // dp--大数BigInteger
    class Solution1 {
        public static final int MOD = (int) (1e9 + 7);

        public int cuttingRope(int n) {
            // 特殊情况
            if (n == 2) return 1;
            if (n == 3) return 2;
            // dp及初始值
            BigInteger[] dp = new BigInteger[n + 1];
            dp[0] = BigInteger.valueOf(-1);
            dp[1] = BigInteger.valueOf(1);
            dp[2] = BigInteger.valueOf(2);
            dp[3] = BigInteger.valueOf(3);
            for (int i = 4; i <= n; i++) {
                BigInteger cur = dp[i - 1];
                for (int j = 1; j < i; j++) {
                    cur = cur.max(dp[j].multiply(BigInteger.valueOf(i - j)));
                }
                dp[i] = cur;
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n].mod(BigInteger.valueOf(MOD)).intValue();
        }
    }

    // 数学--借助long
    class Solution {
        public static final int MOD = (int) (1e9 + 7);

        public int cuttingRope(int n) {
            // 特殊情况
            if (n == 2) return 1;
            if (n == 3) return 2;
            // dp及初始值
            long res = 1;
            while (n > 4) {
                n -= 3;
                res = (res * 3 % MOD);
            }

            return (int) (res * n % MOD);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
