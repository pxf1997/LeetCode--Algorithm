package daily_old; /**
 * 题目Id：872
 * 题目：叶子相似的树
 * 日期：2021-05-10 10:08:49
 */
//请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。 
//
// 
//
// 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。 
//
// 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。 
//
// 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,nul
//l,null,null,null,9,8]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root1 = [1], root2 = [1]
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：root1 = [1], root2 = [2]
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：root1 = [1,2], root2 = [2,2]
//输出：true
// 
//
// 示例 5： 
//
// 
//
// 
//输入：root1 = [1,2,3], root2 = [1,3,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 给定的两棵树可能会有 1 到 200 个结点。 
// 给定的两棵树上的值介于 0 到 200 之间。 
// 
// Related Topics 树 深度优先搜索 
// 👍 119 👎 0


//叶子相似的树

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P872_LeafSimilarTrees {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P872_LeafSimilarTrees().new Solution();
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

        boolean res = solution.leafSimilar(root, root);
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
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leafVal1 = new ArrayList<>();
            List<Integer> leafVal2 = new ArrayList<>();

            dfs(root1, leafVal1);
            dfs(root2, leafVal2);

//            System.out.println("leafVal1 = " + leafVal1);
//            System.out.println("leafVal2 = " + leafVal2);

            if (leafVal1.size() != leafVal2.size()) {
                return false;
            }
            int len = leafVal1.size();

            for (int i = 0; i < len; i++) {
                if (leafVal1.get(i) != leafVal2.get(i)) {
                    return false;
                }
            }

            return true;

//            return leafVal1.equals(leafVal2); //效率较低了

        }

        private void dfs(TreeNode root, List<Integer> leafVal) {
            if (root == null) {
                return;
            }
            dfs(root.left, leafVal);
            if (isLeaf(root)) {
                leafVal.add(root.val);
            }
            dfs(root.right, leafVal);

        }

        private boolean isLeaf(TreeNode root) {
//            return (root.left == null && root.right == null);
            return (root!=null && root.left == null && root.right == null);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
