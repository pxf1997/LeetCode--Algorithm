/**
 * 题目Id：203
 * 题目：移除链表元素
 * 日期：2021-05-07 17:21:16
 */
//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点在范围 [0, 104] 内 
// 1 <= Node.val <= 50 
// 0 <= k <= 50 
// 
// Related Topics 链表 
// 👍 588 👎 0


//移除链表元素

import util.ListNode;

public class P203_RemoveLinkedListElements {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P203_RemoveLinkedListElements().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);

        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l4.next = l5;
        l5.next = l6;

//        ListNode newRoot = solution.removeElements(l1, 1);
        ListNode newRoot = solution.removeElements(l1, 1);
        System.out.println("newRoot = " + newRoot);
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
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, cur = dummy.next;
            while (cur != null) { //最后跳出while时cur为null,pre为最后元素
                if (cur.val == val) {
                    pre.next = cur.next; //删除了元素--指针pre不移动
                } else {
                    pre = cur; //没删除元素--pre移动（保证pre总是在结果链中）
                }
                cur = cur.next;
            }

            return dummy.next;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
