/**
 * 题目Id：6
 * 题目：Z 字形变换
 * 日期：2021-06-11 11:21:10
 */
//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 
// 👍 1177 👎 0


package daily_2021_06;

//Z 字形变换

import java.util.ArrayList;
import java.util.List;

import static util.dp_util.print_2D;

public class P6_ZigzagConversion {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P6_ZigzagConversion().new Solution();
        String convert = solution.convert("PAYPALISHIRING", 3);
//        String convert = solution.convert("PAYPALISHIRING", 4);
        System.out.println("convert = " + convert);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 填入board--朴素方法
    class Solution1 {
        int m, n;
        char[][] board;
        char[] chars;
        int idx = 0; // 当前操作chars的下标
        int col = 0; // 当前填到的列号

        public String convert(String s, int numRows) {
            int len = s.length();
            m = numRows;
            n = s.length(); // n设置大一点也没事
            this.board = new char[m][n];
            chars = s.toCharArray();
            while (idx < len) {
                // 1--从上向下填入--操作完毕col++
                fill_down(col);
                col++;
                // helper
                print_2D(board);
                System.out.println();

                // 2--向右上方填入--col++写在循环里了
                fill_upright();
                // helper
                print_2D(board);
                System.out.println();
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != '\u0000') sb.append(board[i][j]);
                    // 这样写不对
//                    if (board[i][j] != ' ') sb.append(board[i][j]);
                }
            }
            return sb.toString();
        }

        // 向右上填写--共 m-2 行/列
        private void fill_upright() {
            // 斜向上的共 m-2 列
            for (int i = 0; i < m - 2 && idx < chars.length; i++) {
                // 倒数第二行(m-2) --> 第二行(1)
                board[m - 2 - i][col] = chars[idx];
                idx++;
                col++;
            }
        }

        // 向下填写
        private void fill_down(int col) {
            for (int i = 0; i < m && idx < chars.length; i++) {
                board[i][col] = chars[idx];
                idx++;
            }
        }
    }

    // 参考答案--flag判断
    class Solution {
        public String convert(String s, int numRows) {
            if (numRows < 2) return s;
            List<StringBuilder> rows = new ArrayList<StringBuilder>();
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }
            int i = 0, flag = -1;
            // i--当前操作字母所在行
            for (char c : s.toCharArray()) {
                rows.get(i).append(c);
                //  在达到 Z 字形转折点时，执行反向。
                if (i == 0 || i == numRows - 1) flag = -flag;
                // 更新行号 (向下i++/向上i--)
                i += flag;
            }
            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows) res.append(row);
            return res.toString();
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
