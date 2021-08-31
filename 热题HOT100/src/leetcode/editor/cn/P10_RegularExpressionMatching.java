/**
 * 题目Id：10
 * 题目：正则表达式匹配
 * 日期：2021-06-23 10:54:28
 */
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 字符串 动态规划 回溯算法 
// 👍 2194 👎 0


package leetcode.editor.cn;

//正则表达式匹配

import util.dp_util;

public class P10_RegularExpressionMatching {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P10_RegularExpressionMatching().new Solution();
//        boolean match = solution.isMatch("bbaccca", "b*ac*a");
        boolean match = solution.isMatch("bba", "*bba"); // 特例--'*'前无有效匹配,题目中不会出现此情况!!!
        System.out.println("match = " + match);
    }


    class Solution {
        public boolean isMatch(String s, String p) {
            int len1 = s.length(), len2 = p.length();
            // dp[i][j]--统计到 s下标i-1,p下标j-1处,是否匹配
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            // 初始值--第一列除dp[0][0]以外,dp[i][0]均为false
            dp[0][0] = true;
            for (int i = 0; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    // dp[i][j]对应字符下标--i-1,j-1
                    // 1--是'*'
                    if (p.charAt(j - 1) == '*') {
                        // ①匹配0次
                        if (j >= 2) {
                            dp[i][j] = dp[i][j - 2];
                        }
                        // ②匹配若干次 j-2是'*'前的字符
                        if (i > 0 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                    // 2--不是'*'
                    else {
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            dp_util.print_2D(dp);
            return dp[len1][len2];
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 相同功能抽取
    // 注意题目描述--保证每次出现字符*时，前面都匹配到有效的字符
    class Solution2 {
        public boolean isMatch(String s, String p) {
            int len1 = s.length(), len2 = p.length();
            // dp[i][j]--统计到 s下标i-1,p下标j-1处,是否匹配
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            // 初始值--第一列除dp[0][0]以外,dp[i][0]均为false
            dp[0][0] = true;
            for (int i = 0; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    // dp[i][j]对应字符下标--i-1,j-1
                    if (p.charAt(j - 1) == '*') {
                        // case 1 可以直接忽略*前模式的那个元素（*代表出现0次 比如a* 这两个元素做空字符串）
                        dp[i][j] = dp[i][j - 2]; // j=1不是越界了吗???
                        // case 2 如果dp[i][j-2]不等于true 那么要满足第j-1个字符(这个字符也可以为‘.’)与第i个字符匹配即可
                        if (match_helper(s, p, i, j - 1)) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    } else {
                        if (match_helper(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            //dp_util.print_2D(dp);
            return dp[len1][len2];
        }

        private boolean match_helper(String s, String p, int i, int j) {
            if (i == 0) return false;
            if (p.charAt(j - 1) == '.') return true;
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
