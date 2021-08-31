/**
 * 题目Id：剑指 Offer 36
 * 题目：二叉搜索树与双向链表
 * 日期：2021-06-01 11:53:57
 */
//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。 
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。 
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 分治算法 
// 👍 246 👎 0


package leetcode.editor.cn;

//二叉搜索树与双向链表

import java.util.ArrayList;
import java.util.List;

public class 剑指Offer_36_二叉搜索树与双向链表 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_36_二叉搜索树与双向链表().new Solution();
        Node root = new Node(4);
        Node l2_1 = new Node(2);
        Node l2_2 = new Node(6);
        Node l3_1 = new Node(1);
        Node l3_2 = new Node(3);
        Node l3_3 = new Node(5);
        Node l3_4 = new Node(7);
        //层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

//        Node root = new Node(2);
////        Node l2_1 = new Node(1);
////        root.left = l2_1;
        Node res = solution.treeToDoublyList(root);
        System.out.println("res = " + res);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

    // 额外空间法
    class Solution1 {
        public Node treeToDoublyList(Node root) {
            // 0个节点
            if (root == null) {
                return null;
            }
            // 一个节点
            if (root.left == null && root.right == null) {
                root.left = root;
                root.right = root;
                return root;
            }
            List<Node> list = new ArrayList<>();
            inOrder(root, list);

            // 处理除了首尾节点的其余节点
            int len = list.size();
            for (int i = 1; i < len - 1; i++) {
                list.get(i).left = list.get(i - 1);
                list.get(i).right = list.get(i + 1);
            }
            // 处理首尾节点
            Node head = list.get(0);
            Node tail = list.get(len - 1);
            head.left = tail;
            head.right = list.get(1);
            tail.left = list.get(len - 2);
            tail.right = head;
            return head;
        }

        private void inOrder(Node node, List<Node> list) {
            if (node == null) {
                return;
            }
            inOrder(node.left, list);
            list.add(node);
            inOrder(node.right, list);
        }
    }

    // 递归
    class Solution {
        Node pre, head;

        public Node treeToDoublyList(Node root) {
            // 仅有一个节点--也能处理 调试一下就理解了
            if (root == null) return null;
            dfs(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        private void dfs(Node cur) {
            if (cur == null) return;
            dfs(cur.left);

            //  pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,
            //  当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
            //  反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            if (pre != null) pre.right = cur;
            else head = cur;

            cur.left = pre;// pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
            pre = cur;// pre指向当前的cur

            dfs(cur.right);// 全部迭代完成后，pre指向双向链表中的尾节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
