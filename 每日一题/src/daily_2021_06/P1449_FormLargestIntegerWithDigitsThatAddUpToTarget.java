/**
 * 题目Id：1449
 * 题目：数位成本和为目标值的最大数字
 * 日期：2021-06-12 23:23:00
 */
//给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数： 
//
// 
// 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。 
// 总成本必须恰好等于 target 。 
// 添加的数位中没有数字 0 。 
// 
//
// 由于答案可能会很大，请你以字符串形式返回。 
//
// 如果按照上述要求无法得到任何整数，请你返回 "0" 。 
//
// 
//
// 示例 1： 
//
// 
//输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
//输出："7772"
//解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要
//求的数字，但 "7772" 是较大的数字。
// 数字     成本
//  1  ->   4
//  2  ->   3
//  3  ->   2
//  4  ->   5
//  5  ->   6
//  6  ->   7
//  7  ->   2
//  8  ->   5
//  9  ->   5
// 
//
// 示例 2： 
//
// 
//输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
//输出："85"
//解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
// 
//
// 示例 3： 
//
// 
//输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
//输出："0"
//解释：总成本是 target 的条件下，无法生成任何整数。
// 
//
// 示例 4： 
//
// 
//输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
//输出："32211"
// 
//
// 
//
// 提示： 
//
// 
// cost.length == 9 
// 1 <= cost[i] <= 5000 
// 1 <= target <= 5000 
// 
// Related Topics 字符串 动态规划 
// 👍 106 👎 0


package daily_2021_06;

//数位成本和为目标值的最大数字

import java.util.Arrays;

public class P1449_FormLargestIntegerWithDigitsThatAddUpToTarget {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1449_FormLargestIntegerWithDigitsThatAddUpToTarget().new Solution();
//        String res = solution.largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9);
        String res = solution.largestNumber(new int[]{1, 1, 1, 1, 1, 1, 1, 3, 2}, 10);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 完全背包 + 背包问题dp表的回溯(记录拿了哪些物品)
    class Solution1 {
        public String largestNumber(int[] cost, int target) {
            int[][] dp = new int[10][target + 1];
            for (int i = 0; i < 10; ++i) {
                Arrays.fill(dp[i], Integer.MIN_VALUE);
            }
            int[][] from = new int[10][target + 1];
            dp[0][0] = 0;
            for (int i = 0; i < 9; ++i) {
                int c = cost[i];
                for (int j = 0; j <= target; ++j) {
                    if (j < c) {
                        dp[i + 1][j] = dp[i][j];
                        from[i + 1][j] = j;
                    } else {
                        if (dp[i][j] > dp[i + 1][j - c] + 1) {
                            dp[i + 1][j] = dp[i][j];
                            from[i + 1][j] = j;
                        } else {
                            dp[i + 1][j] = dp[i + 1][j - c] + 1;
                            from[i + 1][j] = j - c;
                        }
                    }
                }
            }
            if (dp[9][target] < 0) {
                return "0";
            }
            StringBuffer sb = new StringBuffer();
            int i = 9, j = target;
            while (i > 0) {
                if (j == from[i][j]) {
                    --i;
                } else {
                    sb.append(i);
                    j = from[i][j];
                }
            }
            return sb.toString();
        }
    }


    // 版本2
    // 完全背包 -- 一维
    class Solution {
        public String largestNumber(int[] cost, int target) {
            int len = cost.length;
            String[] dp = new String[target + 1];
            Arrays.fill(dp, "#");
            dp[0] = "";
            System.out.println("dp = " + Arrays.toString(dp));
            //因为i遍历的顺序是从小到大，就保证了整数的高位一定大于等于整数的低位，也就保证了整数最大
            for (int i = 1; i <= len; i++) { // 物品维度,index=i-1,对应数字i
                int cur = cost[i - 1];
                for (int j = 1; j <= target; j++) {
                    if (j >= cur && !dp[j - cur].equals("#")) {
                        dp[j] = CompareString(dp[j], i + dp[j - cur]);
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[target].equals("#") ? "0" : dp[target];
        }

        private String CompareString(String s1, String s2) {
            if (s1.equals("#")) return s2;
            int len1 = s1.length(), len2 = s2.length();
            if (len1 == len2) {
                // 逐个比较就完事了
                for (int i = 0; i < len1; i++) {
                    if (s1.charAt(i) > s2.charAt(i)) return s1;
                    else if (s1.charAt(i) < s2.charAt(i)) return s2;
                    else continue;
                }
            }
            return len1 > len2 ? s1 : s2;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
