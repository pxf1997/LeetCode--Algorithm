/**
 * 题目Id：剑指 Offer 32 - I
 * 题目：从上到下打印二叉树
 * 日期：2021-06-10 15:18:53
 */
//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回： 
//
// [3,9,20,15,7]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 
// 👍 99 👎 0


package leetcode.editor.cn;

//从上到下打印二叉树

import util.TreeNode;

import java.util.*;

public class 剑指Offer_32_I_从上到下打印二叉树 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_32_I_从上到下打印二叉树().new Solution();
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
        int[] ints = solution.levelOrder(root);
        System.out.println("ints = " + Arrays.toString(ints));
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

    // 层序--bfs队列
    class Solution {
        // 每层都写出来
        public int[] levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>(); // 每层分开存储
            List<Integer> res2 = new ArrayList<>(); // 每层不分开存储
            if (root == null) return new int[0];
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int level = 0;
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                List<Integer> thisLevel = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    thisLevel.add(cur.val);
                    res2.add(cur.val);
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
                res.add(thisLevel);
                System.out.println("level = " + level + "  thisLevel = " + thisLevel);
            }
            System.out.println("res = " + res);
            return res2.stream().mapToInt(Integer::valueOf).toArray();
        }

        // 简化
        public int[] levelOrder2(TreeNode root) {
            List<Integer> list = new ArrayList<>(); // 每层不分开存储
            if (root == null) return new int[0];
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // 层数也不统计了
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            // 高端写法咱也不要了
//            return list.stream().mapToInt(Integer::valueOf).toArray();
            // 再遍历一遍
            int[] res = new int[list.size()];
            int idx = 0;
            for (Integer x : list) {
                res[idx++] = x;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
