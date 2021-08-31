/**
 * 题目Id：496
 * 题目：下一个更大元素 I
 * 日期：2021-07-07 15:16:07
 */
//给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
//
// 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
//
// nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
//
//
//
// 示例 1:
//
//
//输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
//输出: [-1,3,-1]
//解释:
//    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
//    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
//    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
//
// 示例 2:
//
//
//输入: nums1 = [2,4], nums2 = [1,2,3,4].
//输出: [3,-1]
//解释:
//    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
//    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
//
//
//
//
// 提示：
//
//
// 1 <= nums1.length <= nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 104
// nums1和nums2中所有整数 互不相同
// nums1 中的所有整数同样出现在 nums2 中
//
//
//
//
// 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
// Related Topics 栈 数组 哈希表 单调栈
// 👍 443 👎 0


package leetcode.editor.cn;

//下一个更大元素 I

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P496_NextGreaterElementI {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P496_NextGreaterElementI().new Solution();
        // case
//        int[] nums1 = new int[]{4, 1, 2};
//        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] nums1 = new int[]{1, 7, 3, 2};
        int[] nums2 = new int[]{2, 3, 5, 1, 0, 7, 4};


        int[] res = solution.nextGreaterElement(nums1, nums2);
        System.out.println("res = " + Arrays.toString(res));

    }


    // 暴力法,复杂度 O(len1 * len2)
    class Solution1 {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int[] res = new int[len1];
            // 遍历nums1 O(len1)
            for (int i = 0; i < len1; i++) {
                res[i] = find_next(nums2, nums1[i]);
            }
            return res;
        }

        // 定位+寻找 O(len2)
        private int find_next(int[] nums, int target) {
            int index = 0;// index--target在数组中的位置
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) index = i;
            }
            // 从index右侧开始寻找
            for (int i = index + 1; i < nums.length; i++) {
                if (nums[i] > target) return nums[i];
            }
            return -1;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 单调栈
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length;
            int[] res = new int[len1];
            int idx = 0;

            // 1--构建nums2每个元素的"下一个元素"映射关系,使用哈希表
            // key--nums2中元素, val--该元素对应的"下一个元素"
            Map<Integer, Integer> map = new HashMap<>();
            // 单调栈--新来的大则出栈更新,新来的小则入栈, 注意存储数值而非下标
            // 从下到上(底到顶)递减
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < len2; i++) {
                int cur = nums2[i];
                System.out.println("入栈元素:" + nums2[i] + "  它未入栈之前的单调栈:" + stack);
                // 新来的元素比栈顶大,不断出栈更新
                while (!stack.isEmpty() && cur > stack.peek()) {
                    int out = stack.pop();
                    map.put(out, cur);
                    System.out.println("出栈元素:" + out + "  它的右侧元素为:" + cur);
                }
                stack.push(cur);
            }
            System.out.println("map = " + map);

            // 2--遍历nums1,从map中取值即可
            for (int num : nums1) {
                // 取num的右侧元素,没有则赋值为-1
                res[idx++] = map.getOrDefault(num, -1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
