/**
 * 题目Id：15
 * 题目：三数之和
 * 日期：2021-06-23 11:17:47
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
// 👍 3439 👎 0


package leetcode.editor.cn;

//三数之和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P15_ThreeSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P15_ThreeSum().new Solution();
//        List<List<Integer>> res = solution.threeSum(new int[]{-2, -1, -1, -1, 3, 3, 3});
        List<List<Integer>> res = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});

        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考1--while(left<right)
    class Solution1 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> threeSum(int[] nums) {
            // 特殊处理
            int len = nums.length;
            if (nums == null || len <= 2) return res;
            Arrays.sort(nums);
            // 遍历第一个元素,后两个元素组用双指针寻找
            for (int i = 0; i < len - 2; i++) {
                // 第一个数大于 0，后面的数都比它大，肯定不成立了
                if (nums[i] > 0) break;
                // 去重
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int target = -nums[i];
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 现在要增加 left，减小 right，但是不能重复，
                        // 比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6,
                        // [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                        left++;
                        right--; // 首先无论如何先要进行加减操作
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }
    }

    // 参考2--for遍历左界left
    class Solution2 {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        public List<List<Integer>> threeSum(int[] nums) {
            int len = nums.length;
            if (nums == null || len <= 2) return res;
            Arrays.sort(nums);
            // 枚举第一个元素
            for (int i = 0; i < len; ++i) {
                // 需要和上一次枚举的数不相同
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // right 对应的指针初始指向数组的最右端
                int right = len - 1;
                int target = -nums[i];
                // 枚举 left
                for (int left = i + 1; left < len; ++left) {
                    // 需要和上一次枚举的数不相同
                    if (left > i + 1 && nums[left] == nums[left - 1]) {
                        continue;
                    }
                    // 需要保证 b 的指针在 c 的指针的左侧
                    while (left < right && nums[left] + nums[right] > target) {
                        --right;
                    }
                    // 如果指针重合，随着 b 后续的增加
                    // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                    if (left == right) {
                        break;
                    }
                    if (nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    }
                }
            }
            return res;
        }
    }

    // 重新练习
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> threeSum(int[] nums) {
            int len = nums.length;
            if (nums == null || len <= 2) return res;
            Arrays.sort(nums);
            for (int i = 0; i < len - 2; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int target = -nums[i];
                int left = i + 1, right = len - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 逻辑--移动到下一个与该元素不同的位置!
                        // 1--无论如何要走一步
                        left++;
                        right--;
                        // 2--再走若干步(重复的)
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (nums[left] + nums[right] < target) {
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
