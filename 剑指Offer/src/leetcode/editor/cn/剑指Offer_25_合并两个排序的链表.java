/**
 * 题目Id：剑指 Offer 25
 * 题目：合并两个排序的链表
 * 日期：2021-05-10 16:16:39
 */
//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。 
//
// 示例1： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4 
//
// 限制： 
//
// 0 <= 链表长度 <= 1000 
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
// Related Topics 分治算法 
// 👍 118 👎 0


package leetcode.editor.cn;

//合并两个排序的链表

import util.ListNode;

public class 剑指Offer_25_合并两个排序的链表 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_25_合并两个排序的链表().new Solution();
        ListNode a3 = new ListNode(5);
        ListNode a2 = new ListNode(3, a3);
        ListNode a1 = new ListNode(1, a2);
        ListNode b3 = new ListNode(6);
        ListNode b2 = new ListNode(4, b3);
        ListNode b1 = new ListNode(2, b2);

        ListNode res = solution.mergeTwoLists(a1, b1);
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
        public ListNode mergeTwoLists_old(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            ListNode cur1 = l1, cur2 = l2;
            while (cur1 != null && cur2 != null) {
                if (cur1.val <= cur2.val) {
                    tail.next = cur1;
                    cur1 = cur1.next;
                } else {
                    tail.next = cur2;
                    cur2 = cur2.next;
                }
                tail = tail.next;
            }
            tail.next = (cur1 == null ? cur2 : cur1);
            return dummy.next;
        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            ListNode dummy = new ListNode(-1);
            // tail 定位合并后当前操作位置
            // cur1 cur2 分别定位两个链表的操作位置
            ListNode tail = dummy;
            ListNode cur1 = l1, cur2 = l2;
            while (cur1 != null && cur2 != null) {
                if (cur1.val <= cur2.val) {
                    tail.next = cur1;
                    cur1 = cur1.next;
                } else {
                    tail.next = cur2;
                    cur2 = cur2.next;
                }
                tail = tail.next;
            }
            // cur接到没有用完的链上
            tail.next = (cur1 == null ? cur2 : cur1);
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
