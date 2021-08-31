/**
 * 题目Id：23
 * 题目：合并K个升序链表
 * 日期：2021-04-27 17:44:51
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
// Related Topics 堆 链表 分治算法 
// 👍 1278 👎 0


package leetcode.editor.cn;

//合并K个升序链表

import leetcode.editor.cn.util.ListNode;

import java.util.*;

public class P23_MergeKSortedLists {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P23_MergeKSortedLists().new Solution();
        ListNode a3 = new ListNode(7);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(1, a2);
        ListNode b3 = new ListNode(8);
        ListNode b2 = new ListNode(5, b3);
        ListNode b1 = new ListNode(2, b2);
        ListNode c3 = new ListNode(9);
        ListNode c2 = new ListNode(6, c3);
        ListNode c1 = new ListNode(3, c2);

        ListNode resHead = solution.mergeKLists(new ListNode[]{a1, b1, c1});
        System.out.println("resHead = " + resHead);


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
//    暴力合并--这么玩就没意思了啊
/*    class Solution1 {

        public ListNode mergeKLists(ListNode[] lists) {
            int len = lists.length;
            List<Integer> valList = new ArrayList<Integer>();
            for (int i = 0; i < len; i++) {
                ListNode cur = lists[i];
                while (cur != null) {   // 终止条件 cur是最后一个节点的next,跳出循环时cur=null
                    valList.add(cur.val);
                    cur = cur.next;
                }
            }
//            Arrays.sort(new int[]{1,2,4,5,3});
            Collections.sort(valList);
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            for (Integer val : valList) {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
            return dummy.next;
        }
    }*/

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            return merge_help(lists, 0, lists.length - 1);
        }

        private ListNode merge_help(ListNode[] lists, int startIndex, int endIndex) {
            if (startIndex == endIndex) {
                return lists[startIndex];
            }
            if (startIndex > endIndex) {
                return null;
            }
            int mid = (startIndex + endIndex) / 2;  //二分一哈
//            ListNode res1 = merge_help(lists, startIndex, mid); //lists链表数组的 start--mid 部分合并结果
//            ListNode res2 = merge_help(lists, mid + 1, endIndex);//lists链表数组的 mid--end 部分合并结果

            ListNode res1 = merge_help(lists, startIndex, mid); //lists链表数组的 start--mid 部分合并结果
            ListNode res2 = merge_help(lists, mid + 1, endIndex);//lists链表数组的 mid--end 部分合并结果

            return mergeTwoLists(res1, res2); //两部分结果  2链合并--P21题
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            ListNode a = l1, b = l2;
            while (a != null && b != null) {
                if (a.val <= b.val) {
                    tail.next = a;
                    a = a.next;
                } else {
                    tail.next = b;
                    b = b.next;
                }
                tail = tail.next;
            }
            tail.next = (a == null ? b : a);
            return dummy.next;
        }

    }

    class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            return merge(lists, 0, lists.length - 1);
        }

        public ListNode merge(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            }
            if (l > r) {
                return null;
            }
            int mid = (l + r) / 2;
            return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
        }

        public ListNode mergeTwoLists(ListNode a, ListNode b) {
            if (a == null || b == null) {
                return a != null ? a : b;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, aPtr = a, bPtr = b;
            while (aPtr != null && bPtr != null) {
                if (aPtr.val < bPtr.val) {
                    tail.next = aPtr;
                    aPtr = aPtr.next;
                } else {
                    tail.next = bPtr;
                    bPtr = bPtr.next;
                }
                tail = tail.next;
            }
            tail.next = (aPtr != null ? aPtr : bPtr);
            return head.next;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
