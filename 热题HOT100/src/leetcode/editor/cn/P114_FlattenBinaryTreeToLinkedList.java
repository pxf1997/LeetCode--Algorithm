/**
 * 题目Id：114
 * 题目：二叉树展开为链表
 * 日期：2021-07-12 17:01:17
 */
//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 
// 👍 848 👎 0


package leetcode.editor.cn;

//二叉树展开为链表

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P114_FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P114_FlattenBinaryTreeToLinkedList().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(4);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
        TreeNode l3_1 = new TreeNode(1);
        TreeNode l3_2 = new TreeNode(3);
        TreeNode l3_3 = new TreeNode(5);
        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

        solution.flatten(root);

    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    // 分析-- right变为next, left始终为空
    // 解法1-- 按先序遍历存储到list里,再修改
    class Solution1 {
        public void flatten(TreeNode root) {
            if (root == null) return;
            List<TreeNode> list = new ArrayList<TreeNode>();
            preOrder(root, list);

            int len = list.size();
            // 处理下标[0, len-2]
            for (int i = 0; i < len - 1; i++) {
                list.get(i).left = null;
                list.get(i).right = list.get(i + 1);
            }
            // 处理最后一个,下标len-1
            list.get(len - 1).left = null;
            list.get(len - 1).right = null;
        }

        // 先序--节点,左子,右子 (类似读书,先读目录再按章节)
        private void preOrder(TreeNode node, List<TreeNode> list) {
            if (node == null) return;
            list.add(node);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }



    // 解法2--前序遍历和展开同步进行
    // 在先序遍历的同时,更改left,right指针
    class Solution2 {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            TreeNode prev = null;
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (prev != null) {
                    prev.left = null;
                    prev.right = cur;
                }
                TreeNode left = cur.left, right = cur.right;
                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
                prev = cur;
            }
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法3--寻找前驱节点
    class Solution {
        public void flatten(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                // 左子树的最下最右的节点，是右子树的父节点.
                if (curr.left != null) {
                    TreeNode next = curr.left;
                    TreeNode predecessor = next;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    predecessor.right = curr.right;
                    curr.left = null;
                    curr.right = next;
                }
                curr = curr.right;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
