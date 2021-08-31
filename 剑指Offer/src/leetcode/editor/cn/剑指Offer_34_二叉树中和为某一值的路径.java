/**
 * 题目Id：剑指 Offer 34
 * 题目：二叉树中和为某一值的路径
 * 日期：2021-06-10 15:56:50
 */
//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。 
//
// 
//
// 示例: 
//给定如下二叉树，以及目标和 target = 22， 
//
// 
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// 
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 
// 👍 185 👎 0


package leetcode.editor.cn;

//二叉树中和为某一值的路径

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer_34_二叉树中和为某一值的路径 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_34_二叉树中和为某一值的路径().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(5);
        TreeNode l2_1 = new TreeNode(4);
        TreeNode l2_2 = new TreeNode(8);
        TreeNode l3_1 = new TreeNode(11);
        TreeNode l3_3 = new TreeNode(13);
        TreeNode l3_4 = new TreeNode(4);
        TreeNode l4_1 = new TreeNode(7);
        TreeNode l4_2 = new TreeNode(2);
        TreeNode l4_7 = new TreeNode(5);
        TreeNode l4_8 = new TreeNode(1);
        //层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        l3_1.left = l4_1;
        l3_1.right = l4_2;
        l3_4.left = l4_7;
        l3_4.right = l4_8;

        List<List<Integer>> res = solution.pathSum(root, 22);
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

    // 回溯
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            if (root == null) return res;
            List<Integer> path = new ArrayList<>();
            backtracking(root, target, path);
            return res;
        }

        // 做减法
        private void backtracking(TreeNode node, int target, List<Integer> path) {
            // 1--递归终止用 null
            if (node == null) return;
            path.add(node.val);
            target -= node.val;
            // 检测条件是否满足
            if (isLeaf(node) && target == 0) {
                System.out.println("找到答案:" + path);
                res.add(new ArrayList<>(path));
            }
            // 2--递归深入:判断非空(写在前面)
            System.out.println("递归之前 => " + path + "  剩余:" + target);

            backtracking(node.left, target, path);
            backtracking(node.right, target, path);

            path.remove(path.size() - 1); // 写在最后即可
            System.out.println("递归之后 => " + path);
        }

        public boolean isLeaf(TreeNode node) {
            return node.left == null && node.right == null;
        }
    }

    // 参考答案
    class Solution1 {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            backtracking(root, sum);
            return res;
        }

        void backtracking(TreeNode root, int tar) {
            if (root == null) return;
            path.add(root.val);
            tar -= root.val;
            if (tar == 0 && root.left == null && root.right == null)
                res.add(new LinkedList(path));
            backtracking(root.left, tar);
            backtracking(root.right, tar);
            path.removeLast();
        }
    }


//leetcode submit region end(Prohibit modification and deletion)
}
