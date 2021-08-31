/**
 * 题目Id：88
 * 题目：合并两个有序数组
 * 日期：2021-03-25 17:25:06
 */
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 
// 👍 811 👎 0


package leetcode.editor.cn;

//合并两个有序数组

import java.util.Arrays;

public class P88_MergeSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P88_MergeSortedArray().new Solution();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        solution.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
//        System.out.println(nums1.toString());
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge1(int[] nums1, int m, int[] nums2, int n) {
            int[] nums1_copy = new int[m];
            System.arraycopy(nums1, 0, nums1_copy, 0, m);
            int i = 0, j = 0, k = 0;
            while (i < m && j < n) {
                nums1[k++] = (nums1_copy[i] < nums2[j]) ? nums1_copy[i++] : nums2[j++];
            }
            //复制剩余
/*            if (i < m) {
                System.arraycopy(nums1_copy, i, nums1, i + j, m + n - i - j);
            }
            if (j < n) {
                System.arraycopy(nums2, j, nums1, i + j, m + n - j - i);
            }*/
            while (i < m) {
                nums1[k++] = nums1_copy[i++];
            }
            while (j < n) {
                nums1[k++] = nums2[j++];
            }


        }

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int index1 = m - 1, index2 = n - 1, indexMerge = m + n - 1;
            while (index1 >= 0 || index2 >= 0) { //总体没停
                if (index1 < 0) {
                    nums1[indexMerge--] = nums2[index2--];
                } else if (index2 < 0) {
                    nums1[indexMerge--] = nums1[index1--];
                } else if (nums1[index1] > nums2[index2]) {
                    nums1[indexMerge--] = nums1[index1--];
                } else {
                    nums1[indexMerge--] = nums2[index2--];
                }

            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
