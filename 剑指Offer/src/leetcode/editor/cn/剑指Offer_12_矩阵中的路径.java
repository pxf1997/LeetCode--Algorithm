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

public class 剑指Offer_12_矩阵中的路径 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_12_矩阵中的路径().new Solution();
        boolean res = solution.exist(new char[][]{
                        {'a', 'b', 'c', 'e'},
                        {'s', 'f', 'c', 's'},
                        {'a', 'd', 'e', 'e'},},
                "abcese");
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // my
    class Solution {
        // 多写类属性,少写回溯形参
        int m, n;
        char[][] board;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public boolean exist(char[][] board, String word) {
            this.board = board;
            m = board.length;
            n = board[0].length;
            char[] chars = word.toCharArray();
            // 遍历起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtracking(chars, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        // 回溯细节--
        // 递归终止条件写在前面,四个方向递归深入时,不判断!
        private boolean backtracking(char[] chars, int i, int j, int beginIndex) {
            // 1--终止条件
            if (!inArea(i, j)) return false;
            if (chars[beginIndex] != board[i][j]) return false;
            // helper
            System.out.println("坐标:(" + i + "," + j + ")  匹配字符:" + board[i][j]);

            if (beginIndex == chars.length - 1) return true;


            // 2--递归深入
            // 细节--不设置visited数组,直接修改board矩阵
            board[i][j] = '\0';
            boolean res = false;
            for (int[] direction : directions) {
                res = res || backtracking(chars, i + direction[0], j + direction[1], beginIndex + 1);
            }
            board[i][j] = chars[beginIndex];
            return res;
        }

        public boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }

    }


//leetcode submit region end(Prohibit modification and deletion)

}
