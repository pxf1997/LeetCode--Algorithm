/**
 * 题目Id：377
 * 题目：组合总和 Ⅳ
 * 日期：2021-04-24 22:33:33
 */
//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。 
//
// 题目数据保证答案符合 32 位整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
// 
//
// 示例 2： 
//
// 
//输入：nums = [9], target = 3
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 1000 
// nums 中的所有元素 互不相同 
// 1 <= target <= 1000 
// 
//
// 
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？ 
// Related Topics 动态规划 
// 👍 399 👎 0


package leetcode.editor.cn;

//组合总和 Ⅳ

public class P377_CombinationSumIv_test {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P377_CombinationSumIv_test().new Solution();
        int[] nums = {2, 3, 5};
        int res = solution.combinationSum4_1(nums, 10);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int combinationSum4_1(int[] nums, int target) {
//            先分析--无限背包、排列而非组合
//            背包的两种不同的概念--1：能拿的物品数量有限（N<nums.length）
//                            --2:物品无限--可以重复使用

//            ！！！此代码的问题----
//            第一个问题----没有考虑重复使用物品--如何改造成可以无限使用每个物品呢?  已经解决
//            第二个问题----输出的是组合而不是排列

//            先不考虑特殊情况好吧--比如nums为null or target为0
//            dp[i][j]可不可以直接照抄上一行----nums[i]元素是否必须被使用
//            dp[i][j]逻辑含义  nums的前i+1个元素中（下标为0到i），总和为j的组合个数
//            状态转移方程 dp[i][j] = dp[i-1][j] + dp[i-k][j-k * nums[i]]
            int[][] dp = new int[nums.length][target + 1];
//            第一列全部为1
            for (int i = 0; i < nums.length; i++) {
                dp[i][0] = 1;
            }
//            第一行
            int k0 = target / nums[0]; //最多放进k个
            for (int i = 0; i <= k0; i++) {
                dp[0][i * nums[0]] = 1;
            }

            dp_util.print_DP_2(dp);
            System.out.println();
/*//          第一列--target=0 总和为0，一个都不选--一种（很恶心的特例来了，target=0，nums[i]=0 可选可不选 ）
//            幸好啊幸好--	'nums' must consist of values from 1 to 1000 only
//            if (target == 0) {
//                int countZero = 0;
//                for (int num : nums) {
//                    if(num==0) countZero++;
//                }
//                return (int) Math.pow(2,countZero);
//            }*/
            for (int i = 1; i < nums.length; i++) {
//              第i种物品最多选V/c[i]件
                int thisNum = nums[i];
                for (int j = 0; j <= target; j++) {
                    dp[i][j] = dp[i - 1][j];
                    int ki = target / thisNum; //最多放进ki个 nums[i]
                    for (int k = 1; k <= ki; k++) {
                        if (i >= k && j >= k * thisNum) {
                            dp[i][j] += dp[i - k][j - k * thisNum]; //加入 k 件 nums[i]
                        }
                    }
                }
            }
//            for (int i = 1; i <= target; i++) {
//                for (int j = 1; j < nums.length; j++) {
//                    dp[j][i] = dp[j - 1][i];
//                    int ki = i / nums[j];
//                    for (int k = 1; k <= ki; k++) {
//                        if (j >= k && i >= k * nums[j]) {
//                            dp[j][i] += dp[j - k][i - k * nums[j]];
//                        }
//                    }
//                }
//            }


            dp_util.print_DP_2(dp);
            return dp[nums.length - 1][target];
        }

        public int combinationSum4(int[] nums, int t) {
            // 因为 nums[i] 最小值为 1，因此构成答案的最大长度为 target
            int len = t;
            int[][] f = new int[len + 1][t + 1];
            f[0][0] = 1;
            int ans = 0;
            for (int i = 1; i <= len; i++) {
                for (int j = 0; j <= t; j++) {
                    for (int num : nums) {
                        if (j >= num) f[i][j] += f[i - 1][j - num];
                    }
                }
                ans += f[i][t];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
