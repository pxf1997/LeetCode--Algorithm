/**
 * 题目Id：344
 * 题目：反转字符串
 * 日期：2021-06-24 15:34:33
 */
//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。 
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。 
//
// 
//
// 示例 1： 
//
// 输入：["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
// 
//
// 示例 2： 
//
// 输入：["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"] 
// Related Topics 递归 双指针 字符串 
// 👍 417 👎 0


package leetcode.editor.cn;

//反转字符串

import java.util.Arrays;

public class P344_ReverseString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P344_ReverseString().new Solution();
        solution.reverseString(new char[]{'h', 'e', 'l', 'l', '0'});
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 要求--原地修改
        public void reverseString(char[] s) {
//            System.out.println("s = " + Arrays.toString(s));
            int len = s.length;
            int left = 0, right = len - 1;
            while (left < right) {
                char temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
//            System.out.println("s = " + Arrays.toString(s));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
