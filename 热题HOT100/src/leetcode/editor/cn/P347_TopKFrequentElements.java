/**
 * 题目Id：347
 * 题目：前 K 个高频元素
 * 日期：2021-07-08 17:40:53
 */
//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 
// 👍 795 👎 0


package leetcode.editor.cn;

//前 K 个高频元素

import java.util.*;

public class P347_TopKFrequentElements {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P347_TopKFrequentElements().new Solution();
        // case1
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 4, 4, 4, 4};
        int k = 2;


        int[] res = solution.topKFrequent(nums, k);
        System.out.println("res = " + Arrays.toString(res));
    }

    // HashMap统计次数 + 按次数排序(存入最大堆)
    // 评价----你在炫技嘛,最小堆(优先队列,存entry)+定制排序
    class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            // 1--map统计出现次数
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            System.out.println("map = " + map);
            // 2--优先队列--最小堆统计出现次数
            // 比堆顶次数大--弹出堆顶,入堆新entry
            // 定制排序注意--堆存的是啥,Comparator泛型就是啥(按什么比)

            // 1--土写法
//            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k,
//                    new Comparator<Map.Entry<Integer, Integer>>() {
//                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                            return o1.getValue() - o2.getValue();
//                        }
//                    });
            // 2--lamda表达式
            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, (o1, o2) -> o1.getValue() - o2.getValue());
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (minHeap.size() < k) {
                    minHeap.offer(entry);
                } else {
                    // 堆满了,当前entry次数 > 堆顶次数 则入堆
                    if (entry.getValue() > minHeap.peek().getValue()) {
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
            System.out.println("minHeap = " + minHeap);
            // 3--返回结果
            int[] res = new int[k];
            int idx = 0;
            while (!minHeap.isEmpty()) {
                Map.Entry<Integer, Integer> cur = minHeap.poll();
                System.out.println("元素值:" + cur.getKey() + "  出现次数:" + cur.getValue());
                res[(k - 1) - idx] = cur.getKey();
                idx++;
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 大道至简
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // 1--map统计出现次数
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            // 2--map的entry取出来到list里,
            // 定制排序(Comparator的泛型你根本不用写!!!)--按次数降序(key--元素 value--次数)
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<>() {
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            //System.out.println("list = " + list);
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = list.get(i).getKey();
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
