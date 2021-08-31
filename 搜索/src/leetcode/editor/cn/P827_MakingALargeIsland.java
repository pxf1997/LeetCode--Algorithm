/**
 * 题目Id：827
 * 题目：最大人工岛
 * 日期：2021-05-06 15:13:24
 */
//在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。 
//
// 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿） 
//
// 示例 1: 
//
// 
//输入: [[1, 0], [0, 1]]
//输出: 3
//解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
// 
//
// 示例 2: 
//
// 
//输入: [[1, 1], [1, 0]]
//输出: 4
//解释: 将一格0变成1，岛屿的面积扩大为 4。 
//
// 示例 3: 
//
// 
//输入: [[1, 1], [1, 1]]
//输出: 4
//解释: 没有0可以让我们变成1，面积依然为 4。 
//
// 说明: 
//
// 
// 1 <= grid.length = grid[0].length <= 50 
// 0 <= grid[i][j] <= 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 90 👎 0


package leetcode.editor.cn;

//最大人工岛

import util.dp_util;

import java.util.*;

public class P827_MakingALargeIsland {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P827_MakingALargeIsland().new Solution();
        int[][] grid = new int[][]{
                {0, 1, 0, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 1, 0, 1, 1},
        };
//        int[][] grid = new int[][]{
//                {0,0},
//                {0,0},
//        };
        int res = solution.largestIsland(grid);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //自己解法--超时
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //顺序--上下左右
        int m, n; // m行n列 m*n

        public int largestIsland(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int count = 0;
            int[] Area = new int[m * n + 2];  //岛屿最大面积 m*n （无海洋）
//            第一遍 DFS 遍历陆地格子，计算每个岛屿的面积并标记岛屿
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int curArea = dfs_1(grid, i, j, count + 2);
                        //index=岛屿编号  Area[index]=对应编号岛屿面积
                        Area[count + 2] = curArea;
                        count++;

                        //helper
                        System.out.println("岛屿起始坐标:" + i + "," + j + "   岛屿编号=" + (count + 1) + "   岛屿面积=" + curArea);
                        System.out.println();
                    }
                }
            }

            //helper
            System.out.println("岛屿个数=" + count);
            System.out.println("编号--面积:" + Arrays.toString(Area));
            System.out.println();


//            第二遍 DFS 遍历海洋格子，观察每个海洋格子相邻的陆地格子。
            int max = Arrays.stream(Area).max().getAsInt(); //初始化--最大的岛屿面积
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
//                        boolean[] visited = new boolean[m * n];
                        Set<Integer> seen = new HashSet();

                        int curMax = 1;
                        for (int[] direction : directions) {
                            int r = i + direction[0], c = j + direction[1];
                            if (r < 0 || r >= m || c < 0 || c >= n) {
                                continue;
                            }
                            int landIndex = grid[r][c];
//                            if (landIndex >= 2 && !visited[landIndex]) { //岛屿编号有效 && 没有跟此编号的岛屿连同过
//                                curMax += Area[landIndex];
//                                visited[landIndex] = true;
//                            }
                            if (landIndex >= 2) {
                                seen.add(landIndex);
                            }
                        }

                        for (Integer seenIndex : seen) {
                            curMax += Area[seenIndex];
                        }

                        System.out.print("填海坐标: (" + i + "," + j + ")  与之相连的岛屿编号=" + seen);
                        System.out.println("  总面积=" + curMax);

                        //四个方向寻找之后，跟max进行比较
                        max = Math.max(max, curMax);

                    }
                }
            }

            return max;
        }

        private int dfs_1(int[][] grid, int r, int c, int count) {
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return 0;
            }
            if (grid[r][c] != 1) { // 海洋 or 访问过的陆地
                return 0;
            }
            grid[r][c] = count;

            //helper
            System.out.println(r + "," + c);
            dp_util.print_2D(grid);
            System.out.println();

            int area = 1;
            for (int[] direction : directions) {
                area += dfs_1(grid, r + direction[0], c + direction[1], count);
            }
            return area;
        }
    }

    //参考答案--连通块 + 编号
/*    class Solution1 {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};
        int[][] grid;
        int N;

        public int largestIsland(int[][] grid) {
            this.grid = grid;
            N = grid.length;

            int index = 2;
            int[] area = new int[N * N + 2];
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    if (grid[r][c] == 1)
                        area[index] = dfs(r, c, index++);

            int ans = 0;
            for (int x : area) ans = Math.max(ans, x);
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    if (grid[r][c] == 0) {
                        Set<Integer> seen = new HashSet();
                        for (Integer move : neighbors(r, c))
                            if (grid[move / N][move % N] > 1)
                                seen.add(grid[move / N][move % N]);

                        int bns = 1;
                        for (int i : seen) bns += area[i];
                        ans = Math.max(ans, bns);
                    }

            return ans;
        }

        public int dfs(int r, int c, int index) {
            int ans = 1;
            grid[r][c] = index;
            for (Integer move : neighbors(r, c)) {
                if (grid[move / N][move % N] == 1) {
                    grid[move / N][move % N] = index;
                    ans += dfs(move / N, move % N, index);
                }
            }

            return ans;
        }

        public List<Integer> neighbors(int r, int c) {
            List<Integer> ans = new ArrayList();
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < N && 0 <= nc && nc < N)
                    ans.add(nr * N + nc);
            }

            return ans;
        }
    }*/


//leetcode submit region end(Prohibit modification and deletion)

}
