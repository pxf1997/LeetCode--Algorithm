/**
 * 题目Id：剑指 Offer 26
 * 题目：树的子结构
 * 日期：2021-06-10 14:02:56
 */
//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。
//
// 例如:
//给定的树 A:
//
// 3
// / \
// 4 5
// / \
// 1 2
//给定的树 B：
//
// 4
// /
// 1
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
//
// 示例 1：
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
//
//
// 示例 2：
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true
//
// 限制：
//
// 0 <= 节点个数 <= 10000
// Related Topics 树
// 👍 282 👎 0


package leetcode.editor.cn;

//树的子结构

import util.TreeNode;

public class 剑指Offer_26_树的子结构 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_26_树的子结构().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(1);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(3);
        TreeNode l3_1 = new TreeNode(4);
//        TreeNode l3_2 = new TreeNode(3);
//        TreeNode l3_3 = new TreeNode(5);
//        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
//        l2_1.right = l3_2;
//        l2_2.left = l3_3;
//        l2_2.right = l3_4;

        TreeNode root2 = new TreeNode(3);
//        TreeNode t2_1 = new TreeNode(1);
//        TreeNode t2_2 = new TreeNode(3);
//        root2.left = t2_1;
//        t2_2.right = t2_2;

        boolean b = solution.isSubStructure(root, root2);
        System.out.println("b = " + b);


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
        // 空树不是子结构
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (A == null || B == null) return false;
            // 逐层寻找可比较的点
            // A.left深入的时候要考虑null
            return if_A_has_B(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }

        private boolean if_A_has_B(TreeNode a, TreeNode b) {
            // 都空--true  b空a不空--true a空b不空--false
            if (a == null && b == null) return true;
            if (a != null && b == null) return true;
            if (a == null && b != null) return false;
            // 至此一定有a,b均不空
            if (a.val != b.val) return false;
            return if_A_has_B(a.left, b.left) && if_A_has_B(a.right, b.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
