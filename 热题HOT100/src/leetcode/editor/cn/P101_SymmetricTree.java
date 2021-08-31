/**
 * 题目Id：101
 * 题目：对称二叉树
 * 日期：2021-07-29 09:44:02
 */
//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1460 👎 0


package leetcode.editor.cn;

//对称二叉树

import util.TreeNode;

public class P101_SymmetricTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P101_SymmetricTree().new Solution();
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

        boolean symmetric = solution.isSymmetric(root);
        System.out.println("symmetric = " + symmetric);
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            else return helper(root.left, root.right);
        }

        private boolean helper(TreeNode left, TreeNode right) {
            // 都空为true,一空一不空为false
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            // 看图说话--左子的左和右子的右比较,左子的右和右子的左比较
            return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
