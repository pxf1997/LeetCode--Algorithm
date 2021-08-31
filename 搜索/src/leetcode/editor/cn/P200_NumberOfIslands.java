/**
 * 题目Id：200
 * 题目：岛屿数量
 * 日期：2021-05-06 11:45:50
 */
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 1140 👎 0


package leetcode.editor.cn;

//岛屿数量

import util.dp_util;

public class P200_NumberOfIslands {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P200_NumberOfIslands().new Solution();
        char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
                {'1','1','0','1','0'},
        };
        int res = solution.numIslands(grid);
        System.out.println("res = " + res);


    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            m = grid.length;
            n = grid[0].length;
            int count = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        //调试
                        System.out.println(i + "," + j);
//                        dp_util.print_2D(grid);

                        count++;

                        System.out.println("count = " + count);
                        System.out.println();
                    }
                }
            }
            return count;


        }

        private void dfs(char[][] grid, int r, int c) {
            //越界
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return;
            }
            //不是岛屿
            if (grid[r][c] != '1') {
                return;
            }
            grid[r][c] = '2';
            //调试
            System.out.println(r + "," + c);
            dp_util.print_2D(grid);
            System.out.println();

            for (int[] direction : directions) {
                dfs(grid, r + direction[0], c + direction[1]);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
