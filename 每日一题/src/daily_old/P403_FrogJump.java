package daily_old;

/**
 * 题目Id：403
 * 题目：青蛙过河
 * 日期：2021-04-29 10:49:28
 */
//一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。 
//
// 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。 
//
// 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [0,1,3,5,6,8,12,17]
//输出：true
//解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然
//后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。 
//
// 示例 2： 
//
// 
//输入：stones = [0,1,2,3,4,8,9,11]
//输出：false
//解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。 
//
// 
//
// 提示： 
//
// 
// 2 <= stones.length <= 2000 
// 0 <= stones[i] <= 231 - 1 
// stones[0] == 0 
// 
// Related Topics 动态规划 
// 👍 221 👎 0


//青蛙过河

public class P403_FrogJump {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P403_FrogJump().new Solution();
        boolean res = solution.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canCross_1(int[] stones) {
/*          动态规划
            dp[i][k] 表示能否由前面的某一个石头 j 通过跳 k 步到达当前这个石头 i ，这个 j 的范围是[1, i - 1]
            当然，这个 k 步是 i 石头 和 j 石头之间的距离
            那么对于 j 石头来说，跳到 j 石头的上一个石头的步数就必须是这个 k -1 || k || k + 1
            由此可得状态转移方程：dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1]
*/
            int len = stones.length;
            if (stones[1] != 1) { // 结论--第一步只能跳1格
                return false;
            }
            boolean[][] dp = new boolean[len][len + 1];
            dp[0][0] = true;
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    int distance = stones[i] - stones[j];
                    if (distance <= j + 1) { // 结论--第j步最多跳j格：即每步递增跳1,2,3,4,....
                        dp[i][distance] = dp[j][distance - 1] || dp[j][distance] || dp[j][distance + 1];
                        //调试
//                        System.out.println("index=" + i + " 位置" + stones[i] + " 通过跳跃长度 = " + distance + " "+ dp[i][distance]);
                        //提前结束循环直接返回结果
                        if (i == len - 1 && dp[i][distance]) {
                            return true;
                        }
                    }
                }
                System.out.println();
            }


            return false;
        }

        public boolean canCross(int[] stones) {
            int n = stones.length;
            boolean[][] dp = new boolean[n][n];
            dp[0][0] = true;
            for (int i = 1; i < n; ++i) {
                if (stones[i] - stones[i - 1] > i) {
                    return false;
                }
            }
            for (int i = 1; i < n; ++i) {
                for (int j = i - 1; j >= 0; --j) {
                    int k = stones[i] - stones[j];
                    if (k > j + 1) {
                        break;
                    }
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == n - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
