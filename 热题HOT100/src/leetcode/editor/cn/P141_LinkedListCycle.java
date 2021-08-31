/**
 * 题目Id：141
 * 题目：环形链表
 * 日期：2021-07-21 20:48:52
 */
//给定一个链表，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的
//位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环，则返回 true 。 否则，返回 false 。 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 104] 
// -105 <= Node.val <= 105 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
// Related Topics 哈希表 链表 双指针 
// 👍 1137 👎 0


package leetcode.editor.cn;

//环形链表

import util.ListNode;

import java.util.HashSet;
import java.util.Set;

public class P141_LinkedListCycle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P141_LinkedListCycle().new Solution();
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
        l6.next = l3;
        boolean res = solution.hasCycle(l1);
        System.out.println("res = " + res);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        // 哈希表法
        public boolean hasCycle1(ListNode head) {
            Set<ListNode> visited = new HashSet<>();
            int cnt = 0;
            ListNode cur = head;
            while (cur != null) {
                if (!visited.add(cur)) {
                    return true;
                }
                cur = cur.next;
                cnt++;
            }
            return false;
        }

        // 快慢指针--
        // 若有环--则跑的快的一定会在某时刻与跑得慢的相遇
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head, fast = head.next;
            while (slow != fast) {
                // 没有圈--fast先走出去
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            // slow==fast,即有圈
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
