/**
 * 题目Id：剑指 Offer 37
 * 题目：序列化二叉树
 * 日期：2021-06-07 21:10:16
 */
//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 设计 
// 👍 161 👎 0


package leetcode.editor.cn;

//序列化二叉树

import util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class 剑指Offer_37_序列化二叉树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(3);
        TreeNode l3_1 = new TreeNode(4);
        TreeNode l3_2 = new TreeNode(5);
        TreeNode l3_3 = new TreeNode(6);
        TreeNode l3_4 = new TreeNode(7);

        root.left = l2_1;
        root.right = l2_2;
        l2_1.left = l3_1;
        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

        //测试代码
        Codec solution = new 剑指Offer_37_序列化二叉树().new Codec();
        String serialize = solution.serialize(root);
        System.out.println("serialize = " + serialize);
        TreeNode newRoot = solution.deserialize(serialize);
        System.out.println("newRoot = " + newRoot);


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

    // 用层序遍历做--逻辑不难
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "[]";
            StringBuilder sb = new StringBuilder("[");
            Deque<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append("null").append(",");
                } else {
                    // 叶子节点的左右子null也入队
                    sb.append(cur.val).append(",");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1).append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) return null;
            String[] nodes = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            Deque<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            int idx = 1;
            // 考虑一个问题--按自己规定的序列化方式,
            // 反序列化时,idx可以"刚好用完"
            while (!queue.isEmpty()) {
                // 1--非空节点出队
                TreeNode cur = queue.poll();
                // 2--处理其左子
                if (nodes[idx].equals("null")) {
                    cur.left = null;
                } else {
                    cur.left = new TreeNode(Integer.parseInt(nodes[idx]));
                    queue.offer(cur.left);
                }
                idx++;
                // 2--处理其右子
                if (nodes[idx].equals("null")) {
                    cur.right = null;
                } else {
                    cur.right = new TreeNode(Integer.parseInt(nodes[idx]));
                    queue.offer(cur.right);
                }
                idx++;
            }
            return root;
        }
    }


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
