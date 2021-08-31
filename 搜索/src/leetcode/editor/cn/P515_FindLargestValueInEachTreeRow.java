/**
 * 题目Id：515
 * 题目：在每个树行中找最大值
 * 日期：2021-04-29 15:36:55
 */
//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 128 👎 0


package leetcode.editor.cn;

//在每个树行中找最大值

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P515_FindLargestValueInEachTreeRow {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P515_FindLargestValueInEachTreeRow().new Solution();
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
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            int level = 0;
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                int max = Integer.MIN_VALUE;
                while (size-- > 0) {
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                    if(cur.val>max){
                        max = cur.val;
                    }
                }
                res.add(max);

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
