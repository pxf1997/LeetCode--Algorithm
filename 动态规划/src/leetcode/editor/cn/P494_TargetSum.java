/**
 * 题目Id：494
 * 题目：目标和
 * 日期：2021-04-22 22:05:31
 */
//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选
//择一个符号添加在前面。 
//
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。 
//
// 
//
// 示例： 
//
// 输入：nums: [1, 1, 1, 1, 1], S: 3
//输出：5
//解释：
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//一共有5种方法让最终目标和为3。
// 
//
// 
//
// 提示： 
//
// 
// 数组非空，且长度不会超过 20 。 
// 初始的数组的和不会超过 1000 。 
// 保证返回的最终结果能被 32 位整数存下。 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 647 👎 0


package leetcode.editor.cn;

//目标和

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static leetcode.editor.cn.dp_util.print_DP_2;

public class P494_TargetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P494_TargetSum().new Solution();
        int[] nums = {1, 1, 1, 1, 1};
//        int[] nums2 = {1, 2, 1};
        int res = solution.findTargetSumWays(nums, 3);
//        int res2 = solution.findTargetSumWays(nums2, 0);
//        int res3 = solution.findTargetSumWays_1D(nums2, 1);


//        System.out.println(res2);
//        System.out.println(res3);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
/*   思想：
    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
    or
    dp[i][j + nums[i]] += dp[i - 1][j]
    dp[i][j - nums[i]] += dp[i - 1][j]
    技巧为----我们给 dp[i][j] 的第二维预先增加 1000*/
//    public class Solution1 {
//        public int findTargetSumWays(int[] nums, int target) {
//            int sum = 0;
//            for (int k : nums) {
//                sum += k;
//            }
//            sum = Math.abs(sum);
//            System.out.println("sum = " + sum);
//
///*            int[][] dp = new int[nums.length][2001];
//            dp[0][nums[0] + 1000] = 1;
//            dp[0][-nums[0] + 1000] += 1;
//            for (int i = 1; i < nums.length; i++) {
//                for (int j = -1000; j <= 1000; j++) {
//                    if (dp[i - 1][j + 1000] > 0) {
//                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
//                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
//                    }
//                }
//            }*/
//            int[][] dp = new int[nums.length][2 * sum + 1];
//            dp[0][nums[0] + sum] = 1;
//            dp[0][-nums[0] + sum] = 1;
//            print_DP_2(dp);
//            System.out.println();
//            for (int i = 1; i < nums.length; i++) {
////              每次更新dp的一行（新的一个物品）
//                for (int j = -sum; j <= sum; j++) {
//                    if (dp[i - 1][j + sum] > 0) {
//                        dp[i][j + nums[i] + sum] += dp[i - 1][j + sum];
//                        dp[i][j - nums[i] + sum] += dp[i - 1][j + sum];
//                    }
//                }
//            }
//            print_DP_2(dp);
//            return target > sum ? 0 : dp[nums.length - 1][target + sum];
//        }
//    }


    class Solution {
//        问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
//        可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：
//        sum(P) - sum(N) = target
//        sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
//        2 * sum(P) = target + sum(nums)
//        因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums))/2，就证明存在解。  思考--是排列不是组合

        // 一个很他妈逼傻逼的例子--{0,0,0,0,0,0,0,0,1}
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            int countZero = 0;
            for (int num : nums) {
                if (num == 0) {
                    countZero++; // 记录0的个数 最后根据个数修正结果
                }
                sum += num;
            }
            if (Math.abs(sum) < Math.abs(target)) return 0;
            if (Math.abs(sum) == Math.abs(target)) return (int) Math.pow(2, countZero);

            nums = removeKey(nums, 0); // 0全部删掉


            if ((target + sum) % 2 == 1) return 0;
            int key = (target + sum) / 2;
            System.out.println("key = " + key);
//            dp[i][j]----前i个元素组合，使得和为j的组合个数
//            状态转移方程：dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
            int[][] dp = new int[nums.length][key + 1];
            //第一列初始化--组成的和为0，就一种--啥都不选
            for (int i = 0; i < nums.length; i++) {
                dp[i][0] = 1;
            }
            dp[0][nums[0]] = 1;

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                for (int j = 0; j < key + 1; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= num) {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                    }
                }
            }
            print_DP_2(dp);
            return (int) Math.pow(2, countZero) * dp[nums.length - 1][key];
        }

        public int[] removeKey(int[] nums, int key) {
            List<Integer> list = new ArrayList<Integer>();
            for (int num : nums) {
                if (num != key) list.add(num);
            }
            int size = list.size();
            int[] res = new int[size];
            for (int i = 0; i < size; i++) {
                res[i] = list.get(i);
            }
            return res;
        }


        public int findTargetSumWays_1D(int[] nums, int S) {
            int sum = computeArraySum(nums);
            if (sum < S || (sum + S) % 2 == 1) {
                return 0;
            }
            int W = (sum + S) / 2;
            int[] dp = new int[W + 1];
            dp[0] = 1;
            for (int num : nums) {
                for (int i = W; i >= num; i--) {
                    dp[i] = dp[i] + dp[i - num];
                }
            }
            return dp[W];
        }

        private int computeArraySum(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
