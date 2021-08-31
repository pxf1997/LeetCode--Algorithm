/**
 * 题目Id：234
 * 题目：回文链表
 * 日期：2021-08-27 12:33:47
 */
//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1092 👎 0


package leetcode.editor.cn;

//回文链表

import util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class P234_PalindromeLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P234_PalindromeLinkedList().new Solution();
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
        public boolean isPalindrome(ListNode head) {
            List<Integer> valueList = new ArrayList<Integer>();
            ListNode cur = head;
            while (cur != null) {
                valueList.add(cur.val);
                cur = cur.next;
            }
            int left = 0, right = valueList.size() - 1;
            while (left < right) {
                if (valueList.get(left++) != valueList.get(right--)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // 参考答案--递归
    class Solution2 {
        private ListNode frontPointer;

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }


}
