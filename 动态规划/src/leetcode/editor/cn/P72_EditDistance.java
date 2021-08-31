/**
 * 题目Id：72
 * 题目：编辑距离
 * 日期：2021-04-26 17:04:55
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
// 👍 1570 👎 0


package leetcode.editor.cn;

//编辑距离

public class P72_EditDistance {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P72_EditDistance().new Solution();
        int minDistance = solution.minDistance("horse", "ros");
        System.out.println("minDistance = " + minDistance);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length(), len2 = word2.length();
            if (len1 * len2 == 0) return len1 + len2;
            int[][] dp = new int[len1 + 1][len2 + 1];
            dp[0][0] = 0;
//            第一行第一列初始化
            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int i = 1; i <= len2; i++) {
                dp[0][i] = i;
            }

//            dp_util.print_DP_2(dp);
//            System.out.println();

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    int left = dp[i - 1][j] + 1;
                    int up = dp[i][j - 1] + 1;
                    int left_up = dp[i - 1][j - 1]; //若相同--copy（无需操作）
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        left_up += 1; //若不同--多一步替换
                    }
                    dp[i][j] = Math.min(left, Math.min(up, left_up));
                }
            }

//            dp_util.print_DP_2(dp);
//            System.out.println();

            return dp[len1][len2];

        }
    }

    class Solution {
        public int minDistance(String word1, String word2) {
            if (word1 == null || word2 == null) {
                return 0;
            }
            int m = word1.length(), n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int i = 1; i <= n; i++) {
                dp[0][i] = i;
            }
            dp_util.print_DP_2(dp);
            System.out.println();
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1]; //无需操作
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1; //无需操作--三个方向，增删改
                    }
                }
            }
            dp_util.print_DP_2(dp);
            System.out.println();
            return dp[m][n];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
