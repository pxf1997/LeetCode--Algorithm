/**
 * 题目Id：64
 * 题目：最小路径和
 * 日期：2021-08-23 23:20:24
 */
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 👍 967 👎 0


package leetcode.editor.cn;

//最小路径和

import util.dp_util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P64_MinimumPathSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P64_MinimumPathSum().new Solution();
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        };
        int res = solution.minPathSum(grid);
        System.out.println("res = " + res);
    }


    // 回溯法
    // 评价--问题规模 1 <= m, n <= 200 ,不用记忆化你要超时啊!!!
    class Solution_my {
        int m, n;
        int[][] directions = new int[][]{{1, 0}, {0, 1}}; // 向下, 向右
        int minPath = Integer.MAX_VALUE;

        public int minPathSum(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            // 分析--不存在走回头路,不用visited
            List<Integer> path = new ArrayList<>();
            backtracking(grid, 0, 0, path);
            return minPath;
        }

        private void backtracking(int[][] grid, int x, int y, List<Integer> path) {
            if (!inArea(x, y)) {
                return;
            }
            path.add(grid[x][y]);
            if (x == m - 1 && y == n - 1) { // 这样写,没统计到最后一个点
                System.out.println("发现路径" + path);
                minPath = Math.min(minPath, helper(path));
                // return; 不可以在此处返回
            }

            for (int[] direction : directions) {
                backtracking(grid, x + direction[0], y + direction[1], path);// 优先向下
            }
            path.remove(path.size() - 1);

        }

        private int helper(List<Integer> path) {
            int sum = 0;
            for (Integer x : path) {
                sum += x;
            }
            return sum;
        }

        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }

    }


    // dp法--好想
    class Solution_dp {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            dp_util.print_2D(dp);
            return dp[m - 1][n - 1];
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 记忆化 + backtracking
    class Solution {
        int m, n;
        int[][] memo;

        public int minPathSum(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i], -1);
            }
            // dfs返回值意义--最短路径
            return dfs(grid, 0, 0);
        }

        private int dfs(int[][] grid, int x, int y) {
            //若越界，则认为不可达，距离为无穷大
            if (!inArea(x, y)) return Integer.MAX_VALUE;
            // 已记忆的值
            if (memo[x][y] > -1) return memo[x][y];
            // 到达终点,终点的贡献值是其本身
            if (x == m - 1 && y == n - 1) return grid[m - 1][n - 1];
            // 两个方向分别计算最短路径
            int right = dfs(grid, x, y + 1);
            int down = dfs(grid, x + 1, y);

            // 更新memo并返回
            int res = Math.min(right, down) + grid[x][y];
            memo[x][y] = res;
            return res;
        }

        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
