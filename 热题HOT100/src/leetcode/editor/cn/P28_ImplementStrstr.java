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

public class P28_ImplementStrstr {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P28_ImplementStrstr().new Solution();
//        int index = solution.strStr("acbacbacfa", "acbacf");
        int index = solution.strStr("aabaabaafa", "aabaaf");
//        int index = solution.strStr("aabaabaafa", "aabaafq");
        System.out.println("index = " + index);
    }

    // 暴力法 O( (len1-len2) * len2 )
    // KMP--实际使用!!!
    class Solution {
        // 构建模式串的 next 数组 ----不做任何处理的前缀数组 例如:"aabaaf"-->[0,1,0,1,2,0]
        public void getNext_original(int[] next, String s) {
            // j--前缀末尾,也代表下标[0,i]范围内的「最长相等前后缀的长度」
            // i--后缀末尾(用for遍历)
            int j = 0;
            next[0] = j;
            for (int i = 1; i < s.length(); i++) {
                // 前后缀不同,j指针回退(一直回退,用while)
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1]; // 注意这里，找前一位的对应的回退位置
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
            getNext_original(next, needle);
            // helper
            System.out.println("haystack = " + haystack);
            System.out.println("needle = " + needle);
            System.out.println("next = " + Arrays.toString(next));
            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                // 完全匹配
                if (j == needle.length()) {
                    // 返回起始下标,匹配串的范围为[i - needle.length() + 1, i],长度为needle.length()
                    // 公式--[i,j] 共有 j-i+1 个元素
                    return (i - needle.length() + 1);
                }
            }
            return -1;
        }
    }


    // 自己练习
    /*class Solution2 {
        public int strStr(String haystack, String needle) {
            int len1 = haystack.length(), len2 = needle.length();
            if (len2 == 0) return 0;
            int[] next = new int[len2];
            getNext(next, needle);
            // helper
            System.out.println("haystack = " + haystack);
            System.out.println("needle = " + needle);
            System.out.println("next = " + Arrays.toString(next));
            int j = 0;
            for (int i = 0; i < len1; i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                // 完全匹配
                if (j == needle.length()) {
                    // 范围[i,j]共j-i+1个元素,例如[2,6]共5个
                    // 范围[i-len2+1, i],共len2个
                    return (i - len2 + 1);
                }
            }
            return -1;
        }

        private void getNext(int[] next, String s) {
            // j--前缀末尾,也代表下标[0,i]范围内的「最长相等前后缀的长度」
            // i--后缀末尾(for循环遍历中的变量)
            int j = 0;
            next[0] = j;
            for (int i = 1; i < s.length(); i++) {
                // 前后缀不同,j指针回退(一直回退,用while)
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1];  // 注意这里，找前一位的对应的回退位置
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
    }*/


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 写在一起
    class Solution2 {
        public int strStr(String haystack, String needle) {
            int len1 = haystack.length(), len2 = needle.length();
            if (len2 == 0) return 0;
            int[] next = new int[len2];
            // 构建next数组
            for (int i = 1, j = 0; i < needle.length(); i++) {
                // 前后缀不同,j指针回退(一直回退,用while)
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];  // 注意这里，找前一位的对应的回退位置
                }
                if (needle.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
             System.out.println("next = " + Arrays.toString(next));
            for (int i = 0, j = 0; i < len1; i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                // 完全匹配
                if (j == needle.length()) {
                    return i - len2 + 1;
                }
            }
            return -1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
