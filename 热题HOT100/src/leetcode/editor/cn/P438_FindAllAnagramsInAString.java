/**
 * 题目Id：438
 * 题目：找到字符串中所有字母异位词
 * 日期：2021-08-25 17:40:05
 */
//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指字母相同，但排列不同的字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 596 👎 0


package leetcode.editor.cn;

//找到字符串中所有字母异位词

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P438_FindAllAnagramsInAString().new Solution();
        String s = "abaacbabc";
        String p = "abc";
        // {0, 6}
        List<Integer> res = solution.findAnagrams(s, p);
        System.out.println("res = " + res);
    }


    // 暴力法,复杂度O(len1 * len2) len1遍历 len2比较
    class Solution_my {
        // 遍历每个起点行不行,当然可行!
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();

            char[] target = p.toCharArray();
            Arrays.sort(target);

            int len1 = s.length(), len2 = p.length();
            // 子串范围[i,j]
            for (int i = 0; i <= len1 - len2; i++) {
                // 下标范围[i, i+len2-1) 子串cur长度为len2
                char[] cur = s.substring(i, i + len2).toCharArray();
                Arrays.sort(cur);
                if (Arrays.equals(target, cur))
                    res.add(i);
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 评价--弄巧成拙!
    // 滑动窗口--用P76题的逻辑,有点小问题的!
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<Integer>();
            // 统计p中各字符出现次数
            int[] need = new int[128];
            for (char c : p.toCharArray()) {
                need[c]++;
            }

            int right = 0, count = p.length(); // need配合count,left,right一起快乐滑动
            // 分析 左指针可以不要,因为窗口大小固定为 p.length()
            // int size = Integer.MAX_VALUE, start = 0; // P76题需要维护的"最小窗口大小"及其开始位置
            while (right < s.length()) {
                int left = right - p.length() + 1;
                char c = s.charAt(right);
                if (need[c] > 0) { // p中需要字符c
                    count--;
                }
                need[c]--; // 更新need数组

                // 如果形成窗口,进行判断
                if (left >= 0) {
                    if (count == 0) {
                        //System.out.println("left:" + left + "  子串为" +s.substring(left, right + 1));
                        res.add(left);
                    }
                }

                // 无论如何要右移窗口
                right++;
                if (left >= 0) {
                    // -1更新为0 -->  count不用增加
                    // 0更新为1 --> 该字符缺少了
                    count += need[s.charAt(left)] < 0 ? 0 : 1;
                    need[s.charAt(left)]++;
                }

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    class Solution_参考答案 {
        public List<Integer> findAnagrams(String s, String p) {
            int m = s.length();
            int n = p.length();
            List<Integer> res = new ArrayList<>();
            if (m < n) {
                return res;
            }
            int[] cnt_p = new int[26];
            int[] cnt_s = new int[26];
            for (int i = 0; i < n; i++) {
                cnt_p[p.charAt(i) - 'a']++;
                cnt_s[s.charAt(i) - 'a']++;
            }
            if (Arrays.equals(cnt_s, cnt_p)) {
                res.add(0);
            }

            for (int i = n; i < m; i++) {
                cnt_s[s.charAt(i) - 'a']++;
                cnt_s[s.charAt(i - n) - 'a']--;
                if (Arrays.equals(cnt_s, cnt_p)) {
                    res.add(i - n + 1);
                }
            }
            return res;
        }
    }

}
