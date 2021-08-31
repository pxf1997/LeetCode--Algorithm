/**
 * 题目Id：415
 * 题目：字符串相加
 * 日期：2021-05-11 17:30:51
 */
//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 字符串 
// 👍 367 👎 0


package leetcode.editor.cn;

//字符串相加

public class P415_AddStrings {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P415_AddStrings().new Solution();
        String s = solution.addStrings("999", "11");
        System.out.println("s = " + s);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addStrings(String num1, String num2) {
            int maxLen = Math.max(num1.length(), num2.length());
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < maxLen; i++) {
                //从后往前
                int indexA = num1.length() - 1 - i;
                int indexB = num2.length() - 1 - i;
                int a = (indexA >= 0 ? num1.charAt(indexA) - '0' : 0);
                int b = (indexB >= 0 ? num2.charAt(indexB) - '0' : 0);

                int cur = (a + b + carry) % 10;
                carry = (a + b + carry) / 10;
                sb.append(cur);
                //helper
                System.out.println("a=" + a + "  b=" + b + "  cur=" + cur+"  carry=" + carry);
                System.out.println();
            }
            if (carry > 0) {
                sb.append(carry);
            }
            return sb.reverse().toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
