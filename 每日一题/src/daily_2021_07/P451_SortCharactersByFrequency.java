/**
 * 题目Id：451
 * 题目：根据字符出现频率排序
 * 日期：2021-07-03 23:46:23
 */
//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。 
//
// 示例 1: 
//
// 
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 
// 👍 311 👎 0


package daily_2021_07;

//根据字符出现频率排序

import java.util.*;

public class P451_SortCharactersByFrequency {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P451_SortCharactersByFrequency().new Solution();
        String res = solution.frequencySort("zzzzzCbbaaaEEEE");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 分析--统计次数+降序排列,考点--集合的使用
    // 好简单啊,频率相同也不用按字母顺序!
    class Solution {
        public String frequencySort(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            List<Character> list = new ArrayList<>(map.keySet());// map.keySet() 返回由map中的key组成的set
            // 注意这里,map没法定制排序,将map中数据取出来定制排序
            Collections.sort(list, new Comparator<Character>(){
                public int compare(Character o1, Character o2) {
                    return map.get(o2)-map.get(o1);
                }
            });
            // 也可以写成lamda表达式
            // Collections.sort(list, (o1, o2) -> map.get(o2)-map.get(o1));
            System.out.println("map = " + map);
            System.out.println("list = " + list);
            StringBuilder sb = new StringBuilder();
            for (Character c : list) {
                int frequency = map.get(c);
                for (int i = 0; i < frequency; i++) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
