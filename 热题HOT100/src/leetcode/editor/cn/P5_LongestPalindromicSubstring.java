/**
 * 题目Id：5
 * 题目：最长回文子串
 * 日期：2021-06-22 18:13:13
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
// 👍 3751 👎 0


package leetcode.editor.cn;

//最长回文子串

import java.util.*;

import static util.dp_util.print_2D;

public class P5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P5_LongestPalindromicSubstring().new Solution();
//        String longestPalindrome = solution.longestPalindrome("babadeeefffqwertytrewq");
        String longestPalindrome = solution.longestPalindrome("ac");
        System.out.println("longestPalindrome = " + longestPalindrome);
    }


    // 暴力搜索,得到所有回文串(长度至少为2) O(n^3)
    class Solution1 {
        List<String> list = new ArrayList<>();

        public String longestPalindrome(String s) {
            char[] chars = s.toCharArray();
            for (int begin = 0; begin < chars.length; begin++) {
                for (int end = begin + 1; end < chars.length; end++) {
                    if (isPalindrome(s, begin, end)) {
                        list.add(s.substring(begin, end + 1));
                    }
                }
            }
            // 按长度定制排序
            Collections.sort(list, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o2.length() - o1.length();
                }
            });
            //System.out.println("list = " + list);

            return list.size() > 0 ? list.get(0) : s.substring(0, 1);
        }

        // 无视奇偶
        private boolean isPalindrome(String s, int begin, int end) {
            while (begin < end) {
                if (s.charAt(begin++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }

    // dp法
    class Solution2 {
        public String longestPalindrome(String s) {
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
            char[] charArray = s.toCharArray();
            // 递推开始
            // 先枚举子串长度(必须从小到大,要用到之前的结果)
            for (int L = 2; L <= len; L++) {
                // 枚举左边界，左边界的上限设置可以宽松一些
                for (int i = 0; i < len; i++) {
                    // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                    // [i,j] 区间共 L 个元素,
                    int j = L + i - 1;
                    // 如果右边界越界，就可以退出当前循环
                    if (j >= len) {
                        break;
                    }
                    if (charArray[i] != charArray[j]) {
                        dp[i][j] = false;
                    } else {
                        // 长度为1或2的串必然为回文串
                        if (j - i < 3) {
                            dp[i][j] = true;
                        }
                        // 状态转移
                        else {
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
            return s.substring(begin, begin + maxLen); // [i,j]
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
            // 记录最大长度,对应的起始下标
            int maxLen = 1, start = 0;
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
