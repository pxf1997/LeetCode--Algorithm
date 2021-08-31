package 最长公共子序列LCS; /**
 * 题目Id：1143
 * 题目：最长公共子序列
 * 日期：2021-05-21 11:05:27
 */
//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 动态规划 
// 👍 553 👎 0


//最长公共子序列

import static util.dp_util.print_2D;

public class P1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1143_LongestCommonSubsequence().new Solution();
//        int longestCommonSubsequence = solution.longestCommonSubsequence("abcde", "ace");
//        int longestCommonSubsequence = solution.longestCommonSubsequence("xxabcdeyy", "zzzabcdewww");
        int longestCommonSubsequence = solution.longestCommonSubsequence("ABCBDAB", "BDCABA");
        System.out.println("longestCommonSubsequence = " + longestCommonSubsequence);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int len1 = text1.length(), len2 = text2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i < len1 + 1; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i < len2 + 1; i++) {
                dp[0][i] = 0;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                        sb.append(text1.charAt(i - 1)); //记录所有斜边--多了
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                }
            }
//            System.out.println("sb = " + sb);
            print_2D(dp);

            return dp[len1][len2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
