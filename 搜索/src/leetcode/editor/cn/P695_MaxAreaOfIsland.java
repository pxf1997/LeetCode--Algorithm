/**
 * 题目Id：695
 * 题目：岛屿的最大面积
 * 日期：2021-05-06 14:08:18
 */
//给定一个包含了一些 0 和 1 的非空二维数组 grid 。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。) 
//
// 
//
// 示例 1: 
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// [[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 数组 
// 👍 485 👎 0


package leetcode.editor.cn;

//岛屿的最大面积

import util.dp_util;

public class P695_MaxAreaOfIsland {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P695_MaxAreaOfIsland().new Solution();
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        int res = solution.maxAreaOfIsland(grid);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //顺序--上下左右
        int m, n; // m行n列 m*n

        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int curArea = dfs(grid, i, j);
//                        //调试helper
                        System.out.println(i + "," + j + "  Area = " + curArea);
//                        dp_util.print_2D(grid);
                        System.out.println();

                        max = Math.max(curArea, max);
                    }
                }
            }
            return max;

        }

        private int dfs(int[][] grid, int r, int c) {
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return 0;
            }
            if (grid[r][c] != 1) {
                return 0;
            }
            grid[r][c] = 2; //标记访问过

            // helper
            System.out.println(r + "," + c);
            dp_util.print_2D(grid);
            System.out.println();

            int area = 1;
            for (int[] direction : directions) {
                area += dfs(grid, r + direction[0], c + direction[1]);
            }
            return area;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
