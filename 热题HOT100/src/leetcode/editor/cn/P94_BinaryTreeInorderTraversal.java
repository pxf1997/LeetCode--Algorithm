/**
 * 题目Id：94
 * 题目：二叉树的中序遍历
 * 日期：2021-07-01 16:43:28
 */
//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1008 👎 0


package leetcode.editor.cn;

//二叉树的中序遍历

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P94_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P94_BinaryTreeInorderTraversal().new Solution();

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
    class Solution {
        List<Integer> res = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) return res;
            inOrder(root);
            return res;
        }

        private void inOrder(TreeNode node) {
            if (node == null) return;
            inOrder(node.left);
            res.add(node.val);
            inOrder(node.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
