/**
 * 题目Id：面试题 10.02
 * 题目：变位词组
 * 日期：2021-07-18 22:10:13
 */
//编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。 
//
// 注意：本题相对原题稍作修改 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 排序 
// 👍 75 👎 0


package daily_2021_07;

//变位词组

import java.util.*;

public class 面试题_10_02_变位词组 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 面试题_10_02_变位词组().new Solution();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = solution.groupAnagrams(strs);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 排序法--
    // 变位词字符串包含的字母相同,排序后的字符串一定相等,可以作为哈希表的键
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);
                List<String> value = map.getOrDefault(key, new ArrayList<>());
                value.add(str);
                // 这句话能不能省略--value对应的地址更改了,需不需要重新put进去
                // --不能省略,value最初肯定是新造的,必须put到map中
                map.put(key, value);
            }
            //System.out.println("map = " + map);
            return new ArrayList<>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
