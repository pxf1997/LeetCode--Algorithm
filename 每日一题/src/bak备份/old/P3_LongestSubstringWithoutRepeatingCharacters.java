/**
 * 题目Id：3
 * 题目：无重复字符的最长子串
 * 日期：2021-04-30 11:35:16
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
// 👍 5388 👎 0


//无重复字符的最长子串

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
//        int res = solution.lengthOfLongestSubstring("pwwkew");
//        int res = solution.lengthOfLongestSubstring("aaaaa");
        int res = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //暴力法
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
//            //调试
//            System.out.println("s = " + s);

            int len = s.length();
            int max = 0;
            char[] sc = s.toCharArray();
//            直接选择多重循环吧
            for (int i = 0; i < len; i++) {
                char start = sc[i];
                int curMax = 0;
//                HashSet<Character> set = new HashSet<Character>();
                List<Character> list = new ArrayList<>();
                // 以start为起始的最长无重复子串
                for (int j = i; j < len; j++) {
                    char end = sc[j];
                    if (!list.contains(end)) {
                        curMax++;
                        list.add(end);
                    } else {
                        break;
                    }
                }
//                //调试
//                System.out.println("start = " + start + " curMax = " + curMax);
//                System.out.println("list = " + list);
//                System.out.println();
                max = Math.max(max, curMax);

            }

            return max;

        }
    }

    //滑动窗口
    class Solution {
        public int lengthOfLongestSubstring_my(String s) {
            char[] sc = s.toCharArray();
            int len = sc.length;
            int right = 0;
//            Set<Character> set = new HashSet<Character>();
            List<Character> set = new ArrayList<>();
            int max = 0;

            for (int i = 0; i < len; i++) { //左指针为i
                if (i != 0) {
                    Character remove = set.remove(0);
//                    System.out.println("remove = " + remove);
                }

                while (right < len) { //先判断 后++
                    char cright = sc[right];
                    if (!set.contains(cright)) {
                        set.add(cright);
                    } else {
                        break;
                    }
                    right++;
                }
                max = Math.max(max, right - i);
                //区间长度为 right- left
//                System.out.println("left=" + i + "  cleft=" + sc[i] + "  right=" + right + "  cright=" + sc[right]);
//                System.out.println("set = " + set);
//                System.out.println(right - i);
//                System.out.println();


            }
            return max;
        }

        public int lengthOfLongestSubstring(String s) {
            System.out.println("s = " + s);
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针技巧，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    System.out.println("remove=" + s.charAt(i - 1));
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 跳出条件 right=rk+1 在此最长区间的右一位
                    // 举例 最长区间[0,1,2,3]--最终right=4

                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                System.out.println("left=" + i + "   right=" + (rk + 1));
                System.out.println("set = " + occ);
                System.out.println();
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
