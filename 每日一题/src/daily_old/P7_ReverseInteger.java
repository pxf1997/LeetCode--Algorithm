package daily_old;

/**
 * 题目Id：7
 * 题目：整数反转
 * 日期：2021-05-03 11:22:14
 */
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2750 👎 0


//整数反转

public class P7_ReverseInteger {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P7_ReverseInteger().new Solution();
//        int reverse = solution.reverse(-123);
//        int reverse = solution.reverse(123);
//        int reverse = solution.reverse(120);
        int reverse = solution.reverse(-9546);
        System.out.println("reverse = " + reverse);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int reverse(int x) {
            String s = Integer.toString(x);
            char a = s.charAt(0);
            if (a == '-') {
                String s1 = s.substring(1);
                s1 = reverse_help(s1);
                return -Integer.parseInt(s1);
            } else {
                String s1 = reverse_help(s);
                return Integer.parseInt(s1);
            }


        }

        private String reverse_help(String s) {
            String res = "";
            for (int i = s.length() - 1; i >= 0; i--) {
                res += s.charAt(i);
            }
            return res;
        }
    }

    class Solution {
        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                //每次取末尾数字
                int temp = x % 10;

                //判断是否 大于 最大32位整数
                if (res > 214748364 || (res == 214748364 && temp > 7)) {
                    return 0;
                }
                //判断是否 小于 最小32位整数
                if (res < -214748364 || (res == -214748364 && temp < -8)) {
                    return 0;
                }
                res = temp + res * 10;
                x /= 10;
            }
            return res;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
