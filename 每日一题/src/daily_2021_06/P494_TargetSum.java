/**
 * 题目Id：494
 * 题目：目标和
 * 日期：2021-06-08 15:44:40
 */
//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 100 
// 
// Related Topics 深度优先搜索 动态规划 
// 👍 799 👎 0


package daily_2021_06;

//目标和

import java.util.ArrayList;
import java.util.List;

import static util.dp_util.print_2D;

public class P494_TargetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P494_TargetSum().new Solution();
        int res = solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
//        int res = solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 1);
//        int res = solution.findTargetSumWays(new int[]{0, 0, 0, 1}, 1);
        System.out.println("res = " + res);
    }


    // 二维dp--处理0挺复杂的
    class Solution_bad {
        // 分析:
        // P+N=sum P-N=target
        // P = (sum+target)/ 2
        // 选出若干个正数(添加正号+)使得和为P, 其余添加符号为N
        public int findTargetSumWays(int[] nums, int target) {
            // 1--处理0 每个0的正负号均可,因此直接在外面修正一个系数
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
            int positive = (target + sum) / 2;
            System.out.println("positive = " + positive);
            // dp[i][j]----前i个元素组合，使得和为j的组合个数
            // 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
            int[][] dp = new int[nums.length][positive + 1];
            //第一列初始化--组成的和为0，就一种--啥都不选
            for (int i = 0; i < nums.length; i++) {
                dp[i][0] = 1;
            }
            dp[0][nums[0]] = 1;

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                for (int j = 0; j < positive + 1; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - nums[i]];
                    }
                }
            }
//            print_2D(dp);
            return (int) Math.pow(2, countZero) * dp[nums.length - 1][positive];
        }

        public int[] removeKey(int[] nums, int key) {
            List<Integer> list = new ArrayList<>();
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
    }


    // 参考
    class Solution1 {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 数组 nums 中选取若干元素，使得这些元素之和等于 neg
            int diff = sum - target;
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            int len = nums.length, neg = diff / 2;
            System.out.println("sum = " + sum + "  diff = " + diff + "  neg = " + neg);
            int[][] dp = new int[len + 1][neg + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= len; i++) {
                int num = nums[i - 1];
                for (int j = 0; j <= neg; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                }
            }
//            print_2D(dp);
            return dp[len][neg];
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int len = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 要求 sum+target 为非负偶数
            if ((sum + target) % 2 != 0 || sum + target < 0) return 0;
            int positive = (sum + target) / 2;
            int[][] dp = new int[len + 1][positive + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= len; i++) {
                int cur = nums[i - 1];
                for (int j = 0; j <= positive; j++) {
                    dp[i][j] = dp[i - 1][j]; // 抄上一行(不考虑新加入的物品)
                    if (j >= cur) {
                        dp[i][j] += dp[i - 1][j - cur];
                    }
                }
            }
            System.out.println("sum = " + sum +"  target = " + target + "  positive = " + positive);
            print_2D(dp);

            return dp[len][positive];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
