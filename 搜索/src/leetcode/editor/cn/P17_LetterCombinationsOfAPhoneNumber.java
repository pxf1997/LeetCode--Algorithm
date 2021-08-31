/**
 * 题目Id：17
 * 题目：电话号码的字母组合
 * 日期：2021-05-07 10:24:16
 */
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1292 👎 0


package leetcode.editor.cn;

//电话号码的字母组合

import java.util.ArrayList;
import java.util.List;

public class P17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P17_LetterCombinationsOfAPhoneNumber().new Solution();
        List<String> letterCombinations = solution.letterCombinations("23");
        System.out.println("letterCombinations = " + letterCombinations);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; //0-9

        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<String>();
            if (digits == null || digits.length() == 0) {
                return res;
            }

            letterCombinations_helper(new StringBuilder(), res, digits);

            return res;

        }

        private void letterCombinations_helper(StringBuilder prefix, List<String> res, String digits) {
            //递归终止条件--base
            if (prefix.length() == digits.length()) {
                res.add(prefix.toString());
                return;
            }
            int cur = digits.charAt(prefix.length()) - '0';
            String letters = keys[cur];

//            System.out.println("prefix = " + prefix);
//            System.out.println();

            for (char c : letters.toCharArray()) {
                prefix.append(c);   // 添加
//                System.out.println("添加字母:" + c);

                letterCombinations_helper(prefix, res, digits);

//                System.out.println("res = " + res);

                prefix.deleteCharAt(prefix.length() - 1);// 删除


            }
            System.out.println();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
