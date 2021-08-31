package util.树;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author pxf
 * @create 2021-05-28 10:47
 */
public class 树相关 {
    public static void main(String[] args) {
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

        // 1--深度(高度)
        int depth = TreeDepth(root);
        System.out.println("depth = " + depth);
        System.out.println("-----分割线-----");

        // 2--中序遍历
        inOrder(root);
        System.out.println("-----分割线-----");

        // 3--层序遍历
        List<List<Integer>> levelOrder = levelOrder(root);
        System.out.println("levelOrder = " + levelOrder);
        System.out.println("-----分割线-----");

        // 4--先序遍历
        preOrder(root);
        System.out.println("-----分割线-----");

        // 5--后续遍历
        postOrder(root);
    }
    // 中序遍历
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        // 处理逻辑
        System.out.println("node.val = " + node.val);

        inOrder(node.right);
    }

    // 树的高度
    public static int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    // 前序遍历
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 处理逻辑
        System.out.println("node.val = " + node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 后续遍历
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        // 处理逻辑
        System.out.println("node.val = " + node.val);

    }


    // 判断叶子节点
    public static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // 层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            //每次处理一层
            int size = queue.size();
            level++;
            List<Integer> thisLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                thisLevel.add(cur.val);
                // 处理左右子
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            // helper
            System.out.println(level + " : " + thisLevel);

            res.add(thisLevel);
        }
        return res;
    }
}
