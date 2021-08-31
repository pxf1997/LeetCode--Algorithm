/**
 * 题目Id：34
 * 题目：在排序数组中查找元素的第一个和最后一个位置
 * 日期：2021-04-20 15:59:57
 */
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 961 👎 0


package leetcode.editor.cn;

//在排序数组中查找元素的第一个和最后一个位置

import java.util.Arrays;

public class P34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] test = {5,7,7,8,8,10};
        int[] res = solution.searchRange(test, 12);
        System.out.println("res = " + Arrays.toString(res));

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
//		我们将寻找 target 最后一个位置，转换成寻找 target+1 第一个位置，
//		再往前移动一个位置。这样我们只需要实现一个二分查找代码即可。
            int first = findFirst(nums, target);
            int last = findFirst(nums, target + 1) - 1;
            if (first == nums.length || nums[first] != target) {
                return new int[]{-1, -1};
            } else {
//                return new int[]{first, Math.max(first, last)};
                return new int[]{first, last};
            }

        }

/*      理解查找为---找到target要插入的位置，可能是最后一个之后 or 第一个之前
        要将 h 取值为 nums.length，
        从而使得 findFirst返回的区间更大，
        能够覆盖 target 大于 nums 最后一个元素的情况。*/
        private int findFirst(int[] nums, int target) {
            int l = 0, h = nums.length; // 注意 h 的初始值
            while (l < h) {
                int m = l + (h - l) / 2;
                if (nums[m] >= target) {
                    h = m;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
