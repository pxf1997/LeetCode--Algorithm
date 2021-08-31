/**
 * 题目Id：20
 * 题目：有效的括号
 * 日期：2021-06-24 11:21:49
 */
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2464 👎 0


package leetcode.editor.cn;

//有效的括号

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P20_ValidParentheses {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P20_ValidParentheses().new Solution();
//        boolean valid = solution.isValid("{()[]}");
        boolean valid = solution.isValid("()");
        System.out.println("valid = " + valid);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 栈
        public boolean isValid(String s) {
            int len = s.length();
            if (len % 2 == 1) return false;
            // key--右括号, val--左括号
            Map<Character, Character> map = new HashMap<>() {{
                put('}', '{');
                put(')', '(');
                put(']', '[');
            }};
            // 栈--左括号入栈,右括号出栈
            Stack<Character> stack = new Stack<>();
            for (Character c : s.toCharArray()) {
                if (map.containsKey(c)) { // 右括号--出栈
                    if (stack.isEmpty() || stack.peek() != map.get(c)) {
                        return false;
                    } else{
                        stack.pop();
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
