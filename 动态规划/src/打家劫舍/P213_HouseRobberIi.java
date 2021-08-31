/**
 * 题目Id：213
 * 题目：打家劫舍 II
 * 日期：2021-04-15 14:51:18
 */
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。 
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
// 👍 587 👎 0


package 打家劫舍;

//打家劫舍 II

import java.util.Arrays;

public class P213_HouseRobberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P213_HouseRobberIi().new Solution();
        int[] test = {1, 2, 3, 1};
//        int res = solution.rob(test);
        int res1 = solution.rob(test);
        System.out.println(res1);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
/*    假设数组 nums 的长度为 n。
    如果不偷窃最后一间房屋，则偷窃房屋的下标范围是[0,n−2]；
    如果不偷窃第一间房屋，则偷窃房屋的下标范围是[1,n−1]。
    在确定偷窃房屋的下标范围之后，即可用第 198 题的方法解决。*/

    class Solution1 {
        public int rob1(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return nums[0];
            if (len == 2) return Math.max(nums[0], nums[1]);

//            dp1--偷首，不能偷尾
            int[] dp1 = new int[len - 1];
            dp1[0] = nums[0];
            dp1[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len - 1; i++) {
                dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
            }
//            dp2--偷尾，不能偷首
            int[] dp2 = new int[len - 1];
            dp2[0] = nums[1];
            dp2[1] = Math.max(nums[1], nums[2]);
            for (int i = 2; i < len - 1; i++) {
                dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i + 1]);
            }

            return Math.max(dp2[len - 2], dp1[len - 2]);
        }

        public int rob(int[] nums) {
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            } else if (length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int res1 = robRange(nums, 0, length - 2);
            int res2 = robRange(nums, 1, length - 1);
            return Math.max(res1, res2);
        }

        private int robRange(int[] nums, int start, int end) {
//            dp长度
            int[] dp = new int[end - start + 1];
            int len = dp.length;
            dp[0] = nums[start];
            dp[1] = Math.max(nums[start], nums[start + 1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }
            return dp[len - 1];
        }

        //        写不好这种，把dp数组留着，先从简单的写起
        private int robRange1(int[] nums, int start, int end) {
//            初始化 first=dp[0]   second=dp[1]
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
//            迭代过程中 first=dp[i-2]   second=dp[i-1]
            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }

    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return nums[0];
            if (len == 2) return Math.max(nums[0], nums[1]);

            int[] head = Arrays.copyOfRange(nums, 0, nums.length - 1);
            int[] tail = Arrays.copyOfRange(nums, 1, nums.length);

            return Math.max(robHelp(head), robHelp(tail));

        }

        private int robHelp(int[] nums) {
            System.out.println("nums = " + Arrays.toString(nums));
            int len = nums.length;
            if (len == 0) return 0;
            if (len == 1) return nums[0];
            if (len == 2) return Math.max(nums[0], nums[1]);
            int[] dp = new int[len];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            System.out.println(Arrays.toString(dp));
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);

            }
            System.out.println(Arrays.toString(dp));
            System.out.println();
            return dp[len - 1];

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
