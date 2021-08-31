/**
 * 题目Id：215
 * 题目：数组中的第K个最大元素
 * 日期：2021-05-12 10:38:44
 */
//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法 
// 👍 1069 👎 0


//数组中的第K个最大元素

import util.sort_exer_util;

import java.util.Arrays;

public class P215_KthLargestElementInAnArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P215_KthLargestElementInAnArray().new Solution();
//        int kthLargest = solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
//        int kthLargest = solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        int[] data = sort_exer_util.gennerateArray(15, 100);
        int kthLargest = solution.findKthLargest(data, 4);
        System.out.println("kthLargest = " + kthLargest);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            //推导一哈--第k大 = 第len-k+1小---取下标
            int Kindex = len - 1 - (k - 1);
            System.out.println(Arrays.toString(nums));
            System.out.println("第" + k + "大的元素在排序数组中的下标应为:" + Kindex);
            return findKthSmallest(nums, Kindex);
        }

        /**
         * @param nums 数组
         * @param k    排序后数组的下标k--第k+1小
         * @return 排序后数组中下标为k的元素
         */
        public int findKthSmallest(int[] nums, int k) {
            System.out.println("k = " + k);
            int lo = 0, hi = nums.length - 1;
            //有点类似二分法
            while (lo < hi) {
                int j = partition(nums, lo, hi);
                System.out.println("lo=" + lo + "  hi=" + hi);
                System.out.println("基准元素=" + nums[j] + " 所在下标=" + j + "  " + Arrays.toString(nums));
                if (j == k) {
                    break;
                }
                if (j < k) {
                    System.out.println("下标为" + k + "的元素出现在右侧");
                    System.out.println();
                    lo = j + 1;
                } else {
                    System.out.println("下标为" + k + "的元素出现在左侧");
                    System.out.println();
                    hi = j - 1;
                }

            }
            return nums[k];
        }

        public int partition(int[] nums, int startIndex, int endIndex) {
            int low = startIndex, high = endIndex + 1;
            int base = nums[startIndex];
            while (true) {
                while (nums[++low] <= base && low < endIndex) ;
                while (nums[--high] >= base && high > startIndex) ;
                if (low < high) {
                    swap(nums, low, high);
                } else {
                    break;
                }
            }
            swap(nums, startIndex, high);
            return high; //快排简化版---只定位base元素所在的下标
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
