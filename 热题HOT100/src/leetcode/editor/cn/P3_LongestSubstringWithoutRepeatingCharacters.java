/**
 * 题目Id：3
 * 题目：无重复字符的最长子串
 * 日期：2021-06-22 15:54:17
 */
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 5641 👎 0


package leetcode.editor.cn;

//无重复字符的最长子串

import java.util.*;

public class P3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
//        int res = solution.lengthOfLongestSubstring("abba");
        int res = solution.lengthOfLongestSubstring("abcabcdbb");
//        int res = solution.lengthOfLongestSubstring("");

        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // dp(以chars[i]结尾的最长无重复子串) + 哈希表(记录上次出现下标)
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            char[] chars = s.toCharArray();
            int len = s.length();
            if (len == 0) return 0;
            if (len == 1) return 1;
            // key--字符  value--上一次出现的下标
            Map<Character, Integer> map = new HashMap<>();
            // dp[i] 以 chars[i] 结尾的最长无重复子串
            int[] dp = new int[len];
            dp[0] = 1;
            map.put(chars[0], 0);
            // 简化逻辑
/*            for (int i = 1; i < len; i++) {
                // 上次出现下标,没出现过的话就是-1
                int lastIndex = map.getOrDefault(chars[i], -1);
                // 这里逻辑有点乱
                // 为什么会出现 : dp[i - 1] < i - lastIndex
                // 因为在 [lastIndex, i] 中间出现了重复的
                if (dp[i - 1] < i - lastIndex) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = i - lastIndex;
                }
                // 更新map
                map.put(chars[i], i);
            }*/
            // 完整逻辑
            for (int i = 1; i < len; i++) {
                // 之前出现过chars[i]
                if (map.containsKey(chars[i])) {
                    int lastIndex = map.get(chars[i]);
                    if (dp[i - 1] >= i - lastIndex) { // 举例--"abcdee"的第二个e
                        dp[i] = i - lastIndex;
                    } else { // 举例--"abba"的第二个a
                        dp[i] = dp[i - 1] + 1;
                    }
                }
                // 之前没有出现过chars[i]
                else {
                    dp[i] = dp[i - 1] + 1;
                }
                // 更新map
                map.put(chars[i], i);
            }
            //System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }
    }

    // 滑动窗口
    // 1、“窗口”的右边界一直向右边滑动，直到发现“窗口”内的元素出现了重复，或者右边界超过了字符串的末尾，
    // 在右边界扩张的过程中，“滑动窗口”的长度在逐渐增大，此时记录下最大值；
    // 2、只要出现了“重复”，“窗口”的右边界停止，此时左边界向右边移动，
    // 直到“滑动窗口”内没有重复的元素，跳转到第 1 步。
    class Solution {
        // 数组代替set
        public int lengthOfLongestSubstring1(String s) {
            int len = s.length();
            if (len < 2) return len;
            int left = 0, right = 0;
            int res = 1;
            // 数组代替set, ACSII码总共128个
            int[] set = new int[128];
            while (right < len) {
                char c = s.charAt(right);
                set[c]++;
                // 发现重复,left右移(缩小窗口)
                while (set[c] == 2) {
                    char c_left = s.charAt(left);
                    set[c_left]--;
                    left++;
                }
                // 无重复范围[left, right], 共 right - left + 1 个
                res = Math.max(res, right - left + 1);
                // right右移(扩大窗口)
                right++;
            }
            return res;
        }

        // 不太好理解!!!
        public int lengthOfLongestSubstring2(String s) {
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
                System.out.println("左界left = " + left + "  右界i = " + i + "  区间长度 = " + (i - left));
                System.out.println();
            }
            return max;
        }

        // 参考--set
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<>();
            int len = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int right = -1, res = 0;
            for (int left = 0; left < len; ++left) {
                if (left != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(left - 1));
                }
                while (right + 1 < len && !occ.contains(s.charAt(right + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(right + 1));
                    ++right;
                }
                // helper
                System.out.println("left = " + left + "  right = " + right);
                System.out.println("occ = " + occ);
                System.out.println();
                // [left, right] 是一个极长的无重复字符子串
                res = Math.max(res, right - left + 1);
            }
            return res;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
