/**
 * 题目Id：剑指 Offer 39
 * 题目：数组中出现次数超过一半的数字
 * 日期：2021-06-17 13:56:25
 */
//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 
//
// 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/ 
//
// 
// Related Topics 位运算 分治算法 
// 👍 165 👎 0


package leetcode.editor.cn;

//数组中出现次数超过一半的数字

import java.util.Arrays;

public class 剑指Offer_39_数组中出现次数超过一半的数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_39_数组中出现次数超过一半的数字().new Solution();
        int majorityElement = solution.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2});
        System.out.println("majorityElement = " + majorityElement);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            int cnt = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (cnt == 0) {
                    candidate = num;
                }
                cnt += (num == candidate ? 1 : -1);
            }
            return candidate;
        }

        public int majorityElement2(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
