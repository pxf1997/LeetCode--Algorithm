/**
 * 题目Id：94
 * 题目：二叉树的中序遍历
 * 日期：2021-05-07 11:53:40
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
// Related Topics 栈 树 哈希表 
// 👍 941 👎 0


//二叉树的中序遍历

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P94_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P94_BinaryTreeInorderTraversal().new Solution();
        TreeNode root = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);
        //层序遍历 4 26 1357  中序1234567
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        List<Integer> res = solution.inorderTraversal(root);
        System.out.println("res = " + res);
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            inOrder_help(root, res);
            return res;
        }

        private void inOrder_help(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }
            inOrder_help(node.left, res);
            res.add(node.val);
            inOrder_help(node.right, res);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
