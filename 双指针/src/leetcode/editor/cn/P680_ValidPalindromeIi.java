/**
 * 题目Id：680
 * 题目：验证回文字符串 Ⅱ
 * 日期：2021-03-25 17:14:33
 */
//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 336 👎 0


package leetcode.editor.cn;

//验证回文字符串 Ⅱ

public class P680_ValidPalindromeIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P680_ValidPalindromeIi().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validPalindrome(String s) {
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);//删除j || 删除i
                }

            }
            return true;

        }

        private boolean isPalindrome(String s, int i, int j) {
            //普通判断回文串
/*            for (int a = i, b = j; a < b; a++, b--) {
                if (s.charAt(a) != s.charAt(b)) return false; //没有第二次机会了。。。
            }*/
            while (i < j) {
                if (s.charAt(i++) != s.charAt(j--)) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
