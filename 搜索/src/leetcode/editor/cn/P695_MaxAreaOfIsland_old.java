/**
 * 题目Id：695
 * 题目：岛屿的最大面积
 * 日期：2021-05-05 23:02:04
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
// {{0,0,1,0,0,0,0,1,0,0,0,0,0},
// {0,0,0,0,0,0,0,1,1,1,0,0,0},
// {0,1,1,0,1,0,0,0,0,0,0,0,0},
// {0,1,0,0,1,1,0,0,1,0,1,0,0},
// {0,1,0,0,1,1,0,0,1,1,1,0,0},
// {0,0,0,0,0,0,0,0,0,0,1,0,0},
// {0,0,0,0,0,0,0,1,1,1,0,0,0},
// {0,0,0,0,0,0,0,1,1,0,0,0,0}}
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
// 👍 484 👎 0


package leetcode.editor.cn;

//岛屿的最大面积

import util.dp_util;

import java.util.Deque;
import java.util.LinkedList;

public class P695_MaxAreaOfIsland_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P695_MaxAreaOfIsland_old().new Solution();
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

    // DFS
    class Solution {
        int m, n; //m行 n列
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        boolean[][] visited;

        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            m = grid.length;
            n = grid[0].length;
//            visited = new boolean[m][n];

            int ans = 0;
            //以每块土地开始做dfs，维护一个max
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int cur = dfs(grid, i, j);
                    System.out.println(i +"," +j + "  Area = " + cur);
                    ans = Math.max(ans, cur);
                }
            }
            dp_util.print_2D(grid);

            return ans;

        }

        private int dfs(int[][] grid, int cur_i, int cur_j) {
            if (cur_i < 0 || cur_i >= m || cur_j < 0 || cur_j >= n) {
                return 0;
            }
            if (grid[cur_i][cur_j] == 0 || grid[cur_i][cur_j] == 3) {
                return 0;
                }

//            visited[cur_i][cur_j] = true;
            grid[cur_i][cur_j] = 3; //访问过置3

            int area = 1;
            for (int[] direction : directions) {
                area += dfs(grid, cur_i + direction[0], cur_j + direction[1]);
            }
            return area;
        }
    }

    //DFS+递归栈
    class Solution1 {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int maxAreaOfIsland(int[][] grid) {
            int ans = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int cur = 0;
                    Deque<Integer> stack_i = new LinkedList<>();
                    Deque<Integer> stack_j = new LinkedList<>();
                    stack_i.push(i);
                    stack_j.push(j);
                    //对比BFS
                    while (!stack_i.isEmpty()) {
                        int cur_i = stack_i.pop();
                        int cur_j = stack_j.pop();
                        //越界+访问过
                        if (cur_i < 0 || cur_i >= grid.length || cur_j < 0 || cur_j >= grid[0].length || grid[cur_i][cur_j] == 0) {
                            continue;
                        }
                        cur++;
                        grid[cur_i][cur_j] = 0;
                        for (int[] direction : directions) {
                            int next_i = cur_i + direction[0];
                            int next_j = cur_j + direction[1];
                            stack_i.push(next_i);
                            stack_j.push(next_j);
                        }
                    }
                    ans = Math.max(cur, ans);

                }
            }
            return ans;

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}



