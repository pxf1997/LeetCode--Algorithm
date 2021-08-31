/**
 * 题目Id：剑指 Offer 50
 * 题目：第一个只出现一次的字符
 * 日期：2021-06-07 16:04:12
 */
//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例: 
//
// s = "abaccdeff"
//返回 "b"
//
//s = "" 
//返回 " "
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 哈希表 
// 👍 103 👎 0


package leetcode.editor.cn;

//第一个只出现一次的字符

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 剑指Offer_50_第一个只出现一次的字符 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_50_第一个只出现一次的字符().new Solution();
//        char firstUniqChar = solution.firstUniqChar("abab");
        char firstUniqChar = solution.firstUniqChar("gooogle");
        System.out.println("firstUniqChar = " + firstUniqChar);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用数组模拟HashMap
        public char firstUniqChar1(String s) {
            int len = s.length();
            if (len == 0) return ' ';
            if (len == 1) return s.charAt(0);
            // 如果有许多种字符呢,没关系因为ASCII总共就一百来个
            int[] map = new int[200];
            // key--数组下表--字母  val--出现次数
            for (char c : s.toCharArray()) {
                map[c]++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (map[s.charAt(i)] == 1) {
                    return s.charAt(i);
                }
            }
            return ' ';

        }

        // Hashmap
        public char firstUniqChar(String s) {
            int len = s.length();
            if (len == 0) return ' ';
            if (len == 1) return s.charAt(0);
            // 如果有许多种字符呢,没关系因为ASCII总共就一百来个
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            System.out.println("map = " + map);
            // 按顺序在s中找
            for (char c : s.toCharArray()) {
                if (map.get(c) == 1) return c;
            }
            return ' ';
        }

        // 用队列
        public char firstUniqChar3(String s) {
            int len = s.length();
            if (len == 0) return ' ';
            if (len == 1) return s.charAt(0);

            Deque<Character> data = new LinkedList<>();
            Deque<Character> firstUniq = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (data.contains(c)) {
                    firstUniq.remove(c);
                } else {
                    firstUniq.add(c);
                }
                data.add(c);
            }
            return firstUniq.isEmpty() ? ' ' : firstUniq.getFirst();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
