/**
 * 题目Id：剑指 Offer 59 - II
 * 题目：队列的最大值
 * 日期：2021-06-09 22:50:46
 */
//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。
//
// 若队列为空，pop_front 和 max_value 需要返回 -1
//
// 示例 1：
//
// 输入:
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
//
//
// 示例 2：
//
// 输入:
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
//
//
//
//
// 限制：
//
//
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000
// 1 <= value <= 10^5
//
// Related Topics 栈 Sliding Window
// 👍 252 👎 0


package leetcode.editor.cn;

//队列的最大值

import java.util.*;

public class 剑指Offer_59_II_队列的最大值 {
    public static void main(String[] args) {
        //测试代码
//	 	 Solution solution = new 剑指Offer_59_II_队列的最大值().new Solution();
        MaxQueue maxQueue = new 剑指Offer_59_II_队列的最大值().new MaxQueue();
        maxQueue.push_back(8);
        maxQueue.push_back(1);
        maxQueue.push_back(3);
        maxQueue.push_back(5);
        maxQueue.push_back(4);
        maxQueue.push_back(2);
        maxQueue.push_back(6);
        maxQueue.push_back(7);
        System.out.println("---------------------");
        System.out.println("maxQueue.max_value() = " + maxQueue.max_value());

        System.out.println("maxQueue.pop_front() = " + maxQueue.pop_front());
        System.out.println("maxQueue.max_value() = " + maxQueue.max_value());

        System.out.println("maxQueue.pop_front() = " + maxQueue.pop_front());
        System.out.println("maxQueue.pop_front() = " + maxQueue.pop_front());
        System.out.println("maxQueue.pop_front() = " + maxQueue.pop_front());
        System.out.println("maxQueue.pop_front() = " + maxQueue.pop_front());
        System.out.println("maxQueue.max_value() = " + maxQueue.max_value());

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my--错误
    class MaxQueue_wrong {
        Deque<Integer> data;
        PriorityQueue<Integer> maxHeap;
        // 显然不对,错误案例:若干个不是最大值的元素已经出队,最大堆里没移除这些元素
        // 类似题目--滑动窗口最大值中的 '左边界走过去了'

        public MaxQueue_wrong() {
            data = new LinkedList<>();
            maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }

        public int max_value() {
            if (!maxHeap.isEmpty()) {
                return maxHeap.peek();
            } else {
                return -1;
            }
        }

        public void push_back(int value) {
            data.addLast(value);
            maxHeap.offer(value);
        }

        public int pop_front() {
            if (!data.isEmpty()) {
                int x = data.pollFirst();
                if (x == maxHeap.peek()) {
                    maxHeap.poll();
                }
                return x;
            } else {
                return -1;
            }
        }
    }

    // 参考答案--单调队列
    class MaxQueue1 {
        Queue<Integer> data;
        Deque<Integer> max_helper;

        public MaxQueue1() {
            data = new LinkedList<Integer>();
            max_helper = new LinkedList<Integer>();
        }

        public int max_value() {
            if (max_helper.isEmpty()) {
                return -1;
            }
            return max_helper.peekFirst();
        }

        public void push_back(int value) {
            while (!max_helper.isEmpty() && max_helper.peekLast() < value) {
                max_helper.pollLast();
            }
            max_helper.offerLast(value);
            data.offer(value);

        }

        public int pop_front() {
            if (data.isEmpty()) {
                return -1;
            }
            int x = data.poll();
            if (x == max_helper.peekFirst()) {
                max_helper.pollFirst();
            }
            return x;
        }
    }

    // 重新练习
    class MaxQueue {
        Queue<Integer> data;
        Deque<Integer> max_helper;

        public MaxQueue() {
            data = new LinkedList<>();
            max_helper = new LinkedList<>();
        }

        public int max_value() {
            if (max_helper.isEmpty()) return -1;
            else return max_helper.peekFirst();
        }

        public void push_back(int value) {
            data.offer(value);
            // 思想 例如:1,1,1,2 2入队时,把1排除出去(之前比自己小的元素排除)
            while (!max_helper.isEmpty() && max_helper.peekLast() < value) {
                max_helper.pollLast();
            }
            max_helper.addLast(value);
            System.out.println("max_helper = " + max_helper);
        }

        public int pop_front() {
            if (data.isEmpty()) return -1;
            int x = data.poll();
            if (x == max_helper.peekFirst()) {
                max_helper.pollFirst();
            }
            return x;
        }
    }


/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
