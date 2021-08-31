/**
 * 题目Id：543
 * 题目：二叉树的直径
 * 日期：2021-08-26 12:26:02
 */
//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 777 👎 0


package leetcode.editor.cn;

//二叉树的直径

import util.TreeNode;

public class P543_DiameterOfBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P543_DiameterOfBinaryTree().new Solution();
        TreeNode root = new TreeNode(4);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
        TreeNode l3_1 = new TreeNode(1);
        TreeNode l3_2 = new TreeNode(3);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;

        int diameterOfBinaryTree = solution.diameterOfBinaryTree(root);
        System.out.println("diameterOfBinaryTree = " + diameterOfBinaryTree);
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
    // 分析--与P124--二叉树路径和 --没啥关系!
    class Solution {
        int maxd;

        public int diameterOfBinaryTree(TreeNode root) {
            depth(root);
            return maxd;
        }

        private int depth(TreeNode node) {
            if (node == null) return 0;
            int left = depth(node.left);
            int right = depth(node.right);
            // 维护全局最大直径(左子树深度+右子树深度)当前最大值比较并取大者
            // 直径 = 左子树高度 + 右子树高度
            maxd = Math.max(left + right, maxd);
            return Math.max(left, right) + 1; // 返回节点深度
        }
    }
    // 小总结--与P124题很类似,dfs(深度优先搜索)过程中--维护一个全局变量(maxd / 最长路径)
    // 这个全局变量 != dfs的返回值 ,但计算过程中用到了返回值

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution_P124 {
        int max = Integer.MIN_VALUE;

        public int diameterOfBinaryTree(TreeNode root) {
            dfs(root);
            return max;
        }

        // 返回值--包含node节点 及其左/右子树中的最大路径长度
        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int leftMax = Math.max(dfs(root.left), 0);
            int rightMax = Math.max(dfs(root.right), 0);
            int curPath = leftMax + rightMax + root.val;
            max = Math.max(max, curPath);
            return root.val + Math.max(leftMax, rightMax);
        }
    }
}
