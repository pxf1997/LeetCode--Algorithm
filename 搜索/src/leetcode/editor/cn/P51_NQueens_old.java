/**
 * 题目Id：51
 * 题目：N 皇后
 * 日期：2021-05-10 14:28:13
 */
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 869 👎 0


package leetcode.editor.cn;

//N 皇后

import util.dp_util;

import java.util.*;

public class P51_NQueens_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P51_NQueens_old().new Solution();
        List<List<String>> resList = solution.solveNQueens(5);

//        System.out.println();
//        for (int i = 0; i < resList.size(); i++) {
//            List<String> answer = resList.get(i);
//            System.out.println("第" + i + "种解法:");
//            for (String s : answer) {
//                System.out.println(s);
//            }
//            System.out.println();
//        }


    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //版本1
    class Solution1 {
        int depth = 0;

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> resList = new ArrayList<>();

            //记录每行的Q 的列坐标--初始化一个非法值 -1
            int[] queens = new int[n];
            Arrays.fill(queens, -1);

            //为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合columns、diagonals1、diagonals2
            //分别记录每一列以及两个方向的每条斜线上是否有皇后。
            Set<Integer> cols = new HashSet<>();
            Set<Integer> diagonals1 = new HashSet<>();
            Set<Integer> diagonals2 = new HashSet<>();

            backtracking(resList, queens, n, 0, cols, diagonals1, diagonals2);

            return resList;

        }

        private void backtracking(List<List<String>> resList, int[] queens, int total, int curRow, Set<Integer> cols, Set<Integer> diagonals1, Set<Integer> diagonals2) {
            if (curRow == total) {
                System.out.println("找到一个答案:" + Arrays.toString(queens));
                List<String> board = generateBoard(queens, total);
                resList.add(board);
            } else {
                for (int i = 0; i < total; i++) {
                    if (cols.contains(i)) {
                        continue;
                    }
                    // 斜下方向对角线(135°)--行坐标与列坐标之差相等
                    int diagonal1 = curRow - i;
                    if (diagonals1.contains(diagonal1)) {
                        continue;
                    }
                    // 斜上方向对角线(45°)--行坐标与列坐标之和相等
                    int diagonal2 = curRow + i;
                    if (diagonals2.contains(diagonal2)) {
                        continue;
                    }

                    //添加
                    queens[curRow] = i; //把Q放在curRow行的 第i列
                    cols.add(i);
                    diagonals1.add(diagonal1);
                    diagonals2.add(diagonal2);
                    depth++;

                    //递归调用
                    backtracking(resList, queens, total, curRow + 1, cols, diagonals1, diagonals2);

                    //删除--回溯
                    queens[curRow] = -1; //把Q放在curRow行的 第i列
                    cols.remove(i);
                    diagonals1.remove(diagonal1);
                    diagonals2.remove(diagonal2);
                    depth--;

                }
            }
        }

        private List<String> generateBoard(int[] queens, int n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                board.add(new String(row));
            }
            return board;
        }
    }

    //版本2
    class Solution {
        private List<List<String>> solutions;
        private char[][] nQueens;
        //        private boolean[] colUsed;
//        private boolean[] diagonals45Used;
//        private boolean[] diagonals135Used;
        Set<Integer> colUsed = new HashSet<Integer>();
        Set<Integer> diagonals45Used = new HashSet<Integer>();
        Set<Integer> diagonals135Used = new HashSet<Integer>();
        private int total;

        public List<List<String>> solveNQueens(int n) {
            solutions = new ArrayList<>();
            nQueens = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(nQueens[i], '.');
            }
//            colUsed = new boolean[n];
//            diagonals45Used = new boolean[2 * n - 1];
//            diagonals135Used = new boolean[2 * n - 1];

            this.total = n;
//            backtracking_v1(0);
            backtracking_v2(0);

            return solutions;
        }

//        private void backtracking_v1(int row) {
//            //递归终止
//            if (row == total) {
//                //helper
//                System.out.println("递归终止--找到一个解:");
//                dp_util.print_2D(nQueens);
//                System.out.println();
//
//                List<String> list = new ArrayList<>();
//                for (char[] chars : nQueens) {
//                    list.add(new String(chars));
//                }
//                solutions.add(list);
//                return;
//            }
//
//            for (int col = 0; col < total; col++) {
//                int diagonals45Idx = row + col;
//                int diagonals135Idx = total - 1 - (row - col);
//                if (colUsed[col] || diagonals45Used[diagonals45Idx] || diagonals135Used[diagonals135Idx]) {
//                    continue;
//                }
//                //添加
//                nQueens[row][col] = 'Q';
//                colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = true;
//                //递归深入
//                backtracking(row + 1);
//                //删除回溯
//                colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = false;
//                nQueens[row][col] = '.';
//            }
//        }

        private void backtracking_v2(int row) {
            //递归终止
            if (row == total) {
                //helper
                System.out.println("递归终止--找到一个解:");
                dp_util.print_2D(nQueens);
                System.out.println();

                List<String> list = new ArrayList<>();
                for (char[] chars : nQueens) {
                    list.add(new String(chars));
                }
                solutions.add(list);
                return;
            }

            for (int col = 0; col < total; col++) {
                int diagonals45Idx = row + col;
                int diagonals135Idx = row - col;
                if (colUsed.contains(col) || diagonals45Used.contains(diagonals45Idx) || diagonals135Used.contains(diagonals135Idx)) {
                    continue;
                }
                //添加
                nQueens[row][col] = 'Q';
                colUsed.add(col);
                diagonals45Used.add(diagonals45Idx);
                diagonals135Used.add(diagonals135Idx);

                //递归深入
                backtracking_v2(row + 1);

                //删除回溯
                nQueens[row][col] = '.';
                colUsed.remove(col);
                diagonals45Used.remove(diagonals45Idx);
                diagonals135Used.remove(diagonals135Idx);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
