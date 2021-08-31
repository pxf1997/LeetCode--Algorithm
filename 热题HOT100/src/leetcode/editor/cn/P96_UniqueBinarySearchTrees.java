/**
 * 题目Id：96
 * 题目：不同的二叉搜索树
 * 日期：2021-08-25 12:04:17
 */
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1292 👎 0


package leetcode.editor.cn;

//不同的二叉搜索树

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P96_UniqueBinarySearchTrees().new Solution();
//        int res = solution.numTrees(3);
        int res = solution.numTrees(3);
        System.out.println("res = " + res);
    }


    // 递归法 + 记忆化
    class Solution_递归 {
        // 能否记忆,当然可以,时间骤减!!!
        Map<Integer, Integer> memo = new HashMap<>();

        public int numTrees(int n) {
            // n个节点为 [1,n]
            // 以 [1,n] 为树根
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (memo.containsKey(n)) return memo.get(n);
            int count = 0;
            for (int i = 1; i <= n; i++) {
                // i为根--
                // i左子树组成方案 numTrees(i-1)
                // i右子树组成方案 numTrees(n-i)
                count += numTrees(i - 1) * numTrees(n - i);
            }
            memo.put(n, count);
            return count;

        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // dp法 分析--
    // 1-- G(n) = F(1,n)+ F(2,n)+...+F(n,n)  F(i,n)是以i为根的BST
    // 2-- F(i,n) = G(i-1) * G(n-i)  左子树i-1 右子树n-i
    class Solution {
        public int numTrees(int n) {
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 1;
            for (int i = 2; i <= n; i++) {
                // G[i]--以i为根
                for (int j = 1; j <= i; j++) { // 讨论--为何不写 j=0
                    G[i] += G[j - 1] * G[i - j];
                }
            }
            System.out.println("G = " + Arrays.toString(G));
            return G[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
