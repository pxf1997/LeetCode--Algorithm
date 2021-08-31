/**
 * 题目Id：15
 * 题目：三数之和
 * 日期：2021-06-02 15:52:46
 */
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 3387 👎 0


package leetcode.editor.cn;

//三数之和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15_ThreeSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P15_ThreeSum().new Solution();
//        List<List<Integer>> res = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        List<List<Integer>> res = solution.threeSum(new int[]{-2, 0, 0, 2, 2});
        List<List<Integer>> res = solution.threeSum(new int[]{0, 0, 0, 0});
//        List<List<Integer>> res = solution.threeSum(new int[]{-4, -3, -2, 6, 7});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //  双指针 O(n^2)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3) return res;
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                //  排序之后如果第一个元素已经大于零，那么无论如何组合都不可能凑成三元组，直接返回结果就可以了
                if (nums[i] > 0) {
                    return res;
                }
                // 老剪枝了
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // a对应下标i  b对应下标left c对应下标right
                int left = i + 1;
                int right = len - 1;
                while (left < right) {
                    // 去重复逻辑如果放在这里，0，0，0 的情况，可能直接导致 right<=left 了，从而漏掉了 0,0,0 这种三元组
//                    while (left < right && nums[left] == nums[left + 1] ) left++;
//                    while (left < right && nums[right] == nums[right - 1]) right--;

                    int sum = nums[left] + nums[right] + nums[i];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 去重逻辑应该放在找到一个三元组之后----短路与逻辑中 left < right 放在前面,保证left下标不越界
                        // 常见写法----定位到第一个与自己元素值不同的位置之前
                        while (left < right && nums[left] == nums[left + 1] ) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        // 找到答案时，双指针同时收缩
                        left++;
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
