/**
 * 题目Id：剑指 Offer 59 - I
 * 题目：滑动窗口的最大值
 * 日期：2021-06-09 15:12:44
 */
//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 Sliding Window 
// 👍 273 👎 0


package leetcode.editor.cn;

//滑动窗口的最大值

import java.util.*;

public class 剑指Offer_59_I_滑动窗口的最大值 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_59_I_滑动窗口的最大值().new Solution();
        int[] res = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println("res = " + Arrays.toString(res));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // O(n)单调队列----递减的
    class Solution1 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            List<Integer> res = new ArrayList<>();
            int len = nums.length;
            Deque<Integer> queue = new LinkedList<>();
            for (int right = 0; right < len; right++) { // 遍历右边界
                int left = right - k + 1;
                if (queue.isEmpty()) {
                    queue.add(right);
                }
                // 队首下标已经不合法了
                if (left > queue.peekFirst()) {
                    queue.pollFirst();
                }
                // 比新元素小的就扔掉,新元素较小的话也要保留(之后再用)
                while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[right]) {
                    queue.pollLast();
                }
                queue.addLast(right);

                // 左界合法,添加队首下标元素
                if (left >= 0) {
                    res.add(nums[queue.peekFirst()]);
                }
                // helper
                System.out.println("窗口坐标范围:[" + left + ", " + right + "]");
                System.out.println("queue = " + queue);
                System.out.println();
            }
            return res.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    // 重新练习
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            if (len == 0 || len < k) return new int[0];
            int[] res = new int[nums.length - k + 1]; // 窗口个数,计算得到
            int idx = 0;
            Deque<Integer> queue = new LinkedList<Integer>(); // 单调队列--递减的,存储的是下标
            // 遍历数组中元素，right表示滑动窗口右边界
            for (int right = 0; right < len; right++) {
                // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
                // 直到，队列为空或当前考察元素小于新的队尾元素
                while (!queue.isEmpty() && nums[right] > nums[queue.peekLast()]) {
                    queue.pollLast();
                }
                // 存储元素下标
                queue.offerLast(right);
                // 计算窗口左侧边界
                int left = right - k + 1;
                // 当队首元素的下标小于滑动窗口左侧边界left时
                // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
                if (left > queue.peekFirst()) {
                    queue.pollFirst();
                }
                // 形成合法窗口。此时，队首元素就是该窗口内的最大值
                if (left >= 0) {
                    res[idx++] = nums[queue.peekFirst()];
                }
                // helper
                System.out.println("窗口坐标范围:[" + left + ", " + right + "]");
                System.out.println("queue = " + queue);
                System.out.println();
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
