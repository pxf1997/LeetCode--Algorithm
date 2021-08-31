/**
 * 题目Id：22
 * 题目：括号生成
 * 日期：2021-07-01 17:57:29
 */
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1854 👎 0


package leetcode.editor.cn;

//括号生成

import java.util.ArrayList;
import java.util.List;

public class P22_GenerateParentheses {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P22_GenerateParentheses().new Solution();
        List<String> res = solution.generateParenthesis(3);
//        List<String> res = solution.generateParenthesis(8);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 递归而非回溯
    class Solution {
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            generate_helper("", n, n);
            return res;
        }

        /**
         * @param s     当前字符串
         * @param left  剩余左括号数量
         * @param right 剩余右括号数量
         */
        private void generate_helper(String s, int left, int right) {
            if (left == 0 && right == 0) {
                System.out.println("递归结束:" + s);
                res.add(s);
                return;
            }
            // 左右括号有剩余----
            // 1--left==right数量相等,下一个必须添加'(
            // 2--left<right数量相等,下一个添加 '(' 或 ')' 均可
            if (left == right) {
                generate_helper(s + "(", left - 1, right);
            } else if (left < right) {
                // 此处需加上,left还有剩余
                if (left > 0) {
                    generate_helper(s + "(", left - 1, right);
                }
                generate_helper(s + ")", left, right - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
