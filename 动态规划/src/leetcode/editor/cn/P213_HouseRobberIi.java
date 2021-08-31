/**
 * 题目Id：213
 * 题目：打家劫舍 II
 * 日期：2021-04-20 18:13:13
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
// 👍 655 👎 0


package leetcode.editor.cn;

//打家劫舍 II

import java.util.Arrays;

public class P213_HouseRobberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P213_HouseRobberIi().new Solution();
        int res = solution.rob(new int[]{1, 2, 3, 1});
        System.out.println(res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return nums[0];

            int res1 = robRange1(nums, 0, len - 2);
            int res2 = robRange1(nums, 1, len - 1);
            return Math.max(res1, res2);

        }

        private int robRange(int[] nums, int startIndex, int endIndex) {
            int pre2 = 0, pre1 = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                int cur = Math.max(pre2 + nums[i], pre1);
                pre2 = pre1;
                pre1 = cur;
            }
            return pre1;
        }
        private int robRange1(int[] nums, int start, int end) {
//            dp长度
            int[] dp = new int[end - start + 1];
            int len = dp.length;
            dp[0] = nums[start];
            dp[1] = Math.max(nums[start], nums[start + 1]);
            System.out.println(Arrays.toString(dp));
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }
            System.out.println(Arrays.toString(dp));
            return dp[len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
