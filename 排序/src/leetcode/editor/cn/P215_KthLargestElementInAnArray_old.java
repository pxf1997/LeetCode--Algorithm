/**
 * 题目Id：215
 * 题目：数组中的第K个最大元素
 * 日期：2021-03-26 10:42:24
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
// 👍 974 👎 0


package leetcode.editor.cn;

//数组中的第K个最大元素

import java.util.Arrays;
import java.util.PriorityQueue;

public class P215_KthLargestElementInAnArray_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P215_KthLargestElementInAnArray_old().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //快速选择
        public int findKthLargest1(int[] nums, int k) {

            /*Arrays.sort(nums);
            return nums[nums.length - k];*/

            int index = nums.length - k; //第k大 == 第len-k小---即在排序后，有序数组中的位置
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int j = partition(nums, low, high);
                if (j == index) break;
                else if (j < index) {
                    low = j + 1;
                } else {
                    high = j - 1;
                }
            }
            return nums[index];

        }

        //类似快排中的子排序，但不关心左右是否有序！！只要定位出base元素位置即可
        // base位置j -- 第j小的元素
        public int partition(int[] arr, int start, int end) {
            int base = arr[start];
            int low = start, high = end + 1;
            while (true) {
                while (low < end && arr[++low] <= base) ;
                while (high > start && arr[--high] >= base) ;
                if (low < high) swap(arr, low, high);
                else break;
            }
            swap(arr, start, high);
            return high;
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }



        //最大堆--手动实现
        //建立一个大根堆，做 k - 1次删除操作后堆顶元素
        public int findKthLargest3(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            // 取出
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        // 小顶堆逻辑--size=k
        // 堆顶为--最大的k个元素中，最小的那个----第K大的
        public int findKthLargest2(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int val : nums) {
                pq.add(val);
                if (pq.size() > k) pq.poll(); // 拿出最大的哦
            }
            return pq.peek();
        }




    }
//leetcode submit region end(Prohibit modification and deletion)

}
