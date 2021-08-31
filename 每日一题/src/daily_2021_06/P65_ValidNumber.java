/**
 * 题目Id：65
 * 题目：有效数字
 * 日期：2021-06-17 11:06:02
 */
//有效数字（按顺序）可以分成以下几个部分： 
//
// 
// 一个 小数 或者 整数 
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数 
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分有效数字列举如下： 
//
// 
// ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
// "53.5e93", "-123.456e789"] 
// 
//
// 部分无效数字列举如下： 
//
// 
// ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"] 
// 
//
// 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = ".1"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。 
// 
// Related Topics 数学 字符串 
// 👍 216 👎 0


package daily_2021_06;

//有效数字

import java.util.HashMap;
import java.util.Map;

public class P65_ValidNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P65_ValidNumber().new Solution();
        boolean number = solution.isNumber("-123.456e789");
        System.out.println("number = " + number);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
// 参考答案--有限状态机
    class Solution {
        public boolean isNumber(String s) {
            // 双括号初始化--{{ }}
            Map[] states = {
                    new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                    new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                    new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                    new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                    new HashMap<>() {{ put('d', 3); }},                                        // 4.
                    new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                    new HashMap<>() {{ put('d', 7); }},                                        // 6.
                    new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                    new HashMap<>() {{ put(' ', 8); }}                                         // 8.
            };
            int p = 0;
            char t;
            System.out.println("起始状态 p = " + p);
            for(char c : s.toCharArray()) {
                if(c >= '0' && c <= '9') t = 'd';
                else if(c == '+' || c == '-') t = 's';
                else if(c == 'e' || c == 'E') t = 'e';
                else if(c == '.' || c == ' ') t = c;
                else t = '?';
                if(!states[p].containsKey(t)) return false;
                p = (int)states[p].get(t);
                // helper
                 System.out.println("c = " + c + "  t = " + t + "  p = " + p);
            }
            return p == 2 || p == 3 || p == 7 || p == 8;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
