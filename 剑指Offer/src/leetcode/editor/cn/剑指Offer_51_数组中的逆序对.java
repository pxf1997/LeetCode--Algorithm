/**
 * 题目Id：剑指 Offer 51
 * 题目：数组中的逆序对
 * 日期：2021-06-02 18:38:16
 */
//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
//
//
// 示例 1:
//
// 输入: [7,5,6,4]
//输出: 5
//
//
//
// 限制：
//
// 0 <= 数组长度 <= 50000
// 👍 427 👎 0


package leetcode.editor.cn;

//数组中的逆序对

import java.util.Arrays;

public class 剑指Offer_51_数组中的逆序对 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_51_数组中的逆序对().new Solution();
        int[] nums = new int[]{5, 2, 3, 1, 4, 6, 7, 0};
        System.out.println("nums = " + Arrays.toString(nums));

        int res = solution.reversePairs(nums);
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 在归并排序中统计
    class Solution {
        int cnt = 0;

        public int reversePairs(int[] nums) {
            int len = nums.length;
            int[] temp = new int[len];
            merge_sort(nums, 0, nums.length - 1, temp);
            return cnt;
        }

        private void merge_sort(int[] nums, int left, int right, int[] temp) {
            if (left < right) {
                int mid = (left + right) / 2;
                merge_sort(nums, left, mid, temp);
                merge_sort(nums, mid + 1, right, temp);
                merge(nums, left, mid, right, temp);
            }

        }

        private void merge(int[] nums, int left, int mid, int right, int[] temp) {
            // 左边 [left,mid]  右边 [mid+1,right]  操作范围 [left,right]
            int i = left;    //左边的有序数组的左边界
            int j = mid + 1; //右边的有序数组的左边界
            int index = left;
            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
                    temp[index++] = nums[i++];
                } else {
                    // j处元素在右区间,但它比若干个左区间元素要小,因此对错位个数进行计算:
                    // 错位的元素(在左区间)下标范围是[i,mid] 共 mid - i + 1 个
                    temp[index++] = nums[j++];
                    cnt += mid - i + 1;
                }
            }
            // 左边没用完的
            while (i <= mid) {
                temp[index++] = nums[i++];
            }
            // 右边没用完的
            while (j <= right) {
                temp[index++] = nums[j++];
            }
            // 至此temp的[left,right]区间已经有序,将结果赋值给nums
            while (left <= right) {
                nums[left] = temp[left++];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
