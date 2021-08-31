/**
 * 题目Id：21
 * 题目：合并两个有序链表
 * 日期：2021-06-22 23:11:34
 */
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1764 👎 0


package leetcode.editor.cn;

//合并两个有序链表

import util.ListNode;

public class P21_MergeTwoSortedLists {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P21_MergeTwoSortedLists().new Solution();
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
        ListNode newHead = solution.mergeTwoLists(l1, l4);
        System.out.println("newHead = " + newHead);
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
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            ListNode cur1 = l1, cur2 = l2;
            // 都没用完
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
            // 谁用完了,就续在另一个上
            tail.next = (cur1 == null ? cur2 : cur1);
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
