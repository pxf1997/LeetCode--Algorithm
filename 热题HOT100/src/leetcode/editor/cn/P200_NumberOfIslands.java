/**
 * 题目Id：200
 * 题目：岛屿数量
 * 日期：2021-08-26 10:47:25
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
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1297 👎 0


package leetcode.editor.cn;

//岛屿数量

import util.dp_util;

public class P200_NumberOfIslands {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P200_NumberOfIslands().new Solution();
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
                {'1', '1', '0', '1', '0'},
        };
        int res = solution.numIslands(grid);
        System.out.println("res = " + res);
    }


    // 分析--dfs
    class Solution_visited {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;
        int count;

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 找到起点做dfs--发现一座岛屿
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        dfs(grid, i, j, visited);
                        count++;

                        //System.out.println("dfs开始的岛屿起点 : "+ i + "," + j);
                        //dp_util.print_2D(grid);
                        //System.out.println();
                    }

                }
            }
            return count;
        }

        private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
            if (!inArea(row, col)) return;
            if (visited[row][col]) return;
            if (grid[row][col] != '1') return;
            visited[row][col] = true;
            for (int[] direction : directions) {
                dfs(grid, row + direction[0], col + direction[1], visited);
            }
        }

        boolean inArea(int row, int col) {
            return row >= 0 && row < m && col >= 0 && col < n;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 改良--不用visited,访问过的土地值改为2
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;
        int count;

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 找到起点做dfs--发现一座岛屿
                    if (grid[i][j] == '1' ) {
                        dfs(grid, i, j);
                        count++;

//                        System.out.println("dfs开始的岛屿起点 : "+ i + "," + j);
//                        dp_util.print_2D(grid);
//                        System.out.println();
                    }

                }
            }
            return count;
        }

        private void dfs(char[][] grid, int row, int col) {
            if (!inArea(row, col)) return;
            if (grid[row][col] != '1') return;
            grid[row][col] = '2';
            for (int[] direction : directions) {
                dfs(grid, row + direction[0], col + direction[1]);
            }
        }

        boolean inArea(int row, int col) {
            return row >= 0 && row < m && col >= 0 && col < n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
