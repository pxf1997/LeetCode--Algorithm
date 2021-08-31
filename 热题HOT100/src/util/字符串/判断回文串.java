package util.字符串;

/**
 * @author pxf
 * @create 2021-06-22 18:15
 */
public class 判断回文串 {
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
