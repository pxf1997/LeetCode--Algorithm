/**
 * 题目Id：987
 * 题目：二叉树的垂序遍历
 * 日期：2021-07-31 23:28:48
 */
//给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。 
//
// 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的
//根结点位于 (0, 0) 。 
//
// 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则
//按结点的值从小到大进行排序。 
//
// 返回二叉树的 垂序遍历 序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[9],[3,15],[20],[7]]
//解释：
//列 -1 ：只有结点 9 在此列中。
//列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
//列  1 ：只有结点 20 在此列中。
//列  2 ：只有结点 7 在此列中。 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//列 -2 ：只有结点 4 在此列中。
//列 -1 ：只有结点 2 在此列中。
//列  0 ：结点 1 、5 和 6 都在此列中。
//          1 在上面，所以它出现在前面。
//          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
//列  1 ：只有结点 3 在此列中。
//列  2 ：只有结点 7 在此列中。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2,3,4,6,5,7]
//输出：[[4],[2],[1,5,6],[3],[7]]
//解释：
//这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
//因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。 
//
// 
//
// 提示： 
//
// 
// 树中结点数目总数在范围 [1, 1000] 内 
// 0 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 哈希表 二叉树 
// 👍 151 👎 0


package daily_2021_07;

//二叉树的垂序遍历

import util.TreeNode;

import java.util.*;

public class P987_VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P987_VerticalOrderTraversalOfABinaryTree().new Solution();
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
        List<List<Integer>> res = solution.verticalTraversal(root);
        System.out.println("res = " + res);
    }


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

    // my解法--稍微有点小问题
    class Solution_my {
        // key--节点 value--{row,col}
//        Map<TreeNode, int[]> map = new HashMap<>();

        // key--列号 value--该列对应的节点值
        Map<Integer, List<Integer>> map2 = new HashMap<>();

        public List<List<Integer>> verticalTraversal(TreeNode root) {
            inOrder(root, 0, 0);
//            System.out.println("map = " + map);
            System.out.println("map2 = " + map2);
            return null;
        }

        private void inOrder(TreeNode node, int row, int col) {
            if (node == null) return;
            inOrder(node.left, row + 1, col - 1);

//            map.put(node, new int[]{row, col});
            List<Integer> value = map2.getOrDefault(col, new ArrayList<>());
            value.add(node.val);
            map2.put(col, value);

            inOrder(node.right, row + 1, col + 1);
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考题解--分析:dfs+定制排序
    class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            // {行,列,节点值}
            List<int[]> nodes = new ArrayList<>();
            inOrder(root, 0, 0, nodes);
            // 定制排序
            Collections.sort(nodes, new Comparator<int[]>() {
                // {行,列,节点值}
                public int compare(int[] o1, int[] o2) {
                    // 列号不同--按列号升序排序
                    if (o1[1] != o2[1]) {
                        return o1[1] - o2[1];
                    }
                    // 列号相同,但行号不同--按行号升序
                    else if (o1[0] != o2[0]) {
                        return o1[0] - o2[0];
                    }
                    // 行号列号相同--按节点值升序
                    else {
                        return o1[2] - o2[2];
                    }
                }
            });
            List<List<Integer>> res = new ArrayList<>();
            // helper
            for (int[] node : nodes) {
                System.out.println("node = " + Arrays.toString(node));
            }
            // 组装返回结果res--按题目要求哦
            int size = 0;
            // 小技巧--上一个记录节点的列号(判断是否需要新建)
            int last_col = Integer.MIN_VALUE;
            for (int[] node : nodes) {
                int col = node[1], row = node[0], value = node[2];
                if (col != last_col) { // 列号变了,咱就新建一个ArrayList
                    last_col = col;
                    res.add(new ArrayList<>());
                    size++;
                }
                res.get(size-1).add(value);
            }

            return res;
        }

        private void inOrder(TreeNode node, int row, int col, List<int[]> nodes) {
            if (node == null) return;
            inOrder(node.left, row + 1, col - 1, nodes);
            nodes.add(new int[]{row, col, node.val});
            inOrder(node.right, row + 1, col + 1, nodes);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
