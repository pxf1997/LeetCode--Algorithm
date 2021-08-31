/**
 * 题目Id：55
 * 题目：跳跃游戏
 * 日期：2021-06-25 11:06:00
 */
//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 动态规划 
// 👍 1243 👎 0


package leetcode.editor.cn;

//跳跃游戏

import java.util.Arrays;

public class P55_JumpGame {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P55_JumpGame().new Solution();
        boolean b = solution.canJump(new int[]{2, 3, 1, 1, 4});
//        boolean b = solution.canJump(new int[]{3, 2, 1, 0, 4});
        System.out.println("b = " + b);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int len = nums.length;
            // dp[i]--下标i处是否可到达
            boolean[] dp = new boolean[len];
            dp[0] = true;
            for (int i = 1; i < len; i++) {
                int last = i - 1;
                while (last >= 0) {
                    // last可到达,且可以跳到下标i
                    if (dp[last] && (i - last) <= nums[last]) {
                        dp[i] = true;
                        break;
                    }
                    last--;
                }
            }
            //System.out.println("dp = " + Arrays.toString(dp));
            return dp[len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
