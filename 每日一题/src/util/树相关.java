package util;

/**
 * @author pxf
 * @create 2021-05-28 10:47
 */
public class 树相关 {
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        // 处理逻辑
        System.out.println("node.val = " + node.val);
        inOrder(node.right);
    }

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 处理逻辑
        System.out.println("node.val = " + node.val);
        inOrder(node.left);
        inOrder(node.right);
    }

    // 判断叶子节点
    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
