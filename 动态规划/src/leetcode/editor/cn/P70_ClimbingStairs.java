/**
 * 题目Id：70
 * 题目：爬楼梯
 * 日期：2021-04-20 17:06:10
 */
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1612 👎 0


package leetcode.editor.cn;

//爬楼梯

public class P70_ClimbingStairs {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P70_ClimbingStairs().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs1(int n) {
            if (n <= 2) return n;
            int[] dp = new int[n + 1];
            int len = dp.length;
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i < len; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

//        滚动数组
        public int climbStairs(int n) {
            int p = 0, q = 0, r = 1;
            for (int i = 1; i <= n; ++i) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
