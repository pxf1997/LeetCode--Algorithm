package 测试;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-07-06 9:31
 */
public class KMP_练习 {
    @Test
    public void test1() {
        KMP kmp = new KMP();
        // 测试用例
        int index = kmp.strStr("aabaabaafa", "aabaaf");
        System.out.println("index = " + index);
    }

    class KMP {
        public int strStr(String haystack, String needle) {
            if (needle.length() == 0) return 0;

            // 1--构造next数组
            int[] next = new int[needle.length()];
            next_helper(next, needle);
            System.out.println("next = " + Arrays.toString(next));

            // 2--利用next数组匹配
            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                if (j == needle.length()) { // needle出现范围为[i-j+1,i] 长度为 j=needle.length()
                    return i - j + 1;
                }
            }
            return -1;
        }

        private void next_helper(int[] next, String s) {
            int j = 0;
            next[0] = j;
            for (int i = 1; i < s.length(); i++) {
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1];
                }
                if (s.charAt(i) == s.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
        }
    }

}
