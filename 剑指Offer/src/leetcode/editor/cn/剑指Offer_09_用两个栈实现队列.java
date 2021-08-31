/**
 * 题目Id：剑指 Offer 09
 * 题目：用两个栈实现队列
 * 日期：2021-06-09 17:59:00
 */
//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 )
//
//
//
// 示例 1：
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
//
//
// 示例 2：
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
//
//
// 提示：
//
//
// 1 <= values <= 10000
// 最多会对 appendTail、deleteHead 进行 10000 次调用
//
// Related Topics 栈 设计
// 👍 240 👎 0


package leetcode.editor.cn;

//用两个栈实现队列

import java.util.Stack;

public class 剑指Offer_09_用两个栈实现队列 {
    public static void main(String[] args) {
        //测试代码
//        Solution solution = new 剑指Offer_09_用两个栈实现队列().new Solution();
        CQueue queue = new 剑指Offer_09_用两个栈实现队列().new CQueue();
        System.out.println("queue.deleteHead() = " + queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println("queue.deleteHead() = " + queue.deleteHead());
        System.out.println("queue.deleteHead() = " + queue.deleteHead());

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class CQueue {
        Stack<Integer> data;
        Stack<Integer> helper;

        public CQueue() {
            data = new Stack<Integer>();
            helper = new Stack<Integer>();
        }

        public void appendTail(int value) {
            data.push(value);
        }

        public int deleteHead() {
            // helper为空 data全部倒入helper
            if (helper.isEmpty()) {
                while (!data.isEmpty()) {
                    helper.push(data.pop());
                }
            }
            if (helper.isEmpty()) { // 非法
                return -1;
            } else {
                return helper.pop();
            }
        }
    }

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
