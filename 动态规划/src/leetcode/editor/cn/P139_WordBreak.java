/**
 * 题目Id：139
 * 题目：单词拆分
 * 日期：2021-04-25 14:39:27
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
// Related Topics 动态规划 
// 👍 955 👎 0


package leetcode.editor.cn;

//单词拆分

import java.util.Arrays;
import java.util.List;


public class P139_WordBreak {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P139_WordBreak().new Solution();
        List<String> wordDict = Arrays.asList("apple", "pen");
        boolean res = solution.wordBreak("applepenapple", wordDict);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public boolean wordBreak(String s, List<String> wordDict) {
//            该问题涉及到字典中单词的使用顺序，也就是说物品必须按一定顺序放入背包中，例如下面的 dict 就不够组成字符串 "leetcode"：
//            ["lee", "tc", "cod"]
//            求解顺序的完全背包问题时，对物品的迭代应该放在最里层，对背包的迭代放在外层，只有这样才能让物品按一定顺序放入背包中。
            int len = s.length();
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;
            System.out.println(Arrays.toString(dp));
            System.out.println();
//            外层迭代物品是错误的
/*            for (String word : wordDict) {
                for (int i = word.length(); i <= len; i++) {
                    dp[i] = dp[i] || dp[i - word.length()];
                }
            }*/
//            对物品的迭代应该放在最里层，对背包（重量限制）的迭代放在外层
            for (int i = 1; i <= len; i++) {
                for (String word : wordDict) {
                    int wlen = word.length();
                    if (wlen <= i && word.equals(s.substring(i - wlen, i))) {
                        dp[i] = dp[i] || dp[i - wlen];
                    }

                }
            }
            System.out.println(Arrays.toString(dp));

            return dp[len];

        }
    }

    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
            System.out.println();
            return dp[s.length()];

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
