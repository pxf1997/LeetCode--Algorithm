/**
 * 题目Id：938
 * 题目：二叉搜索树的范围和
 * 日期：2021-04-27 10:19:27
 */
//给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
//输出：32
// 
//
// 示例 2： 
//
// 
//输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 2 * 104] 内 
// 1 <= Node.val <= 105 
// 1 <= low <= high <= 105 
// 所有 Node.val 互不相同 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 189 👎 0


//二叉搜索树的范围和


import java.util.ArrayList;
import java.util.List;

public class P938_RangeSumOfBst {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P938_RangeSumOfBst().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
    class Solution1 {
        public int rangeSumBST(TreeNode root, int low, int high) {
            List<Integer> valueList = new ArrayList<Integer>();
            inOrder(root, valueList);
            System.out.println("valueList = " + valueList);
            int res = 0;
            for (Integer value : valueList) {
                if (value <= high && value >= low) {
                    res += value;
                }
            }
            return res;
        }

        private void inOrder(TreeNode node, List<Integer> valueList) {
            if (node == null) return;
            inOrder(node.left, valueList);
            valueList.add(node.val);
            inOrder(node.right, valueList);
        }
    }

    class Solution {
        public int sum;

        public int rangeSumBST(TreeNode root, int low, int high) {
            inOrder(root, low, high);
            return sum;
        }

        private void inOrder(TreeNode node, int low, int high) {
            if (node == null) return;

            inOrder(node.left, low, high);
            if (node.val <= high && node.val >= low) sum += node.val;
            inOrder(node.right, low, high);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
