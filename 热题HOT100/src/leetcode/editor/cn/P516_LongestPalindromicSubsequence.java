/**
 * 题目Id：516
 * 题目：最长回文子序列
 * 日期：2021-08-12 11:23:20
 */
//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 536 👎 0


package leetcode.editor.cn;

//最长回文子序列

import util.dp_util;

public class P516_LongestPalindromicSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P516_LongestPalindromicSubsequence().new Solution();
        int res = solution.longestPalindromeSubseq("bbbab");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int len = s.length();
            // dp[i][j]--下标[i,j]范围内的最长回文子序列长度
            int[][] dp = new int[len][len];

            // 遍历顺序的逻辑--后算出来的结果需要用到之前的结论
            // 外层--从后向前遍历
            for (int i = len - 1; i >= 0; i--) {
                dp[i][i] = 1; // 长度为1都是回文子序列
                char c1 = s.charAt(i);
                // 内层--从[i+1, len-1]
                for (int j = i + 1; j < len; j++) {
                    char c2 = s.charAt(j);
                    if (c1 == c2) dp[i][j] = dp[i + 1][j - 1] + 2;
                    else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
            //dp_util.print_2D(dp);
            return dp[0][len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
