/**
 * 题目Id：剑指 Offer 55 - II
 * 题目：平衡二叉树
 * 日期：2021-06-10 18:00:26
 */
//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。 
//
// 
//
// 示例 1: 
//
// 给定二叉树 [3,9,20,null,null,15,7] 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回 true 。 
// 
//示例 2: 
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//
// 
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// 返回 false 。 
//
// 
//
// 限制： 
//
// 
// 0 <= 树的结点个数 <= 10000 
// 
//
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/ 
//
// 
// Related Topics 树 深度优先搜索 
// 👍 158 👎 0


package leetcode.editor.cn;

//平衡二叉树

import util.TreeNode;

public class 剑指Offer_55_II_平衡二叉树 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_55_II_平衡二叉树().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(4);
//        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
//        TreeNode l3_1 = new TreeNode(1);
//        TreeNode l3_2 = new TreeNode(3);
        TreeNode l3_3 = new TreeNode(5);
//        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
//        root.left = l2_1;
        root.right = l2_2;
//        l2_1.left = l3_1;
//        l2_1.right = l3_2;
        l2_2.left = l3_3;
//        l2_2.right = l3_4;
        boolean balanced = solution.isBalanced(root);
        System.out.println("balanced = " + balanced);
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

    // 计算深度
    class Solution1 {
        // 本身逻辑是个先序遍历--先处理root,再处理root左右子树
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) return false;
            return isBalanced(root.left) && isBalanced(root.right);
        }

        public int getDepth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
        }
    }

    // 参考答案--另一种逻辑
    class Solution {
        public boolean isBalanced(TreeNode root) {
            return recur(root) != -1;
        }

        private int recur(TreeNode root) {
            if (root == null) return 0;
            // 左子或右子不平衡--直接返回-1 不平衡
            int left = recur(root.left);
            if (left == -1) return -1;
            int right = recur(root.right);
            if (right == -1) return -1;
            // 左右高度差是否<2 是则返回高度,否则返回-1代表不平衡
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
