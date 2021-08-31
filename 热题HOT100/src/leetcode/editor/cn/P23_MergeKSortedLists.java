/**
 * 题目Id：23
 * 题目：合并K个升序链表
 * 日期：2021-07-05 16:57:18
 */
//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1371 👎 0


package leetcode.editor.cn;

//合并K个升序链表

import util.ListNode;

public class P23_MergeKSortedLists {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P23_MergeKSortedLists().new Solution();
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

    // 思路----
    // 前置结论 mergeTwoLists 合并两个有序链表
    // 然后两两合并即可
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode newHead = null;
            for (ListNode l : lists) {
                newHead = mergeTwoLists(l, newHead);
            }
            return newHead;
        }

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
