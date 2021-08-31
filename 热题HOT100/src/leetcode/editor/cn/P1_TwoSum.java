/**
 * 题目Id：1
 * 题目：两数之和
 * 日期：2021-06-22 15:17:50
 */
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 只会存在一个有效答案 
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？ 
// Related Topics 数组 哈希表 
// 👍 11390 👎 0


package leetcode.editor.cn;

//两数之和

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1_TwoSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1_TwoSum().new Solution();
//        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        int[] ints = solution.twoSum(new int[]{3, 2, 4}, 6);
//        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 999);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 排序肯定是不可行的,双指针也不可行
        public int[] twoSum(int[] nums, int target) {
            // 考虑:
            // key--下标, value--元素值 (考虑到元素值可以重复)
            // 问题--你没法查啊,要找的是 target-nums[i]

            // 更改做法为:
            // key--nums[i], value--下标
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                } else {
                    map.put(nums[i], i);
                }
            }
            return new int[0];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}
