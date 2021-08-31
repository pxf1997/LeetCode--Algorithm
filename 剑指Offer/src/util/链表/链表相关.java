package util.链表;

import util.ListNode;

/**
 * @author pxf
 * @create 2021-06-08 23:09
 */
public class 链表相关 {
    public static void main(String[] args) {
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
        print_LinkedList(l1);
        System.out.println("------");
        print_LinkedList(l4);
    }
    public static void print_LinkedList(ListNode head) {
        ListNode cur = head;
        while(cur!=null){
            System.out.println("cur.val = " + cur.val);
            cur = cur.next;
        }
    }
}
