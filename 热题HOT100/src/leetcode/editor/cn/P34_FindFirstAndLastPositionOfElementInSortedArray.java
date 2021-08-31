/**
 * 题目Id：34
 * 题目：在排序数组中查找元素的第一个和最后一个位置
 * 日期：2021-07-09 17:14:31
 */
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1097 👎 0


package leetcode.editor.cn;

//在排序数组中查找元素的第一个和最后一个位置

import java.util.Arrays;

public class P34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // case
        int[] nums = new int[]{5, 7, 7, 7, 7, 8, 8, 10};
        int target = 5;
//        int[] nums = new int[]{};
//        int target = 5;

        int[] res = solution.searchRange(nums, target);

        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("res = " + Arrays.toString(res));
    }


    // 暴力法 O(n)
    class Solution1 {
        public int[] searchRange(int[] nums, int target) {
            int first = -1, last = -1;
            boolean hasFound = false;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    if (!hasFound) {
                        hasFound = true;
                        first = i; // 第一次出现位置--只记录第一次
                    }
                    last = i; // 最后一次出现位置--不断更新
                }
            }
            return new int[]{first, last};
        }
    }

    // 因为有序,所以可以二分查找,复杂度自然是 O(log(n))
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) return new int[]{-1, -1}; // 数组长度为0,有何意义呢?
            int lo = 0, hi = nums.length - 1;
            // 具体实现--根据二分法的 "细节写法" 不同,
            // 两次分别去查找--第一次出现位置及最后一次出现位置

            // 1--查找第一次位置
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 往左找----  < = > 三种情况咱写明白了,他不香吗!!!
                if (nums[mid] < target) {
                    lo = mid + 1;
                }
                // mid处元素等于target，尝试向左寻找(hi--)
                else if (nums[mid] == target) {
                    if (mid > 0 && nums[mid - 1] == nums[mid]) {
                        hi--;
                    } else {
                        // 举例--nums[mid - 1] < nums[mid] 直接返回mid位置即可
                        lo = mid;
                        break;
                    }
                } else {
                    hi = mid - 1;
                }
            }
            // 注意二分法结束,取得结果的位置是 lo
            int first = (nums[lo] == target ? lo : -1);
            System.out.println("插入点lo = " + lo + "  实际first = " + first);

            lo = 0;
            hi = nums.length - 1;
            // 2--查找最后一次位置
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 往右找----  < = > 三种情况咱写明白了,他不香吗!!!
                if (nums[mid] < target) {
                    lo = mid + 1;
                } else if (nums[mid] == target) {
                    if (mid < nums.length - 1 && nums[mid + 1] == nums[mid]) {
                        lo++;
                    } else {
                        // 举例--nums[mid + 1] > nums[mid] 直接返回mid位置即可
                        lo = mid;
                        break;
                    }
                } else {
                    hi = mid - 1;
                }
            }
            int last = (nums[lo] == target ? lo : -1);
            System.out.println("插入点lo = " + lo + "  实际last = " + last);

            return new int[]{first, last};
            // 总结--二分查找确实变数多,但也是有规律可循的
            // 变数总结---- hi,lo缩小范围的时候 "是否+1" / 比较mid元素值和target的时候 "是否加等于号"
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案
    // 评价--二分思想很简单，细节是魔鬼!!!
    class Solution3 {
        public int[] searchRange(int[] nums, int target) {
            int leftIdx = binarySearch(nums, target, true);
            int rightIdx = binarySearch(nums, target, false) - 1;
            // 得到 leftIdx, rightIdx 后进行校验
            if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
                return new int[]{leftIdx, rightIdx};
            }
            return new int[]{-1, -1};
        }
        // lower=true 第一个大于等于target的下标
        // lower=false 第一个大于target的下标
        public int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
