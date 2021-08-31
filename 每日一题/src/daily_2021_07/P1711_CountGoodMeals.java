/**
 * 题目Id：1711
 * 题目：大餐计数
 * 日期：2021-07-07 09:57:48
 */
//大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。 
//
// 你可以搭配 任意 两道餐品做一顿大餐。 
//
// 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大
//餐 的数量。结果需要对 109 + 7 取余。 
//
// 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。 
//
// 
//
// 示例 1： 
//
// 
//输入：deliciousness = [1,3,5,7,9]
//输出：4
//解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
//它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
// 
//
// 示例 2： 
//
// 
//输入：deliciousness = [1,1,1,3,3,3,7]
//输出：15
//解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。 
//
// 
//
// 提示： 
//
// 
// 1 <= deliciousness.length <= 105 
// 0 <= deliciousness[i] <= 220 
// 
// Related Topics 数组 哈希表 
// 👍 54 👎 0


package daily_2021_07;

//大餐计数

import java.util.HashMap;
import java.util.Map;

public class P1711_CountGoodMeals {
    public static void main(String[
                                    ] args) {
        //测试代码
        Solution solution = new P1711_CountGoodMeals().new Solution();
//        int res = solution.countPairs(new int[]{1, 3, 5, 7, 9});
        int res = solution.countPairs(new int[]{1, 1, 1, 3, 3, 3, 7});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--
    // 朴素法,找出每一对判断和是否是2的幂,O(n^2)会超时,改用哈希表
    // 数组中的最大元素记为 maxVal,两元素和不可能超过 2*maxVal
    // maxSum=2*maxVal 两元素和为不超过maxSum的某个2的幂
    class Solution {
        public int countPairs(int[] deliciousness) {
            final int MOD = 1000000007;
            int maxVal = 0;
            for (int num : deliciousness) {
                maxVal = Math.max(maxVal, num);
            }
            int maxSum = 2 * maxVal;
            int res = 0;
            // key--元素数值  val--个数
            Map<Integer, Integer> map = new HashMap<>();
            int len = deliciousness.length;
            for (int i = 0; i < len; i++) {
                int key = deliciousness[i];
                // 两元素和为2的幂,这个幂值的范围为[0, maxSum]
                for (int sum = 1; sum <= maxSum; sum <<= 1) {
                    int cnt = map.getOrDefault(sum - key, 0);
                    res = (res + cnt) % MOD;
                }
                map.put(key, map.getOrDefault(key, 0) + 1);// 更新map
            }
            // helper
            System.out.print("maxVal = " + maxVal);
            System.out.println("  maxSum = " + maxSum);
            System.out.println("map = " + map);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
