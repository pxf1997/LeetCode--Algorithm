/**
 * 题目Id：102
 * 题目：二叉树的层序遍历
 * 日期：2021-07-01 16:38:21
 */
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 910 👎 0


package leetcode.editor.cn;

//二叉树的层序遍历

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P102_BinaryTreeLevelOrderTraversal().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(4);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
        TreeNode l3_1 = new TreeNode(1);
        TreeNode l3_2 = new TreeNode(3);
        TreeNode l3_3 = new TreeNode(5);
        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

        List<List<Integer>> res = solution.levelOrder(root);
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
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                List<Integer> this_level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    this_level.add(cur.val);
                    // 确保了入队的都不是null
                    if (cur.left != null) queue.add(cur.left);
                    if (cur.right != null) queue.add(cur.right);
                }
                System.out.println("level = " + level + "  this_level = " + this_level);
                res.add(this_level);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
