/**
 * 题目Id：633
 * 题目：平方数之和
 * 日期：2021-04-28 09:56:45
 */
//给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。 
//
// 
//
// 示例 1： 
//
// 输入：c = 5
//输出：true
//解释：1 * 1 + 2 * 2 = 5
// 
//
// 示例 2： 
//
// 输入：c = 3
//输出：false
// 
//
// 示例 3： 
//
// 输入：c = 4
//输出：true
// 
//
// 示例 4： 
//
// 输入：c = 2
//输出：true
// 
//
// 示例 5： 
//
// 输入：c = 1
//输出：true 
//
// 
//
// 提示： 
//
// 
// 0 <= c <= 231 - 1 
// 
// Related Topics 数学 
// 👍 199 👎 0


//平方数之和

import java.util.Arrays;

public class P633_SumOfSquareNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P633_SumOfSquareNumbers().new Solution();
        boolean b = solution.judgeSquareSum(5);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //        逻辑有误---dp[i]是”能否分解为任意个正整数平方和“
        public boolean judgeSquareSum(int c) {
            boolean[] dp = new boolean[c + 1];
            dp[0] = true;
            dp[1] = true;
            for (int i = 2; i <= c; i++) {
                for (int j = 0; j <= Math.sqrt(i); j++) {
                    if (dp[i - j * j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[c];
        }
    }

    class Solution {
        //        暴力枚举----超时
        public boolean judgeSquareSum_1(int c) {
            if (c == 0 || c == 1) {
                return true;
            }
            for (int i = 0; i <= Math.sqrt(c); i++) {
                for (int j = 0; j <= Math.sqrt(c); j++) {
                    if (i * i + j * j == c) {
                        System.out.println("i = " + i);
                        System.out.println("j = " + j);
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean judgeSquareSum(int c) {
            if (c == 0 || c == 1 || c == 2) {
                return true;
            }
            int low = 0, high = (int) Math.sqrt(c);
            while (low <= high) {
                int sum = low * low + high * high;
                if (sum == c) {
//                    System.out.println("low = " + low);
//                    System.out.println("high = " + high);
                    return true;
                } else if (sum < c) {
                    low++;
                } else {
                    high--;
                }
            }
            return false;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
