/**
 * 题目Id：124
 * 题目：二叉树中的最大路径和
 * 日期：2021-08-25 12:30:29
 */
//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 10⁴]
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1181 👎 0


package leetcode.editor.cn;

//二叉树中的最大路径和

import util.TreeNode;

public class P124_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P124_BinaryTreeMaximumPathSum().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(10);
        TreeNode l2_1 = new TreeNode(9);
        TreeNode l2_2 = new TreeNode(20);
        TreeNode l3_3 = new TreeNode(-10);
        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        int res = solution.maxPathSum(root);
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

    // 分析--难点在哪?  路径可以从子节点找到父节点,还不一定要经过根节点
    class Solution {
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            if (root == null) return 0;
            dfs(root);
            return max;
        }

        /**
         * 返回经过root的单边分支最大和, 即 Math.max(root, root+left, root+right)
         */
        private int dfs(TreeNode root) {
            if (root == null) return 0;
            // 递归计算左右子节点的最大贡献值
            // 只有在最大贡献值大于 0 时，才会选取对应子节点
            int leftMax = Math.max(dfs(root.left), 0);
            int rightMax = Math.max(dfs(root.right), 0);

            // left -> root -> right 作为路径与已记录的最大值比较
            max = Math.max(max, root.val + leftMax + rightMax);

            // 返回经过root的单边最大分支给当前root的父节点计算使用
            return root.val + Math.max(leftMax, rightMax);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
