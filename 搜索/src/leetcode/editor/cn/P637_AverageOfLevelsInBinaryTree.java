/**
 * 题目Id：637
 * 题目：二叉树的层平均值
 * 日期：2021-04-29 15:27:02
 */
//给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。 
//
// 
//
// 示例 1： 
//
// 输入：
//    3
//   / \
//  9  20
//    /  \
//   15   7
//输出：[3, 14.5, 11]
//解释：
//第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
// 
//
// 
//
// 提示： 
//
// 
// 节点值的范围在32位有符号整数范围内。 
// 
// Related Topics 树 
// 👍 256 👎 0


package leetcode.editor.cn;

//二叉树的层平均值

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P637_AverageOfLevelsInBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P637_AverageOfLevelsInBinaryTree().new Solution();
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(7);
        root.left = t1;
        root.right = t2;
        t2.left = t3;
        t2.right = t4;

        List<Double> res = solution.averageOfLevels(root);
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
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                int num = size;
                level++;
//                List<Integer> thisLevel = new ArrayList<>();
                double thisLevelSum = 0;
                while (size-- > 0) {
                    TreeNode cur = queue.poll();
//                    thisLevel.add(cur.val);
                    thisLevelSum += cur.val;
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
//                System.out.println(level + " : " + thisLevel);
                double avg = (double) thisLevelSum / num;
                res.add(avg);


            }

            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
