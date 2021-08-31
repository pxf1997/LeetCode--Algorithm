/**
 * 题目Id：671
 * 题目：二叉树中第二小的节点
 * 日期：2021-07-27 12:13:14
 */
//给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
//个。 
//
// 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。 
//
// 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,2,5,null,null,5,7]
//输出：5
//解释：最小的值是 2 ，第二小的值是 5 。
// 
//
// 示例 2： 
//
// 
//输入：root = [2,2,2]
//输出：-1
//解释：最小的值是 2, 但是不存在第二小的值。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 25] 内 
// 1 <= Node.val <= 231 - 1 
// 对于树中每个节点 root.val == min(root.left.val, root.right.val) 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 181 👎 0


package daily_2021_07;

//二叉树中第二小的节点

import util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P671_SecondMinimumNodeInABinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P671_SecondMinimumNodeInABinaryTree().new Solution();
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
        public int findSecondMinimumValue(TreeNode root) {
            // 记录中序
            List<Integer> list = new ArrayList<Integer>();
            inOrder(root, list);
            Collections.sort(list);
            //System.out.println("list = " + list);
            // 最小值一定是第一个,然后判断是否有 "第二小值"
            int min = list.get(0);
            for (Integer x : list) {
                if (x > min) {
                    return x;
                }
            }
            return -1;
        }

        private void inOrder(TreeNode root, List<Integer> list) {
            if (root == null) return;
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }

    // 参考答案
    class Solution2 {
        int ans;
        int rootvalue;

        public int findSecondMinimumValue(TreeNode root) {
            ans = -1;
            rootvalue = root.val;
            dfs(root);
            return ans;
        }

        public void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            if (ans != -1 && node.val >= ans) {
                return;
            }
            if (node.val > rootvalue) {
                ans = node.val;
            }
            dfs(node.left);
            dfs(node.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
