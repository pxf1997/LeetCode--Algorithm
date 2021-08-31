/**
 * 题目Id：171
 * 题目：Excel表列序号
 * 日期：2021-07-30 09:41:36
 */
//给定一个Excel表格中的列名称，返回其相应的列序号。 
//
// 例如， 
//
//     A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
// 
//
// 示例 1: 
//
// 输入: "A"
//输出: 1
// 
//
// 示例 2: 
//
// 输入: "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 输入: "ZY"
//输出: 701 
//
// 致谢： 
//特别感谢 @ts 添加此问题并创建所有测试用例。 
// Related Topics 数学 字符串 
// 👍 257 👎 0


package daily_2021_07;

//Excel表列序号

public class P171_ExcelSheetColumnNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P171_ExcelSheetColumnNumber().new Solution();
//        int res = solution.titleToNumber("ZY");
        int res = solution.titleToNumber("FXSHRXW");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--P168题的逆向
    class Solution {
        public int titleToNumber(String columnTitle) {
            int base = 1, res = 0;
            for (int i = columnTitle.length() - 1; i >= 0; i--) {
                int index = columnTitle.charAt(i) - 'A' + 1;
                System.out.println(columnTitle.charAt(i) + " : " + index);
                res += index * base;
                base *= 26;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
