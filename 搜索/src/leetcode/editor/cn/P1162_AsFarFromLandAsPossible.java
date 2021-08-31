/**
 * 题目Id：1162
 * 题目：地图分析
 * 日期：2021-04-29 13:56:05
 */
//你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单
//元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。 
//
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - 
//x1| + |y0 - y1| 。 
//
// 如果网格上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图 
// 👍 194 👎 0


package leetcode.editor.cn;

//地图分析

import util.dp_util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P1162_AsFarFromLandAsPossible {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1162_AsFarFromLandAsPossible().new Solution();
//        int res = solution.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
//        int res = solution.maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
//        int res = solution.maxDistance(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        int res = solution.maxDistance(new int[][]{{1, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 1}});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 辅助函数，逐个寻找
    class Solution1 {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        //        思路--BFS+最短路径 + 记录全局最短的最大值
        public int maxDistance(int[][] grid) {
            int m = grid.length, n = grid[0].length; // m行 n列
            int max = -1;
            //遍历每个节点，以节点为起始做BFS，找到陆地的最短路径，得到这些最短路径中的最大值
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        int curMin = findMinLand(i, j, grid);
                        //调试
                        System.out.println("坐标(" + i + "," + j + ")最近陆地距离: " + curMin);
                        max = Math.max(curMin, max);
                    }
                }
            }
            return max;
        }

        private int findMinLand(int x, int y, int[][] grid) {
            int m = grid.length, n = grid[0].length; // m行 n列
            boolean[][] marked = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>(); //坐标队列
            queue.add(new int[]{x, y});
            marked[x][y] = true;
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                while (size-- > 0) {
                    int[] curp = queue.poll();
                    int x0 = curp[0];
                    int y0 = curp[1];
                    for (int[] direction : directions) {
                        int x1 = x0 + direction[0];
                        int y1 = y0 + direction[1];
                        //判断出界
                        if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m) {
                            continue;
                        }
                        if (marked[x1][y1]) {
                            continue;
                        }
                        marked[x1][y1] = true;
                        queue.add(new int[]{x1, y1});
                        if (grid[x1][y1] == 1) {
                            return level;
                        }
                    }
                }
            }
            return -1; //找不到-- 异常值-1
        }
    }

    // 多源BFS
    class Solution {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int maxDistance(int[][] grid) {
//            dp_util.print_2D(grid);
//            System.out.println();

            int m = grid.length, n = grid[0].length; //m*n
            Queue<int[]> queue = new LinkedList<>();
            int[][] res = new int[m][n];
            boolean[][] visited = new boolean[m][n];
            int max = -1;
            // 将所有的陆地格子加入队列----抽象的“超级1” superOne概念
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
//                System.out.println("出队坐标 = " + Arrays.toString(cur));

                int x0 = cur[0], y0 = cur[1];
                for (int[] direction : directions) {
                    int x1 = x0 + direction[0], y1 = y0 + direction[1];
                    //判断边界
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                        continue;
                    }
                    if (visited[x1][y1]) {
                        continue;
                    }
//                    res[x1][y1]++; //错啦，应该是以res[x0][y0]基础上再多走一步
                    res[x1][y1] = res[x0][y0] + 1;
                    max = Math.max(res[x1][y1], max);
                    queue.add(new int[]{x1, y1});
                    visited[x1][y1] = true;

//                    dp_util.print_2D(res);
//                    System.out.println();
                }


            }

            return max;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
