/**
 * 题目Id：226
 * 题目：翻转二叉树
 * 日期：2021-07-22 17:25:15
 */
//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 921 👎 0


package leetcode.editor.cn;

//翻转二叉树

import util.TreeNode;

import java.util.ArrayDeque;

public class P226_InvertBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P226_InvertBinaryTree().new Solution();
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

        TreeNode newRoot = solution.invertTree(root);
        System.out.println("newRoot = " + newRoot);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

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
    // backtracking
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            // 处理右子树,接在左侧
            TreeNode temp = root.left;
            root.left = invertTree(root.right);
            // 处理左子树,接在右侧
            root.right = invertTree(temp);
            return root;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // 参考答案--多个版本
    // DFS
    class Solution_DFS {
        /**
         * 前后序遍历都可以
         * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            invertTree(root.left);
            invertTree(root.right);
            swapChildren(root);
            return root;
        }

        private void swapChildren(TreeNode root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    //BFS
    class Solution_BFS {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            ArrayDeque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                while (size-- > 0) {
                    TreeNode node = deque.poll();
                    swap(node);
                    if (node.left != null) {
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        deque.offer(node.right);
                    }
                }
            }
            return root;
        }

        public void swap(TreeNode root) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
    }


}
