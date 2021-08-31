/**
 * 题目Id：1049
 * 题目：最后一块石头的重量 II
 * 日期：2021-06-08 11:31:34
 */
//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。 
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
// 
//
// 示例 2： 
//
// 
//输入：stones = [31,26,33,21,40]
//输出：5
// 
//
// 示例 3： 
//
// 
//输入：stones = [1,2]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 214 👎 0


package daily_2021_06;

//最后一块石头的重量 II

import java.util.Comparator;
import java.util.PriorityQueue;

import static util.dp_util.print_2D;

public class P1049_LastStoneWeightIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1049_LastStoneWeightIi().new Solution();
        int min = solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1});
//        int min = solution.lastStoneWeightII(new int[]{31, 26, 33, 21, 40});
        System.out.println("min = " + min);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my--贪心--逻辑错
    class Solution1 {
        public int lastStoneWeightII(int[] stones) {
            // 贪心法 每步优先找两个最大的石头进行粉碎
            // 逻辑是错的!!!
            int len = stones.length;
            if (len == 0) return 0;
            if (len == 1) return stones[0];
            // 为何不用最大堆呢?
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                public int compare(Integer int1, Integer int2) {
                    return int2 - int1;
                }
            });
            for (int stone : stones) {
                maxHeap.offer(stone);
            }
            System.out.println("maxHeap = " + maxHeap);
            System.out.println();
            while (maxHeap.size() > 1) {
                // 每次出队最大的两个元素
                int max1 = maxHeap.poll();
                int max2 = maxHeap.poll();
                int newStone = Math.abs(max1 - max2);
                if (newStone > 0) {
                    System.out.println("粉碎后不为0, 添加回最大堆: " + newStone);
                    maxHeap.offer(newStone);
                }
                System.out.println("两个最大元素:" + max1 + "," + max2);
                System.out.println("maxHeap = " + maxHeap);
                System.out.println();
            }


            return maxHeap.isEmpty() ? 0 : maxHeap.poll();
        }
    }

    // 参考答案--dp
    // 分析看题解,如何转化为昨天的 P494题目标和
    class Solution {
        public int lastStoneWeightII(int[] stones) {
            int len = stones.length;
            int sum = 0;
            for (int i : stones) sum += i;
            int t = sum / 2;
            System.out.println("sum = " + sum + "  t = " + t);
            // 正部分=sum - dp[len][t]  负部分=dp[len][t]  结果=正部分-负部分
            // 负部分逻辑含义--从 stones 数组中选择，凑成总和不超过  sum/2 的最大价值
            int[][] dp = new int[len + 1][t + 1];
            for (int i = 1; i <= len; i++) {
                int x = stones[i - 1];
                for (int j = 0; j <= t; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= x) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] + x);
                }
            }
            print_2D(dp);

            return Math.abs(sum - dp[len][t] - dp[len][t]);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
