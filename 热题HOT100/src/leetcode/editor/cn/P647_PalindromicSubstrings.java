/**
 * 题目Id：647
 * 题目：回文子串
 * 日期：2021-08-26 11:34:56
 */
//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//
//
//
// 示例 1：
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
//
//
// 示例 2：
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//
//
//
// 提示：
//
//
// 输入的字符串长度不会超过 1000 。
//
// Related Topics 字符串 动态规划 👍 659 👎 0


package leetcode.editor.cn;

//回文子串

public class P647_PalindromicSubstrings {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P647_PalindromicSubstrings().new Solution();
        String s = "aaa";
        int count = solution.countSubstrings(s);
        System.out.println("count = " + count);
    }


    // 暴力法--枚举所有子串 O(n^2) 再判断是否是回文串O(n)
    // 优化--回文中心法
    class Solution1 {
        public int countSubstrings(String s) {
            int len = s.length(), count = 0;
            for (int i = 0; i < 2 * len - 1; i++) {
                int left = i / 2, right = left + i % 2;
//                System.out.print("left = " + left);
//                System.out.println("  right = " + right);
                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                    count++;
                }
            }
            return count;
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 思路一样,换一种更好理解的写法
    class Solution {
        public int countSubstrings(String s) {
            int count = 0;
            int len = s.length();
            // 遍历回文中心点
            for (int i = 0; i < len; i++) {
                // j=0中心是一个点, j=1中心是两个点
                for (int j = 0; j <= 1; j++) {
                    int left = i, right = left + j;
                    while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                        left--;
                        right++;
                        count++;
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
