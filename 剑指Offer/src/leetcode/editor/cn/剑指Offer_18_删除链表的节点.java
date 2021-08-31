/**
 * 题目Id：剑指 Offer 18
 * 题目：删除链表的节点
 * 日期：2021-06-09 22:23:48
 */
//给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。 
//
// 返回删除后的链表的头节点。 
//
// 注意：此题对比原题有改动 
//
// 示例 1: 
//
// 输入: head = [4,5,1,9], val = 5
//输出: [4,1,9]
//解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
// 
//
// 示例 2: 
//
// 输入: head = [4,5,1,9], val = 1
//输出: [4,5,9]
//解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
// 
//
// 
//
// 说明： 
//
// 
// 题目保证链表中节点的值互不相同 
// 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点 
// 
// Related Topics 链表 
// 👍 139 👎 0


package leetcode.editor.cn;

//删除链表的节点

import util.ListNode;

public class 剑指Offer_18_删除链表的节点 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_18_删除链表的节点().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        ListNode res = solution.deleteNode(l1, 3);
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

    // 一个工作指针cur--删去所有值为val的节点
    class Solution1 {
        public ListNode deleteNode(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode cur = dummy;
            // 如果被删除的是最后一个
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                }
                if (cur.next != null) {
                    cur = cur.next;
                }
            }
            return dummy.next;

        }
    }

    // 双指针--只能删掉一个
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            if (head.val == val) return head.next;
            ListNode pre = head, cur = head.next;
            // pre为cur之前的一个 cur定位到被删除处
            while (cur != null && cur.val != val) {
                pre = cur;
                cur = cur.next;
            }
            // 找到被删节点cur
            if (cur != null) pre.next = cur.next;
            return head;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
