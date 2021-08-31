/**
 * 题目Id：面试题 02.01
 * 题目：移除重复节点
 * 日期：2021-06-09 09:19:05
 */
//编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
//
// 示例1:
//
//
// 输入：[1, 2, 3, 3, 2, 1]
// 输出：[1, 2, 3]
//
//
// 示例2:
//
//
// 输入：[1, 1, 1, 1, 2]
// 输出：[1, 2]
//
//
// 提示：
//
//
// 链表长度在[0, 20000]范围内。
// 链表元素在[0, 20000]范围内。
//
//
// 进阶：
//
// 如果不得使用临时缓冲区，该怎么解决？
// Related Topics 链表
// 👍 108 👎 0


package leetcode.editor.cn;

//移除重复节点

import util.ListNode;

import java.util.HashSet;
import java.util.Set;

public class 面试题_02_01_移除重复节点 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 面试题_02_01_移除重复节点().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(2);
        ListNode l6 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        ListNode res = solution.removeDuplicateNodes(l1);
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

    // cur + prev 双指针 繁琐了点
    class Solution1 {
        public ListNode removeDuplicateNodes(ListNode head) {
            if (head == null || head.next == null) return head;
            Set<Integer> set = new HashSet<Integer>();
            // head一定保留,无需哑结点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode cur = head, prev = dummy;
            while (cur != null) {
                if (!set.contains(cur.val)) {
                    set.add(cur.val);
                    prev.next = cur;
                    prev = prev.next;
                } else {
                    prev.next = null;
                }
                cur = cur.next;
            }
            return dummy.next;
        }
    }

    // cur 单个指针即可解决问题
    class Solution {
        public ListNode removeDuplicateNodes(ListNode head) {
            if (head == null) {
                return head;
            }
            Set<Integer> occurred = new HashSet<Integer>();
            occurred.add(head.val);
            ListNode pos = head;
            // 枚举前驱节点
            // 跳出循环pos为最后一个节点(下标len-1),但在pos为倒数第二个节点时(len-2)已经判断过最后一个节点了
            while (pos.next != null) {
                // 当前待删除节点
                ListNode cur = pos.next;
                if (occurred.add(cur.val)) {
                    pos = pos.next;
                } else {
                    pos.next = pos.next.next;
                }
            }
            pos.next = null;
            return head;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
