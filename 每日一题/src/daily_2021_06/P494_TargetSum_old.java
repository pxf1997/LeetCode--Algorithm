/**
 * 题目Id：494
 * 题目：目标和
 * 日期：2021-06-07 10:37:39
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
// -1000 <= target <= 100 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 729 👎 0


package daily_2021_06;

//目标和

import static util.dp_util.print_2D;

public class P494_TargetSum_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P494_TargetSum_old().new Solution();
        int res = solution.computeArraySum(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 回溯
    class Solution1 {
        int count = 0;

        public int findTargetSumWays(int[] nums, int target) {
            backtrack(nums, target, 0, 0);
            return count;
        }

        public void backtrack(int[] nums, int target, int index, int sum) {
            if (index == nums.length) {
                if (sum == target) {
                    count++;
                }
            } else {
                backtrack(nums, target, index + 1, sum + nums[index]);
                backtrack(nums, target, index + 1, sum - nums[index]);
            }
        }
    }

    // dp
    class Solution {
        // dp[i][j]--从数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量。
        public int computeArraySum(int[] nums, int s) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            // 绝对值范围超过了sum的绝对值范围则无法得到
            if (Math.abs(s) > Math.abs(sum)) return 0;

            int len = nums.length;
            // - 0 +
            int t = sum * 2 + 1;
            int[][] dp = new int[len][t];
            // 初始化
            if (nums[0] == 0) {
                dp[0][sum] = 2;
            } else {
                dp[0][sum + nums[0]] = 1;
                dp[0][sum - nums[0]] = 1;
            }
            print_2D(dp);
            System.out.println();

            for (int i = 1; i < len; i++) {
                for (int j = 0; j < t; j++) {
                    // 边界
                    int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                    int r = (j + nums[i]) < t ? j + nums[i] : 0;
                    dp[i][j] = dp[i - 1][l] + dp[i - 1][r];

//                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]];
                }
            }
            print_2D(dp);
            return dp[len - 1][sum + s];
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}
