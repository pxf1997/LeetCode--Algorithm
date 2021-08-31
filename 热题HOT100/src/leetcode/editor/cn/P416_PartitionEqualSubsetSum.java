/**
 * 题目Id：416
 * 题目：分割等和子集
 * 日期：2021-06-28 17:39:24
 */
//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 843 👎 0


package leetcode.editor.cn;

//分割等和子集

import util.dp_util;

public class P416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P416_PartitionEqualSubsetSum().new Solution();
//        boolean b = solution.canPartition(new int[]{1, 5, 11, 5});
        boolean b = solution.canPartition(new int[]{1, 2, 3, 6});
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 暴力法分析--
        // 枚举,每个元素可以选也可以不选,因此n个元素复杂度为O(2^n),必然超时

        // dp
        public boolean canPartition(int[] nums) {
            int len = nums.length;
            if (len < 2) return false;
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 == 1) return false;// 不是偶数必然false
            int target = sum / 2;
            if (maxNum > target) return false;// 记录最大值,单个元素超过target必然false

            // dp[i][j]--考虑到下标i,能否选出和为j

            boolean[][] dp = new boolean[len][target + 1];
            // 初始化第一行,上面记录最大值保证了此处不会数组越界!
            dp[0][nums[0]] = true;
            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= target; j++) {
                    dp[i][j] = dp[i - 1][j];  // 逻辑--照抄上一行(不考虑新加入的物品)
                    if (j >= nums[i]) {
                        // 拿了nums[i]这件物品
                        dp[i][j] |= dp[i - 1][j - nums[i]];
                    }
                }
            }
            System.out.println("target = " + target);
            dp_util.print_2D(dp);

            return dp[len - 1][target]; //下标[0,len-1],总和为j
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
