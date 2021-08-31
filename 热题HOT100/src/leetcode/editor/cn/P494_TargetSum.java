/**
 * 题目Id：494
 * 题目：目标和
 * 日期：2021-08-26 12:02:41
 */
//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 884 👎 0


package leetcode.editor.cn;

//目标和

public class P494_TargetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P494_TargetSum().new Solution();
        int[] nums = {1, 1, 1, 1, 1};
        int count = solution.findTargetSumWays(nums, 3);
        System.out.println("count = " + count);
    }


    // 回溯
    class Solution_backtracking {
        int count;

        public int findTargetSumWays(int[] nums, int target) {
            backtracking(nums, target, 0, 0);
            return count;
        }

        private void backtracking(int[] nums, int target, int begin, int sum) {
            if (begin == nums.length) {
                if (sum == target) {
                    count++;
                }
            } else {
                backtracking(nums, target, begin + 1, sum + nums[begin]);
                backtracking(nums, target, begin + 1, sum - nums[begin]);
            }

        }
    }
    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // dp
    //                   sum(P) - sum(N) = target
    // sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    //                        2 * sum(P) = target + sum(nums)

    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int len = nums.length;
            int sum = 0;
            for (int i : nums) sum += Math.abs(i);
            if (target > sum || (sum - target) % 2 != 0) return 0;
            int m = (sum - target) / 2;
            int[][] dp = new int[len + 1][m + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= len; i++) {
                int x = nums[i - 1];
                for (int j = 0; j <= m; j++) {
                    dp[i][j] += dp[i - 1][j];
                    if (j >= x) dp[i][j] += dp[i - 1][j - x];
                }
            }
            return dp[len][m];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
