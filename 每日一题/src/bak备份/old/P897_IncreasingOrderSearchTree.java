/**
 * 题目Id：897
 * 题目：递增顺序搜索树
 * 日期：2021-04-25 11:10:05
 */
//给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 179 👎 0


//递增顺序搜索树

import java.util.ArrayList;

public class P897_IncreasingOrderSearchTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P897_IncreasingOrderSearchTree().new Solution();
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
//    方法二：在中序遍历的过程中改变节点指向
    class Solution {
        private TreeNode resNode;

        public TreeNode increasingBST(TreeNode root) {
            TreeNode dummyNode = new TreeNode(-1);
            resNode = dummyNode;
            inOrder(root);
            return dummyNode.right;

        }

        private void inOrder(TreeNode node) {
            if (node == null) return;

            inOrder(node.left);
            // 在中序遍历的过程中修改节点指向
            resNode.right = node;
            node.left = null;
            resNode = node;

            inOrder(node.right);

        }
    }

//    方法一：中序遍历之后生成新的树
    class Solution1 {
        public TreeNode increasingBST(TreeNode root) {
            ArrayList<Integer> nodeValueList = new ArrayList<>();
            inOrder(root, nodeValueList);
            System.out.println("nodeValueList = " + nodeValueList);

//            类似新建一个链表--自己的写法--不好！！！
/*            TreeNode res = new TreeNode(nodeValueList.get(0));
            TreeNode cur = res;
            for (int i = 1; i < nodeValueList.size(); i++) {
                TreeNode newNode = new TreeNode(nodeValueList.get(i));
                cur.right = newNode;
                cur = newNode;
            }
            return res;*/

//            优化版--哑结点法
            TreeNode dummy = new TreeNode(-1);
            TreeNode cur = dummy;
            for (int val : nodeValueList) {
                cur.right = new TreeNode(val);
                cur = cur.right;
            }
            return dummy.right;
        }

        private void inOrder(TreeNode node, ArrayList<Integer> nodeList) {
//            终止条件 + 自己的逻辑 + 套路（前中后序的三种DFS）
            if (node == null) return;
            inOrder(node.left, nodeList);

            nodeList.add(node.val); //升序的列表

            inOrder(node.right, nodeList);
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}
