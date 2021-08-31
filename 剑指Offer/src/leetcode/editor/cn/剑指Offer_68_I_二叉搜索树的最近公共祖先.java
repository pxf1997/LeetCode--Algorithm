/**
 * 题目Id：剑指 Offer 68 - I
 * 题目：二叉搜索树的最近公共祖先
 * 日期：2021-06-10 18:29:39
 */
//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
//
// 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a
//-binary-search-tree/ 
// Related Topics 树 
// 👍 138 👎 0


package leetcode.editor.cn;

//二叉搜索树的最近公共祖先

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 剑指Offer_68_I_二叉搜索树的最近公共祖先 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_68_I_二叉搜索树的最近公共祖先().new Solution();
        // level_x_index
        TreeNode root = new TreeNode(6);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(8);
        TreeNode l3_1 = new TreeNode(0);
        TreeNode l3_2 = new TreeNode(4);
        TreeNode l3_3 = new TreeNode(7);
        TreeNode l3_4 = new TreeNode(9);
        TreeNode l4_3 = new TreeNode(3);
        TreeNode l4_4 = new TreeNode(5);
        //层序遍历 4 26 1357  中序1234567
        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;
        l3_2.left = l4_3;
        l3_2.right = l4_4;
        TreeNode lowestCommonAncestor = solution.lowestCommonAncestor(root, l4_3, l3_1);
        System.out.println("lowestCommonAncestor = " + lowestCommonAncestor);

//        boolean aHasB = solution.a_Has_b(l2_1, l4_4);
//        System.out.println("aHasB = " + aHasB);
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
    // 分别计算路径
    class Solution1 {
        // 重要条件没看到--BST,节点值不重复
        // 思路--记录找到p和q的路径,路径的分叉点就是最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            List<TreeNode> path_p = new ArrayList<>();
            List<TreeNode> path_q = new ArrayList<>();
            TreeNode ancestor = null;
            getPath(root, p, path_p);
            getPath(root, q, path_q);
            for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
                if (path_p.get(i) == path_q.get(i)) {
                    ancestor = path_p.get(i); // 沿公共路径往下走
                } else {
                    break;
                }
            }
            return ancestor;
        }

        // 注意--不可能找不到
        private void getPath1(TreeNode root, TreeNode target, List<TreeNode> path) {
            path.add(root);
            if (target.val == root.val) return;
            if (target.val < root.val) {
                getPath(root.left, target, path);
            } else {
                getPath(root.right, target, path);
            }
        }

        private void getPath(TreeNode root, TreeNode target, List<TreeNode> path) {
            TreeNode cur = root;
            while (cur != target) { // cur一直定位到target
                path.add(cur);
                if (target.val < cur.val) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            path.add(cur); // 最终找到--别忘了添加
        }

        // 从start节点出发可以找到target--没用到BST条件
        private boolean a_Has_b(TreeNode start, TreeNode target) {
            if (start == null) return false;
            if (start == target) return true;
            return a_Has_b(start.left, target) || a_Has_b(start.right, target);
        }

    }

    // 一次遍历
    class Solution2 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) { // 二者均在左子树中
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) { // 二者均在右子树中
                    ancestor = ancestor.right;
                } else { // 找到分叉点
                    break;
                }
            }
            return ancestor;
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (p.val < root.val && q.val < root.val) { //都在左侧
                return lowestCommonAncestor(root.left, p, q);
            } else if (p.val > root.val && q.val > root.val) { // 都在右侧
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
