/**
 * 题目Id：130
 * 题目：被围绕的区域
 * 日期：2021-05-06 18:26:29
 */
//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X"
//,"X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 531 👎 0


package leetcode.editor.cn;

//被围绕的区域

import util.dp_util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P130_SurroundedRegions {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P130_SurroundedRegions().new Solution();
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X', 'O'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'X', 'X'},
        };
//        char[][] board = new char[][]{
//                {'O', 'O', 'O', 'O'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'O', 'O', 'O'},
//                {'O', 'O', 'O', 'O'},
//        };
        solution.solve(board);
        System.out.println("result:");
        dp_util.print_2D(board);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution1 {
        //        我们可以想到，所有的不被包围的 O 都直接或间接与边界上的 O 相连。我们可以利用这个性质判断 O 是否在边界上
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }

            m = board.length;
            n = board[0].length;

            for (int i = 0; i < m; i++) {
                dfs(board, i, 0);
                dfs(board, i, n - 1);
            }
            for (int i = 0; i < n; i++) {
                dfs(board, 0, i);
                dfs(board, m - 1, i);
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'T') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }

        }

        private void dfs(char[][] board, int r, int c) {
            if (r < 0 || c < 0 || r >= m || c >= n) {
                return;
            }
            if (board[r][c] != 'O') {
                return;
            }
            board[r][c] = 'T';
            //helper
            System.out.println(r + "," + c);
            dp_util.print_2D(board);
            System.out.println();

            for (int[] direction : directions) {
                dfs(board, r + direction[0], c + direction[1]);
            }
        }
    }

    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;

        public void solve(char[][] board) {
            m = board.length;
            n = board[0].length;
            Queue<int[]> queue = new LinkedList<>();
            //四个边界入队
            for (int i = 0; i < m; i++) {  // 第一列、最后一列
                if (board[i][0] == 'O') {
                    queue.add(new int[]{i, 0});
                }
                if (board[i][n - 1] == 'O') {
                    queue.add(new int[]{i, n - 1});
                }
            }
            for (int i = 1; i < n - 1; i++) { // 第一行、最后一行 (左上角、右下角重复)
                if (board[0][i] == 'O') {
                    queue.add(new int[]{0, i});
                }
                if (board[m - 1][i] == 'O') {
                    queue.add(new int[]{m - 1, i});
                }
            }
            System.out.println("queue = ");
            for (int[] ints : queue) {
                System.out.println("ints = " + Arrays.toString(ints));
            }

            //BFS套路
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x0 = cur[0], y0 = cur[1];
                board[x0][y0] = 'A';

                System.out.println("出队坐标  " + x0 + "," + y0);
                dp_util.print_2D(board);

                for (int[] direction : directions) { //有重复入队--无所谓了
                    int x1 = x0 + direction[0], y1 = y0 + direction[1];
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                        continue;
                    }
                    if (board[x1][y1] != 'O') {
                        continue;
                    }
                    queue.add(new int[]{x1, y1});
                    System.out.println("    入队坐标 " + x1 + "," + y1);
                }
                System.out.println();
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    }
                    else if(board[i][j] == 'O'){
                        board[i][j] = 'X';
                    }
                    //逻辑错---刚变回的'O'也涂成'X'了
//                    if (board[i][j] == 'O') {
//                        board[i][j] = 'X';
//                    }

                }
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
