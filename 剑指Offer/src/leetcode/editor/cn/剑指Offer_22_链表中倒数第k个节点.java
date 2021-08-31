/**
 * 题目Id：剑指 Offer 22
 * 题目：链表中倒数第k个节点
 * 日期：2021-06-09 18:11:56
 */
//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。 
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。 
//
// 
//
// 示例： 
//
// 
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5. 
// Related Topics 链表 双指针 
// 👍 200 👎 0


package leetcode.editor.cn;

//链表中倒数第k个节点

import util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class 剑指Offer_22_链表中倒数第k个节点 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_22_链表中倒数第k个节点().new Solution();
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
        ListNode res = solution.getKthFromEnd(l1, 2);
        System.out.println("res = " + res);
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
        // 直接遍历一遍存到list里,略lowb
        public ListNode getKthFromEnd1(ListNode head, int k) {
            List<ListNode> list = new ArrayList<>();
            ListNode cur = head;
            while (cur != null) {
                list.add(cur);
                cur = cur.next;
            }
            int len = list.size();
            if (k <= 0 || len < k) return null;
            return list.get(len - k);
        }

        // 快慢指针
        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode slow = head, fast = head;
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
