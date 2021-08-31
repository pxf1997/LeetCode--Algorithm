/**
 * 题目Id：168
 * 题目：Excel表列名称
 * 日期：2021-06-29 10:59:46
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
// Related Topics 数学 字符串 
// 👍 390 👎 0


package daily_2021_07;

//Excel表列名称

public class P168_ExcelSheetColumnTitle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P168_ExcelSheetColumnTitle().new Solution();
//        String s = solution.convertToTitle(26);
//        String s = solution.convertToTitle(701);
//        String s = solution.convertToTitle(28);
        String res = solution.convertToTitle(2147483647);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 分析--没有0
        public String convertToTitle(int columnNumber) {
            StringBuilder sb = new StringBuilder();
            while (columnNumber != 0) {
                char c = (char) ((columnNumber - 1) % 26 + 'A');
                System.out.println("c = " + c);
                sb.append(c);
                columnNumber = (columnNumber - 1) / 26;
            }
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
