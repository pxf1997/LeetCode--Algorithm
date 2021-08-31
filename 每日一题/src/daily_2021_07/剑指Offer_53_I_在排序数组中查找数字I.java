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


package daily_2021_07;

//在排序数组中查找数字 I

import java.util.Arrays;

public class 剑指Offer_53_I_在排序数组中查找数字I {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_53_I_在排序数组中查找数字I().new Solution();
        // case1
//        int[] nums = new int[]{1, 2, 3, 3, 3, 3, 4, 7};
//        int target = 3;
        int[] nums = new int[]{1};
        int target = 1;

        System.out.println("nums = " + Arrays.toString(nums));
        int res = solution.search(nums, target);
        System.out.println("res = " + res);
    }

    // 参考答案--
    // 优化--将二分查找右边界 right的代码封装至函数 helper()
    class Solution_参考答案 {
        public int search(int[] nums, int target) {
            System.out.println("nums = " + Arrays.toString(nums));
            // target 的插入点
            int index1 = helper(nums, target);
            System.out.println(target + "插入点:" + index1);
            // target-1 的插入点
            int index2 = helper(nums, target - 1);
            System.out.println((target - 1) + "插入点:" + index2);
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

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    // 重新练习
    // 分析--二分查找(插入点),搜索target和target-1即可
    class Solution {
        public int search(int[] nums, int target) {
            int idx1 = binarySearch_insert(nums, target);
            int idx2 = binarySearch_insert(nums, target - 1);
            System.out.println(target + " : idx1=" + idx1);
            System.out.println((target - 1) + " : idx2=" + idx2);
            return idx1 - idx2;
        }

        // 二分插入,查找target插入位置,若多个元素相同则插在右侧
        private int binarySearch_insert(int[] nums, int target) {
            int len = nums.length;
            int lo = 0, hi = len - 1;
            while (lo <= hi) { // 这里 <=
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] <= target) {
                    lo = mid + 1;
                } else{ // nums[mid] > target
                    hi = mid - 1;
                }
            }
            return lo;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
