/**
 * 题目Id：剑指 Offer 44
 * 题目：数字序列中某一位的数字
 * 日期：2021-06-17 16:58:00
 */
//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 
// 👍 137 👎 0


package leetcode.editor.cn;

//数字序列中某一位的数字

public class 剑指Offer_44_数字序列中某一位的数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_44_数字序列中某一位的数字().new Solution();
//        int nthDigit = solution.findNthDigit(11);
//        int nthDigit = solution.findNthDigit(15);
        int nthDigit = solution.findNthDigit(25);
        System.out.println("nthDigit = " + nthDigit);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 暴力法--O(n)
    // n过大的话,sb串必然过长(超出内存限制)
    class Solution1 {
        public int findNthDigit(int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= n; i++) {
                sb.append(i);
            }
            System.out.println("sb = " + sb);
            return sb.charAt(n) - '0';
        }
    }


/* 数字范围    数量    位数     占多少位
    1-9        9      1       9
    10-99      90     2       180
    100-999    900    3       2700
    1000-9999  9000   4       36000  ...

    例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;
    数据为 = 1000 + (12 - 1)/ 4  = 1000 + 2 = 1002
    定位1002中的位置 = (n - 1) %  4 = 3    s.charAt(3) = 2;
*/
    class Solution {
        public int findNthDigit(int n) {
            int digit = 1;
            long start = 1;
            long count = 9;
            // 1--确定所求数位的所在数字(num)的位数(digit)
            while (n > count) { // 1.
                n -= count;
                digit += 1; // n所在数字的位数: 1, 2, 3
                start *= 10;// 数字范围开始的第一个数: 1, 10, 100
                count = digit * start * 9;// 占多少位: 9, 180, 2700
            }
            System.out.println("digit = " + digit);

            // 2--确定所求数位所在的数字(num)
            long num = start + (n - 1) / digit;
            System.out.println("num = " + num);

            // 3--确定所求数位在 numnum 的哪一数位
            String str = Long.toString(num);
            int res = str.charAt((n - 1) % digit) - '0';
            System.out.println("res = " + res);
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
