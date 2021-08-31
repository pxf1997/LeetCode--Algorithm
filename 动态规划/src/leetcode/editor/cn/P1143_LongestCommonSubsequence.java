/**
 * 题目Id：1143
 * 题目：最长公共子序列
 * 日期：2021-04-22 10:01:11
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
// 👍 528 👎 0


package leetcode.editor.cn;

//最长公共子序列

public class P1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1143_LongestCommonSubsequence().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度
//        注意字符串下标问题
        public int longestCommonSubsequence1(String text1, String text2) {
            int[][] dp = new int[text1.length()][text2.length()];
            dp[0][0] = (text1.charAt(0) == text2.charAt(0)) ? 1 : 0;
//            第一列
            for (int i = 1; i < text1.length(); i++) {
                dp[i][0] = (text1.charAt(i) == text2.charAt(0)) ? 1 : dp[i - 1][0];
            }
//            第一行
            for (int i = 1; i < text2.length(); i++) {
                dp[0][i] = (text1.charAt(0) == text2.charAt(i)) ? 1 : dp[0][i - 1];
            }

            for (int i = 1; i < text1.length(); i++) {
                for (int j = 1; j < text2.length(); j++) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[text1.length() - 1][text2.length() - 1];


        }

//        dp长度弄成 len+1 的原理--边界处理方便，这是提高技巧了
        public int longestCommonSubsequence(String text1, String text2) {
            int n1 = text1.length(), n2 = text2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];
            // i j 下标范围和对应字符串  看题解的图很清晰
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[n1][n2];

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
