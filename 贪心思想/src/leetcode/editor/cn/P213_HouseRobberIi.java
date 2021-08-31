/**
 * 题目Id：213
 * 题目：打家劫舍 II
 * 日期：2021-04-18 22:03:25
 */
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 651 👎 0


package leetcode.editor.cn;

//打家劫舍 II

public class P213_HouseRobberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P213_HouseRobberIi().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return nums[0];
            if (len == 2) return Math.max(nums[0], nums[1]);
//            int[] dp1 = new int[len - 1], dp2 = new int[len - 1];
            int res1 = robRange(nums, 0, len - 2);
            int res2 = robRange(nums, 1, len - 1);

            return Math.max(res1, res2);


        }

        private int robRange(int[] nums, int start, int end) { //Index索引
            int len = end - start + 1;
            int[] dp = new int[len];
            dp[0] = nums[start];
            dp[1] = Math.max(nums[start], nums[start + 1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }

            return dp[len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
