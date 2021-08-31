/**
 * 题目Id：72
 * 题目：编辑距离
 * 日期：2021-06-24 17:17:27
 */
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1663 👎 0


package leetcode.editor.cn;

//编辑距离

import util.dp_util;

public class P72_EditDistance {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P72_EditDistance().new Solution();
        int minDistance = solution.minDistance("ros", "horse");
        System.out.println("minDistance = " + minDistance);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length(), len2 = word2.length();
            // 特殊情况,有空串
            if (len1 * len2 == 0) return len1 + len2;
            // dp[]
            int[][] dp = new int[len1 + 1][len2 + 1];
            // 初始值,0行 0列
            dp[0][0] = 0;
            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int i = 1; i <= len2; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    // 左上(斜)--dp[i-1][j-1]--改,若相同就不用改
                    // 上--dp[i-1][j]--删
                    // 左--dp[i][j-1]--插(增)
                    int up = dp[i - 1][j] + 1;
                    int left = dp[i][j - 1] + 1;
                    int up_left = (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1) + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(up, Math.min(left, up_left));
                }
            }
            //dp_util.print_2D(dp);
            return dp[len1][len2];


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
