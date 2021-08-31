/**
 * 题目Id：494
 * 题目：目标和
 * 日期：2021-04-22 17:38:45
 */
//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选
//择一个符号添加在前面。 
//
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。 
//
// 
//
// 示例： 
//
// 输入：nums: [1, 1, 1, 1, 1], S: 3
//输出：5
//解释：
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//一共有5种方法让最终目标和为3。
// 
//
// 
//
// 提示： 
//
// 
// 数组非空，且长度不会超过 20 。 
// 初始的数组的和不会超过 1000 。 
// 保证返回的最终结果能被 32 位整数存下。 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 646 👎 0


package leetcode.editor.cn;

import static leetcode.editor.cn.dp_util.print_DP_2;
//目标和

public class P494_TargetSum_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P494_TargetSum_old().new Solution();
        int[] nums = {1, 1, 1, 1, 1};
        int res = solution.findTargetSumWays(nums, 3);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int k : nums) {
                sum += k;
            }
            if (Math.abs(target) > Math.abs(sum)) return 0;

            int len = nums.length;
            // - 0 +
            int width = sum * 2 + 1;
            int[][] dp = new int[len + 1][width];
            dp[0][0] = 1;
            if (nums[0] == 0) {
                dp[1][sum] = 2;
            } else {
                dp[1][sum + nums[0]] = 1;
                dp[1][sum - nums[0]] = 1;
            }

            for (int i = 2; i <= len; i++) {
                int num = nums[i - 1];
                for (int j = 0; j < width; j++) {
//                    dp[i][j] = dp[i - 1][j];  // 直接抄上一行意味着 不使用nums[i-1]--不符合题意
/*                    if (j - num >= 0) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                    //这句话有大问题
                    if (j + num <= target) {
                        dp[i][j] += dp[i - 1][j + num];
                    }
*/
                    // 边界
                    int l = (j - num) >= 0 ? j - num : 0;
                    int r = (j + num) <width ? j + num : 0;
                    dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
                }

            }
            print_DP_2(dp);
            return dp[len][sum + target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
