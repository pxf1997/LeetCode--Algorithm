/**
 * 题目Id：剑指 Offer 27
 * 题目：二叉树的镜像
 * 日期：2021-06-10 14:43:03
 */
//请完成一个函数，输入一个二叉树，该函数输出它的镜像。 
//
// 例如输入： 
//
// 4 
// / \ 
// 2 7 
// / \ / \ 
//1 3 6 9 
//镜像输出： 
//
// 4 
// / \ 
// 7 2 
// / \ / \ 
//9 6 3 1 
//
// 
//
// 示例 1： 
//
// 输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/ 
// Related Topics 树 
// 👍 136 👎 0


package leetcode.editor.cn;

//二叉树的镜像

import util.TreeNode;

public class 剑指Offer_27_二叉树的镜像 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_27_二叉树的镜像().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(4);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(6);
        TreeNode l3_1 = new TreeNode(1);
        TreeNode l3_2 = new TreeNode(3);
        TreeNode l3_3 = new TreeNode(5);
        TreeNode l3_4 = new TreeNode(7);
        // 层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

        TreeNode newroot = solution.mirrorTree(root);
        System.out.println("newroot = " + newroot);
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution_my {
        // 递归终止--判断叶子--复杂了!!!
        public TreeNode mirrorTree1(TreeNode root) {
            // 1--终止条件
            if (root == null) return null;
            if (isLeaf(root)) return root;
            TreeNode temp = root.left;
            root.left = mirrorTree1(root.right);
            root.right = mirrorTree1(temp);
            return root;
        }

        public boolean isLeaf(TreeNode node) {
            return node.left == null && node.right == null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution {
        // 递归终止--判断null--简单
        public TreeNode mirrorTree(TreeNode root) {
            // 1--终止条件
            if (root == null) return null;
            TreeNode temp = root.left;
            root.left = mirrorTree(root.right);
            root.right = mirrorTree(temp);
            return root;
        }
    }

}
