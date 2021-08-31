/**
 * 题目Id：1838
 * 题目：最高频元素的频数
 * 日期：2021-07-19 09:48:14
 */
//元素的 频数 是该元素在一个数组中出现的次数。 
//
// 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。 
//
// 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,4], k = 5
//输出：3
//解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
//4 是数组中最高频元素，频数是 3 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,4,8,13], k = 5
//输出：2
//解释：存在多种最优解决方案：
//- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
//- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
//- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,9,6], k = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 1 <= k <= 105 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 
// 👍 69 👎 0


package daily_2021_07;

//最高频元素的频数

import java.util.Arrays;

public class P1838_FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1838_FrequencyOfTheMostFrequentElement().new Solution();
        // case1
        int[] nums = new int[]{1, 2, 4};
        int k = 5;

        int maxFrequency = solution.maxFrequency(nums, k);
        System.out.println("maxFrequency = " + maxFrequency);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 滑动窗口
    class Solution {
        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            int len = nums.length;
            long total = 0;
            int left = 0, res = 1;
            // 滑动窗口--枚举nums[right]作为目标值
            for (int right = 1; right < len; right++) {
                // 右边界 r 右移到 r+1
                // 元素个数--[l, r-1] 共 r-l 个
                // 每个元素需改变的值--nums[r]-nums[r-1]
                total += (long) (nums[right] - nums[right - 1]) * (right - left);
                // 左边界 l 右移
                while (total > k) {
                    total -= nums[right] - nums[left];
                    left++;
                }
                res = Math.max(res, right - left + 1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
