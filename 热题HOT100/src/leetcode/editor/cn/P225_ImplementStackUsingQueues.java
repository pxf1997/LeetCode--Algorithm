/**
 * 题目Id：225
 * 题目：用队列实现栈
 * 日期：2021-06-29 14:02:28
 */
//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。 
//
// 实现 MyStack 类： 
//
// 
// void push(int x) 将元素 x 压入栈顶。 
// int pop() 移除并返回栈顶元素。 
// int top() 返回栈顶元素。 
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 注意： 
//
// 
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。 
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用100 次 push、pop、top 和 empty 
// 每次调用 pop 和 top 都保证栈不为空 
// 
//
// 
//
// 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的
//时间。你可以使用两个以上的队列。 
// Related Topics 栈 设计 队列 
// 👍 339 👎 0


package leetcode.editor.cn;

//用队列实现栈

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class P225_ImplementStackUsingQueues {
    public static void main(String[] args) {
        //测试代码
        MyStack solution = new P225_ImplementStackUsingQueues().new MyStack();
        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);
        System.out.println("solution.pop() = " + solution.pop());
        System.out.println("solution.top() = " + solution.top());
        System.out.println("solution.empty() = " + solution.empty());
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 思路--
    // 一个队列在模拟栈弹出元素的时候只要将队列头部的元素（除了最后一个元素外）
    // 重新添加到队列尾部，此时在去弹出元素就是栈的顺序了。
    class MyStack1 {
        // Deque 接口继承了 Queue 接口
        // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
        Deque<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack1() {
            queue = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queue.addLast(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            int size = queue.size();
            size--;
            // 将 que1 导入 que2 ，但留下最后一个值
            for (int i = 0; i < size; i++) {
                queue.addLast(queue.pollFirst());
            }
            return queue.pollFirst();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue.peekLast(); // 有开挂的嫌疑
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    // 两个队列--一个存储栈数据,一个辅助队列
    class MyStack {
        Queue<Integer> data;
        Queue<Integer> helper;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
            helper = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            // 举例--data={4,3,2,1}, push(5)
            // 操作完毕后, data=空 helper={5,4,3,2,1},二者交换后,helper恢复为空
            helper.offer(x);
            while(!data.isEmpty()){
                helper.offer(data.poll());
            }
            Queue<Integer> temp = data;
            data = helper;
            helper = temp;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return data.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return data.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.isEmpty();
        }
    }
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
