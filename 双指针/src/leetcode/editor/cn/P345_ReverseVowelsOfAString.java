/**
 * 题目Id：345
 * 题目：反转字符串中的元音字母
 * 日期：2021-03-25 17:11:45
 */
//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 
// 👍 148 👎 0


package leetcode.editor.cn;

//反转字符串中的元音字母

import java.util.Arrays;
import java.util.HashSet;

public class P345_ReverseVowelsOfAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P345_ReverseVowelsOfAString().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final HashSet<Character> vowels = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        );

        public String reverseVowels(String s) {
            if (s == null) return null;
            int i = 0, j = s.length() - 1;
            char[] result = new char[s.length()];
            //逻辑复杂了
           /* while (i <= j) {
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if (vowels.contains(ci) && vowels.contains(cj)) {
                    result[i] = cj;
                    i++;
                    result[j] = ci;
                    j--;
                } else if (vowels.contains(ci) && !vowels.contains(cj)) {
                    result[j] = cj;
                    j--;
                } else if (!vowels.contains(ci) && vowels.contains(cj)) {
                    result[i] = ci;
                    i++;
                } else {
                    result[i] = ci;
                    i++;
                    result[j] = cj;
                    j--;
                }
            }*/

            //逻辑优化
            while (i <= j) {
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if (!vowels.contains(ci)) { // i指针往右走，停在第一个元音字母
                    result[i++] = ci;
                } else if (!vowels.contains(cj)) { // j指针往左走，停在第一个元音字母
                    result[j--] = cj;
                } else { // 定位好后，交换
                    result[i++] = cj;
                    result[j--] = ci;
                }
            }
            return new String(result);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
