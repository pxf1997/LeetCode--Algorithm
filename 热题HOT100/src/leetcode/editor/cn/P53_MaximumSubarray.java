/**
 * 题目Id：53
 * 题目：最大子序和
 * 日期：2021-07-01 17:52:21
 */
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 3354 👎 0


package leetcode.editor.cn;

//最大子序和

import java.util.Arrays;

public class P53_MaximumSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P53_MaximumSubarray().new Solution();
        int maxSubArray = solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("maxSubArray = " + maxSubArray);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            // dp[i]--以nums[i]结尾的最大子序和
            // 可以用滚动变量(一维dp压缩为一个变量),我就是学不会,CNM
            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
