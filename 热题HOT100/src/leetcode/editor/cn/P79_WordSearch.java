/**
 * 题目Id：79
 * 题目：单词搜索
 * 日期：2021-07-02 18:04:15
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
// Related Topics 数组 回溯 矩阵 
// 👍 944 👎 0


package leetcode.editor.cn;

//单词搜索

import java.util.ArrayList;
import java.util.List;

public class P79_WordSearch {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P79_WordSearch().new Solution();
//        char[][] board = new char[][]{
//                {'A', 'B', 'C', 'E', 'X'},
//                {'S', 'F', 'C', 'S', 'E'},
//                {'A', 'D', 'E', 'E', 'E'}
//        };
//        boolean exist = solution.exist(board, "ABCCED");
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean exist = solution.exist(board, "ABCB");
        System.out.println("exist = " + exist);
    }


    // my--回溯
    class Solution1 {
        boolean exists = false;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四个方向寻找
        int m, n;
        char[][] board;
        //boolean[][] visited;//记录访问过的,它并不是一个全局的变量!!!

        public boolean exist(char[][] board, String word) {
            this.board = board;
            m = board.length;
            n = board[0].length;

            List<Character> path = new ArrayList<>();
            // 遍历所有可行起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n]; // 不能重复访问(走回头路)
                    backtracking(word, path, i, j, visited);
                    if (exists) return true;// 找到了直接返回
                }
            }
            return false;
        }

        // 对word做减法
        private void backtracking(String word, List<Character> path, int x0, int y0, boolean[][] visited) {
            // 1--递归结束,word长度为0
            if (word.length() == 0) {
                //System.out.println("递归结束,找到:" + path);//这里会输出四次
                exists = true;
                return;
            }
            // 若干排除条件写在前面
            if (!inArea(x0, y0)) return;
            if (visited[x0][y0]) return;
            if (board[x0][y0] != word.charAt(0)) {
                return;
            }

            path.add(word.charAt(0));
            visited[x0][y0] = true;

            // 2--递归深入
            for (int[] direction : directions) {
                int x1 = x0 + direction[0], y1 = y0 + direction[1];
                // 递归深入时不做任何判断
                backtracking(word.substring(1), path, x1, y1, visited);
            }

            path.remove(path.size() - 1);
            visited[x0][y0] = false;

        }

        private boolean inArea(int row, int col) {
            return row >= 0 && row < m && col >= 0 && col < n;
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 优化一些,没必要记录path,不用visited(原地修改字符)
    class Solution {
        public boolean exist(char[][] board, String word) {
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    boolean flag = check(board, visited, i, j, word, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == s.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(board, visited, newi, newj, s, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
