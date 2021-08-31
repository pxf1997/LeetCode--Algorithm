/**
 * 题目Id：剑指 Offer 49
 * 题目：丑数
 * 日期：2021-06-15 09:47:16
 */
//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学 
// 👍 176 👎 0


package leetcode.editor.cn;

//丑数

import java.util.Arrays;

public class 剑指Offer_49_丑数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_49_丑数().new Solution();
        int nthUglyNumber = solution.nthUglyNumber(10);
        System.out.println("nthUglyNumber = " + nthUglyNumber);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //  p = 2 ^ x * 3 ^ y * 5 ^ z
        public int nthUglyNumber(int n) {
            // 候选丑数的下标为 t2,t3,t5
            int[] dp = new int[n];
            int t2 = 0, t3 = 0, t5 = 0;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = Math.min(dp[t2] * 2, Math.min(dp[t3] * 3, dp[t5] * 5));

                // helper
                System.out.println("t2=" + t2 + "  t3=" + t3 + "  t5=" + t5 +
                        "  dp[" + i + "]=" + dp[i]);

                if (dp[i] == dp[t2] * 2) t2++;
                if (dp[i] == dp[t3] * 3) t3++;
                if (dp[i] == dp[t5] * 5) t5++;
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
