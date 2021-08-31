/**
 * 题目Id：560
 * 题目：和为K的子数组
 * 日期：2021-05-29 21:36:17
 */
//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
// 
//
// 说明 : 
//
// 
// 数组的长度为 [1, 20,000]。 
// 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。 
// 
// Related Topics 数组 哈希表 
// 👍 921 👎 0


//和为K的子数组

import java.util.HashMap;
public class P560_SubarraySumEqualsK {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P560_SubarraySumEqualsK().new Solution();
//        int cnt = solution.subarraySum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10);
        int cnt = solution.subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7);
        System.out.println("cnt = " + cnt);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 前缀和 + 哈希表---不完善
    class Solution1 {
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;
            // preSum[i]= 下标 0 到 i-1 的所有元素之和
            // 推导出共识 区间 [i,j] 元素和 = preSum[j+1] - preSum[i]
            int[] preSum = new int[len + 1];
            int cur = 0;
            for (int i = 0; i < len; i++) {
                cur += nums[i];
                preSum[i + 1] = cur;
            }
//            System.out.println("preSum = " + Arrays.toString(preSum));

            // 子区间 preSum[j+1] - preSum[i] = k
            // preSum[j+1] = preSum[i] + k

            // O(n^2) 平方复杂度--双重循环遍历preSum
            int cnt = 0;
            for (int i = 0; i < len + 1; i++) {
                for (int j = i + 1; j < len + 1; j++) {
                    if (preSum[j] - preSum[i] == k) {
//                        System.out.println("preSum[" + j + "] - preSum[" + i + "] == " + k);
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }

    // 参考
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int cnt = 0, preSum = 0;
            // key--前缀和  value--此前缀和出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                if (map.containsKey(preSum - k)) {
                    cnt += map.get(preSum - k);
                }
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            }
            System.out.println("map = " + map);
            return cnt;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
