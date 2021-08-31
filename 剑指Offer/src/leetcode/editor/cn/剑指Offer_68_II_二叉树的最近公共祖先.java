/**
 * 题目Id：剑指 Offer 68 - II
 * 题目：二叉树的最近公共祖先
 * 日期：2021-06-10 23:16:41
 */
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a
//-binary-tree/ 
// Related Topics 树 
// 👍 269 👎 0


package leetcode.editor.cn;

//二叉树的最近公共祖先

import util.TreeNode;

public class 剑指Offer_68_II_二叉树的最近公共祖先 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_68_II_二叉树的最近公共祖先().new Solution();
        TreeNode root = new TreeNode(3);
        TreeNode l2_1 = new TreeNode(5);
        TreeNode l2_2 = new TreeNode(1);
        TreeNode l3_1 = new TreeNode(6);
        TreeNode l3_2 = new TreeNode(2);
        TreeNode l3_3 = new TreeNode(0);
        TreeNode l3_4 = new TreeNode(8);
        TreeNode l4_3 = new TreeNode(7);
        TreeNode l4_4 = new TreeNode(4);
        //层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        l3_2.left = l4_3;
        l3_2.right = l4_4;
        TreeNode ancestor = solution.lowestCommonAncestor(root, l3_1, l4_4);
        System.out.println("ancestor = " + ancestor);
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

    // 不是BST了,但思路相似--p,q两节点同属于左子树/右子树 or 分属两边--分叉点
    class Solution1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // root.left 同时包含p和q 则下移至root.left
            if (a_Has_b(root.left, p) && a_Has_b(root.left, q)) {
                return lowestCommonAncestor(root.left, p, q);
            }
            // root.right 同时包含p和q 则下移至root.right
            else if (a_Has_b(root.right, p) && a_Has_b(root.right, q)) {
                return lowestCommonAncestor(root.right, p, q);
            }
            // p q 分属root两侧,则分叉点为root
            else {
                return root;
            }
        }

        // 从start节点出发可以找到target--没用到BST条件
        private boolean a_Has_b(TreeNode start, TreeNode target) {
            if (start == null) return false;
            if (start == target) return true;
            return a_Has_b(start.left, target) || a_Has_b(start.right, target);
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 递归终止条件
            if (root == null || p == root || q == root) return root;
            TreeNode l = lowestCommonAncestor(root.left, p, q);
            TreeNode r = lowestCommonAncestor(root.right, p, q);
            // l、r 非空时，说明 p、q 分居 root 的两侧，root 就是 LCA
            // l、r 任一为空，说明 LCA 位于另一子树或其祖先中
            return l == null ? r : (r == null ? l : root);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
