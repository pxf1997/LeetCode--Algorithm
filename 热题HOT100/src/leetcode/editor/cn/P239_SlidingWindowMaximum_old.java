/**
 * 题目Id：239
 * 题目：滑动窗口最大值
 * 日期：2021-07-06 14:34:28
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

import java.util.*;

public class P239_SlidingWindowMaximum_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P239_SlidingWindowMaximum_old().new Solution();
        // 测试用例
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

//        int[] nums = new int[]{-7, -8, 7, 5, 7, 1, 6, 0};
//        int k = 4;

//        int[] nums = new int[]{7, 2, 4};
//        int k = 2;

        System.out.println("nums = " + Arrays.toString(nums));
        int[] res = solution.maxSlidingWindow(nums, k);
        System.out.println("res = " + Arrays.toString(res));
    }


    // my--单调队列
    // 评价--这个版本写的比较清楚!!!
    class Solution{
        // 单调队列中存储的是--下标
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            if (len == 0 || len < k) return new int[0]; // 特殊情况

            List<Integer> list = new ArrayList<>(); // 可以计算出长度为len-k+1,或直接存入list
            Deque<Integer> queue = new LinkedList<>(); // 存储的是下标
            // 窗口范围[left, right]
            for (int right = 0; right < len; right++) {
                int left = right - k + 1;
                // 比新元素小的就扔掉,新元素较小的话则要保留(之后再用)
                while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[right]) {
                    queue.pollLast();
                }
                queue.addLast(right);

                // 左界失效,即删除过期数值
                if (left > queue.peekFirst()) {
                    queue.pollFirst();
                }
                // 窗口形成了
                if (left >= 0) {
                    list.add(nums[queue.peekFirst()]);
                }
                // helper
                System.out.println("窗口坐标范围:[" + left + ", " + right + "]");
                System.out.println("queue = " + queue);
                System.out.println();
            }
            return list.stream().mapToInt(Integer::valueOf).toArray();
        }
        // 分析----直接存储数值,咱不存下标了行不行?
        // 尝试结论----不好弄,出错!
        /*public int[] maxSlidingWindow(int[] nums, int k) {
            if (k == 1) return nums; // 窗口长度为1,返回原数组
            int len = nums.length;
            List<Integer> list = new ArrayList<>();
            Deque<Integer> queue = new LinkedList<>();
            // 窗口范围[left, right]
            for (int right = 0; right < len; right++) {
                int left = right - k + 1;
                if (queue.isEmpty()) { // 第一次添加了两个元素?
                    queue.addLast(nums[right]);
                }
                // 左界失效,出队
                if (left > 0) {
                    // left-1 在有效窗口左侧,且这个值被存储在队列中
                    if (nums[left - 1] == queue.peekFirst()) {
                        int out = queue.pollFirst();
                        System.out.println("窗口失效,出队:" + out);
                    }
                }
                // 比新元素小的就扔掉,新元素较小的话则要保留(之后再用)
                while (!queue.isEmpty() && queue.peekLast() < nums[right]) {
                    queue.pollLast();
                }
                queue.addLast(nums[right]);

                // 窗口形成了
                if (left >= 0) {
                    list.add(queue.peekFirst());
                }
                // helper
                System.out.println("窗口坐标范围:[" + left + ", " + right + "]");
                System.out.println("queue = " + queue);
                System.out.println();

            }
            return list.stream().mapToInt(Integer::valueOf).toArray();
        }*/
    }


    // 参考解法--自定义数组(单调队列的数据结构)
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1) {
                return nums;
            }
            int len = nums.length - k + 1;
            //存放结果元素的数组
            int[] res = new int[len];
            int idx = 0;
            //自定义队列
            MyQueue myQueue = new MyQueue();
            //先将前k的元素放入队列
            for (int i = 0; i < k; i++) {
                myQueue.add(nums[i]);
            }
            System.out.println("myQueue = " + myQueue);
            res[idx++] = myQueue.peek();

            for (int i = k; i < nums.length; i++) {
                //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
                myQueue.poll(nums[i - k]);
                //滑动窗口加入最后面的元素
                myQueue.add(nums[i]);
                System.out.println("i = " + i + "  myQueue = " + myQueue);
                //记录对应的最大值
                res[idx++] = myQueue.peek();
            }
            return res;
        }

        class MyQueue {
            Deque<Integer> deque = new LinkedList<>();

            //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
            //同时判断队列当前是否为空
            void poll(int val) {
                if (!deque.isEmpty() && val == deque.peek()) {
                    deque.poll();
                }
            }

            //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
            //保证队列元素单调递减
            //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
            void add(int val) {
                while (!deque.isEmpty() && val > deque.getLast()) {
                    deque.removeLast();
                }
                deque.add(val);
            }

            //队列队顶元素始终为最大值
            int peek() {
                return deque.peek();
            }

            @Override
            public String toString() {
                return deque.toString();
            }
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--官方题解
    class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            // 先入队k个元素,形成第一个窗口
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }
            System.out.println("deque = " + deque);

            int[] ans = new int[len - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < len; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                // 失效的窗口
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
