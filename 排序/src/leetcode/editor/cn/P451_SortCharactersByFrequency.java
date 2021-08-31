/**
 * 题目Id：451
 * 题目：根据字符出现频率排序
 * 日期：2021-03-29 09:46:35
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
// Related Topics 堆 哈希表 
// 👍 230 👎 0


package leetcode.editor.cn;

//根据字符出现频率排序

import java.util.*;

public class P451_SortCharactersByFrequency {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P451_SortCharactersByFrequency().new Solution();
        String s = "tttree";
        String res = solution.frequencySort(s);
        System.out.println(res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String frequencySort(String s) {
            Map<Character, Integer> frequencyForNum = new HashMap<>();
            for (Character c : s.toCharArray()) {
                frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);
            }
            System.out.println("frequencyForNum = " + frequencyForNum);
            List<Character>[] buckets = new ArrayList[s.length() + 1];
            // 举例： Map中1出现2次，3出现2次 === 桶中出现2次的有 1和3
            for (Character c : frequencyForNum.keySet()) {
                int frequency = frequencyForNum.get(c);
                if (buckets[frequency] == null) buckets[frequency] = new ArrayList<>();
                buckets[frequency].add(c);
            }
            System.out.println("buckets = " + Arrays.toString(buckets));

            StringBuilder str = new StringBuilder();
            for (int i = buckets.length - 1; i >= 0; i--) {
                if (buckets[i] == null) continue;
                for (Character character : buckets[i]) { // bucket[i]中元素出现次数为--i次
//                    str.append(buckets[i]);
                    for (int j = 0; j < i; j++) {
                        str.append(character);
                    }

                }
            }
            return str.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
