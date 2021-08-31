package daily_2021_05; /**
 * 题目Id：5
 * 题目：最长回文子串
 * 日期：2021-05-21 16:51:42
 */
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3660 👎 0


//最长回文子串

import java.util.Arrays;

import static util.dp_util.print_2D;

public class P5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P5_LongestPalindromicSubstring().new Solution();
//        String longestPalindrome = solution.longestPalindrome("abcba");
//        String longestPalindrome = solution.longestPalindrome("babad");
//        String longestPalindrome = solution.longestPalindrome("ccd");
        String longestPalindrome = solution.longestPalindrome("eabcb");

        System.out.println("longestPalindrome = " + longestPalindrome);
    }


    // 朴素想法--双指针+不断判断回文串----逻辑漏洞!
    class Solution_wrong {
        // 考虑 "eabcb" 回文串为bcb 双指针取不到这个情况!!!
        public String longestPalindrome(String s) {
            if (s.length() == 1 || s.length() == 0) {
                return s;
            }
            int lo = 0, hi = s.length() - 1;
            while (lo < hi) {
                if (isPalindrome(s, lo, hi)) {
                    System.out.println("下标范围 [" + lo + "," + hi + "]是回文串");
                    return s.substring(lo, hi + 1);
                } else if (isPalindrome(s, lo + 1, hi)) {
                    System.out.println("下标范围 [" + (lo + 1) + "," + hi + "]是回文串");
                    return s.substring(lo + 1, hi + 1);
                } else if (isPalindrome(s, lo, hi - 1)) {
                    System.out.println("下标范围 [" + lo + "," + (hi - 1) + "]是回文串");
                    return s.substring(lo, hi);
                }
                lo++;
                hi--;
            }
            return null;
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


    // dp法
    class Solution2 {
        public String longestPalindrome(String s) {
            char[] charArray = s.toCharArray();
            int len = s.length();
            if (len < 2) {
                return s;
            }
            int maxLen = 1;
            int begin = 0;
            // dp[i][j] 表示 s[i..j] 是否是回文串
            boolean[][] dp = new boolean[len][len];
            // 初始化：所有长度为 1 的子串都是回文串
            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }
            // 递推开始
            // 先枚举子串长度
            for (int L = 2; L <= len; L++) {
                // 枚举左边界，左边界的上限设置可以宽松一些
                for (int i = 0; i < len; i++) {
                    // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                    int j = L + i - 1;
                    // 如果右边界越界，就可以退出当前循环
                    if (j >= len) {
                        break;
                    }
                    if (charArray[i] != charArray[j]) {
                        dp[i][j] = false;
                    } else {
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            print_2D(dp);
            return s.substring(begin, begin + maxLen);
        }

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 重新练习
    class Solution {
        public String longestPalindrome(String s) {
            char[] chars = s.toCharArray();
            int len = chars.length;
            if (len < 2) return s;
            // dp[i][j]--下标范围[i,j]是否是回文串
            boolean[][] dp = new boolean[len][len];
            // 初始化：所有长度为 1 的子串都是回文串
            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }
            int maxLen = 0, start = 0;
            // 回文串长度[2,len]
            for (int L = 2; L <= len; L++) {
                // 可行起点i,终点j 该串范围[i,j]
                for (int i = 0; i < len; i++) {
                    int j = i + L - 1;
                    // 右边界越界--跳出当前循环
                    if (j >= len) break;
                    // 更新dp[i][j]
                    if (chars[i] != chars[j]) dp[i][j] = false;
                    else {
                        if (L <= 3) { // 举例--a, aa, aba
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    // dp[i][j]==true 更新全局最大长度及起点
                    if (dp[i][j] && L > maxLen) {
                        maxLen = L;
                        start = i;
                    }
                }
            }
            // helper
//            print_2D(dp);
//            System.out.println("chars = " + Arrays.toString(chars));
//            System.out.println("maxLen = " + maxLen + "  start = " + start);
            return s.substring(start, start + maxLen); // 推算--[2,6]长度为5,即[2, 2+5)
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
