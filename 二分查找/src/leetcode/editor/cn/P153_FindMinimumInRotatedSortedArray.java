/**
 * 题目Id：153
 * 题目：寻找旋转排序数组中的最小值
 * 日期：2021-04-20 14:55:58
 */
//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
// 
//
// 示例 3： 
//
// 
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数 互不相同 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
// Related Topics 数组 二分查找 
// 👍 472 👎 0


package leetcode.editor.cn;

//寻找旋转排序数组中的最小值

public class P153_FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P153_FindMinimumInRotatedSortedArray().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int len = nums.length;
            int lo = 0, hi = len - 1;
//    	while终止时 lo=hi
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
//                考虑nums[mid] 和 nums[hi] 存在大小的逻辑关系
                if (nums[mid] > nums[hi]) lo = mid + 1; //mid偏左
                else hi = mid;
            }
//            return nums[hi];
            return nums[lo];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
