/**
 * 题目Id：881
 * 题目：救生艇
 * 日期：2021-08-26 10:00:38
 */
//第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。 
//
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。 
//
// 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。 
//
// 
//
// 示例 1： 
//
// 输入：people = [1,2], limit = 3
//输出：1
//解释：1 艘船载 (1, 2)
// 
//
// 示例 2： 
//
// 输入：people = [3,2,2,1], limit = 3
//输出：3
//解释：3 艘船分别载 (1, 2), (2) 和 (3)
// 
//
// 示例 3： 
//
// 输入：people = [3,5,3,4], limit = 5
//输出：4
//解释：4 艘船分别载 (3), (3), (4), (5) 
//
// 提示： 
//
// 
// 1 <= people.length <= 50000 
// 1 <= people[i] <= limit <= 30000 
// 
// Related Topics 贪心 数组 双指针 排序 👍 133 👎 0


package daily_2021_08;

//救生艇

import java.util.Arrays;

public class P881_BoatsToSavePeople {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P881_BoatsToSavePeople().new Solution();
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        int count = solution.numRescueBoats(people, limit);
        System.out.println("count = " + count);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--能否贪心--优先载体重小的人!
    // 评价--思路错误,每艘船坐2人,优先考虑 大+小
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            int count = 0;
            Arrays.sort(people);
            int light = 0, heavy = people.length - 1;
            while (light < heavy) {
                if (people[light] + people[heavy] <= limit) {
                    light++;
                }
                // heavy无法同乘,单独分配一条船给heavy
                heavy--;
                count++;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
