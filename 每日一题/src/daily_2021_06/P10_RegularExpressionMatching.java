/**
 * 题目Id：10
 * 题目：正则表达式匹配
 * 日期：2021-06-17 11:09:33
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
// 👍 2181 👎 0


package daily_2021_06;

//正则表达式匹配

import util.dp_util;

public class P10_RegularExpressionMatching {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P10_RegularExpressionMatching().new Solution();
        boolean match = solution.isMatch("bbaccca", "b*ac*a");
        System.out.println("match = " + match);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public boolean isMatch(String s, String p) {
            int len1 = s.length(), len2 = p.length();
            // dp[i][j]--统计到下标i和j处,是否匹配
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            // 第一列除了dp[0][0] 全部为false
            dp[0][0] = true;
            for (int i = 0; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    // '*' 符号
                    if (p.charAt(j - 1) == '*') {
                        // 1--匹配0次
                        dp[i][j] = dp[i][j - 2];
                        if (match_helper(s, p, i, j - 1)) { // dp[i][j-1]
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    } else {
                        if (match_helper(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            dp_util.print_2D(dp);
            return dp[len1][len2];
        }

        private boolean match_helper(String s, String p, int i, int j) {
            if (i == 0) return false; // s是空串
            if (p.charAt(j - 1) == '.') return true;
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }

    // 参考2
    class Solution {
        // 字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）
        public boolean isMatch(String str, String pattern) {
            int len1 = str.length();
            int len2 = pattern.length();
            // dp[i][j] 表示模式串前j个是否与字符串前i个匹配

            boolean[][] dp = new boolean[len1 + 1][len2 + 1];

            // 第一列--模式串为空串,只有dp[0][0]=true
            dp[0][0] = true;
            for (int i = 0; i <= len1; i++) {//实际上模式串和字符串的起点为1(所以后面的下标都是i-1 j-1)
                for (int j = 1; j <= len2; j++) {
                    // 第0列--模式串为空串
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

            dp_util.print_2D(dp);
            return dp[len1][len2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
