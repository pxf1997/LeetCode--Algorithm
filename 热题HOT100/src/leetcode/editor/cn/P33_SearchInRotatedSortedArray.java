/**
 * 题目Id：33
 * 题目：搜索旋转排序数组
 * 日期：2021-08-06 10:46:26
 */
//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1492 👎 0


package leetcode.editor.cn;

//搜索旋转排序数组

public class P33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P33_SearchInRotatedSortedArray().new Solution();
        // case1
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int res = solution.search(nums, target);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 联想到了 P153题--旋转数组中的最小值
    // 分析--左端最小 > 右端最大
    class Solution {
        public int search(int[] nums, int target) {
            // 特殊情况
            int len = nums.length;
            if (len == 0) return -1;
            if (len == 1) return nums[0] == target ? 0 : -1;

            // 二分查找
            int lo = 0, hi = len - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] == target) return mid;
                // 评价--逻辑"对偶"
                if (nums[0] <= nums[mid]) {
                    // [lo, mid-1]有序,且 nums[lo] <= target < nums[mid], 范围缩小至[lo,mid-1]
                    if (nums[0] <= target && target < nums[mid]) {
                        hi = mid - 1;
                    }
                    // 否则缩小至[mid+1, hi]
                    else {
                        lo = mid + 1;
                    }
                } else {
                    // [mid, hi]有序,且 nums[mid] < target <= nums[hi], 范围缩小至[mid+1,hi]
                    if (nums[mid] < target && target <= nums[len - 1]) {
                        lo = mid + 1;
                    }
                    // 否则缩小至[lo, mid-1]
                    else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
