/**
 * 题目Id：17
 * 题目：电话号码的字母组合
 * 日期：2021-06-30 10:17:39
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
// Related Topics 哈希表 字符串 回溯 
// 👍 1376 👎 0


package leetcode.editor.cn;

//电话号码的字母组合

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P17_LetterCombinationsOfAPhoneNumber().new Solution();
//        List<String> res = solution.letterCombinations("7777");
        List<String> res = solution.letterCombinations("");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--回溯求组合
    class Solution {
        List<String> res = new ArrayList<String>();
        HashMap<Integer, char[]> map = new HashMap<>() {{
            put(2, new char[]{'a', 'b', 'c'});
            put(3, new char[]{'d', 'e', 'f'});
            put(4, new char[]{'g', 'h', 'i'});
            put(5, new char[]{'j', 'k', 'l'});
            put(6, new char[]{'m', 'n', 'o'});
            put(7, new char[]{'p', 'q', 'r', 's'});
            put(8, new char[]{'t', 'u', 'v'});
            put(9, new char[]{'w', 'x', 'y', 'z'});
        }};

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return res;
            }
            List<Character> path = new ArrayList<>();
            backtracking(digits, 0, path);
            return res;
        }

        private void backtracking(String digits, int beginIndex, List<Character> path) {
            if (beginIndex == digits.length()) {
                //System.out.println("递归终止:" + path);
                res.add(make_String_helper(path));
                return;
            }
            int digit = digits.charAt(beginIndex) - '0';
            for (char c : map.get(digit)) {
                path.add(c);
                backtracking(digits, beginIndex + 1, path);
                path.remove(path.size() - 1);
            }
        }

        private String make_String_helper(List<Character> path) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
