/**
 * 题目Id：53
 * 题目：最大子序和
 * 日期：2021-04-27 16:12:27
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
// 👍 3173 👎 0


package leetcode.editor.cn;

//最大子序和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P53_MaximumSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P53_MaximumSubarray().new Solution();

//        int res = solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
//        System.out.println("res = " + res);

        int[] reslist = solution.maxSubArray_output(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("reslist = " + Arrays.toString(reslist));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maxSubArray(int[] nums) {
//        	dp[i]--以nums[i]为结尾的最大子序和
            int len = nums.length;
            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]); //逻辑：nums[i]必选，如果dp[i-1]比0大，就接在前面
            }
            System.out.println("dp = " + Arrays.toString(dp));
            return Arrays.stream(dp).max().getAsInt();
        }


        public int[] maxSubArray_output_my(int[] nums) {
            //        	dp[i]--以nums[i]为结尾的最大子序和
            int len = nums.length;
            int[] dp = new int[len];
            dp[0] = nums[0];

            for (int i = 1; i < len; i++) {
                //逻辑：nums[i]必选，如果dp[i-1]比0大，就接在前面
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
//                if (dp[i - 1] + nums[i] > nums[i]) {
//                    indexList.add(i);//记录每次状态转移--无意义（类似于背包问题记录每条斜线，咱不是按这个回溯的！）
//                }
            }
            System.out.println("dp = " + Arrays.toString(dp));

            int maxVal = Arrays.stream(dp).max().getAsInt();
            int maxIndex = 0;
            for (int i = 0; i < len; i++) {
                if (dp[i] == maxVal) {
                    maxIndex = i;
                    break;
                }
            }

//            回溯
            int n = maxIndex;
            List<Integer> indexList = new ArrayList<Integer>();
            while (n > 0) {
                if (dp[n] == dp[n - 1] + nums[n]) {
                    indexList.add(n);
                    n--;
                } else {
                    indexList.add(n);
                    break;
                }
            }
//            组装返回
            int size = indexList.size();
            int[] res = new int[size];
            for (int i = size - 1; i >= 0; i--) {
                res[i] = nums[indexList.get(i)];
            }

            return res;
        }

        public int[] maxSubArray_output(int[] nums) {
            //        	dp[i]--以nums[i]为结尾的最大子序和
            int len = nums.length;
            int[] dp = new int[len];
            dp[0] = nums[0];

            int max = nums[0];//dp全局最大值
            int left = 0, right = 0; //每段区间左右index
            int[] maxIndex = new int[2];

            for (int i = 1; i < len; i++) {
                //逻辑：nums[i]必选，如果dp[i-1]比0大，就接在前面
//                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
                if (dp[i - 1] + nums[i] > nums[i]) {
                    dp[i] = dp[i - 1] + nums[i];
                    right = i;
                } else {
                    dp[i] = nums[i];
                    left = i;
                    right = i;
                }

                if (dp[i] > max) {
                    max = dp[i];
                    maxIndex[0] = left;
                    maxIndex[1] = right;
                }
            }

            System.out.println("dp = " + Arrays.toString(dp));
            System.out.println("max_left = " + maxIndex[0]);
            System.out.println("max_right = " + maxIndex[1]);


            return Arrays.copyOfRange(nums, maxIndex[0], maxIndex[1] + 1);
        }
    }

    //分治法--极其复杂
    class Solution1 {

    }

    //leetcode submit region end(Prohibit modification and deletion)


}
