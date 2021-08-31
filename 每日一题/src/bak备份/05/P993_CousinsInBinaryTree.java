/**
 * 题目Id：993
 * 题目：二叉树的堂兄弟节点
 * 日期：2021-05-17 10:26:26
 */
//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。 
//
// 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。 
//
// 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。 
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。 
//
// 
//
// 示例 1： 
// 
//
// 
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
// 
//
// 示例 2： 
// 
//
// 
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
// 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点数介于 2 到 100 之间。 
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。 
// 
//
// 
// Related Topics 树 广度优先搜索 
// 👍 155 👎 0


//二叉树的堂兄弟节点

import util.TreeNode;

public class P993_CousinsInBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P993_CousinsInBinaryTree().new Solution();
        //BST
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

        boolean cousins = solution.isCousins(root, 1, 3);
//        boolean cousins = solution.isCousins(root, 5, 3);
        System.out.println("cousins = " + cousins);

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
        // 不能有值相同的节点(否则输入参数x和y都无法确定!)  因此直接用int表示父亲,不需要用TreeNode
        int xparent, xdepth, yparent, ydepth;

        public boolean isCousins(TreeNode root, int x, int y) {
            dfs(root, 0, x, y, 0);
            //helper
            System.out.println("xparent=" + xparent + "  xdepth=" + xdepth);
            System.out.println("yparent=" + yparent + "  ydepth=" + ydepth);

            return xparent != yparent && xdepth == ydepth;

        }

        // 需求----记录x y的深度以及他们的爸爸
        private void dfs(TreeNode node, int depth, int x, int y, int parentVal) {
            if (node == null) {
                return;
            }
            // 发现 x or y
            if (node.val == x) {
                xdepth = depth;
                xparent = parentVal;
            } else if (node.val == y) {
                ydepth = depth;
                yparent = parentVal;
            }
            // 常规操作 记录爸爸的值
            dfs(node.left, depth + 1, x, y, node.val);
            dfs(node.right, depth + 1, x, y, node.val);


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
