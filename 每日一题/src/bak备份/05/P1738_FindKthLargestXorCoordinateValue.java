/**
 * 题目Id：1738
 * 题目：找出第 K 大的异或坐标值
 * 日期：2021-05-19 11:09:56
 */
//给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。 
//
// 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
//标从 0 开始计数）执行异或运算得到。 
//
// 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[5,2],[1,6]], k = 1
//输出：7
//解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 
//
// 示例 2： 
//
// 输入：matrix = [[5,2],[1,6]], k = 2
//输出：5
//解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。 
//
// 示例 3： 
//
// 输入：matrix = [[5,2],[1,6]], k = 3
//输出：4
//解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。 
//
// 示例 4： 
//
// 输入：matrix = [[5,2],[1,6]], k = 4
//输出：0
//解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 1000 
// 0 <= matrix[i][j] <= 106 
// 1 <= k <= m * n 
// 
// Related Topics 数组 
// 👍 39 👎 0


//找出第 K 大的异或坐标值

import util.dp_util;

import java.util.*;

public class P1738_FindKthLargestXorCoordinateValue {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1738_FindKthLargestXorCoordinateValue().new Solution();
//        int kthLargestValue = solution.kthLargestValue(new int[][]{
//                {5, 2},
//                {1, 6}
//        }, 1);
        int kthLargestValue = solution.kthLargestValue(new int[][]{
                {5, 2, 3},
                {1, 6, 2},
                {7, 4, 9},
        }, 3);
        System.out.println("kthLargestValue = " + kthLargestValue);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 异或前缀----二维
    class Solution1 {
        public int kthLargestValue(int[][] matrix, int k) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] pre = new int[m + 1][n + 1];
            // 写成固定数组--长度已知
            int[] resList = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    pre[i + 1][j + 1] = matrix[i][j] ^ pre[i][j + 1] ^ pre[i + 1][j] ^ pre[i][j];
                    resList[i * n + j] = pre[i + 1][j + 1]; // 矩阵下标 --> 数组下标
                }
            }
            dp_util.print_2D(matrix);
            System.out.println();
            dp_util.print_2D(pre);

//            for (int i = 1; i < m + 1; i++) {
//                for (int j = 1; j < n + 1; j++) {
//                    resList[(i - 1) * n + (j - 1)] = pre[i][j]; // (i,j) --> i*n + j
//                }
//            }


            System.out.println("resList = " + Arrays.toString(resList));
            Arrays.sort(resList);
            System.out.println("resList = " + Arrays.toString(resList));

            return resList[m * n - k];
        }
    }

    class Solution {
        public int kthLargestValue(int[][] matrix, int k) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] pre = new int[m + 1][n + 1];
            List<Integer> resList = new ArrayList<Integer>();
            //  遍历matrix
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    pre[i + 1][j + 1] = matrix[i][j] ^ pre[i][j + 1] ^ pre[i + 1][j] ^ pre[i][j];
                    resList.add(pre[i + 1][j + 1]);
                }
            }
            dp_util.print_2D(matrix);
            System.out.println();
            dp_util.print_2D(pre);

            Collections.sort(resList, new Comparator<Integer>() { // Comparator的泛型--你比较的是啥,对应list中存的类型
                @Override
                public int compare(Integer num1, Integer num2) {
                    return num2 - num1; //降序
                }
            });
            System.out.println("resList = " + resList);

            return resList.get(k - 1);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
