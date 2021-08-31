/**
 * 题目Id：198
 * 题目：打家劫舍
 * 日期：2021-04-15 15:00:38
 */
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1387 👎 0


package 打家劫舍;

//打家劫舍
/*      如果房屋数量大于两间，应该如何计算能够偷窃到的最高总金额呢？对于第 k(k>2) 间房屋，有两个选项：
        偷窃第 k 间房屋，那么就不能偷窃第 k-1间房屋，偷窃总金额为前 k-2间房屋的最高总金额与第 k间房屋的金额之和。
        不偷窃第 k 间房屋，偷窃总金额为前 k-1间房屋的最高总金额。
        在两个选项中选择偷窃总金额较大的选项，该选项对应的偷窃总金额即为前 k 间房屋能偷窃到的最高总金额。
        用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
        *** dp方程 dp[i] = max(dp[i-2]+nums[i], dp[i-1])
        *** 边界条件
            dp[0]=nums[0]              只有一间房屋，则偷窃该房屋
            dp[1]=max(nums[0],nums[1]) 只有两间房屋，选择其中金额较高的房屋进行偷窃*/


public class P198_HouseRobber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P198_HouseRobber().new Solution();
        int[] test = {2, 7, 9, 3, 1};
        int res = solution.rob(test);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
//            特殊情况处理
            if (len == 0) return 0;
            if (len == 1) return nums[0];
//            dp表的书写：包括①状态转移方程 ②边界条件（初始条件）
            int[] dp = new int[len];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[len - 1];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
