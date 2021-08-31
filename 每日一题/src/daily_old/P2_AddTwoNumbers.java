package daily_old; /**
 * 题目Id：2
 * 题目：两数相加
 * 日期：2021-04-30 10:27:51
 */
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6123 👎 0


//两数相加

import util.ListNode;

public class P2_AddTwoNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P2_AddTwoNumbers().new Solution();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;

        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        ListNode res = solution.addTwoNumbers(l1, l4);
        System.out.println("res = " + res);

//        System.out.println("7%10 = " + 7 % 10);
//        System.out.println("11%10 = " + 11 / 10);
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //  如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 0 。
            //  此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            int add = 0;
            while (l1 != null || l2 != null) {
                int num1 = (l1 == null ? 0 : l1.val);
                int num2 = (l2 == null ? 0 : l2.val);
                int sum = num1 + num2 + add;
                add = sum / 10;

                //  调试
                System.out.println("sum % 10 = " + sum % 10);
                System.out.println("add = " + add);
                System.out.println();

                //  三个指针的移动
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }

            System.out.println("最后add = " + add);
            if (add != 0) {
                tail.next = new ListNode(add);
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
