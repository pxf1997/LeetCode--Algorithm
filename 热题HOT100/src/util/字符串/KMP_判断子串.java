package util.字符串;

import org.junit.Test;

import java.util.Arrays;
// 特殊符号--左右角括号  「」

/**
 * @author pxf
 * @create 2021-06-23 15:21
 */
public class KMP_判断子串 {
    @Test
    public void test1() {
        KMP kmp = new KMP();
        // 测试用例
        int index = kmp.strStr("aabaabaafa", "aabaaf");
        System.out.println("index = " + index);
    }

    @Test
    public void test2() {
        KMP kmp = new KMP();
        // 测试--next数组
        String s = "asdfasdfasdf";
        int[] next = new int[s.length()];
        kmp.getNext_original(next, s);
        System.out.println("next = " + Arrays.toString(next));
    }

    class KMP {
        // 构建模式串的 next 数组 ----不做任何处理的前缀数组 例如:"aabaaf"-->[0,1,0,1,2,0]

        /**
         * 不做减一处理,原始的next数组(前缀表)
         */
        public void getNext_original(int[] next, String s) {
            // j--前缀末尾,也代表下标[0,i]范围内的「最长相等前后缀的长度」
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
                    System.out.println("i = " + i + "  j = " + j + "  判断范围:" + s.substring(0, i + 1));
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
            // 1--构建模式串的next数组
            getNext_original(next, needle);
            // helper
            System.out.println("haystack = " + haystack);
            System.out.println("needle = " + needle);
            System.out.println("next = " + Arrays.toString(next));
            System.out.println("---------下面为匹配----------");
            // 2--根据next数组进行判断
            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    System.out.print("j不匹配:" + j);
                    j = next[j - 1];
                    System.out.println("  j回退为:" + j);
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    System.out.print("haystack.charAt(" + i + ") = " + haystack.charAt(i));
                    System.out.println("  needle.charAt(" + j + ") = " + needle.charAt(j));
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
}
