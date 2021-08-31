/**
 * 题目Id：37
 * 题目：解数独
 * 日期：2021-05-10 10:53:56
 */
//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 哈希表 回溯算法 
// 👍 832 👎 0


package leetcode.editor.cn;

//解数独

import java.util.ArrayList;
import java.util.List;

public class P37_SudokuSolver {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P37_SudokuSolver().new Solution();

        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solution.printBoard(board);
        System.out.println();
        solution.solveSudoku(board);
        solution.printBoard(board);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //参考
    class Solution1 {
        private final boolean[][] rowsUsed = new boolean[9][10];
        private final boolean[][] colsUsed = new boolean[9][10];
        private final boolean[][] cubesUsed = new boolean[9][10];
        private char[][] board;

        public void solveSudoku(char[][] board) {
            this.board = board;
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    int num = board[i][j] - '0';
                    rowsUsed[i][num] = true;
                    colsUsed[j][num] = true;
                    cubesUsed[cubeNum(i, j)][num] = true;
                }
            backtracking(0, 0);
        }

        private boolean backtracking(int row, int col) {
            // 行+列 遍历
            while (row < 9 && board[row][col] != '.') {
                row = (col == 8 ? row + 1 : row);
                col = (col == 8 ? 0 : col + 1);
            }
            if (row == 9) {
                return true;
            }
            for (int num = 1; num <= 9; num++) {
                if (rowsUsed[row][num] || colsUsed[col][num] || cubesUsed[cubeNum(row, col)][num]) {
                    continue;
                }

                rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = true;
                board[row][col] = (char) (num + '0');

                //上面填法能走得通
                if (backtracking(row, col)) {
                    return true;
                }

                board[row][col] = '.';
                rowsUsed[row][num] = colsUsed[col][num] = cubesUsed[cubeNum(row, col)][num] = false;
            }
            return false;
        }

        private int cubeNum(int i, int j) { //大九宫格上坐标映射到----小九宫格里的坐标
            int r = i / 3;
            int c = j / 3;
            return r * 3 + c;
        }

        private void printBoard(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }


    //my
    class Solution {
        int depth;
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];

        //第 i 行第 j 列的格子位于第(⌊i/3⌋,⌊j/3⌋) 个九宫格中
        boolean[][][] block = new boolean[3][3][9];
        boolean valid = false;
        List<int[]> spaces = new ArrayList<int[]>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //空白格加入处理列表
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    } else {
                        //数字 1 对应下标 0
                        int index = (board[i][j] - '0') - 1;
                        //i行、j列、block格 使用了index这个数字
                        row[i][index] = col[j][index] = block[i / 3][j / 3][index] = true;
                    }
                }
            }
            dfs(board, 0);
        }

        private void dfs(char[][] board, int pos) {
            if (pos == spaces.size()) {
                System.out.println("全部填完了!!!");
                valid = true;
                return;
            }
            int[] space = spaces.get(pos);
            int i = space[0], j = space[1];
            for (int index = 0; index < 9 && !valid; index++) {
                if (!row[i][index] && !col[j][index] && !block[i / 3][j / 3][index]) {
                    //添加
                    row[i][index] = col[j][index] = block[i / 3][j / 3][index] = true;
                    board[i][j] = (char) (index + '0' + 1);
                    depth++;
                    System.out.print("添加--递归深度:" + depth);
                    System.out.println("  i=" + i + "  j=" + j + "  填入" + board[i][j]);
//                    printBoard(board);
//                    System.out.println();

                    //递归下一层
                    dfs(board, pos + 1);

                    //删除
                    row[i][index] = col[j][index] = block[i / 3][j / 3][index] = false;
                    depth--;
                    System.out.println("删除--递归深度:" + depth);
//                    System.out.println("  i=" + i + "  j=" + j);
//                    System.out.println();

                }
            }
            System.out.println();
        }

        private void printBoard(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
