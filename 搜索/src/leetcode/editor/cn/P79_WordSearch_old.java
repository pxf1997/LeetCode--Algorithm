/**
 * 题目Id：79
 * 题目：单词搜索
 * 日期：2021-05-07 15:23:30
 */
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯算法 
// 👍 892 👎 0


package leetcode.editor.cn;

//单词搜索

import util.dp_util;

public class P79_WordSearch_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P79_WordSearch_old().new Solution();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E', 'X'},
                {'S', 'F', 'C', 'S', 'E'},
                {'A', 'D', 'E', 'E', 'E'}
        };

//        String word = "ABCCED";
        String word = "SEE";

//        char[][] board = new char[][]{
//                {'A'}
//        };
//        String word = "A"; //特例失败原因--direction四个方向去寻找后，坐标全部越界


        dp_util.print_2D(board);
        System.out.println();

        boolean exist = solution.exist(board, word);
        System.out.println("exist = " + exist);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //my--回溯
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;
        boolean ans = false;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        StringBuilder find = new StringBuilder();
                        dfs(board, i, j, word, visited, find);
                    }
                }
            }
            return ans;

        }

        private void dfs(char[][] board, int r, int c, String word, boolean[][] visited, StringBuilder find) {
            //越界
            if (r < 0 || r >= m || c < 0 || c >= n) {
                return;
            }
            //访问过
            if (visited[r][c]) {
                return;
            }

            //word用尽--写法（位置版本1）--不好
//            if (word.length() == 0) {
//                ans = true;
//                System.out.println("最终: (" + r + "," + c + ")");
//                System.out.println("成功找到" + find); //有可能输出好几次--多个坐标(r,c)合法、word用尽
//                System.out.println();
//                return;
//            }

            //与第一个字母不相等
            if (board[r][c] != word.charAt(0)) {
                return;
            }

            //添加
            visited[r][c] = true;
            find.append(board[r][c]);

            //helper
            System.out.println("(" + r + "," + c + ")");
            System.out.println("find = " + find);
            System.out.println();

            String subWord = word.substring(1);
            if (subWord.equals("")) {
                ans = true;
                System.out.println("最终: (" + r + "," + c + ")");
                System.out.println("成功找到" + find);
                System.out.println();
                return;
            }

            for (int[] direction : directions) {
                dfs(board, r + direction[0], c + direction[1], subWord, visited, find);
            }

            //回溯
            visited[r][c] = false;
            find.deleteCharAt(find.length() - 1);


        }
    }

    //参考答案
    class Solution1 {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m, n;

        public boolean exist(char[][] board, String word) {
            //特殊情况
            if (word == null || word.length() == 0) {
                return true;
            }
            if (board == null || board.length == 0 || board[0].length == 0) {
                return false;
            }
            m = board.length;
            n = board[0].length;
            boolean[][] hasVisited = new boolean[m][n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (backtracking(0, r, c, hasVisited, board, word)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean backtracking(int curLen, int r, int c, boolean[][] visited, final char[][] board, final String word) {
            if (curLen == word.length()) {
                return true;
            }

            //排除情况--index越界在前，其余在后
            if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != word.charAt(curLen) || visited[r][c]) {
                return false;
            }

            visited[r][c] = true;

            for (int[] d : directions) {
                if (backtracking(curLen + 1, r + d[0], c + d[1], visited, board, word)) {
                    return true;
                }
            }

            visited[r][c] = false;

            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
