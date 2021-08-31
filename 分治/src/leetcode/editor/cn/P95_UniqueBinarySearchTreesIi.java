/**
 * 题目Id：95
 * 题目：不同的二叉搜索树 II
 * 日期：2021-04-27 11:00:27
 */
//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。 
//
// 
//
// 示例： 
//
// 输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 8 
// 
// Related Topics 树 动态规划 
// 👍 859 👎 0


package leetcode.editor.cn;

//不同的二叉搜索树 II


import leetcode.editor.cn.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class P95_UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P95_UniqueBinarySearchTreesIi().new Solution();
        List<TreeNode> res = solution.generateTrees(3);
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


        public List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<TreeNode>();
            }
            return generateTrees_help(1, n);

        }

        private List<TreeNode> generateTrees_help(int start, int end) {
//            分析：根节点的值为 i，左子树的节点值的集合为 [1…i−1]，右子树的节点值的集合[i+1…n]
//            1----i为根，左右分，[start,i−1] 和 [i+1,end]
//            2----递归调用这两部分得到可行左子树 右子树
//            3----二者各选一颗拼接到根节点上，加入答案数组
            List<TreeNode> allTrees = new LinkedList<TreeNode>();
            if (start > end) {
                allTrees.add(null);
                return allTrees;
            }
//             枚举可行根节点
            for (int i = start; i <= end; i++) {
//                获得所有可行的左、右子树集合
                List<TreeNode> leftTrees = generateTrees_help(start, i - 1);
                List<TreeNode> rightTrees = generateTrees_help(i + 1, end);
//                从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode curTree = new TreeNode(i);
                        curTree.left = leftTree;
                        curTree.right = rightTree;
                        allTrees.add(curTree);
                    }
                }
            }
//            想多一点----递归的最小条件：generateTrees_help(i,i) 生成叶子节点i
            return allTrees;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
