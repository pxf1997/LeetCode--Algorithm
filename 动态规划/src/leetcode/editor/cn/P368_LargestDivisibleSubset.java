/**
 * 题目Id：368
 * 题目：最大整除子集
 * 日期：2021-04-23 10:33:17
 */
//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
// 
// answer[i] % answer[j] == 0 ，或 
// answer[j] % answer[i] == 0 
// 
//
// 如果存在多个有效解子集，返回其中任何一个均可。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 2 * 109 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数学 动态规划 
// 👍 249 👎 0


package leetcode.editor.cn;

//最大整除子集

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P368_LargestDivisibleSubset {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P368_LargestDivisibleSubset().new Solution();
        int[] nums = {1,2,3,4,8,10};
        List<Integer> res = solution.largestDivisibleSubset(nums);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        状态定义：dp[i] 表示在输入数组nums 升序排列的前提下，以nums[i] 为最大整数的「整除子集」的大小（nums[i] 必须被选择）
//        状态转移方程：枚举 j = 0----i−1 的所有整数 nums[j]，
//          如果nums[j] 能整除nums[i]，说明nums[i] 可以扩充在以 nums[j] 为最大整数的整除子集里成为一个更大的整除子集。
//        输出是难点

        public List<Integer> largestDivisibleSubset(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);

            // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
            int[] dp = new int[len];
            Arrays.fill(dp, 1);
            int maxSize = 1; //最大整除子集的大小
            int maxVal = dp[0];//该最大子集中的最大整数
            System.out.println("dp = " + Arrays.toString(dp));

            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0) { //续上
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
//              更新完一个dp后--二维的一列
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    maxVal = nums[i];
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
            System.out.println("maxSize = " + maxSize);
            System.out.println("maxVal = " + maxVal);
            // 第 2 步：倒推获得最大子集
            List<Integer> res = new ArrayList<Integer>();
            if (maxSize == 1) {
                res.add(nums[0]);
                return res;
            }

            for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
                if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                    res.add(nums[i]);
                    maxVal = nums[i]; // 迭代更新maxVal 确保彼此都能整除
                    maxSize--;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
