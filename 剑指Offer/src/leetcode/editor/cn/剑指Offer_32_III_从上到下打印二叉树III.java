/**
 * 题目Id：剑指 Offer 32 - III
 * 题目：从上到下打印二叉树 III
 * 日期：2021-06-10 15:50:01
 */
//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 
// 👍 102 👎 0


package leetcode.editor.cn;

//从上到下打印二叉树 III

import util.TreeNode;

import java.util.*;

public class 剑指Offer_32_III_从上到下打印二叉树III {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_32_III_从上到下打印二叉树III().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(4);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
        TreeNode l3_1 = new TreeNode(1);
        TreeNode l3_2 = new TreeNode(3);
        TreeNode l3_3 = new TreeNode(5);
        TreeNode l3_4 = new TreeNode(7);
        //层序遍历 4 26 1357  中序1234567
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
     * TreeNode(int x) { val = x; }
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
            // 用res.size()也可以确定奇偶层,但多一个level也无所谓啊!!!
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
                if (level % 2 == 0) {
                    Collections.reverse(thisLevel);
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
