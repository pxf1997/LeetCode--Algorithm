/**
 * 题目Id：53
 * 题目：最大子序和
 * 日期：2021-04-19 11:48:16
 */
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 3147 👎 0


package leetcode.editor.cn;

//最大子序和



//import com.sun.tools.javac.util.ArrayUtils;
//import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;

public class P53_MaximumSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P53_MaximumSubarray().new Solution();
        int[] test = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = solution.maxSubArray(test);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            if (len == 1) return nums[0];

//            int preSum = nums[0];
//            int maxSum = preSum;
//            for (int i = 1; i < len; i++) {
//                preSum = preSum > 0 ? preSum + nums[i] : nums[i];
//                maxSum = Math.max(maxSum, preSum);
//            }

            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            }
//            int maxSum = findmax(dp);
            int maxSum = Arrays.stream(dp).max().getAsInt();

            return maxSum;
        }

        public int findmax(int[] data) {
            int max = Integer.MIN_VALUE;
            for (int x : data) {
                if (x > max) max = x;
            }
            return max;
        }




//        我们可以考虑 nums[i] 单独成为一段还是加入f(i−1) 对应的那一段
//        DP--  f(i)=max{ f(i−1)+nums[i], nums[i] }

//        考虑到 f(i) 只和 f(i−1) 相关，
//        于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少，
//        从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。

        public int maxSubArray1(int[] nums) {
//            pre即为f(i)函数--以i为结束点的子数列最大和  maxAns为全局之最大
            int pre = 0, maxAns = nums[0];

            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
