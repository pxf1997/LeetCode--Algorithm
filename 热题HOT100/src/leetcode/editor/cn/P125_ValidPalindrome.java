/**
 * 题目Id：125
 * 题目：验证回文串
 * 日期：2021-08-25 12:30:33
 */
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 
//
// 示例 1: 
//
// 
//输入: "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
// 
//
// 示例 2: 
//
// 
//输入: "race a car"
//输出: false
//解释："raceacar" 不是回文串
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// 字符串 s 由 ASCII 字符组成 
// 
// Related Topics 双指针 字符串 👍 405 👎 0


package leetcode.editor.cn;

//验证回文串

import javax.xml.stream.events.Characters;

public class P125_ValidPalindrome {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P125_ValidPalindrome().new Solution();
        String s = "A man, a plan, a canal: Panama";
        boolean res = solution.isPalindrome(s);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 评价--这题考的是数据预处理
    class Solution {
        public boolean isPalindrome(String s) {
            String s1 = helper(s);
//            System.out.println("s1 = " + s1);
            int left = 0, right = s1.length() - 1;
            while (left < right) {
                if (s1.charAt(left++) != s1.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }

        private String helper(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                // 只考虑字母和数字字符
                if(Character.isLetterOrDigit(c)){
                    sb.append(Character.toLowerCase(c));
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
