/**
 * 题目Id：剑指 Offer 67
 * 题目：把字符串转换成整数
 * 日期：2021-06-11 15:51:28
 */
//写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。 
//
// 
//
// 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。 
//
// 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连
//续的数字字符组合起来，形成整数。 
//
// 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。 
//
// 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。 
//
// 在任何情况下，若函数不能进行有效的转换时，请返回 0。 
//
// 说明： 
//
// 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231
// − 1) 或 INT_MIN (−231) 。 
//
// 示例 1: 
//
// 输入: "42"
//输出: 42
// 
//
// 示例 2: 
//
// 输入: "   -42"
//输出: -42
//解释: 第一个非空白字符为 '-', 它是一个负号。
//     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
// 
//
// 示例 3: 
//
// 输入: "4193 with words"
//输出: 4193
//解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
// 
//
// 示例 4: 
//
// 输入: "words and 987"
//输出: 0
//解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
//     因此无法执行有效的转换。 
//
// 示例 5: 
//
// 输入: "-91283472332"
//输出: -2147483648
//解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
//     因此返回 INT_MIN (−231) 。
// 
//
// 
//
// 注意：本题与主站 8 题相同：https://leetcode-cn.com/problems/string-to-integer-atoi/ 
// Related Topics 数学 字符串 
// 👍 89 👎 0


package leetcode.editor.cn;

//把字符串转换成整数

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer_67_把字符串转换成整数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_67_把字符串转换成整数().new Solution();
//        int res = solution.strToInt("4193 with words");
//        int res = solution.strToInt(" with words 4193 with words");
//        int res = solution.strToInt("  +9999017897198269798789with words");
        int res = solution.strToInt("9223372036854775808");

        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 常规逻辑
    class Solution1 {
        public int strToInt(String str) {
            char[] chars = str.trim().toCharArray();
            if (chars.length == 0) return 0;
            int res = 0, boundary = Integer.MAX_VALUE / 10;
            // i--记录第一个数字位置  sign--记录正负号
            int i = 1, sign = 1;
            // 第一个符号为负号
            if (chars[0] == '-') sign = -1;
                // 第一个符号为数字
            else if (chars[0] != '+') i = 0;
            // 第一个符号为正号--不处理

            for (int j = i; j < chars.length; j++) {
                // 发现不是数字
                if (chars[j] < '0' || chars[j] > '9') {
                    break;
                }
                // 判断越界
                if (res > boundary || (res == boundary && chars[j] > '7')) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = res * 10 + (chars[j] - '0');
            }
            return res * sign;
        }
    }

    // 有限状态机
    class Solution {
        public int strToInt(String str) {
            Automaton automaton = new Automaton();
            int len = str.length();
            for (int i = 0; i < len; i++) {
                automaton.get(str.charAt(i));
            }
            // 错误版本--直接算出来这个结果,再判断是否越界--不可以!!!运算过程中long也越界了
//            long num_l = automaton.sign * automaton.ans;
//            if (num_l > (long) Integer.MAX_VALUE) return Integer.MAX_VALUE;
//            if (num_l < (long) Integer.MIN_VALUE) return Integer.MIN_VALUE;
//            return (int) num_l;

            //正确版本
            return (int) (automaton.sign * automaton.ans);
        }

        class Automaton {
            int sign = 1;
            long ans = 0;
            String state = "start";
            // 初始化--匿名内部类
            Map<String, String[]> table = new HashMap<>();

            public Automaton() {
                // 表格每一行
                table.put("start", new String[]{"start", "signed", "in_number", "end"});
                table.put("signed", new String[]{"end", "end", "in_number", "end"});
                table.put("in_number", new String[]{"end", "end", "in_number", "end"});
                table.put("end", new String[]{"end", "end", "end", "end"});
            }

            public void get(char c) {
                state = table.get(state)[get_col(c)];
                // 数字计算,判断越界
                if (state.equals("in_number")) {
                    ans = ans * 10 + (c - '0');
                    // 每次都要判断越界
                    ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
                } else if (state.equals("signed")) {
                    sign = (c == '+' ? 1 : -1);
                }
            }

            private int get_col(char c) {
                if (c == ' ') return 0;
                if (c == '+' || c == '-') return 1;
                if (c <= '9' && c >= '0') return 2;
//                if (Character.isDigit(c)) return 2;
                return 3;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
