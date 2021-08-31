/**
 * 题目Id：413
 * 题目：等差数列划分
 * 日期：2021-04-21 11:09:31
 */
//如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。 
//
// 例如，以下数列为等差数列: 
//
// 
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9 
//
// 以下数列不是等差数列。 
//
// 
//1, 1, 2, 5, 7 
//
// 
//
// 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。 
//
// 如果满足以下条件，则称子数组(P, Q)为等差数组： 
//
// 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。 
//
// 函数要返回数组 A 中所有为等差数组的子数组个数。 
//
// 
//
// 示例: 
//
// 
//A = [1, 2, 3, 4]
//
//返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
// 
// Related Topics 数学 动态规划 
// 👍 232 👎 0


package leetcode.editor.cn;

//等差数列划分

import java.util.stream.IntStream;

public class P413_ArithmeticSlices {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P413_ArithmeticSlices().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //       dp[i]其实表示的是，以A[i]结尾的等差数列个数
/*      考虑A=[3,4,5,6,7,8,9], 当前已经计算出dp[2]=1, dp[3]=2,需要求dp[4]。
        dp[4]=dp[3]+1 的原因是：以A[3]=6结尾的等差数列已经有了dp[3]=2个：[3,4,5,6]和[4,5,6]。
        想要知道以A[4]=7结尾的等差数列个数，那么需要在已经有的dp[3]个等差数列的尾部接上一个A[4]=7,
        还有一个新等差数列[5,6,7]，每次都会产生这个长度为3的等差数列。 所以才有了dp[i-1]+1。*/
        public int numberOfArithmeticSlices(int[] A) {
            if (A == null || A.length <= 2) {
                return 0;
            }

            int len = A.length;
            int[] dp = new int[len];
            dp[0] = 0;
            dp[1] = 0;
            for (int i = 2; i < len; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp[i] = dp[i - 1] + 1;
                }
            }


            return IntStream.of(dp).sum();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
