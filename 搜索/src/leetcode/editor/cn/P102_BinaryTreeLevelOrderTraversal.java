/**
 * 题目Id：102
 * 题目：二叉树的层序遍历
 * 日期：2021-04-29 10:10:09
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
// Related Topics 树 广度优先搜索 
// 👍 855 👎 0


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
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(7);
        root.left = t1;
        root.right = t2;
        t2.left = t3;
        t2.right = t4;

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                //每次处理一层
                int size = queue.size();
                level++;
                List<Integer> thisLevel = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    thisLevel.add(cur.val);
                    // 处理左右子
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
                // helper
                System.out.println(level + " : " + thisLevel);
                res.add(thisLevel);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
