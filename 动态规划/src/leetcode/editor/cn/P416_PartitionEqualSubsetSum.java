/**
 * 题目Id：416
 * 题目：分割等和子集
 * 日期：2021-04-22 15:31:41
 */
//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 755 👎 0


package leetcode.editor.cn;

//分割等和子集

public class P416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P416_PartitionEqualSubsetSum().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
/*        在「填表格」的时候，当前行只参考了上一行的值，因此状态数组可以只设置 2 行，使用「滚动数组」的技巧「填表格」即可；

        实际上，在「滚动数组」的基础上还可以优化，在「填表格」的时候，
        当前行总是参考了它上面一行 「头顶上」 那个位置和「左上角」某个位置的值。
        因此，我们可以只开一个一维数组，从后向前依次填表即可。*/

//        使用一维表格，并且「从后向前」填表格的代码。
        public boolean canPartition(int[] nums) {
            int len = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if ((sum & 1) == 1) {
                return false;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            if (nums[0] <= target) {
                dp[nums[0]] = true;
            }


            for (int i = 1; i < len; i++) {
                for (int j = target; nums[i] <= j; j--) {
                    if (dp[target]) {
                        return true;
                    }
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
            return dp[target];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
