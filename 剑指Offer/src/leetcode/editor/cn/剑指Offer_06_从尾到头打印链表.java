/**
 * 题目Id：剑指 Offer 06
 * 题目：从尾到头打印链表
 * 日期：2021-06-09 17:06:59
 */
//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表 
// 👍 155 👎 0


package leetcode.editor.cn;

//从尾到头打印链表

import util.ListNode;

import java.util.Arrays;
import java.util.Stack;

public class 剑指Offer_06_从尾到头打印链表 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_06_从尾到头打印链表().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        int[] reversePrint = solution.reversePrint(l1);
        System.out.println("reversePrint = " + Arrays.toString(reversePrint));
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int[] reversePrint(ListNode head) {
            if (head == null) return new int[0];
            Stack<ListNode> stack = new Stack<>();
            ListNode cur = head;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            int size = stack.size();
            int[] res = new int[size];
            int idx = 0;
            while (!stack.isEmpty()) {
                res[idx++] = stack.pop().val;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
