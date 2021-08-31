/**
 * 题目Id：199
 * 题目：二叉树的右视图
 * 日期：2021-04-29 14:53:18
 */
//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
//
// 示例: 
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// 
// Related Topics 树 深度优先搜索 广度优先搜索 递归 队列 
// 👍 453 👎 0


package leetcode.editor.cn;

//二叉树的右视图

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P199_BinaryTreeRightSideView {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P199_BinaryTreeRightSideView().new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        root.left = t1;
        root.right = t2;
        t1.right = t3;
        t2.right = t4;
        List<Integer> view = solution.rightSideView(root);
        System.out.println(view);
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
        //        分析--BFS层序遍历 + 记录每层最后出队的节点
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                for (int i = 0; i < size; i++) {
                    //层序BFS的模板
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                    //每层最后一个
                    if (i == size - 1) {
                        //调试
//                        System.out.println(level + "层最右侧为" + cur.val);
                        res.add(cur.val);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
