/**
 * 题目Id：剑指 Offer 16
 * 题目：数值的整数次方
 * 日期：2021-06-16 16:36:55
 */
//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
//
// 
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/ 
// Related Topics 递归 
// 👍 169 👎 0


package leetcode.editor.cn;

//数值的整数次方

public class 剑指Offer_16_数值的整数次方 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_16_数值的整数次方().new Solution();
        double myPow = solution.myPow(2.0, 9);
        System.out.println("myPow = " + myPow);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 朴素法--超时
        public double myPow1(double x, int n) {
            if (n == 0) {
                return 1;
            } else if (n > 0) {
                double res = 1;
                for (int i = 0; i < n; i++) {
                    res *= x;
                }
                return res;
            } else {
                double res = 1;
                for (int i = 0; i < -n; i++) {
                    res *= 1 / x;
                }
                return res;
            }
        }

        // 快速幂
        public double myPow(double x, int n) {
            if (x == 0) return 0;
            long b = n;// 防止越界
            double res = 1.0;
            // 处理负数
            if (b < 0) {
                b = -b;
                x = 1 / x;
            }
            // 底数为--x^1 x^2 x^4 x^8 ...x^(2 ^ i-1)
            // 指数为--b的二进制位b1,b2,b3....
            while (b > 0) {
                if ((b & 1) == 1) res *= x;
                x *= x;
                b >>>= 1;
            }
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
