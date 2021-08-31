/**
 * 题目Id：剑指 Offer 35
 * 题目：复杂链表的复制
 * 日期：2021-05-31 18:26:34
 */
//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
//
// 
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-point
//er/ 
//
// 
// Related Topics 链表 
// 👍 213 👎 0


package leetcode.editor.cn;

//复杂链表的复制

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer_35_复杂链表的复制 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_35_复杂链表的复制().new Solution();
        Node l1 = new Node(1);
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(4);
        Node l5 = new Node(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l1.random = l5;
        l2.random = l4;
        l3.random = l3;
        l4.random = l2;
        l5.random = l1;
        Node res = solution.copyRandomList(l1);
        System.out.println("res = " + res);

    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
    // 每个节点复制 + 拆分
    class Solution1 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            // 1--每个节点复制插在原来的后面
            Node cur = head;
            while (cur != null) {
                Node temp = cur.next;
                Node copy = new Node(cur.val);
                cur.next = copy;
                copy.next = temp;
                cur = temp;
            }
            // 2--随机指针复制
            cur = head;
            while (cur != null) {
                Node clone = cur.next;
                clone.random = (cur.random == null ? null : cur.random.next);
                cur = cur.next.next;
            }
            // 3--拆分出新链表
            cur = head;
            Node cloneHead = head.next;
            while (cur != null) {
                Node temp = cur.next;
                cur.next = temp.next;
                temp.next = (temp.next == null ? null : temp.next.next);
                cur = cur.next;
            }
            return cloneHead;
        }
    }

    // 哈希表
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            // key--老节点  value--新节点
            Map<Node, Node> map = new HashMap<>();
            Node cur = head;
            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }
            for (Node node : map.keySet()) {
                // map取出新造节点,它的next等于在map中取出老节点next对应的val
                map.get(node).next = map.get(node.next);
                map.get(node).random = map.get(node.random);
            }
            return map.get(head);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
