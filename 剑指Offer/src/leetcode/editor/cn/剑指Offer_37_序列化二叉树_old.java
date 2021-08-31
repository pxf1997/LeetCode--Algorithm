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

import java.util.*;

public class 剑指Offer_37_序列化二叉树_old {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l2_1 = new TreeNode(2);
        TreeNode l2_2 = new TreeNode(3);
//        TreeNode l3_1 = new TreeNode(4);
//        TreeNode l3_2 = new TreeNode(5);
        TreeNode l3_3 = new TreeNode(6);
        TreeNode l3_4 = new TreeNode(7);

        root.left = l2_1;
        root.right = l2_2;
//        l2_1.left = l3_1;
//        l2_1.right = l3_2;
        l2_2.left = l3_3;
        l2_2.right = l3_4;

        //测试代码
        Codec solution = new 剑指Offer_37_序列化二叉树_old().new Codec();
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


    // 参考--层序遍历
    public class Codec2 {
        // bfs队列--注意拼串
        public String serialize(TreeNode root) {
            if (root == null) return "[]";
            StringBuilder sb = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur == null) { // null的子节点不再入队
                    sb.append("null").append(",");
                } else { // 其余节点的null子节点也入队
                    sb.append(cur.val).append(",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1).append(']');
            return sb.toString();
        }

        // 维护一个指针确定当前位置
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) return null;

            String[] nodes = data.substring(1, data.length() - 1).split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            int index = 0;
            TreeNode root = new TreeNode(Integer.parseInt(nodes[index]));
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                // 空节点咱们不用造
                index++;
                if (!nodes[index].equals("null")) {
                    cur.left = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.add(cur.left);
                }
                index++;
                if (!nodes[index].equals("null")) {
                    cur.right = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.add(cur.right);
                }

            }

            return root;
        }
    }

    // my--中序遍历(每层写满) 理论是ok的
    // 大规模用例(2000层)不能通过!
    public class Codec {
        // 序列化----如果有空节点记为# 保证整棵树的完整性 例如三层的树共7个节点
        // 最后一层的空子节点--不作为#加入结果
        String serialize(TreeNode root) {
            if (root == null) return "[]";
            int depth = TreeDepth(root);
            List<String> nodes = new ArrayList<>();

            // 写满每层,默认填入null
            for (int i = 0; i < (int) (Math.pow(2, depth) - 1); i++) {
                nodes.add("null");
            }

            Serialize_helper(root, nodes, 0, nodes.size() - 1);

            // 处理结果
            StringBuilder sb = new StringBuilder("[");
            for (String node : nodes) {
                sb.append(node).append(",");
            }
            sb.deleteCharAt(sb.length() - 1).append("]");

            return sb.toString();
        }

        // 序列化时就规划好空间大小
        private void Serialize_helper(TreeNode node, List<String> nodes, int startIndex, int endIndex) {
            if (startIndex == endIndex) {
                nodes.set(startIndex, Integer.toString(node.val));
                return;
            }
            int root_idx = (endIndex + startIndex) / 2;
            nodes.set(root_idx, Integer.toString(node.val));
            // 递归深入的时候已经保证了节点不为null
            if (node.left != null) {
                Serialize_helper(node.left, nodes, startIndex, root_idx - 1);
            }
            if (node.right != null) {
                Serialize_helper(node.right, nodes, root_idx + 1, endIndex);
            }
        }

        public int TreeDepth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
        }

        // 反序列化--根据完整的树序列化字符串进行重建
        TreeNode deserialize(String str) {
            if (str.equals("[]")) return null;
            String[] nodes = str.substring(1, str.length() - 1).split(",");
            return buildTree(nodes);
        }

        // 建树--这部分没问题
        private TreeNode buildTree(String[] nodes) {
            int len = nodes.length;
            if (len == 1) {
                if (nodes[0].equals("null")) {
                    return null;
                } else {
                    return new TreeNode(Integer.parseInt(nodes[0]));
                }
            }
            // 写的冗余但清楚明白
            int root_idx = len / 2;
            // 如果左子树全部为空(有多层进行了补全,此时 nodes 为多个 null)
            if (nodes[root_idx].equals("null")) return null;
            int root_val = Integer.parseInt(nodes[root_idx]);
            TreeNode root = new TreeNode(root_val);

            String[] left = Arrays.copyOfRange(nodes, 0, root_idx);
            String[] right = Arrays.copyOfRange(nodes, root_idx + 1, len);
            root.left = buildTree(left);
            root.right = buildTree(right);
            return root;
        }
    }


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
