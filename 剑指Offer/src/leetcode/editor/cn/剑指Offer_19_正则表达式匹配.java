/**
 * 题目Id：剑指 Offer 19
 * 题目：正则表达式匹配
 * 日期：2021-06-07 10:12:53
 */
//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3:
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
// 示例 4:
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//
//
// 示例 5:
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
//
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
//
// Related Topics 动态规划
// 👍 233 👎 0


package leetcode.editor.cn;

//正则表达式匹配

import util.dp_util;

import static util.dp_util.print_2D;

public class 剑指Offer_19_正则表达式匹配 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_19_正则表达式匹配().new Solution();
//        boolean match = solution.isMatch("aaa", "ab*ac*a");
        boolean match = solution.isMatch("bbaccca", "b*ac*a");
//        boolean match = solution.isMatch("bbccc", "b*c*");
//        boolean match = solution.isMatch("aa", "a*");
        System.out.println("match = " + match);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考1
    class Solution {
        public boolean isMatch(String s, String p) {
            int len1 = s.length(), len2 = p.length();
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            dp[0][0] = true; // 第0列 除了dp[0][0] 其余全为false
            for (int i = 0; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (p.charAt(j - 1) == '*') {
                        // case 1 可以直接忽略*前模式的那个元素（*代表出现0次 比如a* 这两个元素做空字符串）
                        dp[i][j] = dp[i][j - 2];
                        // case 2 如果dp[i][j-2]不等于true 那么要满足第j-1个字符(这个字符也可以为‘.’)与第i个字符匹配即可
                        if (match_helper(s, p, i, j - 1)) {
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    } else {// p中的第j个位置不为'*'
                        if (match_helper(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            print_2D(dp);
            return dp[len1][len2];

        }

        // s中第i个元素和p中第j个元素的匹配情况
        private boolean match_helper(String s, String p, int i, int j) {
            if (i == 0) return false;
            if (p.charAt(j - 1) == '.') return true;
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }

    // 参考2
    class Solution2 {
        // 字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）
        public boolean isMatch(String str, String pattern) {
            int len1 = str.length();
            int len2 = pattern.length();
            // dp[i][j] 表示模式串前j个是否与字符串前i个匹配
            // dp[0][0] 用于存放两个空字符串的结果
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            for (int i = 0; i <= len1; i++) {//实际上模式串和字符串的起点为1(所以后面的下标都是i-1 j-1)
                for (int j = 0; j <= len2; j++) {
                    // 第0列--模式串为空串
                    if (j == 0) {
                        dp[i][j] = (i == 0);//只有字符串和模式串都为空的时候才匹配，当模式串为空，字符串不为空则返回false
                    } else {
                        if (pattern.charAt(j - 1) != '*') {//如果第j-1个字符不是*
                            if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                                //正常匹配
                                dp[i][j] = dp[i - 1][j - 1];
                            }
                        } else {
                            // 如果第j个是* 那么分两种情况，有一种成立即可
                            // case 1 可以直接忽略*前模式的那个元素（*代表出现0次 比如a* 这两个元素做空字符串）
                            // 那么dp[i][j]==true 只需满足 dp[i][j-2]==true即可
                            if (j >= 2) {
                                dp[i][j] = dp[i][j - 2];
                            }
                            // case 2 如果dp[i][j-2]不等于true 那么要满足第j-1个字符(这个字符也可以为‘.’)与第i个字符匹配即可
                            // 下标多减1是因为dp是从1开始记录的
                            if (i > 0 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                                dp[i][j] |= dp[i - 1][j];//使用或等于 两种情况有一种符合就行
                            }
                        }
                    }
                }
            }
            dp_util.print_2D(dp);
            return dp[len1][len2];
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
