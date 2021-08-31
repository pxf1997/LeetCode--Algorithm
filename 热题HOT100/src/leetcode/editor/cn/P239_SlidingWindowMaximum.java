/**
 * 题目Id：239
 * 题目：滑动窗口最大值
 * 日期：2021-07-06 15:38:40
 */
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [9,11], k = 2
//输出：[11]
// 
//
// 示例 5： 
//
// 
//输入：nums = [4,-2], k = 2
//输出：[4] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1058 👎 0


package leetcode.editor.cn;

//滑动窗口最大值

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class P239_SlidingWindowMaximum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P239_SlidingWindowMaximum().new Solution();
//        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
//        int k = 3;
        int[] nums = new int[]{-7, -8, 7, 5, 7, 1, 6, 0};
        int k = 4;

        System.out.println("nums = " + Arrays.toString(nums));
        int[] res = solution.maxSlidingWindow(nums, k);
        System.out.println("res = " + Arrays.toString(res));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 重新练习
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            int[] res = new int[len - k + 1];
            int idx = 0;
            Deque<Integer> deque = new LinkedList<>();
            for (int right = 0; right < len; right++) {
                int left = right - k + 1;// [right-k+1, right] 共k个元素
                // 比新添加的小,就出队. 注意相等的话如何处理
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {// 测试发现,此处 < 和 <= 均可,没太搞懂!
//                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {// 测试发现,此处 < 和 <= 均可,没太搞懂!
                    deque.pollLast();
                }
                deque.addLast(right);
                // 失效删除
                if (left > deque.peekFirst()) {
                    deque.pollFirst();
                }
                // 窗口形成
                if (left >= 0) {
                    res[idx++] = nums[deque.peekFirst()];
                }
                // helper
                System.out.println("窗口坐标范围:[" + left + ", " + right + "]");
                System.out.println("deque = " + deque);
                System.out.println();
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
