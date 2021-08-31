/**
 * 题目Id：剑指 Offer 65
 * 题目：不用加减乘除做加法
 * 日期：2021-06-22 11:42:26
 */
//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。 
//
// 
//
// 示例: 
//
// 输入: a = 1, b = 1
//输出: 2 
//
// 
//
// 提示： 
//
// 
// a, b 均可能是负数或 0 
// 结果不会溢出 32 位整数 
// 
// 👍 188 👎 0


package leetcode.editor.cn;

//不用加减乘除做加法

public class 剑指Offer_65_不用加减乘除做加法 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_65_不用加减乘除做加法().new Solution();
        int add = solution.add(6, 7);
        System.out.println("add = " + add);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 递归
    class Solution1 {
        //  a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
        public int add(int a, int b) {
            int temp = a ^ b;
            int carry = (a & b) << 1;
            if (carry == 0) return temp;
            else return add(temp, carry);
        }
    }

    // 迭代
    class Solution {
        // b视为进位
        public int add(int a, int b) {
            while (b != 0) { // 当进位为 0 时跳出
                int carry = (a & b) << 1;  // carry = 进位
                a = a ^ b; // a = 非进位和
                b = carry; // b = 进位
            }
            return a;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
