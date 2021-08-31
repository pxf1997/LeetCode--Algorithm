/**
 * 题目Id：剑指 Offer 52
 * 题目：两个链表的第一个公共节点
 * 日期：2021-05-10 16:18:52
 */
//输入两个链表，找出它们的第一个公共节点。 
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lis
//ts/ 
// 
// Related Topics 链表 
// 👍 221 👎 0


package daily_2021_07;

//两个链表的第一个公共节点

import util.ListNode;

public class 剑指Offer_52_两个链表的第一个公共节点 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_52_两个链表的第一个公共节点().new Solution();
        //case1
//        ListNode c3 = new ListNode(5);
//        ListNode c2 = new ListNode(4, c3);
//        ListNode c1 = new ListNode(8, c2);
//
//        ListNode a2 = new ListNode(1, c1);
//        ListNode a1 = new ListNode(4, a2);
//
//        ListNode b3 = new ListNode(1, c1);
//        ListNode b2 = new ListNode(0, b3);
//        ListNode b1 = new ListNode(5, b2);

        //case2
        ListNode a2 = new ListNode(5);
        ListNode a1 = new ListNode(1, a2);

        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(6, b3);
        ListNode b1 = new ListNode(2, b2);

        ListNode intersectionNode = solution.getIntersectionNode(a1, b1);
        System.out.println("intersectionNode = " + intersectionNode);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution1 {
        public ListNode getIntersectionNode_old(ListNode headA, ListNode headB) {
            int lenA = 0, lenB = 0;

            ListNode curA = headA;
            ListNode curB = headB;

            while (curA != null) {
                curA = curA.next;
                lenA++;
            }
            while (curB != null) {
                curB = curB.next;
                lenB++;
            }
            curA = headA;
            curB = headB;
            System.out.println("lenA = " + lenA);
            System.out.println("lenB = " + lenB);

            int cnt = 0;
            while (curA != null && curB != null && cnt <= lenA + lenB) {
                if (curA == curB) {
                    return curA;
                }
                //对curA处理，两种情况应当是互斥的--if else
                if (curA.next == null) {
                    curA = headB;
                } else {
                    curA = curA.next;
                }
                if (curB.next == null) {
                    curB = headA;
                } else {
                    curB = curB.next;
                }
                cnt++;
            }
            return null;
        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode curA = headA;
            ListNode curB = headB;
            //若相交---正常情况
            //若不相交---二者均为null跳出while(走过路径为两链表之和)
            while (curA != curB) {
                curA = (curA == null ? headB : curA.next);
                curB = (curB == null ? headA : curB.next);
            }
            return curA;
        }
    }

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode curA = headA, curB = headB;
            while (curA != curB) {
                curA = curA == null ? headB : curA.next;
                curB = curB == null ? headA : curB.next;
            }
            return curA;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
