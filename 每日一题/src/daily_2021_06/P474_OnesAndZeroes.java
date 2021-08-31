/**
 * 题目Id：474
 * 题目：一和零
 * 日期：2021-06-06 11:07:27
 */
//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
// Related Topics 动态规划 
// 👍 437 👎 0


package daily_2021_06;

//一和零

public class P474_OnesAndZeroes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P474_OnesAndZeroes().new Solution();
        int maxForm = solution.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
        System.out.println("maxForm = " + maxForm);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 类似0-1背包
     * dp[k][i][j]表示对于前k个元素，能容纳i个0和j个1的最大子集的大小。
     * 1. 如果不包含第k个元素，则 dp[k][i][j] = dp[k-1][i][j]
     * 2. 如果包含第k个元素，则 dp[k][i][j] = dp[k-1][i-x][i-y]， 其中x和y分别为第k个元素包含的0和1的数量
     * 二者取大。
     */
    class Solution {
        public int findMaxForm(String[] strs, int m, int n) {
            // dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量。
            // strs--背包
            int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
            for (int i = 1; i < strs.length + 1; i++) {
                int[] count_0_1 = count_0_1(strs[i - 1]);
                for (int j = 0; j < m + 1; j++) {
                    for (int k = 0; k < n + 1; k++) {
                        dp[i][j][k] = dp[i - 1][j][k];
                        int zeros = count_0_1[0], ones = count_0_1[1];
                        if (j >= zeros && k >= ones) {
                            // 由strs[i-1] 提供 count_0_1[0]个0、count_0_1[1]个1
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                        }
                    }
                }
            }
            return dp[strs.length][m][n];
        }


        private int[] count_0_1(String s) {
            int count0 = 0, count1 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') count0++;
                else count1++;
            }
            return new int[]{count0, count1};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
