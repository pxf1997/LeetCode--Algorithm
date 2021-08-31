/**
 * 题目Id：剑指 Offer 46
 * 题目：把数字翻译成字符串
 * 日期：2021-06-17 22:58:01
 */
//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 231 
// 
// 👍 236 👎 0


package leetcode.editor.cn;

//把数字翻译成字符串

import java.util.Arrays;

public class 剑指Offer_46_把数字翻译成字符串 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_46_把数字翻译成字符串().new Solution();
//        int translateNum = solution.translateNum(12258);
        int translateNum = solution.translateNum(506); // 06不允许这么翻译
        System.out.println("translateNum = " + translateNum);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my版本--定义的变量有点多
    class Solution1 {
        public int translateNum(int num) {
            // dp[i] 表示统计到下标i处,翻译方案数
            String str = Integer.toString(num);
            int len = str.length();
            int[] dp = new int[len + 1];
            // 初始条件
            dp[0] = 1; // 测试得出:零位方案数为 1
            dp[1] = 1;

            for (int i = 2; i <= len; i++) {
                char cur = str.charAt(i - 1);
                char prev = str.charAt(i - 2);
                // 一个数字单独翻译--肯定没问题
                dp[i] = dp[i - 1];
                // prev + cur一起翻译 注意'06'不能等同于6,即prev必须不为0
                // 换言之, prev+cur ∈[10,25]
                if (compute_helper(prev, cur) <= 25 && compute_helper(prev, cur) >= 10) {
                    dp[i] += dp[i - 2];
                }
            }
            // helper
            System.out.println("str = " + str);
            System.out.println("dp = " + Arrays.toString(dp));

            return dp[len];
        }

        private int compute_helper(char prev, char cur) {
            return 10 * (prev - '0') + (cur - '0');
        }
    }

    // 参考答案--我就不学空间优化
    class Solution {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int len = s.length();
            int[] dp = new int[len + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= len; i++) {
                dp[i] = dp[i - 1];
                // 对应s的下标范围[i-2, i-1]
                String temp = s.substring(i - 2, i);
                if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                    dp[i] += dp[i - 2];
                }

            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[len];
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
