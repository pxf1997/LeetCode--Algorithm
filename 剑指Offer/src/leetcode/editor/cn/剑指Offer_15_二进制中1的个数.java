/**
 * 题目Id：剑指 Offer 15
 * 题目：二进制中1的个数
 * 日期：2021-06-16 16:15:49
 */
//请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 
//9，则该函数输出 2。 
//
// 
//
// 示例 1： 
//
// 
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
//
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/ 
// Related Topics 位运算 
// 👍 119 👎 0


package leetcode.editor.cn;

//二进制中1的个数

public class 剑指Offer_15_二进制中1的个数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_15_二进制中1的个数().new Solution();
//        int hammingWeight = solution.hammingWeight(9);
        int hammingWeight = solution.hammingWeight(Integer.parseInt("11111111111111111101", 2));
        System.out.println("hammingWeight = " + hammingWeight);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {
        // you need to treat n as an unsigned value

        // 取最右一位判断是否是1 然后右移
        public int hammingWeight1(int n) {
            int cnt = 0;
            while (n != 0) {
                if ((n & 1) == 1) cnt++;
                n = n >> 1;
            }
            return cnt;
        }

        // n&(n-1) 消去最低位1
        public int hammingWeight(int n) {
            int cnt = 0;
            while (n != 0) {
                cnt++;
                n = n & (n - 1);
            }
            return cnt;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
