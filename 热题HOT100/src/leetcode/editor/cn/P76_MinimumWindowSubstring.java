/**
 * 题目Id：76
 * 题目：最小覆盖子串
 * 日期：2021-08-24 20:53:12
 */
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1307 👎 0


package leetcode.editor.cn;

//最小覆盖子串

public class P76_MinimumWindowSubstring {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P76_MinimumWindowSubstring().new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = solution.minWindow(s, t);
        System.out.println("res = " + res);
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 滑动窗口--参考答案
    class Solution1 {
        public String minWindow(String s, String t) {
            // 特殊情况
            if (s == null || s.length() == 0 || t == null || t.length() == 0) {
                return "";
            }
            int[] need = new int[128];
            //记录需要的字符的个数
            for (int i = 0; i < t.length(); i++) {
                need[t.charAt(i)]++;
            }
            //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
            int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
            //遍历所有字符
            while (r < s.length()) {
                char c = s.charAt(r);
                if (need[c] > 0) {//需要字符c
                    count--;
                }
                need[c]--;//把右边的字符加入窗口
                if (count == 0) {//窗口中已经包含所有字符
                    while (l < r && need[s.charAt(l)] < 0) {
                        need[s.charAt(l)]++;//释放右边移动出窗口的字符
                        l++;//指针右移
                    }
                    // 窗口范围 [l,r]
                    if (r - l + 1 < size) {//不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                        size = r - l + 1;
                        start = l;//记录下最小值时候的开始位置，最后返回覆盖串时候会用到
                    }
                    //l向右移动后窗口肯定不能满足了 重新开始循环
                    need[s.charAt(l)]++;
                    l++;
                    count++;
                }
                r++;
            }
            return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // my练习
    class Solution {
        public String minWindow(String s, String t) {
            // 特殊情况排除
            if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
            // 统计t所需字符
            int[] need = new int[128];
            for (char c : t.toCharArray()) {
                need[c]++;
            }
            // 该方法的几个套路变量----
            // 窗口左右边界
            int left = 0, right = 0;
            // size--所需大小 count--当前剩余数量 start--开始下标
            int size = Integer.MAX_VALUE, count = t.length(), start = 0;
            // 滑动窗口
            while (right < s.length()) {
                char c = s.charAt(right);
                // 用到字符 c
                if (need[c] > 0) {
                    count--;
                }
                need[c]--; // 右界扩大,更新 need 数组
                // 当前窗口已经包含所有所需
                if (count == 0) {
                    // helper
                    System.out.print("count == 0 ==> 当前窗口已经包含所有所需 : ");
                    System.out.println(s.substring(left, right + 1));

                    // 逻辑--left右移到临界值
                    // 何为临界状态-- need[s.charAt(left)] 从0变为1-->该字符缺少了
                    while (left < right && need[s.charAt(left)] < 0) {
                        need[s.charAt(left)]++; // 左界缩小,更新 need 数组
                        left++;
                    }
                    System.out.print("left右移缩小窗口为 : ");
                    System.out.println(s.substring(left, right + 1));
                    System.out.println();
                    // 窗口范围 [l,r]
                    if (right - left + 1 < size) {
                        size = right - left + 1;
                        start = left;
                    }
                    // 重新开始循环,只需left右移一步即可
                    need[s.charAt(left)]++;
                    left++;
                    count++;
                }
                right++;
            }
            return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
        }
    }


}
