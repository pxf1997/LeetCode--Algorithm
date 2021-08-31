/**
 * 题目Id：剑指 Offer 40
 * 题目：最小的k个数
 * 日期：2021-06-17 14:49:36
 */
//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 254 👎 0


package leetcode.editor.cn;

//最小的k个数

import util.排序.sort_exer_util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 剑指Offer_40_最小的k个数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_40_最小的k个数().new Solution();
//        int[] leastNumbers = solution.getLeastNumbers(new int[]{10, 1, 9, 8, 7, 4, 5, 6, 2, 3}, 0);



        int[] ints = sort_exer_util.gennerateArray(10, 100);
        System.out.println("ints = " + Arrays.toString(ints));
        int[] leastNumbers = solution.getLeastNumbers(ints, 3);
        System.out.println("leastNumbers = " + Arrays.toString(leastNumbers));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 最大堆--维护前k小的值
    // 若比堆顶(当前最大值)小则入堆
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0) return new int[0];
            // 最大堆,不用 lamda表达式
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<>() {
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int x : arr) {
                // 堆没满
                if (maxHeap.size() < k) {
                    maxHeap.offer(x);
                }
                // 堆满了--与堆顶进行比较
                else {
                    if (maxHeap.peek() > x) {
                        maxHeap.poll();
                        maxHeap.offer(x);
                    }
                }
            }
            System.out.println("maxHeap = " + maxHeap);
            return maxHeap.stream().mapToInt(Integer::valueOf).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
