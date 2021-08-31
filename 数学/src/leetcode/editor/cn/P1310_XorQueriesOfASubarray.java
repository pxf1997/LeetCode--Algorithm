/**
 * 题目Id：1310
 * 题目：子数组异或查询
 * 日期：2021-05-12 11:06:27
 */
//有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。 
//
// 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为
//本次查询的结果。 
//
// 并返回一个包含给定查询 queries 所有结果的数组。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//输出：[2,7,14,8] 
//解释：
//数组中元素的二进制表示形式是：
//1 = 0001 
//3 = 0011 
//4 = 0100 
//8 = 1000 
//查询的 XOR 值为：
//[0,1] = 1 xor 3 = 2 
//[1,2] = 3 xor 4 = 7 
//[0,3] = 1 xor 3 xor 4 xor 8 = 14 
//[3,3] = 8
// 
//
// 示例 2： 
//
// 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//输出：[8,0,4,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 3 * 10^4 
// 1 <= arr[i] <= 10^9 
// 1 <= queries.length <= 3 * 10^4 
// queries[i].length == 2 
// 0 <= queries[i][0] <= queries[i][1] < arr.length 
// 
// Related Topics 位运算 
// 👍 81 👎 0


package leetcode.editor.cn;

//子数组异或查询

import java.util.Arrays;

public class P1310_XorQueriesOfASubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1310_XorQueriesOfASubarray().new Solution();
//        int[] xorQueries = solution.xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}});
        int[] xorQueries = solution.xorQueries(new int[]{16}, new int[][]{{0, 0}, {0, 0}, {0, 0}});
        System.out.println("xorQueries = " + Arrays.toString(xorQueries));

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //朴素
    class Solution1 {
        public int[] xorQueries(int[] arr, int[][] queries) {
            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int startIndex = queries[i][0];
                int endIndex = queries[i][1];
                int cur = xor_helper(arr, startIndex, endIndex);
                res[i] = cur;
            }
            return res;
        }

        private int xor_helper(int[] arr, int startIndex, int endIndex) {
            int ans = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                ans ^= arr[i];
            }
            return ans;
        }
    }

    //异或前缀
    class Solution {
        // xors[i] 为从 arr[0] 到 arr[i−1] 的元素的异或运算结果
        // 对于查询 Q(left,right)
        // left=0 时，Q(left,right) = xors[right+1]               ---公式①
        // left>0 时，Q(left,right) = xors[right+1] ^ xors[left]  ---公式②
        // 且left=0 时，xors[left]=0  均满足公式②
        public int[] xorQueries(int[] arr, int[][] queries) {
            //异或前缀
            int len = arr.length;
            int[] xors = new int[len + 1];
            for (int i = 0; i < len; i++) {
                xors[i + 1] = arr[i] ^ xors[i];
            }
            int len2 = queries.length;
            int[] res = new int[len2];
            for (int i = 0; i < len2; i++) {
                int left = queries[i][0], right = queries[i][1];
                res[i] = xors[left] ^ xors[right + 1];
            }
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
