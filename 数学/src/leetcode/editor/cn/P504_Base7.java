/**
 * 题目Id：504
 * 题目：七进制数
 * 日期：2021-05-10 18:24:45
 */
//给定一个整数，将其转化为7进制，并以字符串形式输出。 
//
// 示例 1: 
//
// 
//输入: 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: -7
//输出: "-10"
// 
//
// 注意: 输入范围是 [-1e7, 1e7] 。 
// 👍 83 👎 0


package leetcode.editor.cn;

//七进制数

public class P504_Base7 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P504_Base7().new Solution();
        String s = solution.convertToBase7(-7);
        System.out.println("s = " + s);

//        String s = solution.convertToBaseX(256, 2);
//        System.out.println("s = " + s);
//
        String s1 = Integer.toString(-255, 16);
        System.out.println("s1 = " + s1);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToBase7(int num) {
            if (num == 0) {
                return "0";
            }
            boolean isNegative = (num < 0);
            if (isNegative) {
                num = -num;
            }
            StringBuilder str = new StringBuilder();
            while (num > 0) {
                str.append(num % 7);
                num /= 7;
            }
            String res = str.reverse().toString();
            return (isNegative ? "-" + res : res);
        }

        public String convertToBaseX(int num, int x) {
            if (num == 0) {
                return "0";
            }
            boolean isNegative = (num < 0);
            if (isNegative) {
                num = -num;
            }
            StringBuilder str = new StringBuilder();
            while (num > 0) {
                str.append(num % x);
                num /= x;
            }
            String res = str.reverse().toString();
            return (isNegative ? "-" + res : res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
