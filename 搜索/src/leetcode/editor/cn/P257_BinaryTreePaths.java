/**
 * 题目Id：257
 * 题目：二叉树的所有路径
 * 日期：2021-05-07 16:36:59
 */
//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 493 👎 0


package leetcode.editor.cn;

//二叉树的所有路径

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P257_BinaryTreePaths {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P257_BinaryTreePaths().new Solution();
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

        List<String> res = solution.binaryTreePaths(root);
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
    //回溯
    class Solution {
        List<String> totalPath = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            List<Integer> onePath = new ArrayList<>();
            dfs_backtracking(root, onePath);
            return totalPath;
        }

        private void dfs_backtracking(TreeNode node, List<Integer> path) {
            if (node == null) {
                return;
            }
            path.add(node.val);
            if (isLeaf(node)) {
                String s = buildPath(path);
                System.out.println("s = " + s);
                totalPath.add(s);
            } else {
                dfs_backtracking(node.left, path);
                dfs_backtracking(node.right, path);
            }
            //如果没有回溯，肯定错啊，因为你的path会记录所有经过的点
            path.remove(path.size() - 1);
        }

        private boolean isLeaf(TreeNode node) {
            return node.left == null && node.right == null;
        }

        private String buildPath(List<Integer> path) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                str.append(path.get(i));
                if (i != path.size() - 1) { //不是最后一个
                    str.append("->");
                }
            }
            return str.toString();
        }
    }

    //dfs 没回溯？
    class Solution1 {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> paths = new ArrayList<String>();
            constructPaths(root, "", paths);
            return paths;
        }

        public void constructPaths(TreeNode root, String path, List<String> paths) {
            if (root != null) {
                StringBuffer pathSB = new StringBuffer(path);
                pathSB.append(root.val);
                if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                    System.out.println("pathSB = " + pathSB);
                    paths.add(pathSB.toString());  // 把路径加入到答案中
                } else {
                    pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                    constructPaths(root.left, pathSB.toString(), paths);
                    constructPaths(root.right, pathSB.toString(), paths);
                }
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
