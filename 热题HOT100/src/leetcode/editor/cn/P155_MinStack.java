/**
 * 题目Id：155
 * 题目：最小栈
 * 日期：2021-06-22 18:04:15
 */
//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 934 👎 0


package leetcode.editor.cn;

//最小栈

import java.util.Stack;

public class P155_MinStack {
    public static void main(String[] args) {
        //测试代码
        MinStack minStack = new P155_MinStack().new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("minStack.getMin() = " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top());
        System.out.println("minStack.getMin() = " + minStack.getMin());
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {
        Stack<Integer> min;
        Stack<Integer> data;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            min = new Stack<Integer>();
            data = new Stack<Integer>();
        }

        public void push(int val) {
            data.push(val);
            if (min.isEmpty()) {
                min.add(val);
            } else {
                if (min.peek() >= val) {
                    min.push(val);
                }
            }
        }

        public void pop() {
            int x = data.pop();
            if (min.peek() == x) {
                min.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
