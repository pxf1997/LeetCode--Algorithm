/**
 * 题目Id：103
 * 题目：二叉树的锯齿形层序遍历
 * 日期：2021-04-29 15:49:25
 */
//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 栈 树 广度优先搜索 
// 👍 438 👎 0


package leetcode.editor.cn;

//二叉树的锯齿形层序遍历

import org.junit.Test;
import util.TreeNode;

import java.util.*;

public class P103_BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;

        List<List<Integer>> zigzagLevelOrder = solution.zigzagLevelOrder(root);
        System.out.println("zigzagLevelOrder = " + zigzagLevelOrder);
    }

    @Test
    public void testLinkedList() {
        LinkedList<String> sites = new LinkedList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (int size = sites.size(), i = 0; i < size; i++) {
            System.out.println(sites.get(size - 1 - i));
        }
    }
    @Test
    public void testLinkedList2() {
        LinkedList<String> sites = new LinkedList<String>();
//        sites.addLast("Google");
//        sites.addLast("Runoob");
//        sites.addLast("Taobao");
//        // 使用 addFirst() 在头部添加元素
//        sites.addFirst("Wiki");

        sites.offerLast("Google");
        sites.offerLast("Runoob");
        sites.offerLast("Taobao");
        // 使用 addFirst() 在头部添加元素
        sites.offerFirst("Wiki");
        System.out.println(sites);
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
    class Solution1 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            int level = 0;
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> thisLevel = new ArrayList<>();
                level++; //按level分奇偶--全乱套了
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();

                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                    thisLevel.add(cur.val);
                }
                if (level % 2 == 0) {
                    Collections.reverse(thisLevel);
                }
//                System.out.println(level + " : " + thisLevel);
                res.add(thisLevel);
            }


            return res;
        }
    }
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<List<Integer>>();
            if (root == null) {
                return ans;
            }

            Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
            nodeQueue.offer(root);
            boolean isOrderLeft = true;

            while (!nodeQueue.isEmpty()) {
                Deque<Integer> levelList = new LinkedList<Integer>();
                int size = nodeQueue.size();
                for (int i = 0; i < size; ++i) {
                    TreeNode curNode = nodeQueue.poll();
                    if (isOrderLeft) {
                        levelList.offerLast(curNode.val);
                    } else {
                        levelList.offerFirst(curNode.val);
                    }
                    if (curNode.left != null) {
                        nodeQueue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        nodeQueue.offer(curNode.right);
                    }
                }
                ans.add(new LinkedList<Integer>(levelList));
                isOrderLeft = !isOrderLeft;
                System.out.println("levelList = " + levelList);
            }

            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


}
