/**
 * 题目Id：297
 * 题目：二叉树的序列化与反序列化
 * 日期：2021-06-24 17:58:01
 */
//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 583 👎 0


package leetcode.editor.cn;

//二叉树的序列化与反序列化

import util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class P297_SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Codec solution = new P297_SerializeAndDeserializeBinaryTree().new Codec();
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
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // bfs层序遍历,注意叶子节点的空子节点也写进去
            if (root == null) return "[]";
            Deque<TreeNode> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder("[");
            queue.offer(root);
            while (!queue.isEmpty()) {
                // 入队时不管空不空,出队再处理
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append("null").append(",");
                } else {
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


            Deque<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            queue.offer(root);
            int idx = 1;
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                // 不为 null 就造,为 null 就不造
                // 执行两次,处理 cur 的左右子
                // 不难得出结论--idx恰好用完!
                if (!nodes[idx].equals("null")) {
                    cur.left = new TreeNode(Integer.parseInt(nodes[idx]));
                    queue.offer(cur.left);
                } else {
                    cur.left = null;
                }
                idx++;

                if (!nodes[idx].equals("null")) {
                    cur.right = new TreeNode(Integer.parseInt(nodes[idx]));
                    queue.offer(cur.right);
                } else {
                    cur.right = null;
                }
                idx++;
            }

            // helper
//            System.out.println("nodes = " + Arrays.toString(nodes));
//            System.out.println("nodes.length = " + nodes.length + "  idx = " + idx);
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
