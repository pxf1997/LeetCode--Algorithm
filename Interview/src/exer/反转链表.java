package exer;

import util.ListNode;

/**
 * @author pxf
 * @create 2021-08-26 21:14
 */
public class 反转链表 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 反转链表().new Solution();
        // case1
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


        ListNode res = solution.ReverseList(l1);
        System.out.println("res = " + res);
    }

    public class Solution {
        public ListNode ReverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            // 基本结束条件--只有一个节点
            if (head.next == null) {
                return head;
            }

            ListNode cur = head;
            ListNode previous = null;
            // 跳出循环 cur为null, previous为最后一个节点
            while(cur!=null){
                ListNode next = cur.next;
                cur.next = previous;
                previous = cur;
                cur = next;
            }
            return previous;
        }
    }
}
