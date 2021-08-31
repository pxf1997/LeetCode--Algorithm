/**
 * 题目Id：692
 * 题目：前K个高频单词
 * 日期：2021-05-20 09:53:33
 */
//给一非空的单词列表，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。 
//
// 示例 1： 
//
// 
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 
// 输入的单词均由小写字母组成。 
// 
//
// 
//
// 扩展练习： 
//
// 
// 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// 
// Related Topics 堆 字典树 哈希表 
// 👍 265 👎 0


//前K个高频单词

import java.util.*;

public class P692_TopKFrequentWords {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P692_TopKFrequentWords().new Solution();
        List<String> strings = solution.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
//        List<String> strings = solution.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        System.out.println("strings = " + strings);

    }

    //力扣代码

    //  my----不规范
    class Solution_my {
        public List<String> topKFrequent(String[] words, int k) {
            HashMap<String, Integer> mapForFrequency = new HashMap<>();
            for (String word : words) {
                mapForFrequency.put(word, mapForFrequency.getOrDefault(word, 0) + 1);
            }
//            System.out.println("mapForFrequency = " + mapForFrequency);
            //  按value定制排序
            List<Map.Entry<String, Integer>> list = new ArrayList<>(mapForFrequency.entrySet());
            //  版本1
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            //  版本2
//            Collections.sort(list, Comparator.comparingInt(Map.Entry::getValue));

            //  版本3
//            list.sort(Comparator.comparingInt(Map.Entry::getValue));

//            System.out.println("list = " + list);
            List<String> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                res.add(list.get(i).getKey());
            }
            return res;

        }
    }


    //  定制排序
    class Solution1 {
        public List<String> topKFrequent(String[] words, int k) {
            HashMap<String, Integer> mapForFrequency = new HashMap<>();
            for (String word : words) {
                mapForFrequency.put(word, mapForFrequency.getOrDefault(word, 0) + 1);
            }
            System.out.println("mapForFrequency = " + mapForFrequency);
            List<String> res = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : mapForFrequency.entrySet()) {
                res.add(entry.getKey());
            }
            System.out.println("res = " + res);
            //  定制排序
            Collections.sort(res, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    boolean isequal = (mapForFrequency.get(o1) == mapForFrequency.get(o2));
                    int res1 = o1.compareTo(o2); // String升序
                    int res2 = mapForFrequency.get(o2) - mapForFrequency.get(o1);   //  按频率降序
                    return (isequal ? res1 : res2);
                }
            });
            System.out.println("res = " + res);

            return res.subList(0, k);   //  下标0到k-1 共k个

        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 优先队列
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> cnt = new HashMap<String, Integer>();
            for (String word : words) {
                cnt.put(word, cnt.getOrDefault(word, 0) + 1);
            }
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                    return entry1.getValue() == entry2.getValue() ?
                            entry2.getKey().compareTo(entry1.getKey()) :    //  频率相等--字母
                            entry1.getValue() - entry2.getValue();  //  频率不等--频率
                }
            });

            for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
                pq.offer(entry);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            System.out.println("pq = " + pq);
            List<String> ret = new ArrayList<String>();
            while (!pq.isEmpty()) {
                ret.add(pq.poll().getKey());
            }
            Collections.reverse(ret);
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
