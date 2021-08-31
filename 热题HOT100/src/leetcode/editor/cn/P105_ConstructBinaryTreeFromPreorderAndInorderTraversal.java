/**
 * 题目Id：105
 * 题目：从前序与中序遍历序列构造二叉树
 * 日期：2021-07-12 18:10:19
 */
//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1106 👎 0


package leetcode.editor.cn;

//从前序与中序遍历序列构造二叉树

import util.TreeNode;

import java.util.Arrays;

public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();

        int[] preorder = new int[]{4, 2, 1, 3, 6, 5, 7};
        int[] inorder = new int[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode newRoot = solution.buildTree(preorder, inorder);
        System.out.println("root = " + newRoot);
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 1--递归终止条件
            int len = preorder.length;
            if (len == 0) return null;
            if (len == 1) return new TreeNode(preorder[0]);

            // 2--确定根和左右子树的范围,进行递归
            int rootVal = preorder[0];
            int rootIndex = 0;// 体会这个理所当然的必然结论--前序和中序序列不许重复,要不无法定位!
            for (int i = 0; i < len; i++) {
                if (inorder[i] == rootVal) {
                    rootIndex = i;
                    break;
                }
            }
            // 左右子树的中序遍历
            int[] inorder_left = Arrays.copyOfRange(inorder, 0, rootIndex);
            int[] inorder_right = Arrays.copyOfRange(inorder, rootIndex + 1, len);
            int left_size = inorder_left.length;
            int right_size = inorder_right.length;
            // 左右子树的先序遍历
            int[] preorder_left = Arrays.copyOfRange(preorder, 1, 1 + left_size);
            int[] preorder_right = Arrays.copyOfRange(preorder, len - right_size, len);

            TreeNode root = new TreeNode(rootVal);
            root.left = buildTree(preorder_left, inorder_left);
            root.right = buildTree(preorder_right, inorder_right);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
