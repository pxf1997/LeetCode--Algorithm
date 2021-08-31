/**
 * 题目Id：326
 * 题目：3的幂
 * 日期：2021-05-12 14:28:18
 */
//给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 27
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 9
//输出：true
// 
//
// 示例 4： 
//
// 
//输入：n = 45
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -231 <= n <= 231 - 1 
// 
//
// 
//
// 进阶： 
//
// 
// 你能不使用循环或者递归来完成本题吗？ 
// 
// Related Topics 数学 
// 👍 162 👎 0


package leetcode.editor.cn;

//3的幂

public class P326_PowerOfThree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P326_PowerOfThree().new Solution();
//        boolean b = solution.isPowerOfThree(27);
        boolean b = solution.isPowerOfThree(6);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfThree_1(int n) {
            if (n == 1) {
                return true;
            }
            if (n < 3) {
                return false;
            }
            while (n >= 3) {
                if (n % 3 != 0) {
                    return false;
                }
                n /= 3;
            }
            return n == 1;
        }

        public boolean isPowerOfThree_2(int n) {
            if (n < 1) {
                return false;
            }
            while (n % 3 == 0) {
                n /= 3;
            }
            return n == 1;
        }
        public boolean isPowerOfThree(int n) {
            return n > 0 && (1162261467 % n == 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
