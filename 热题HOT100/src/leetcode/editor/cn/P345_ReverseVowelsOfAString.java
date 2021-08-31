/**
 * 题目Id：345
 * 题目：反转字符串中的元音字母
 * 日期：2021-08-19 09:49:46
 */
//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 
// 👍 182 👎 0


package leetcode.editor.cn;

//反转字符串中的元音字母

import util.排序.sort_exer_util;

import java.util.*;

public class P345_ReverseVowelsOfAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P345_ReverseVowelsOfAString().new Solution();
        int[] nums = sort_exer_util.gennerateArray(15, 100);
        System.out.println("nums = " + Arrays.toString(nums));
        solution.quick_sort(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void quick_sort(int[] nums) {
            helper(nums, 0, nums.length - 1);
        }

        private void helper(int[] nums, int startIndex, int endIndex) {
            if (startIndex < endIndex) {
                int base = nums[startIndex];
                int left = startIndex, right = endIndex + 1;
                while (true) {
                    while (left < endIndex && nums[++left] <= base) ;
                    while (right > startIndex && nums[--right] >= base) ;
                    if (left < right) {
                        swap(nums, left, right);
                    } else {
                        break;
                    }
                }
                swap(nums, startIndex, right);
                helper(nums, startIndex, right - 1);
                helper(nums, right + 1, endIndex);
            }
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        public int find(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (Integer key : map.keySet()) {
                if (map.get(key) == 1) return key;
            }
            return -1;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
