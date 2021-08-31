/**
 * 题目Id：501
 * 题目：二叉搜索树中的众数
 * 日期：2021-08-26 11:36:48
 */
//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
//
// 假定 BST 有如下定义： 
//
// 
// 结点左子树中所含结点的值小于等于当前结点的值 
// 结点右子树中所含结点的值大于等于当前结点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 例如： 
//给定 BST [1,null,2,2], 
//
//    1
//    \
//     2
//    /
//   2
// 
//
// 返回[2]. 
//
// 提示：如果众数超过1个，不需考虑输出顺序 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 340 👎 0


package leetcode.editor.cn;

//二叉搜索树中的众数

import util.TreeNode;

import java.util.*;

public class P501_FindModeInBinarySearchTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P501_FindModeInBinarySearchTree().new Solution();
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
        Map<Integer, Integer> map = new HashMap<>();

        public int[] findMode(TreeNode root) {
            inOrder(root);
            int max_count = 0;
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= max_count) max_count = entry.getValue();
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max_count) list.add(entry.getKey());
            }
//            System.out.println("map = " + map);
//            System.out.println("max_count = " + max_count);
//            System.out.println("list = " + list);
            return list.stream().mapToInt(Integer::valueOf).toArray();

        }

        private void inOrder(TreeNode root) {
            if (root == null) return;
            inOrder(root.left);
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            inOrder(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
