/**
 * 题目Id：剑指 Offer 43
 * 题目：1～n 整数中 1 出现的次数
 * 日期：2021-06-01 17:31:18
 */
//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 13
//输出：6
//
//
//
// 限制：
//
//
// 1 <= n < 2^31
//
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
// Related Topics 数学
// 👍 182 👎 0


package leetcode.editor.cn;

//1～n 整数中 1 出现的次数

public class 剑指Offer_43_1到n整数中1出现的次数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_43_1到n整数中1出现的次数().new Solution();
        int res = solution.countDigitOne(12345);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 例子看图
        public int countDigitOne(int n) {
            int digit = 1, res = 0;
            int high = n / 10, cur = n % 10, low = 0;
            while (high != 0 || cur != 0) {
                int add = 0;
                if (cur == 0) {
                    add = high * digit;
                    res += high * digit;
                } else if (cur == 1) {
                    add = high * digit + low + 1;
                    res += high * digit + low + 1;
                } else {
                    add = (high + 1) * digit;
                    res += (high + 1) * digit;
                }
                System.out.println("digit = " + digit +
                        "  high = " + high + "  cur = " + cur + "  low = " + low
                        + "  add = " + add);
                System.out.println();

                low += cur * digit;
                cur = high % 10;
                high /= 10;
                digit *= 10;
            }
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
