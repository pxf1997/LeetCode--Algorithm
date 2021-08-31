/**
 * 题目Id：剑指 Offer 53 - I
 * 题目：在排序数组中查找数字 I
 * 日期：2021-06-03 11:51:44
 */
//统计一个数字在排序数组中出现的次数。
//
//
//
// 示例 1:
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2
//
// 示例 2:
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0
//
//
//
// 限制：
//
// 0 <= 数组长度 <= 50000
//
//
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/
// Related Topics 数组 二分查找
// 👍 138 👎 0


package leetcode.editor.cn;

//在排序数组中查找数字 I

import java.util.Arrays;

public class 剑指Offer_53_I_在排序数组中查找数字I {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_53_I_在排序数组中查找数字I().new Solution();

//        int res = solution.search(new int[]{1, 2, 3, 3, 3, 3, 4, 7}, 8);
        int res = solution.search(new int[]{1, 2, 3, 3, 3, 3, 4, 7}, 3);
//        int res = solution.search(new int[]{1}, 1);
        System.out.println("res = " + res);
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)

    // 参考答案--常规二分查找
    class Solution1 {
        public int search(int[] nums, int target) {
            // 搜索右边界 right
            int lo = 0, hi = nums.length - 1;
            while (lo <= hi) {
                int m = (lo + hi) / 2;
                if (nums[m] <= target) lo = m + 1;
                else hi = m - 1;
            }
            int right = lo;
            // 若数组中无 target ，则提前返回
            if (hi >= 0 && nums[hi] != target) return 0;
            // 搜索左边界 right
            lo = 0;
            hi = nums.length - 1;
            while (lo <= hi) {
                int m = (lo + hi) / 2;
                if (nums[m] < target) lo = m + 1;
                else hi = m - 1;
            }
            int left = hi;
            return right - left - 1;
        }
    }

    // 参考答案--优化
    // 将二分查找右边界 right的代码封装至函数 helper()
    class Solution {
        public int search(int[] nums, int target) {
            System.out.println("nums = " + Arrays.toString(nums));
            // target 的插入点
            int index1 = helper(nums, target);
            System.out.println(target + "插入点下标:" + index1);
            // target-1 的插入点
            int index2 = helper(nums, target - 1);
            System.out.println((target - 1) + "插入点下标:" + index2);

            // 值为target的下标范围 [index2, index1-1],共 index1-index2个
            return index1 - index2;
        }

        // 查找数字 target 在数组 nums 中的 插入点 ，且若数组中存在值相同的元素，则插入到这些元素的右边
        // 换言之--第一个比target大的元素位置
        int helper(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] <= target) lo = mid + 1; // mid等于也往右移
                else hi = mid - 1; // mid < target 才向左移动
            }
            return lo;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
