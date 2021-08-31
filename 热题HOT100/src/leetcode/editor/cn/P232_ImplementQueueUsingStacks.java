/**
 * 题目Id：232
 * 题目：用栈实现队列
 * 日期：2021-06-29 14:26:56
 */
//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）： 
//
// 实现 MyQueue 类： 
//
// 
// void push(int x) 将元素 x 推到队列的末尾 
// int pop() 从队列的开头移除并返回元素 
// int peek() 返回队列开头的元素 
// boolean empty() 如果队列为空，返回 true ；否则，返回 false 
// 
//
// 
//
// 说明： 
//
// 
// 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
// 
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
// 
//
// 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用 100 次 push、pop、peek 和 empty 
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作） 
// 
// Related Topics 栈 设计 队列 
// 👍 429 👎 0


package leetcode.editor.cn;

//用栈实现队列

import java.util.Stack;

public class P232_ImplementQueueUsingStacks {
    public static void main(String[] args) {
        //测试代码
        MyQueue solution = new P232_ImplementQueueUsingStacks().new MyQueue();
        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);
        System.out.println("solution.pop() = " + solution.pop());
        System.out.println("solution.peek() = " + solution.peek());
        System.out.println("solution.empty() = " + solution.empty());
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {
        Stack<Integer> inStack; // 负责入栈
        Stack<Integer> outStack; // 负责出栈

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            inStack = new Stack<Integer>();
            outStack = new Stack<Integer>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            inStack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            dump_inStack();
            return outStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            dump_inStack();
            return outStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        // 如果stack2为空，那么将stack1中的元素全部放到stack2中
        private void dump_inStack() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
