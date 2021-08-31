/**
 * 题目Id：剑指 Offer 54
 * 题目：二叉搜索树的第k大节点
 * 日期：2021-06-10 16:56:22
 */
//给定一棵二叉搜索树，请找出其中第k大的节点。 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 1 ≤ k ≤ 二叉搜索树元素个数 
// Related Topics 树 
// 👍 166 👎 0


package leetcode.editor.cn;

//二叉搜索树的第k大节点

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 剑指Offer_54_二叉搜索树的第k大节点 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_54_二叉搜索树的第k大节点().new Solution();
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
        int kthLargest = solution.kthLargest(root, 3);
        System.out.println("kthLargest = " + kthLargest);
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

    // 记录整个中序遍历结果
    class Solution1 {
        List<Integer> list = new ArrayList<>();

        public int kthLargest(TreeNode root, int k) {
            if (root == null) return Integer.MIN_VALUE;
            inOrder(root);
            return list.get(list.size() - 1 - (k - 1));
        }

        private void inOrder(TreeNode root) {
            if (root == null) return;
            inOrder(root.left);
            list.add(root.val);
            inOrder(root.right);
        }
    }

    // 参考答案--打印中序遍历倒序: 右 根 左
    class Solution {
        // 从大到小数,第k个,cnt初始为k最后减到0
        int cnt;
        int res;

        public int kthLargest(TreeNode root, int k) {
            cnt = k;
            inOrder_reverse(root);
            return res;
        }

        private void inOrder_reverse(TreeNode node) {
            if (node == null) return;
            inOrder_reverse(node.right);
            // 提前返回--后续不遍历了!
            if (cnt == 0) return;
            if (--cnt == 0) res = node.val;
            inOrder_reverse(node.left);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
