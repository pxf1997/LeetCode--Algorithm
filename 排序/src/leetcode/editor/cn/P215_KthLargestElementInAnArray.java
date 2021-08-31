/**
 * 题目Id：215
 * 题目：数组中的第K个最大元素
 * 日期：2021-03-26 15:26:25
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
// 👍 976 👎 0


package leetcode.editor.cn;

//数组中的第K个最大元素

public class P215_KthLargestElementInAnArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P215_KthLargestElementInAnArray().new Solution();
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int[] nums2 = new int[]{2, 1};
        int result = solution.findKthLargest(nums, 4);
//        int result2 = solution.findKthLargest(nums2, 2);
        System.out.println(result);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            buildMaxHeap(nums, len);
            //取出k-1个最大的，之后看堆顶
/*            for (int i = 0; i < k - 1; i++) {
                System.out.println("堆顶 = " + nums[0]);
                swap(nums, 0, len - 1 - i); // swap形参是index
                maxHeapify(nums, 0, len - 1 - i);
            }*/
            for (int i = nums.length - 1; i > nums.length - k; i--) {
                System.out.println("堆顶 = " + nums[0]);
                swap(nums, 0, i);
                len--;
                maxHeapify(nums, 0 , len);
            }
            return nums[0];


        }

        public void buildMaxHeap(int[] arr, int heapSize) {
            for (int i = heapSize / 2; i >= 0; i--) {
                maxHeapify(arr, i, heapSize);

            }
        }

        private void maxHeapify(int[] arr, int i, int heapSize) {
            int left = 2 * i + 1, right = 2 * i + 2;
            int largest = i;
            if (left < heapSize && arr[left] > arr[largest]) largest = left;
            if (right < heapSize && arr[right] > arr[largest]) largest = right;
            if (largest != i) {
                swap(arr, i, largest);
                maxHeapify(arr, largest, heapSize);
            }

        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
