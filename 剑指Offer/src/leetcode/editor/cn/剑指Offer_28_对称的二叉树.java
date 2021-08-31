/**
 * 题目Id：剑指 Offer 28
 * 题目：对称的二叉树
 * 日期：2021-06-10 15:08:52
 */
//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 
// 👍 180 👎 0


package leetcode.editor.cn;

//对称的二叉树

import util.TreeNode;

public class 剑指Offer_28_对称的二叉树 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_28_对称的二叉树().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(1);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(2);
        TreeNode l3_1 = new TreeNode(3);
        TreeNode l3_2 = new TreeNode(4);
        TreeNode l3_3 = new TreeNode(4);
        TreeNode l3_4 = new TreeNode(3);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        boolean b = solution.isSymmetric(root);
        System.out.println("b = " + b);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return helper(root.left, root.right);
        }

        private boolean helper(TreeNode left, TreeNode right) {
            // 全空--true 一个空一个不空--false
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            // 至此保证全不空
            if (left.val != right.val) return false;
            // 看图说话, {左子的左,右子的右}凑一对, {左子的右,右子的左}凑一对
            return helper(left.left, right.right) && helper(left.right, right.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
