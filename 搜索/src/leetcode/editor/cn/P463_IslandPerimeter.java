/**
 * 题目Id：463
 * 题目：岛屿的周长
 * 日期：2021-05-06 14:30:05
 */
//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。 
//
// 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//输出：16
//解释：它的周长是上面图片中的 16 个黄色的边 
//
// 示例 2： 
//
// 
//输入：grid = [[1]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 哈希表 
// 👍 401 👎 0


package leetcode.editor.cn;

//岛屿的周长

import util.dp_util;

public class P463_IslandPerimeter {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P463_IslandPerimeter().new Solution();
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
//        int[][] grid = {
//                {1,1},
//                {1,1},
//        };
        int res = solution.islandPerimeter(grid);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;

        public int islandPerimeter(int[][] grid) {
            int ans = 0;
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        ans = dfs(grid, i, j);
                        //helper
//                        System.out.println(i + "," + j);
//                        dp_util.print_2D(grid);
//                        System.out.println();
                    }
                }
            }
            return ans;


        }

        private int dfs(int[][] grid, int r, int c) {
            //蓝色--与海洋格子相邻
            //黄色--与网格边界相邻

            // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return 1;
            }
            // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
            if (grid[r][c] == 0) {
                return 1;
            }
            // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
            if (grid[r][c] == 2) {
                return 0;
            }

            grid[r][c] = 2;
            int perimeter = 0;
            //helper
            System.out.println(r + "," + c);
            dp_util.print_2D(grid);
            System.out.println();

            for (int[] direction : directions) {
                perimeter += dfs(grid, r + direction[0], c + direction[1]);
            }
            return perimeter;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
