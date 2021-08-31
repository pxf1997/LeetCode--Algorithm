/**
 * 题目Id：347
 * 题目：前 K 个高频元素
 * 日期：2021-03-27 01:39:31
 */
//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 696 👎 0


package leetcode.editor.cn;

//前 K 个高频元素

import java.util.*;

public class P347_TopKFrequentElements {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P347_TopKFrequentElements().new Solution();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {1};
        int[] res = solution.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(res));

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            //my
            HashMap<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            System.out.println("frequencyForNum = " + frequencyMap);

            ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1]; //考虑极端情况 所有元素相同  频率nums.length有一个元素

            List<Integer[]> buckets2 = new ArrayList<Integer[]>(nums.length);

            for (Integer key : frequencyMap.keySet()) {
                int frequency = frequencyMap.get(key);
                if (buckets[frequency] == null) buckets[frequency] = new ArrayList<>();
                buckets[frequency].add(key);
            }
            System.out.println("buckets = " + Arrays.toString(buckets));

            List<Integer> topK = new ArrayList<>();
            for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
                int remainSize = k - topK.size();
                if (buckets[i] == null) continue;
                if (buckets[i].size() <= remainSize) {
                    topK.addAll(buckets[i]);
                } else {
                    topK.addAll(buckets[i].subList(0, remainSize));
                }
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = topK.get(i);
            }
            return res;


        }

        public int[] topKFrequent2(int[] nums, int k) {
            // 桶思想
            // map key=元素 value=出现次数
            Map<Integer, Integer> frequencyForNum = new HashMap<>();
            for (int num : nums) {
                frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
            }
            System.out.println("frequencyForNum = " + frequencyForNum);
            //桶 下标为出现频率--即第 i 个桶中存储的数出现的频率为 i
            List<Integer>[] buckets = new ArrayList[nums.length + 1];
            for (Integer key : frequencyForNum.keySet()) {
                int frequency = frequencyForNum.get(key);
                if (buckets[frequency] == null) {
                    buckets[frequency] = new ArrayList<>();
                }
                buckets[frequency].add(key); // key出现次数为 frequency--可以理解为Map键值对映射反转
                // 举例： Map中1出现2次，3出现2次 === 桶中出现2次的有 1和3
            }
            System.out.println("buckets = " + Arrays.toString(buckets));
            List<Integer> topK = new ArrayList<>();
            for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
                if (buckets[i] == null) continue;
                if (buckets[i].size() <= (k - topK.size())) {
                    topK.addAll(buckets[i]);
                } else {
                    topK.addAll(buckets[i].subList(0, k - topK.size()));
                }
            }
            System.out.println("topK = " + topK);
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = topK.get(i);
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
