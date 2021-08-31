/**
 * 题目Id：19
 * 题目：删除链表的倒数第 N 个结点
 * 日期：2021-06-25 10:31:49
 */
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1421 👎 0


package leetcode.editor.cn;

//删除链表的倒数第 N 个结点

import util.ListNode;

public class P19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P19_RemoveNthNodeFromEndOfList().new Solution();
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

        ListNode head = solution.removeNthFromEnd(l1, 6);
        System.out.println("head = " + head);
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

        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 分析--快慢指针,考虑细节--有没有可能删除第一个节点
            // 有可能,n=len,例子-- 1-2-3-4-5,倒数第5个
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode slow = dummy, fast = dummy;
            // fast先走n步
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }
            // fast停在最后一个节点,而非null(画图看下)
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            // 删除 slow.next
            slow.next = slow.next.next;
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
