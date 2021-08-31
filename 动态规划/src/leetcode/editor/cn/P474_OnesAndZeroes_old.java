/**
 * 题目Id：474
 * 题目：一和零
 * 日期：2021-04-25 10:48:07
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
// 👍 380 👎 0


package leetcode.editor.cn;

//一和零

public class P474_OnesAndZeroes_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P474_OnesAndZeroes_old().new Solution();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int res = solution.findMaxForm(strs, 5, 3);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int findMaxForm(String[] strs, int m, int n) {
/*            这是一个多维费用的 0-1 背包问题，有两个背包大小，0 的数量和 1 的数量。
            思路：把总共的 0 和 1 的个数视为背包的容量，每一个字符串视为装进背包的物品。
            这道题就可以使用 0-1 背包问题的思路完成，这里的目标值是能放进背包的字符串的数量。*/

//            dp[i][j]逻辑解释----在strs数组中，包含 i个0，j个1 的最大子集（子集中元素的个数）
            if (strs == null || strs.length == 0) {
                return 0;
            }
            int[][] dp = new int[m + 1][n + 1];
//            dp_util.print_DP_2(dp);
//            System.out.println();
            for (String s : strs) {    // 每个字符串只能用一次
                //记录 0和1的个数
                int ones = 0, zeros = 0;
                for (char c : s.toCharArray()) {
                    if (c == '0') {
                        zeros++;
                    } else {
                        ones++;
                    }
                }
                for (int i = m; i >= zeros; i--) {
                    for (int j = n; j >= ones; j--) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    }
                }
//                dp_util.print_DP_2(dp);
//                System.out.println();
            }

            return dp[m][n];

        }
    }

    public class Solution {

        public int findMaxForm(String[] strs, int m, int n) {
            int len = strs.length;
//            定义状态：尝试题目问啥，就把啥定义成状态。
//            dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量。
//            三维----物品维度-i 背包0重量维度-j 背包1重量维度-k

            int[][][] dp = new int[len + 1][m + 1][n + 1];

            for (int i = 1; i <= len; i++) {
                // 注意：有一位偏移
                int[] count = countZeroAndOne(strs[i - 1]);
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        // 先把上一行抄下来
                        dp[i][j][k] = dp[i - 1][j][k];
                        int zeros = count[0];
                        int ones = count[1];
                        if (j >= zeros && k >= ones) {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                        }
                    }
                }
            }
            return dp[len][m][n];
        }

        private int[] countZeroAndOne(String str) {
            int[] cnt = new int[2];
            for (char c : str.toCharArray()) {
                cnt[c - '0']++;
            }
            return cnt;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
