/**
 * 题目Id：28
 * 题目：实现 strStr()
 * 日期：2021-06-23 14:02:20
 */
//实现 strStr() 函数。 
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 104 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 
// 👍 941 👎 0


package leetcode.editor.cn;

//实现 strStr()

import java.util.Arrays;

public class P28_ImplementStrstr_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P28_ImplementStrstr_old().new Solution();
        int index = solution.strStr("aabaabaafa", "aabaaf");
//        int index = solution.strStr("aabaabaafa", "aabaafq");
        System.out.println("index = " + index);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 暴力法 O( (len1-len2) * len2 )
    class Solution1 {
        public int strStr(String ss, String pp) {
            int len1 = ss.length(), len2 = pp.length();
            char[] s = ss.toCharArray(), p = pp.toCharArray();
            // 枚举原串的「发起点」
            for (int i = 0; i <= len1 - len2; i++) {
                // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
                int a = i, b = 0;
                while (b < len2 && s[a] == p[b]) {
                    a++;
                    b++;
                }
                // 如果能够完全匹配，返回原串的「发起点」下标
                if (b == len2) return i;
            }
            return -1;
        }
    }

    // KMP--参考1--不看
    class Solution2 {
        // KMP 算法
        // ss: 原串(string)  pp: 匹配串(pattern)
        public int strStr(String ss, String pp) {
            if (pp.isEmpty()) return 0;

            // 分别读取原串和匹配串的长度
            int n = ss.length(), m = pp.length();
            // 原串和匹配串前面都加空格，使其下标从 1 开始
            ss = " " + ss;
            pp = " " + pp;

            char[] s = ss.toCharArray();
            char[] p = pp.toCharArray();

            // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
            int[] next = new int[m + 1];
            // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
            for (int i = 2, j = 0; i <= m; i++) {
                // 匹配不成功的话，j = next(j)
                while (j > 0 && p[i] != p[j + 1]) j = next[j];
                // 匹配成功的话，先让 j++
                if (p[i] == p[j + 1]) j++;
                // 更新 next[i]，结束本次循环，i++
                next[i] = j;
            }

            // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
            for (int i = 1, j = 0; i <= n; i++) {
                // 匹配不成功 j = next(j)
                while (j > 0 && s[i] != p[j + 1]) j = next[j];
                // 匹配成功的话，先让 j++，结束本次循环后 i++
                if (s[i] == p[j + 1]) j++;
                // 整一段匹配成功，直接返回下标
                if (j == m) return i - m;
            }
            return -1;
        }
    }


    // KMP--参考2--实际使用!!!
    class Solution {
        // 构建模式串的next数组
        // 方法一:前缀表使用减1实现
        public void getNext(int[] next, String s) {
            // j+1--前缀末尾,也代表下标[0,i]范围内的「最长相等前后缀的长度」
            // i--后缀末尾(用for遍历)
            int j = -1;
            next[0] = j;
            for (int i = 1; i < s.length(); i++) {
                // 前后缀不同,j指针回退(一直回退,用while)
                while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                    j = next[j];
                }
                if (s.charAt(i) == s.charAt(j + 1)) {
                    j++;
                }
                next[i] = j;
            }
        }

        // 不做任何处理的前缀数组 例如:"aabaaf"-->[0,1,0,1,2,0]
        public void getNext1(int[] next, String s) {
            // j+1--前缀末尾,也代表下标[0,i]范围内的「最长相等前后缀的长度」
            // i--后缀末尾(用for遍历)
            int j = 0;
            next[0] = j;
            for (int i = 1; i < s.length(); i++) {
                // 前后缀不同,j指针回退(一直回退,用while)
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1];
                }
                if (s.charAt(i) == s.charAt(j)) {
                    // helper--辅助理解!
                    System.out.println("i = " + i + "  j = " + j);
                    System.out.println("相等的前后缀为:" + s.substring(0, j + 1));
                    System.out.println();
                    j++;
                }
                next[i] = j;
            }
        }

        public int strStr(String haystack, String needle) {
            if (needle.length() == 0) {
                return 0;
            }
            int[] next = new int[needle.length()];
            // 构建模式串的next数组
//            getNext_original(next, needle);
            getNext1(next, needle);
            // helper
            System.out.println("haystack = " + haystack);
            System.out.println("needle = " + needle);
            System.out.println("next = " + Arrays.toString(next));

            int j = -1;
            for (int i = 0; i < haystack.length(); i++) {
                while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                    j = next[j];
                }
                if (haystack.charAt(i) == needle.charAt(j + 1)) {
                    j++;
                }
                // 完全匹配
                if (j == needle.length() - 1) {
                    // 返回起始下标,匹配串的范围为[i - needle.length() + 1, i],长度为needle.length()
                    // 公式--[i,j] 共有 j-i+1 个元素
                    return (i - needle.length() + 1);
                }
            }
            return -1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
