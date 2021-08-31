/**
 * 题目Id：583
 * 题目：两个字符串的删除操作
 * 日期：2021-04-26 16:05:58
 */
//给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例： 
//
// 输入: "sea", "eat"
//输出: 2
//解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
// 
//
// 
//
// 提示： 
//
// 
// 给定单词的长度不超过500。 
// 给定单词中的字符只含有小写字母。 
// 
// Related Topics 字符串 
// 👍 187 👎 0


package leetcode.editor.cn;

//两个字符串的删除操作

public class P583_DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P583_DeleteOperationForTwoStrings().new Solution();
        int res = solution.minDistance("seat", "noeat");
//        int res = solution.minDistance("a", "a");
        System.out.println(res);

//        String a = "";  //空字符串不等于null
//        System.out.println(a == null);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int minDistance(String word1, String word2) {
//              我的思路：
//              1--dp含义：dp[i][j] 表示范围是word1前i个字符，word2前j个字符，所需删除的步数
//              2--dp状态转移：
//                  ①i、j位置字符相等：dp[i][j] = dp[i - 1][j - 1];
//                  ②i、j位置字符不等：dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
//              3--dp初始化边界条件：第一行和第一列
//              4--结果提取：最右下角的元素

            int len1 = word1.length(), len2 = word2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
//          dp初始化边界条件：第一行和第一列
            dp[0][0] = 0;
            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= len2; j++) {
                dp[0][j] = j;
            }
//            dp_util.print_DP_2(dp);
//            System.out.println();
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
//            dp_util.print_DP_2(dp);
//            System.out.println();

            return dp[len1][len2];

        }
    }

    class Solution {
        public int minDistance(String word1, String word2) {

//            思路：转化为求最长公共子序列
//            1--dp含义：dp[i][j] 表示范围是word1前i个字符，word2前j个字符，公共子串长度
            int len1 = word1.length(), len2 = word2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
//            dp[0][0] = 1;
            dp[0][0] = 0;
            if (len1 == 0 || len2 == 0) {
                return len1 + len2;
            }

            dp_util.print_DP_2(dp);
            System.out.println();

//            2--dp初始化边界条件：第一行和第一列应当为 0 直接不写
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            dp_util.print_DP_2(dp);
            System.out.println();

            return len1 + len2 - 2 * dp[len1][len2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
