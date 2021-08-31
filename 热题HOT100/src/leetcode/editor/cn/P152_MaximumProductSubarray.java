/**
 * 题目Id：152
 * 题目：乘积最大子数组
 * 日期：2021-08-26 10:37:15
 */
//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 👍 1248 👎 0


package leetcode.editor.cn;

//乘积最大子数组

import java.util.Arrays;

public class P152_MaximumProductSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P152_MaximumProductSubarray().new Solution();
//        int[] nums = new int[]{2, 3, -2, 4};
        int[] nums = new int[]{-2, 3, -4};
        int max = solution.maxProduct(nums);
        System.out.println("max = " + max);
    }


    // dp思路是对的,逻辑上有小缺陷,如何处理负数!
    // 分析--我们得到了一个结论：当前位置的最优解未必是由前一个位置的最优解转移得到的。
    class Solution_wrong {
        // dp[i]--以下标i结尾的 最大乘积子数组的值
        public int maxProduct(int[] nums) {
            int len = nums.length;
            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
            }
            //System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }
    }
    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案
    class Solution {
        public int maxProduct(int[] nums) {
            int length = nums.length;
            int[] maxF = new int[length]; // i元素结尾的 乘积最大子数组的乘积
            int[] minF = new int[length]; // i元素结尾的 乘积最小子数组的乘积 (负的最多!)
            System.arraycopy(nums, 0, maxF, 0, length);
            System.arraycopy(nums, 0, minF, 0, length);
            for (int i = 1; i < length; ++i) {
                // maxF*nums[i] / minF*nums[i] / nums[i] 三者比较
                maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
                minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
            }
//            System.out.println("maxF = " + Arrays.toString(maxF));
//            System.out.println("minF = " + Arrays.toString(minF));
            return Arrays.stream(maxF).max().getAsInt();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
