/**
 * 题目Id：131
 * 题目：分割回文串
 * 日期：2021-05-08 17:22:10
 */
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 712 👎 0


package leetcode.editor.cn;

//分割回文串

import java.util.ArrayList;
import java.util.List;

public class P131_PalindromePartitioning {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P131_PalindromePartitioning().new Solution();
//        List<List<String>> res = solution.partition("aab");
        List<List<String>> res = solution.partition("aabaa");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int depth;

        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            List<String> path = new ArrayList<>();
            backtracking(s, res, path);
            return res;
        }

        private void backtracking(String s, List<List<String>> res, List<String> path) {
            if (s.length() == 0) {
                System.out.println("结束递归  path = " + path);
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (isPalindrome(s, 0, i)) {
                    path.add(s.substring(0, i + 1));
                    depth++;
                    System.out.println("递归深度：" + depth + "  递归之前 => " + path + "，剩余 = " + s.substring(i + 1));

                    backtracking(s.substring(i + 1), res, path);

                    path.remove(path.size() - 1);
                    depth--;
                    System.out.println("递归深度：" + depth + "  递归之后 => " + path);
                }
            }

        }

        private boolean isPalindrome(String s, int beginIndex, int endIndex) {
            while (beginIndex < endIndex) {
                if (s.charAt(beginIndex++) != s.charAt(endIndex--)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
