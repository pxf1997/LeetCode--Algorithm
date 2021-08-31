/**
 * 题目Id：剑指 Offer 30
 * 题目：包含min函数的栈
 * 日期：2021-05-10 16:55:51
 */
//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。 
//
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// 各函数的调用总次数不超过 20000 次 
// 
//
// 
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/ 
// Related Topics 栈 设计 
// 👍 126 👎 0


package leetcode.editor.cn;

//包含min函数的栈

import java.util.Stack;

public class 剑指Offer_30_包含min函数的栈 {
    public static void main(String[] args) {
        //测试代码
//        Solution solution = new P30().new Solution();'
        MinStack minStack = new 剑指Offer_30_包含min函数的栈().new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("minStack.min() = " + minStack.min());
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top());
        System.out.println("minStack.min() = " + minStack.min());


    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    class MinStack1 {
        Stack<Integer> data;
        Stack<Integer> mindata;

        /**
         * initialize your data structure here.
         */
        public MinStack1() {
            data = new Stack<>();
            mindata = new Stack<>();

        }

        public void push(int x) {
            data.push(x);
            if (mindata.empty() || mindata.peek() >= x) {
                mindata.push(x);
            }
        }

        public void pop() {
            int x = data.pop();
            if (x == mindata.peek()) {
                mindata.pop();
            }
        }

        public int top() {
            return data.peek();

        }

        public int min() {
            return mindata.peek();
        }
    }

    class MinStack {
        Stack<Integer> data;
        Stack<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            min = new Stack<>(); // min栈也是单调栈哟,从底到顶递减
        }

        public void push(int x) {
            data.push(x);
            if (min.isEmpty() || x <= min.peek()) { // <= 要包含等于号
                min.push(x);
            }
        }

        public void pop() {
            int x = data.pop();
            if (x == min.peek()) {
                min.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int min() {
            if (!min.isEmpty()) {
                return min.peek();
            } else {
                return Integer.MAX_VALUE; // 非法
            }
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
