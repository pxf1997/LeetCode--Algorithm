/**
 * 题目Id：518
 * 题目：零钱兑换 II
 * 日期：2021-04-22 16:01:15
 */
//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
//
// 
//
// 
// 
//
// 示例 1: 
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2: 
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
// 
//
// 示例 3: 
//
// 输入: amount = 10, coins = [10] 
//输出: 1
// 
//
// 
//
// 注意: 
//
// 你可以假设： 
//
// 
// 0 <= amount (总金额) <= 5000 
// 1 <= coin (硬币面额) <= 5000 
// 硬币种类不超过 500 种 
// 结果符合 32 位符号整数 
// 
// 👍 375 👎 0


package leetcode.editor.cn;

//零钱兑换 II

import java.util.Arrays;

public class P518_CoinChange2 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P518_CoinChange2().new Solution();
        int[] coins = {1,2,3};
        int res = solution.change(4, coins);
        System.out.println(res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        分析----是组合而不是排列
//        dp[i]-- i块钱的找零方案个数
//        dp[i]初始为0，状态转移为 dp[i]+=dp[i-coin]
        public int change(int amount, int[] coins) {

            int[] dp = new int[amount + 1];
            dp[0] = 1;
            System.out.println(Arrays.toString(dp));

//            循环嵌套顺序的解释看题解--先列后行
            for (int i = 1; i <= amount; i++) {
//              注意细节--如果不进if条件，那么要考虑dp每个位置的初始化值
                for (int coin : coins) {
                    if (i >= coin) {
                        dp[i] += dp[i - coin];
                    }
                }
                System.out.println("amount="+i +" "+ Arrays.toString(dp));
            }


//          先行后列
//            for (int coin : coins) {
//                for (int i = coin; i <= amount; i++) {
//                    dp[i] += dp[i - coin];
//                }
//                System.out.println("coin="+coin +" "+ Arrays.toString(dp));
//            }
//

            return dp[amount];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
