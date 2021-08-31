/**
 * 题目Id：343
 * 题目：整数拆分
 * 日期：2021-04-21 11:28:18
 */
//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
//
// 示例 1: 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 说明: 你可以假设 n 不小于 2 且不大于 58。 
// Related Topics 数学 动态规划 
// 👍 495 👎 0


package leetcode.editor.cn;

//整数拆分

public class P343_IntegerBreak {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P343_IntegerBreak().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int integerBreak1(int n) {
//            dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积
/*            当i≥2 时，假设对正整数 i 拆分出的第一个正整数是 j（1≤j<i），则有以下两种方案：
            将 i 拆分成 j 和 i−j 的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j×(i−j)
            将 i 拆分成 j 和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j] */
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            for (int i = 2; i <= n; i++) {
                int max = 0;
                for (int j = 1; j < i; j++) {
                    max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
                }
                dp[i] = max;

            }
            return dp[n];

        }

        public int integerBreak(int n) {
//            dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积
/*            当i≥2 时，假设对正整数 i 拆分出的第一个正整数是 j（1≤j<i），则有以下两种方案：
            将 i 拆分成 j 和 i−j 的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j×(i−j)
            将 i 拆分成 j 和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j] */
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            for (int i = 2; i <= n; i++) {
                int max = Integer.MIN_VALUE;
                for (int j = 1; j < i; j++) {
                    int max1 = Math.max(j * (i - j), j * dp[i - j]);
                    max = Math.max(max, max1);

                }
                dp[i] = max;
            }
            return dp[n];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
