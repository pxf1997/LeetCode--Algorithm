/**
 * 题目Id：剑指 Offer 42
 * 题目：连续子数组的最大和
 * 日期：2021-06-15 18:18:20
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
// Related Topics 分治算法 动态规划 
// 👍 285 👎 0


package leetcode.editor.cn;

//连续子数组的最大和

import java.util.Arrays;

public class 剑指Offer_42_连续子数组的最大和 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_42_连续子数组的最大和().new Solution();
        int maxSubArray = solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("maxSubArray = " + maxSubArray);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            if (len == 1) return nums[0];
            // dp[i] 以nums[i]结尾的,子数组和的最大值
            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                 dp[i] = Math.max(nums[i] + dp[i - 1], nums[i]);
                // >= or > 逻辑是--出现相同值的时候,倾向于 选or不选
//                if (dp[i - 1] >= 0) {
//                    dp[i] = nums[i] + dp[i - 1];
//                } else {
//                    dp[i] = nums[i];
//                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
