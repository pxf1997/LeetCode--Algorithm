/**
 * 题目Id：剑指 Offer 48
 * 题目：最长不含重复字符的子字符串
 * 日期：2021-06-18 14:37:20
 */
//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。 
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-rep
//eating-characters/ 
// Related Topics 哈希表 双指针 Sliding Window 
// 👍 220 👎 0


package leetcode.editor.cn;

//最长不含重复字符的子字符串

import java.util.*;

public class 剑指Offer_48_最长不含重复字符的子字符串 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_48_最长不含重复字符的子字符串().new Solution();
//        int res = solution.lengthOfLongestSubstring("abcabcdbb");
        int res = solution.lengthOfLongestSubstring("abba");
//        int res = solution.lengthOfLongestSubstring("");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 逻辑错误
    // 例如 "dvdf" dv处就新建一个set则不可行!
    class Solution_wrong {
        public int lengthOfLongestSubstring(String s) {
            char[] chars = s.toCharArray();
            Set<Character> set = new HashSet<Character>();
            int max = 0;
            for (char c : chars) {
                if (!set.add(c)) {
                    max = Math.max(max, set.size());
                    System.out.println("可行候选集 set = " + set);
                    set = new HashSet<>();
                    set.add(c);
                }
            }
            // 如果到最后能组成最长子串
            max = Math.max(max, set.size());
            return max;
        }
    }

    // dp + 哈希表
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int len = s.length();
            if (len == 0) return 0;
            // dp[i] 以下标i结尾的,"最长不重复子字符串"长度
            // 使用哈希表统计 各字符最后一次出现位置的索引
            int[] dp = new int[len];
            dp[0] = 1;
            Map<Character, Integer> map = new HashMap<>();
            map.put(s.charAt(0), 0);
            for (int i = 1; i < len; i++) {
                int index = map.getOrDefault(s.charAt(i), -1);
                map.put(s.charAt(i), i);
                // 这个判断条件很难想啊
                if (dp[i - 1] < i - index) dp[i] = dp[i - 1] + 1;
                else dp[i] = i - index;
            }
            System.out.println("dp = " + Arrays.toString(dp));

            return Arrays.stream(dp).max().getAsInt();
        }
    }

    // 双指针
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            System.out.println("s = " + s);
            Map<Character, Integer> map = new HashMap<>();
            int left = -1, max = 0;
            for (int i = 0; i < s.length(); i++) {
                // [left+1, i] 区间内无重复字符
                if (map.containsKey(s.charAt(i))) {
                    left = Math.max(left, map.get(s.charAt(i))); // 更新左指针 left

                    // 疑问,get出来一定比原来的大?
                    // 错误案例 "abba" 自己画一下就知道了
                    // left = map.get(s.charAt(i));
                }
                map.put(s.charAt(i), i); // 哈希表记录
                max = Math.max(max, i - left); // 更新结果

                // helper
                System.out.println("s.charAt(" + i + ") = " + s.charAt(i));
                System.out.println("左界index = " + left + "  右界i = " + i + "  区间长度 = " + (i - left));
                System.out.println();
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
