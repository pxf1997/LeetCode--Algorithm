/**
 * 题目Id：剑指 Offer 58 - II
 * 题目：左旋转字符串
 * 日期：2021-06-11 15:31:23
 */
//字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数
//将返回左旋转两位得到的结果"cdefgab"。 
//
// 
//
// 示例 1： 
//
// 输入: s = "abcdefg", k = 2
//输出: "cdefgab"
// 
//
// 示例 2： 
//
// 输入: s = "lrloseumgh", k = 6
//输出: "umghlrlose"
// 
//
// 
//
// 限制： 
//
// 
// 1 <= k < s.length <= 10000 
// 
// Related Topics 字符串 
// 👍 125 👎 0


package leetcode.editor.cn;

//左旋转字符串

public class 剑指Offer_58_II_左旋转字符串 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_58_II_左旋转字符串().new Solution();
        String res = solution.reverseLeftWords("abcdefg", 9999);
        System.out.println("res = " + res);
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    // 注意限制: 1 <= k < s.length <= 10000
    class Solution {
        public String reverseLeftWords(String s, int n) {
            String helper = s + s;
            return helper.substring(n % s.length(), n % s.length() + s.length());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
