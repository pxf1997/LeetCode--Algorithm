/**
 * 题目Id：剑指 Offer 24
 * 题目：反转链表
 * 日期：2021-06-09 22:07:40
 */
//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。 
//
// 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 链表 
// 👍 243 👎 0


package leetcode.editor.cn;

//反转链表

import util.ListNode;

public class 剑指Offer_24_反转链表 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_24_反转链表().new Solution();
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
        ListNode res = solution.reverseList(l1);
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
        public ListNode reverseList(ListNode head) {
            ListNode cur = head, prev = null;
            // prev--前一个 cur--当前操作的节点
            while (cur != null) { // 跳出while时 cur=null prev=最后一个
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
