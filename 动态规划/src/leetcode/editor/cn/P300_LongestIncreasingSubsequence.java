/**
 * 题目Id：300
 * 题目：最长递增子序列
 * 日期：2021-04-22 09:49:57
 */
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 二分查找 动态规划 
// 👍 1546 👎 0


package leetcode.editor.cn;

//最长递增子序列

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P300_LongestIncreasingSubsequence().new Solution();
        int[] test = {10, 9, 2, 5, 3, 7, 21, 18, 5};
//        int[] test = {10, 9, 2, 5, 3, 7, 21};

//        int res = solution.lengthOfLIS(test);
//        System.out.println("res = " + res);

        List<Integer> reslist = solution.listOfLIS(test);
        System.out.println("reslist = " + reslist);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
//            dpi---以numsi结尾的最长递增子序列长度  返回值为dp中最大元素而非末尾
            int len = nums.length;
            int[] dp = new int[len];
            Arrays.fill(dp, 1);
            System.out.println("dp = " + Arrays.toString(dp));

            for (int i = 1; i < len; i++) {
                int max = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j])
                        max = Math.max(max, dp[j] + 1);
                }
                dp[i] = max;
            }
            System.out.println("dp = " + Arrays.toString(dp));

            int res = Arrays.stream(dp).max().getAsInt();
            return res;
        }

        public List<Integer> listOfLIS(int[] nums) {
//            dpi---以numsi结尾的最长递增子序列长度  返回值为dp中最大元素而非末尾
            int len = nums.length;
            int[] dp = new int[len];
            Arrays.fill(dp, 1);
            System.out.println("dp = " + Arrays.toString(dp));

            int maxSize = 1; //最长递增子序的大小
            int maxVal = dp[0];//该序列中的最大整数

            for (int i = 1; i < len; i++) {
                int max = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j])
                        max = Math.max(max, dp[j] + 1);
                }
                dp[i] = max;
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    maxVal = nums[i];
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            System.out.println("maxSize = " + maxSize);
            System.out.println("maxVal = " + maxVal);
//            回溯遍历，输出所选序列
            List<Integer> res = new ArrayList<>();
            for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
                if (dp[i] == maxSize && maxVal>=nums[i]) {
                    res.add(nums[i]);
                    maxVal = nums[i];
                    maxSize--;
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
