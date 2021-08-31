/**
 * 题目Id：650
 * 题目：只有两个键的键盘
 * 日期：2021-04-26 17:50:14
 */
//最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作： 
//
// 
// Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。 
// Paste (粘贴) : 你可以粘贴你上一次复制的字符。 
// 
//
// 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。 
//
// 示例 1: 
//
// 
//输入: 3
//输出: 3
//解释:
//最初, 我们只有一个字符 'A'。
//第 1 步, 我们使用 Copy All 操作。
//第 2 步, 我们使用 Paste 操作来获得 'AA'。
//第 3 步, 我们使用 Paste 操作来获得 'AAA'。
// 
//
// 说明: 
//
// 
// n 的取值范围是 [1, 1000] 。 
// 
// Related Topics 动态规划 
// 👍 276 👎 0


package leetcode.editor.cn;

//只有两个键的键盘

import java.util.Arrays;

public class P650_TwoKeysKeyboard {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P650_TwoKeysKeyboard().new Solution();
        int res = solution.minSteps(20);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSteps1(int n) {
//            将所有操作分成以 copy 为首的多组，形如 (copy, paste, ..., paste)，
//            再使用 C 代表 copy，P 代表 paste。例如操作 CPPCPPPPCP 可以分为 [CPP][CPPPP][CP] 三组。
//            假设每组的长度为 g_1, g_2, ...。完成第一组操作后，字符串有 g_1 个 A，
//            完成第二组操作后字符串有 g_1 * g_2 个 A。当完成所有操作时，共有 g_1 * g_2 * ... * g_n 个 'A'。
//            如果 g_i 是合数，存在 g_i = p * q，
//            那么这组操作可以分解为两组，第一组包含 1 个 C 和 p-1 个 P，第二组包含 1 个 C 和 q-1 个 P。
            int ans = 0, d = 2;
            while (n > 1) {
                while (n % d == 0) {
                    ans += d;
                    n /= d;
                }
                d++;
            }
            return ans;
        }

        public int minSteps(int n) {
//          ！！！最重要的--理解状态转移的原理：
//            介绍如下规律：
//            假设目前有2个A，如何得到6个A？
//            如果把2个A看成一组，那么从2个A得到6个A的操作，和从1个A得到3个A的操作是一样的：

//          dp理解--dp[i]到i处用了多少步
//          状态转移方程：dp[i] = dp[j] + dp[i/j] （ j是一个小于i的数，且i能被j整除，其实就是i的因数，注意把j==1的情况排除掉）
            int[] dp = new int[n + 1];
            int h = (int) Math.sqrt(n);
//            dp初始化--dp[0]无实际意义，dp[1]=0不用操作

            for (int i = 2; i <= n; i++) {
                dp[i] = i; //最坏情况，第一次复制，后面一个一个粘贴过去
                for (int j = 2; j * j <= i; j++) {
                    if (i % j == 0) {
                        dp[i] = dp[j] + dp[i / j];
                        break;
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[n];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
