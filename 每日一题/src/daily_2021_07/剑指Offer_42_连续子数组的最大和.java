/**
 * 题目Id：剑指 Offer 42
 * 题目：连续子数组的最大和
 * 日期：2021-07-17 23:00:29
 */
//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 数组 分治 动态规划 
// 👍 341 👎 0


package daily_2021_07;

//连续子数组的最大和

import java.util.Arrays;

public class 剑指Offer_42_连续子数组的最大和 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_42_连续子数组的最大和().new Solution();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSubArray = solution.maxSubArray(nums);
        System.out.println("maxSubArray = " + maxSubArray);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            // dp[i]--以下标i结尾的"子数组"的最大和
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            }
//            System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
