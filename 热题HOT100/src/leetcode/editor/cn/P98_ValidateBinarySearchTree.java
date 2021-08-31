/**
 * 题目Id：98
 * 题目：验证二叉搜索树
 * 日期：2021-08-24 23:25:33
 */
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1183 👎 0


package leetcode.editor.cn;

//验证二叉搜索树

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P98_ValidateBinarySearchTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P98_ValidateBinarySearchTree().new Solution();
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

    // 中序遍历存在list里
    class Solution {
        public boolean isValidBST(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            inOrder(root, list);
//            System.out.println("list = " + list);

            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) >= list.get(i + 1)) return false;
            }
            return true;
        }

        private void inOrder(TreeNode node, List<Integer> list) {
            if (node == null) return;
            inOrder(node.left, list);
            list.add(node.val);
            inOrder(node.right, list);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 递归
    class Solution2 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
        }
    }

    // 在中序中判断(维护前一个节点)
    class Solution3 {
        long pre = Long.MIN_VALUE;
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            // 访问左子树
            if (!isValidBST(root.left)) {
                return false;
            }
            // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            // 访问右子树
            return isValidBST(root.right);
        }
    }



}
