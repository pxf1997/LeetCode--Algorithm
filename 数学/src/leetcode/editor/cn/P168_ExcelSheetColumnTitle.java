/**
 * 题目Id：168
 * 题目：Excel表列名称
 * 日期：2021-05-11 15:33:40
 */
//给定一个正整数，返回它在 Excel 表中相对应的列名称。 
//
// 例如， 
//
//     1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB 
//    ...
// 
//
// 示例 1: 
//
// 输入: 1
//输出: "A"
// 
//
// 示例 2: 
//
// 输入: 28
//输出: "AB"
// 
//
// 示例 3: 
//
// 输入: 701
//输出: "ZY"
// 
// Related Topics 数学 
// 👍 343 👎 0


package leetcode.editor.cn;

//Excel表列名称

public class P168_ExcelSheetColumnTitle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P168_ExcelSheetColumnTitle().new Solution();
//        String res = solution.convertToTitle(26);
//        String res = solution.convertToTitle(28);
        String res = solution.convertToTitle(701);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 先明确到底是多少进制  1-A   26-Z  27--AA
        // 1-26共26个字符，因此为26进制，而非27进制

        // 因为是从 1 开始计算的，而不是从 0 开始，因此需要对 n 执行 -1 操作。
        public String convertToTitle(int columnNumber) {
            StringBuilder sb = new StringBuilder();
            while (columnNumber > 0) {
                int index = (columnNumber - 1) % 26;
                char c = (char) ('A' + index);
                sb.append(c);
                columnNumber = (columnNumber - 1) / 26;
            }
            return sb.reverse().toString();

        }
        public String convertToTitle2(int n) {
            if (n == 0) {
                return "";
            }
            n--;
            return convertToTitle(n / 26) + (char) (n % 26 + 'A');
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
