/**
 * 题目Id：1337
 * 题目：矩阵中战斗力最弱的 K 行
 * 日期：2021-08-01 22:44:05
 */
//给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。 
//
// 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。 
//
// 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。 
//
// 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。 
//
// 
//
// 示例 1： 
//
// 
//输入：mat = 
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]], 
//k = 3
//输出：[2,0,3]
//解释：
//每行中的军人数目：
//行 0 -> 2 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 2 
//行 4 -> 5 
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
// 
//
// 示例 2： 
//
// 
//输入：mat = 
//[[1,0,0,0],
// [1,1,1,1],
// [1,0,0,0],
// [1,0,0,0]], 
//k = 2
//输出：[0,2]
//解释： 
//每行中的军人数目：
//行 0 -> 1 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 1 
//从最弱到最强对这些行排序后得到 [0,2,3,1]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 2 <= n, m <= 100 
// 1 <= k <= m 
// matrix[i][j] 不是 0 就是 1 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 
// 👍 100 👎 0


package daily_2021_08;

//矩阵中战斗力最弱的 K 行

import java.util.*;

public class P1337_TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1337_TheKWeakestRowsInAMatrix().new Solution();
        int[][] mat = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1},
        };
        int k = 3;
        int[] res = solution.kWeakestRows(mat, k);
        System.out.println("res = " + Arrays.toString(res));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m, n;

        public int[] kWeakestRows(int[][] mat, int k) {
            m = mat.length;
            n = mat[0].length;
            // 定制排序 int[2]{行号, 战斗力}
            List<int[]> rows = new ArrayList<>();
            helper(rows, mat);

            Collections.sort(rows, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    // 战斗力不一样,按战斗力升序排
                    if (o1[1] != o2[1]) {
                        return o1[1] - o2[1];
                    }
                    // 战斗力一样,按行号升序排
                    else {
                        return o1[0] - o2[0];
                    }
                }
            });
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = rows.get(i)[0];
            }
            return res;
        }

        private void helper(List<int[]> rows, int[][] mat) {
            for (int i = 0; i < m; i++) {
                int score = 0;
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1) score++;
                }
                rows.add(new int[]{i, score});
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
