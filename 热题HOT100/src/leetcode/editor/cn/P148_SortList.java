/**
 * 题目Id：148
 * 题目：排序链表
 * 日期：2021-08-26 10:22:40
 */
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1268 👎 0


package leetcode.editor.cn;

//排序链表

import util.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P148_SortList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P148_SortList().new Solution();
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(1);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode res = solution.sortList(l1);
        System.out.println("res = " + res);
    }

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
    // 暴力法--拷贝出来 时间O(2n) 空间O(n)
    class Solution_my {
        public ListNode sortList(ListNode head) {
            ListNode cur = head;
            List<Integer> list = new ArrayList<Integer>();
            while (cur != null) { // 跳出循环时 cur==null
                list.add(cur.val);
                cur = cur.next;
            }
            cur = head;
            Collections.sort(list);
            int index = 0;
            while (cur != null) { // 跳出循环时 cur==null
                cur.val = list.get(index);
                cur = cur.next;
                index++;
            }
            return head;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 归并排序
    class Solution {
        public ListNode sortList(ListNode head) {
            return sort_helper(head, null);
        }

        private ListNode sort_helper(ListNode head, ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            // 找终点--快慢指针
            ListNode slow = head, fast = head;
            while (fast != tail) {
                fast = fast.next;
                slow = slow.next;
                if (fast != tail) fast = fast.next;
            }
            ListNode mid = slow;
            ListNode list1 = sort_helper(head, mid);
            ListNode list2 = sort_helper(mid, tail);
            ListNode merge = merge(list1, list2);
            return merge;

        }

        // 合并两个有序链表
        private ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy, cur1 = head1, cur2 = head2;
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
            // 没有用完的
            tail.next = cur1 != null ? cur1 : cur2;
            return dummy.next;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
