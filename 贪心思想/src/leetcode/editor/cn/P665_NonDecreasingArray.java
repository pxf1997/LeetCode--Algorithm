/**
 * 题目Id：665
 * 题目：非递减数列
 * 日期：2021-04-19 11:25:00
 */
//给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。 
//
// 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [4,2,3]
//输出: true
//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
// 
//
// 示例 2: 
//
// 
//输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10 ^ 4 
// - 10 ^ 5 <= nums[i] <= 10 ^ 5 
// 
// Related Topics 数组 
// 👍 567 👎 0


package leetcode.editor.cn;

//非递减数列

public class P665_NonDecreasingArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P665_NonDecreasingArray().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*     在出现 nums[i] < nums[i - 1] 时，需要考虑的是应该修改数组的哪个数，
               使得本次修改能使 i 之前的数组成为非递减数组，并且 不影响后续的操作 。
               优先考虑令 nums[i - 1] = nums[i]，因为如果修改 nums[i] = nums[i - 1] 的话，
               那么 nums[i] 这个数会变大，就有可能比 nums[i + 1] 大，从而影响了后续操作。
               还有一个比较特别的情况就是 nums[i] < nums[i - 2]，
               修改 nums[i - 1] = nums[i] 不能使数组成为非递减数组，只能修改 nums[i] = nums[i - 1]*/
        public boolean checkPossibility(int[] nums) {
            int err = 0;
            for (int i = 1; i < nums.length && err < 2; i++) {
                if (nums[i] < nums[i - 1]) {
                    err++;
                    //举例 2,3,1,5 变1为3
                    if (i - 2 >= 0 && nums[i - 2] > nums[i]) {//举例 2,3,1,5 变1为3
                        nums[i] = nums[i - 1];
                    } else { //举例 1,3,2,5 变3为2
                        nums[i - 1] = nums[i];
                    }
                }
            }
            return err <= 1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
