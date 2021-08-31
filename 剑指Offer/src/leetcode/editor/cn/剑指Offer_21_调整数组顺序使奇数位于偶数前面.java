/**
 * 题目Id：剑指 Offer 21
 * 题目：调整数组顺序使奇数位于偶数前面
 * 日期：2021-06-16 23:03:38
 */
//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
//
//
//
// 示例：
//
//
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 50000
// 1 <= nums[i] <= 10000
//
// 👍 134 👎 0


package leetcode.editor.cn;

//调整数组顺序使奇数位于偶数前面

import java.util.Arrays;

public class 剑指Offer_21_调整数组顺序使奇数位于偶数前面 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_21_调整数组顺序使奇数位于偶数前面().new Solution();
        int[] exchange = solution.exchange(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("exchange = " + Arrays.toString(exchange));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 双指针
        public int[] exchange(int[] nums) {
            int len = nums.length;
            int left = 0, right = len - 1;
            while (true) {
                // left定位到左边第一个偶数, right定位到右边第一个奇数, 进行交换
                // 类似快速排序的定位(与第一个元素base比较大小)
                while (left < len && nums[left] % 2 == 1) left++; // 奇数跳过
                while (right > 0 && nums[right] % 2 == 0) right--;// 偶数跳过
                if (left < right) {
                    swap(nums, left, right);
                } else {
                    break;
                }
            }
            return nums;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
