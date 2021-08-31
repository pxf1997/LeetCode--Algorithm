/**
 * 题目Id：49
 * 题目：字母异位词分组
 * 日期：2021-08-23 21:54:06
 */
//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 👍 820 👎 0


package leetcode.editor.cn;

//字母异位词分组

import java.util.*;

public class P49_GroupAnagrams {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P49_GroupAnagrams().new Solution();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = solution.groupAnagrams(strs);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--哈希表
    // 分组结果: [["bat"],["nat","tan"],["ate","eat","tea"]] --三个 Hashset

    // 方法一:排序
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // key--排序后的键 举例:nat,tan均排为ant  value--对应的单词列表
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);

                String key = String.valueOf(chars);
//                System.out.println("str = " + str);
//                System.out.println("key = " + key);
//                System.out.println();
                List<String> value = map.getOrDefault(key, new ArrayList<>());
                value.add(str);
                map.put(key, value);
            }
//            System.out.println("map = " + map);

//            List<List<String>> res = new ArrayList<>();
//            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//                res.add(entry.getValue());
//            }
//            return res;

            // 可以替换为
            return new ArrayList<>(map.values());
        }
    }


//leetcode submit region end(Prohibit modification and deletion)
    // 方法二 : 可以使用长度为 26 的数组记录每个字母出现的次数
    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                int[] counts = new int[26];
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    counts[str.charAt(i) - 'a']++;
                }
                // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        sb.append((char) ('a' + i));
                        sb.append(counts[i]);
                    }
                }
                String key = sb.toString();
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }

}
