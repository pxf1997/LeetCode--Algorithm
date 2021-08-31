/**
 * 题目Id：剑指 Offer 58 - I
 * 题目：翻转单词顺序
 * 日期：2021-06-11 14:38:25
 */
//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/ 
//
//
// 注意：此题对比原题有改动 
// Related Topics 字符串 
// 👍 106 👎 0


package leetcode.editor.cn;

//翻转单词顺序

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 剑指Offer_58_I_翻转单词顺序 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_58_I_翻转单词顺序().new Solution();
        String res = solution.reverseWords("  the  sky    is blue    ");
//        String res = solution.reverseWords("    a   b   ");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 遍历一遍,存到wordList里--繁琐
    class Solution1 {
        public String reverseWords(String s) {
            s += " ";
            char[] chars = s.toCharArray();
            List<StringBuilder> wordList = new ArrayList<>();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                // 该字符为' '
                if (chars[i] != ' ') {
                    word.append(chars[i]);
                }
                // 该字符为' '
                else {
                    // 记录过字符,存入
                    if (word.length() > 0) {
                        wordList.add(new StringBuilder(word));
                        word.setLength(0);
                    }
                    // 没记录过字符(连续的空格)
                    else {
                        continue;
                    }
                }
            }
            // System.out.println("wordList = " + wordList);
            if (wordList.size() == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = wordList.size() - 1; i >= 0; i--) {
                sb.append(wordList.get(i)).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    // 双指针--下标也不好写
    class Solution2 {
        public String reverseWords(String s) {
            s.trim();
            char[] chars = s.toCharArray();
            StringBuilder res = new StringBuilder();
            int left = s.length() - 1, right = left;
            while (left >= 0) {
                // 略过空格
                while (left >= 0 && s.charAt(left) != ' ') left--; // 搜索首个空格
                res.append(s, left + 1, right + 1).append(" ");
                while (left >= 0 && s.charAt(left) == ' ') left--; // 跳过单词间空格
                right = left; // j 指向下个单词的尾字符
            }
            return res.toString().trim(); // 转化为字符串并返回

        }
    }

    // 优化的分割法
    class Solution {
        public String reverseWords(String s) {
            String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
            StringBuilder res = new StringBuilder();
            for (int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
                if (strs[i].equals("")) continue; // 遇到空单词则跳过
                res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
            }
            return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
