/**
 * 题目Id：剑指 Offer 57
 * 题目：和为s的两个数字
 * 日期：2021-06-21 16:56:40
 */
//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
// 
//
// 示例 2： 
//
// 输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^6 
// 
// 👍 114 👎 0


package leetcode.editor.cn;

//和为s的两个数字

import java.util.Arrays;

public class 剑指Offer_57_和为s的两个数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_57_和为s的两个数字().new Solution();
        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 999);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) left++;
                else if (sum > target) right--;
                else return new int[]{nums[left], nums[right]};
            }
            return new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
