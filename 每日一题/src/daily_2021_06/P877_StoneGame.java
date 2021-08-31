/**
 * 题目Id：877
 * 题目：石子游戏
 * 日期：2021-06-16 10:00:16
 */
//亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。 
//
// 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。 
//
// 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
// 
//
// 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。 
//
// 
//
// 示例： 
//
// 
//输入：[5,3,4,5]
//输出：true
//解释：
//亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
//假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
//如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
//如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
//这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= piles.length <= 500 
// piles.length 是偶数。 
// 1 <= piles[i] <= 500 
// sum(piles) 是奇数。 
// 
// Related Topics 极小化极大 数学 动态规划 
// 👍 275 👎 0


package daily_2021_06;

//石子游戏

import static util.dp_util.print_2D;

public class P877_StoneGame {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P877_StoneGame().new Solution();
        boolean b = solution.stoneGame(new int[]{5, 3, 4, 5});
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--dp
    // dp[i][j] 表示当剩下的石子堆为下标 i 到下标 j 时，当前玩家与另一个玩家的石子数量之差的最大值
    class Solution {
        public boolean stoneGame(int[] piles) {
            int len = piles.length;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = piles[i];
            }
            // 行下标范围 [0,len-2]
            // 每行的列号范围 [i+1,len-1] (对角线i=j的右边)
            // 注意行号计算顺序,从下到上
            for (int i = len - 2; i >= 0; i--) {
//            for (int i = 0; i <= len - 2; i++) {
                for (int j = i + 1; j < len; j++) {
                    // 这句话怎么理解--
                    // 取走石子i + 剩余石子堆 --> piles[i] - dp[i + 1][j]
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
            print_2D(dp);
            return dp[0][len - 1] > 0;
        }

        // 数学分析--分组
        public boolean stoneGame2(int[] piles) {
            return true;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
