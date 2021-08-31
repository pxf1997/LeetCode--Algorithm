/**
 * 题目Id：863
 * 题目：二叉树中所有距离为 K 的结点
 * 日期：2021-07-28 09:41:01
 */
//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。 
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 320 👎 0


package daily_2021_07;

//二叉树中所有距离为 K 的结点

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P863_AllNodesDistanceKInBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P863_AllNodesDistanceKInBinaryTree().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(3);
        TreeNode l2_1 = new TreeNode(5);
        TreeNode l2_2 = new TreeNode(1);
        TreeNode l3_1 = new TreeNode(6);
        TreeNode l3_2 = new TreeNode(2);
        TreeNode l3_3 = new TreeNode(0);
        TreeNode l3_4 = new TreeNode(8);
        TreeNode l4_3 = new TreeNode(7);
        TreeNode l4_4 = new TreeNode(4);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        l3_2.left = l4_3;
        l3_2.right = l4_4;

        List<Integer> res = solution.distanceK(root, l2_1, 2);
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
        // 分析--
        // 难点--如何从5去寻找1,即往上走到根,再到兄弟节点
        // 解法--二叉树转为图,进行图的dfs

        // key--节点值  value--对应的父节点
        Map<Integer, TreeNode> parents = new HashMap<>();// 注意节点值不相同,可以作为key
        List<Integer> res = new ArrayList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 从root出发dfs,记录所有节点的父节点
            findParents(root);
            //System.out.println("parents = " + parents);

            // 从target出发dfs,寻找深度为k的节点
            findRes(target, null, 0, k);
            return res;
        }

        private void findRes(TreeNode node, TreeNode from, int depth, int k) {
            // 1--结束条件
            if (node == null) return;
            if (depth == k) {
                //System.out.println("找到结果:" + node.val);
                res.add(node.val);
                return;
            }
            // 2--递归深入,左子/右子/父节点
            if (node.left != from) {
                findRes(node.left, node, depth + 1, k);
            }
            if (node.right != from) {
                findRes(node.right, node, depth + 1, k);
            }
            if (parents.get(node.val) != from) {
                findRes(parents.get(node.val), node, depth + 1, k);
            }
        }

        private void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
