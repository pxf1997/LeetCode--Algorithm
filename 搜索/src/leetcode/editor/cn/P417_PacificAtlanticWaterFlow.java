/**
 * 题目Id：417
 * 题目：太平洋大西洋水流问题
 * 日期：2021-05-06 21:12:37
 */
//给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。 
//
// 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。 
//
// 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。 
//
// 
//
// 提示： 
//
// 
// 输出坐标的顺序不重要 
// m 和 n 都小于150 
// 
//
// 
//
// 示例： 
//
// 
//
// 
//给定下面的 5x5 矩阵:
//
//  太平洋 ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * 大西洋
//
//返回:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
// 
//
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 239 👎 0


package leetcode.editor.cn;

//太平洋大西洋水流问题

import org.junit.Test;
import util.dp_util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P417_PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P417_PacificAtlanticWaterFlow().new Solution();
        int[][] height = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 4, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4},
        };
        List<List<Integer>> res = solution.pacificAtlantic(height);
        System.out.println("res = " + res);
    }

    @Test
    public void asList() {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1, 2));
        res.add(Arrays.asList(3, 4));
        System.out.println("res = " + res);

        List<Integer> a = Arrays.asList(1, 2, 5, 6, 7);
        System.out.println("a = " + a);

        List<String> b = Arrays.asList("Google", "Baidu", "Alibaba");
        System.out.println("b = " + b);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        思路:从低点走向高点。例如：1 -> 2 ，或者平着 2 -> 2。
//          首先从太平洋出发，看看能逆向走到哪些点。
//          然后再从大西洋出发，看看能逆向走到哪些点。
//          在大西洋的旅途当中，如果看到了已经由太平洋出发留下的标记，说明大西洋来过了，因此就把该点放到返回值中。

        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;
        int[][] heights;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new ArrayList<>();
            if (heights == null || heights.length == 0) {
                return res;
            }
            m = heights.length;
            n = heights[0].length;
            this.heights = heights;

            boolean[][] canReachP = new boolean[m][n];
            boolean[][] canReachA = new boolean[m][n];

            //从边界开始DFS 类似P130
            for (int i = 0; i < m; i++) { //行遍历--0列 & n-1列
                dfs(i, 0, canReachP);
                dfs(i, n - 1, canReachA);
            }
            for (int i = 0; i < n; i++) { //列遍历--0行 & m-1行
                dfs(0, i, canReachP);
                dfs(m - 1, i, canReachA);
            }

            //helper
//            dp_util.print_2D(canReachP);
//            System.out.println();
//            dp_util.print_2D(canReachA);
//            System.out.println();


            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (canReachP[i][j] && canReachA[i][j]) {
                        res.add(Arrays.asList(i, j));
                    }
                }
            }
            return res;


        }

        //dfs模板 + 本题逻辑：水往低处流
        private void dfs(int r, int c, boolean[][] canReach) {
            if (canReach[r][c]) { // visited
                return;
            }
            canReach[r][c] = true;
            for (int[] direction : directions) {
                int next_r = r + direction[0];
                int next_c = c + direction[1];
                if (next_r < 0 || next_r >= m || next_c < 0 || next_c >= n) { //排除--边界不合法
                    continue;
                }
                if (heights[next_r][next_c] < heights[r][c]) {//排除--next高度大于此处
                    continue;
                }
                dfs(next_r, next_c, canReach);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}

