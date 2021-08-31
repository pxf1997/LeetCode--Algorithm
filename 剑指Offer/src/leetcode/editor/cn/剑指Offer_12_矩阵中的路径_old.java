/**
 * 题目Id：剑指 Offer 12
 * 题目：矩阵中的路径
 * 日期：2021-06-05 23:40:33
 */
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
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
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 深度优先搜索 
// 👍 330 👎 0


package leetcode.editor.cn;

//矩阵中的路径

public class 剑指Offer_12_矩阵中的路径_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_12_矩阵中的路径_old().new Solution();
        boolean res = solution.exist(new char[][]{
                        {'a', 'b', 'c', 'e'},
                        {'s', 'f', 'c', 's'},
                        {'a', 'd', 'e', 'e'},},
                "abcese");
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my--不超时
    class Solution {
        // 多设置类属性,少写回溯函数的形参
        int m, n;
        char[][] board;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public boolean exist(char[][] board, String word) {
            this.board = board;
            m = this.board.length;
            n = this.board[0].length;
            char[] chars = word.toCharArray();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtracking(chars, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean backtracking(char[] chars, int x0, int y0, int beginIndex) {
            // 排除条件--写在外面 不写在四个方向遍历里面
            if (!inArea(x0, y0)) return false;
            if (chars[beginIndex] != board[x0][y0]) return false;
            // 递归终止条件
            if (beginIndex == chars.length - 1) return true;

            System.out.println("坐标:(" + x0 + "," + y0 + ")  匹配字符:" + board[x0][y0]);

            // 回溯套路
            // 细节--不设置visited数组,直接修改board矩阵
            board[x0][y0] = '\0';
            boolean res = false;
            for (int[] direction : directions) {
                res = res || backtracking(chars, x0 + direction[0], y0 + direction[1], beginIndex + 1);
            }
            board[x0][y0] = chars[beginIndex];
            return res;
        }

        public boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }

    // my--超时
    class Solution2 {
        int m, n;
        char[][] board;
        boolean found;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public boolean exist(char[][] board, String word) {
            this.board = board;
            m = this.board.length;
            n = this.board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n];
                    // 用beginIndex 代替path
                    System.out.println("寻找起点:(" + i + "," + j + ")");
                    backtrackintg(word, visited, i, j, 0);
                    System.out.println("--------------------");
                    if (found) return true;
                }
            }
            return false;
        }

        private void backtrackintg(String word, boolean[][] visited, int x, int y, int beginIndex) {
            // 非法排除
            if (!inArea(x, y)) return;
            if (board[x][y] != word.charAt(beginIndex)) return;
            // 递归终止
            if (beginIndex == word.length() - 1) {
                System.out.println("递归终止:");
                found = true;
                return;
            }
            visited[x][y] = true;
            System.out.println("坐标:(" + x + "," + y + ")  匹配字符:" + board[x][y] + "  beginIndex=" + beginIndex);
            // 思路--四个方向遍历就直接写,越不越界进去再判断!
            for (int[] direction : directions) {
                int x1 = x + direction[0], y1 = y + direction[1];
                // 这里不做任何判断,直接递归深入!
                backtrackintg(word, visited, x1, y1, beginIndex + 1);
            }
            visited[x][y] = false;
        }

        public boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
