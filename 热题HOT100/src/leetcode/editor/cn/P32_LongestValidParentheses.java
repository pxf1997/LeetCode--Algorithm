/**
 * 题目Id：32
 * 题目：最长有效括号
 * 日期：2021-08-06 10:15:14
 */
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1396 👎 0


package leetcode.editor.cn;

//最长有效括号

import java.util.Stack;

public class P32_LongestValidParentheses {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P32_LongestValidParentheses().new Solution();
        int res = solution.longestValidParentheses(")()())");
        System.out.println("res = " + res);
    }


    // dp法
    class Solution_dp {
        public int longestValidParentheses(String s) {
            int res = 0;
            int len = s.length();
            // dp[i]--以下标i结尾的最长有效括号的长度
            int[] dp = new int[len];
            for (int i = 1; i < len; i++) {
                // '(' 结尾对应dp值为0
                if (s.charAt(i) == ')') {
                    // case1 下标i为')' ,下标i-1为'(' 形如".....()"  dp[i]=dp[i-2]
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    }
                    // case2 下标i为')' ,下标i-1为')' 形如".....))"
                    // 很复杂----dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
                    else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    res = Math.max(res, dp[i]);
                }
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 栈
    class Solution {
        public int longestValidParentheses(String s) {
            int max = 0;
            Stack<Integer> stack = new Stack<Integer>();//栈中存储下标
            // 技巧:放入-1
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                // 左括号入栈
                if (s.charAt(i) == '(') {
                    stack.push(i);
                }
                // 右括号
                else {
                    stack.pop();
                    // 栈空,当前右括号没有被匹配的右括号,入栈
                    // 最后一个没有被匹配的右括号的下标
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }
    }
//    分析----栈的这个方法虽然能理解但是很反直觉
//    1--遇到右括号竟然不是先判断栈内有没有元素，而是先弹栈再判断（原因是事先入栈了一个分隔元素）
//    2--竟然是边入栈边更新长度的，而不是碰到下一个分隔符后，计算两个分隔符之间的差距
//    3--每次更新的长度是最近一对括号的长度，而不是一组连续有效括号的长度
//    知道有没有人跟我有一样的困惑
//leetcode submit region end(Prohibit modification and deletion)

}
