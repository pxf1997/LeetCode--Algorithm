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
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class P22_GenerateParentheses_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P22_GenerateParentheses_old().new Solution();
        List<String> res = solution.generateParenthesis(3);
//        List<String> res = solution.generateParenthesis(8);
        System.out.println("res = " + res);
    }


    // 思路----暴力法O(2^2n * n)
    // 1--n对括号,造出长度为2n的候选集
    // 2--对候选集求排列,注意是有相同元素的排列
    // 3--得到一种排列后用栈判断括号是否合法
    class Solution1 {
        List<String> res = new ArrayList<String>();
        Stack<Character> stack = new Stack<Character>();//判断是否合法

        public List<String> generateParenthesis(int n) {
            List<Character> path = new ArrayList<>();
            char[] candidates = new char[2 * n];
            for (int i = 0; i < n; i++) {
                candidates[i] = '(';
                candidates[i + n] = ')';
            }
            System.out.println("candidates = " + Arrays.toString(candidates));
            // 排列
            boolean[] visited = new boolean[2 * n];
            backtracking(candidates, visited, path);
            return res;
        }

        private void backtracking(char[] candidates, boolean[] visited, List<Character> path) {
            if (path.size() == candidates.length) {
                if (isValid(path)) {
                    System.out.println("合法解:" + path);
                    res.add(make_String_helper(path));
                }
            }
            for (int i = 0; i < candidates.length; i++) {
                if (visited[i]) continue;
                if (i != 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) continue;

                path.add(candidates[i]);
                visited[i] = true;
                System.out.println("递归之前 => " + path);

                backtracking(candidates, visited, path);

                path.remove(path.size() - 1);
                visited[i] = false;
                System.out.println("递归之后 => " + path);
            }
        }

        // 用栈判断括号是合法的
        private boolean isValid(List<Character> path) {
            Stack<Character> stack = new Stack<>();
            for (Character c : path) {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }

        public String make_String_helper(List<Character> path) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            return sb.toString();
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 看不懂题解，这个题有这么复杂吗？
    // 有规律啊，剩余左括号总数要小于等于右括号。 递归把所有符合要求的加上去就行了：
    class Solution {
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            if (n <= 0) {
                return res;
            }
            getParenthesis("", n, n);
            return res;
        }

        private void getParenthesis(String str, int left, int right) {
            if (left == 0 && right == 0) {
                res.add(str);
                return;
            }
            if (left == right) {
                //剩余左右括号数相等，下一个只能用左括号
                getParenthesis(str + "(", left - 1, right);
            } else if (left < right) {
                //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
                if (left > 0) {
                    getParenthesis(str + "(", left - 1, right);
                }
                getParenthesis(str + ")", left, right - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
