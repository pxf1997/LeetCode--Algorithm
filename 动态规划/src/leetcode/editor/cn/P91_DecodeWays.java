/**
 * 题目Id：91
 * 题目：解码方法
 * 日期：2021-04-21 14:44:53
 */
//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 755 👎 0


package leetcode.editor.cn;

//解码方法

import java.util.Arrays;

public class P91_DecodeWays {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P91_DecodeWays().new Solution();
        int res = solution.numDecodings("225");
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int numDecodings(String s) {
            //dp[i]----s在i处的解码方案个数
            //状态转移方程---s.charAt(i)当做单个 或者  i和i-1处和成一个
            int len = s.length();
            int[] dp = new int[len + 1];//数组初始值全部为0
            dp[0] = 1; //空字符串解码 = 1
//            字符串的下标是从 0 而不是 1 开始的(最后一个，i=len 而 字符串index为len-1)
            System.out.println(Arrays.toString(dp));
            for (int i = 1; i <= len; i++) {
                //数字0不能单独解码
                if (s.charAt(i - 1) != '0') {
                    dp[i] += dp[i - 1];
                }
                //数字02不能成组解码，且解码组的数字需要小于26  组合解码发生条件是2个数字以上
                if (i >= 2) {
                    boolean flag = ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26);
                    if (s.charAt(i - 2) != '0' && flag) {
                        dp[i] += dp[i - 2];
                    }
                }

            }
            System.out.println(Arrays.toString(dp));

            return dp[len];


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
