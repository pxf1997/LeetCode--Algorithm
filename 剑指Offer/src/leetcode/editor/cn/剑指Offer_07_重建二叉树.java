/**
 * 题目Id：剑指 Offer 07
 * 题目：重建二叉树
 * 日期：2021-06-09 17:09:34
 */
//输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//
//
//
// 例如，给出
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
//
// 限制：
//
// 0 <= 节点个数 <= 5000
//
//
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/
// Related Topics 树 递归
// 👍 466 👎 0


package leetcode.editor.cn;

//重建二叉树

import util.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class 剑指Offer_07_重建二叉树 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_07_重建二叉树().new Solution();
        // 层序 {4, 2,6, 1,3,5,7}
        TreeNode root = solution.buildTree(
                new int[]{4, 2, 1, 3, 6, 5, 7},
                new int[]{1, 2, 3, 4, 5, 6, 7}
                );
        System.out.println("root = " + root);
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

    //my
    class Solution {
        // 分析--必须不含重复的数字
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 1--递归终止条件
            int len = preorder.length;
            if (len == 0) return null;
            if (len == 1) return new TreeNode(preorder[0]);

            // 「前序」第一个是根,从「中序」里分左右子树
            TreeNode root = new TreeNode(preorder[0]);
            int root_index = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (inorder[i] == preorder[0]) {
                    root_index = i;
                    break;
                }
            }

            // 「中序」的左右子树部分
            int[] inorder_left = Arrays.copyOfRange(inorder, 0, root_index);
            int[] inorder_right = Arrays.copyOfRange(inorder, root_index + 1, len);
            int left_size = inorder_left.length;
            int right_size = inorder_right.length;
            // 「前序」的左右子树部分
            int[] preorder_left = Arrays.copyOfRange(preorder, 1, 1 + left_size);
            int[] preorder_right = Arrays.copyOfRange(preorder, 1 + left_size, len);

            // helper
            System.out.println("root = " + root.val);
            System.out.println("inorder_left = " + Arrays.toString(inorder_left) + "  inorder_right = " + Arrays.toString(inorder_right));
            System.out.println("preorder_left = " + Arrays.toString(preorder_left) + "  preorder_right = " + Arrays.toString(preorder_right));
            System.out.println();

            // 2--递归深入(减小规模调用自身)
            root.left = buildTree(preorder_left, inorder_left);
            root.right = buildTree(preorder_right, inorder_right);
            return root;
        }
    }

    // 参考--不太好理解
    class Solution2 {
        //利用原理,先序遍历的第一个节点就是根。在中序遍历中通过根 区分哪些是左子树的，哪些是右子树的
        //左右子树，递归
        HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
        int[] preorder;//保留的先序遍历

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            for (int i = 0; i < preorder.length; i++) {
                map.put(inorder[i], i);
            }
            return recursive(0, 0, inorder.length - 1);
        }

        /**
         * @param pre_root_idx 先序遍历的索引
         * @param in_left_idx  中序遍历的索引
         * @param in_right_idx 中序遍历的索引
         */
        public TreeNode recursive(int pre_root_idx, int in_left_idx, int in_right_idx) {
            //相等就是自己
            if (in_left_idx > in_right_idx) {
                return null;
            }
            //root_idx是在先序里面的
            TreeNode root = new TreeNode(preorder[pre_root_idx]);
            // 有了先序的,再根据先序的，在中序中获 当前根的索引
            int idx = map.get(preorder[pre_root_idx]);

            //左子树的根节点就是 左子树的(前序遍历）第一个，就是+1,左边边界就是left，右边边界是中间区分的idx-1
            root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);

            //由根节点在中序遍历的idx 区分成2段,idx 就是根

            //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
            // pre_root_idx 当前的根  左子树的长度 = 左子树的左边-右边 (idx-1 - in_left_idx +1) 。最后+1就是右子树的根了
            root.right = recursive(pre_root_idx + (idx - 1 - in_left_idx + 1) + 1, idx + 1, in_right_idx);
            return root;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
