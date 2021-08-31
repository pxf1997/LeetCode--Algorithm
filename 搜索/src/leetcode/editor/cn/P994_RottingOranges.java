/**
 * 题目Id：994
 * 题目：腐烂的橘子
 * 日期：2021-04-30 16:21:22
 */
//在给定的网格中，每个单元格可以有以下三个值之一： 
//
// 
// 值 0 代表空单元格； 
// 值 1 代表新鲜橘子； 
// 值 2 代表腐烂的橘子。 
// 
//
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。 
//
// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[2,1,1],[1,1,0],[0,1,1]]
//输出：4
// 
//
// 示例 2： 
//
// 输入：[[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
// 
//
// 示例 3： 
//
// 输入：[[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 10 
// 1 <= grid[0].length <= 10 
// grid[i][j] 仅为 0、1 或 2 
// 
// Related Topics 广度优先搜索 
// 👍 353 👎 0


package leetcode.editor.cn;

//腐烂的橘子

import util.dp_util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P994_RottingOranges {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P994_RottingOranges().new Solution();

//        int res = solution.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}});
//        int res = solution.orangesRotting(new int[][]{
//                {2, 1, 1},
//                {1, 1, 0},
//                {0, 1, 1}
//        });
        int res = solution.orangesRotting(new int[][]{
                {2, 1, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1},
                {0, 1, 1, 2, 1},
        });
//        int res = solution.orangesRotting(new int[][]{{0, 2}});
        System.out.println("最终结果:" + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 值 0 代表空单元格；
        // 值 1 代表新鲜橘子；
        // 值 2 代表腐烂的橘子。
        //分析：2--烂橘子为源，找每个1--好橘子的最短路径，此路径的全局最大就是所用时间
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int orangesRotting(int[][] grid) {

            dp_util.print_2D(grid);
            System.out.println();

            int max = -1;
            int m = grid.length, n = grid[0].length;
            int[][] res = new int[m][n];
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>();
            int orange = 0;
            //源头入队
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        orange++;
                    } else if (grid[i][j] == 2) {
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                    } else {
                        res[i][j] = -5;
                        visited[i][j] = true;
                    }

                }
            }
            if (orange == 0) {
                return 0;
            }
            System.out.println("orange = " + orange);

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                System.out.println("出队坐标 = " + Arrays.toString(cur));
                int x0 = cur[0], y0 = cur[1];
                for (int[] direction : directions) {
                    int x1 = x0 + direction[0];
                    int y1 = y0 + direction[1];
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                        continue;
                    }
                    if (visited[x1][y1]) {
                        continue;
                    }
                    if (grid[x1][y1] == 0) {
                        continue;
                    }

                    queue.add(new int[]{x1, y1});
                    visited[x1][y1] = true;
                    res[x1][y1] = res[x0][y0] + 1;
                    orange--;
                    max = Math.max(res[x1][y1], -1);

                    dp_util.print_2D(res);
                    System.out.println();

                }
            }

            return orange != 0 ? -1 : max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
