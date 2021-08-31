/**
 * 题目Id：139
 * 题目：单词拆分
 * 日期：2021-08-23 22:54:56
 */
//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1130 👎 0


package leetcode.editor.cn;

//单词拆分

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P139_WordBreak {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P139_WordBreak().new Solution();

        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        boolean b = solution.wordBreak(s, wordDict);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            // dp[i] 字符串下标[0,i)范围内能否被拆分,
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            // 状态转移分析--
            // dp[i] = dp[j] && check[j,i]  即下标[j,i]是一个单词

            // i有意义的取值为[1, len-1]
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && set.contains(s.substring(j, i))) {
                        System.out.println("[" + j + "," + i + ") = " + s.substring(j, i));
                        dp[i] = true;
                        break;
                    }
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return dp[s.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
