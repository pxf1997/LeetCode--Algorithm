/**
 * 题目Id：337
 * 题目：打家劫舍 III
 * 日期：2021-04-13 16:27:49
 */
//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 动态规划 
// 👍 804 👎 0


package 打家劫舍;

//打家劫舍 III

import java.util.HashMap;
import java.util.Map;

public class P337_HouseRobberIii {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P337_HouseRobberIii().new Solution();
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
//    DP
    class Solution1 {
        Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
        Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

        public int rob(TreeNode root) {
            dfs(root);
            return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
        }

        private void dfs(TreeNode node) {
            if (node == null) return;

            //后序遍历
            dfs(node.left);
            dfs(node.right);
            //核心逻辑--有父子关系的两节点不能同时选中
            //fx--选中x节点，不能选中x的孩子  fx=gl+gr
            f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
            //gx--不选中x节点，有可能选中x的孩子  gx=max(fl,gl) + max(fr,gr)
            int maxLeft = Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0));
            int maxRight = Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0));
            g.put(node, maxLeft + maxRight);
        }


    }

//    递归
    class Solution {
        public int rob(TreeNode root) {
            if (root == null) return 0;
//            val1 打劫此节点--不能打劫左右子，直接去找孙子层
            int val1 = root.val;
            //核心逻辑--有父子关系的两节点不能同时选中
            if (root.left != null) val1 += rob(root.left.left) + rob(root.left.right);
            if (root.right != null) val1 += rob(root.right.left) + rob(root.right.right);
//            val2 不打劫此节点--打劫左右子
            int val2 = rob(root.left) + rob(root.right);
            return Math.max(val1, val2);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
