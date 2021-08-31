/**
 * 题目Id：206
 * 题目：反转链表
 * 日期：2021-06-30 09:29:59
 */
//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 1819 👎 0


package leetcode.editor.cn;

//反转链表

import util.ListNode;

public class P206_ReverseLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P206_ReverseLinkedList().new Solution();
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
        ListNode reverse = solution.reverseList(l1);
        System.out.println("reverse = " + reverse);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return head;
            ListNode cur = head, prev = null;
            // 跳出循环时:cur为null, prev为最后一个
            while(cur != null){
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
