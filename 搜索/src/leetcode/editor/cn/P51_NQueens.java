/**
 * 题目Id：51
 * 题目：N 皇后
 * 日期：2021-05-10 15:30:01
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

public class P51_NQueens {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P51_NQueens().new Solution();
        List<List<String>> resList = solution.solveNQueens(5);
        System.out.println("resList = " + resList);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //写在类属性里---免得写backtracking的时候一堆传参
        private final char FILL = '.';
        List<List<String>> resList;
        char[][] Nqueens;
        int len;
        Set<Integer> cols = new HashSet<>();
        Set<Integer> line1 = new HashSet<>();
        Set<Integer> line2 = new HashSet<>();

        public List<List<String>> solveNQueens(int n) {
            resList = new ArrayList<>();
            Nqueens = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(Nqueens[i], FILL);
            }
            len = n;
            backtracking(0);
            return resList;
        }

        private void backtracking(int curRow) {
            //递归终止
            if (curRow == len) {
                //helper
                System.out.println("递归终止--找到一个解:");
                dp_util.print_2D(Nqueens);
                System.out.println();
                //构造解的格式
                List<String> res = new ArrayList<>();
                for (char[] row : Nqueens) {
                    res.add(new String(row));
                }
                resList.add(res);

            } else {
                for (int col = 0; col < len; col++) {
                    int val1 = curRow + col;
                    int val2 = curRow - col;

                    if (cols.contains(col) || line1.contains(val1) || line2.contains(val2)) {
                        continue;
                    }

                    Nqueens[curRow][col] = 'Q';
                    cols.add(col);
                    line1.add(val1);
                    line2.add(val2);

                    backtracking(curRow + 1);

                    Nqueens[curRow][col] = FILL;
                    cols.remove(col);
                    line1.remove(val1);
                    line2.remove(val2);


                }
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
