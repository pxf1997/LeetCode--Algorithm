/**
 * 题目Id：313
 * 题目：超级丑数
 * 日期：2021-08-09 10:09:04
 */
//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
//
// 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
//
// 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
//
//
//
// 示例 1：
//
//
//输入：n = 12, primes = [2,7,13,19]
//输出：32
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
//28,32] 。
//
// 示例 2：
//
//
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
//
//
//
//
//
//
// 提示：
//
//
// 1 <= n <= 106
// 1 <= primes.length <= 100
// 2 <= primes[i] <= 1000
// 题目数据 保证 primes[i] 是一个质数
// primes 中的所有值都 互不相同 ，且按 递增顺序 排列
//
//
//
//
// Related Topics 数组 哈希表 数学 动态规划 堆（优先队列）
// 👍 193 👎 0


package leetcode.editor.cn;

//超级丑数

import java.util.Arrays;

public class P313_SuperUglyNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P313_SuperUglyNumber().new Solution();
        int res = solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            int len = primes.length;
            // 下一个超级丑数是当前指针指向的超级丑数 * 对应的质因数
            // 类似{2,3,5} --> p2,p3,p5
            int[] pointers = new int[len];
            Arrays.fill(pointers, 1);
            for (int i = 2; i <= n; i++) {
                int[] nums = new int[len];
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < len; j++) {
                    // dp下标 pointers[j], 对应质因数 primes[j]
                    nums[j] = dp[pointers[j]] * primes[j];
                    min = Math.min(nums[j], min);
                }
                dp[i] = min;
                for (int j = 0; j < len; j++) {
                    // 对应 pointers[j]加一
                    if (min == nums[j]) pointers[j]++;
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
