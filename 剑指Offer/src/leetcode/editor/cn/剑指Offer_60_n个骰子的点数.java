/**
 * 题目Id：剑指 Offer 60
 * 题目：n个骰子的点数
 * 日期：2021-06-21 18:11:44
 */
//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// 👍 251 👎 0


package leetcode.editor.cn;

//n个骰子的点数

import util.dp_util;

import java.util.Arrays;

public class 剑指Offer_60_n个骰子的点数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_60_n个骰子的点数().new Solution();
        double[] probability = solution.dicesProbability(2);
        System.out.println("probability = " + Arrays.toString(probability));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // dp--不压缩空间,写二维
    class Solution {
        public double[] dicesProbability(int n) {
            // 几个骰子投出几的概率 本来只需要n  和6n 为了方便计算 我们都+1
            double[][] dp = new double[n + 1][6 * n + 1];
            for (int i = 1; i <= 6; i++) {
                dp[1][i] = 1 / 6.0; //一个骰子 骰出1-6的概率都是1/6
            }
            for (int i = 2; i <= n; i++) { //几个骰子
                for (int j = i; j <= 6 * i; j++) { //骰出几
                    // 更新目标:dp[i][j], 从哪转移来的:dp[i-1][j-k]
                    for (int k = 1; k <= 6; k++) { // 一个骰子投出k
                        // 防止越界
                        if (i - 1 >= 0 && j - k >= 0) {
                            dp[i][j] += (dp[1][k] * dp[i - 1][j - k]);
                        }
                    }
                }
            }
            dp_util.print_2D(dp);

            double[] res = new double[5 * n + 1];
            int idx = 0;
            // 从下标为n的行中取出结果,点数范围[n,6n] 共5n+1个
            for (int i = n; i < 6 * n + 1; i++) {
                res[idx++] = dp[n][i];
            }
            return res;
        }
    }

    // dp--对物品(几个骰子)维度压缩
    class Solution2 {
        public double[] dicesProbability(int n) {
            double[] dp = new double[6];
            Arrays.fill(dp, 1.0 / 6.0);
            for (int i = 2; i <= n; i++) {
                double[] tmp = new double[5 * i + 1];
                for (int j = 0; j < dp.length; j++) {
                    for (int k = 0; k < 6; k++) {
                        tmp[j + k] += dp[j] / 6.0;
                    }
                }
                dp = tmp;
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
