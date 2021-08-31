/**
 * 题目Id：1109
 * 题目：航班预订统计
 * 日期：2021-08-31 10:58:00
 */
//这里有 n 个航班，它们分别从 1 到 n 进行编号。 
//
// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 
//firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。 
//
// 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
// 
//
// 示例 2： 
//
// 
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2 * 10⁴ 
// 1 <= bookings.length <= 2 * 10⁴ 
// bookings[i].length == 3 
// 1 <= firsti <= lasti <= n 
// 1 <= seatsi <= 10⁴ 
// 
// Related Topics 数组 前缀和 👍 213 👎 0


package daily_2021_08;

//航班预订统计

import java.util.*;

public class P1109_CorporateFlightBookings {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1109_CorporateFlightBookings().new Solution();

        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        int[] res = solution.corpFlightBookings(bookings, n);
        System.out.println("res = " + Arrays.toString(res));

    }

    // 暴力法--逻辑简单,但是超时!!!
    class Solution_my {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] res = new int[n];
            // 列表数组--数组中的元素是 list集合
            List<Integer>[] help = new List[n];
            for (int i = 0; i < n; i++) {
                help[i] = new ArrayList<>();
            }
            for (int[] book : bookings) {
                int first = book[0] - 1, last = book[1] - 1, value = book[2];
                for (int i = first; i <= last; i++) {
                    help[i].add(value);
                }
            }
            // helper
//            for (int i = 0; i < n; i++) {
//                System.out.println("help[" + i + "] = " + help[i]);
//            }
            for (int i = 0; i < n; i++) {
                res[i] = sum_List(help[i]);
            }
            return res;
        }

        private int sum_List(List<Integer> integers) {
            int sum = 0;
            for (Integer integer : integers) {
                sum += integer;
            }
            return sum;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 参考答案--  差分数组 / 前缀和
    // 举例--{1,2,2,4} 差分数组为{1,1,0,2} 即i个数为 nums[i]-nums[i-1]

    // 性质--
    // 差分数组的前缀和 = 原数组
    // 差分数组的性质是，当我们希望对原数组的某一个区间 [l,r] 施加一个增量inc 时，
    // 差分数组 dd 对应的改变是：d[l] 增加inc，d[r+1] 减少 inc。

    // 这样对于区间的修改就变为了对于两个位置的修改。
    // 并且这种修改是可以叠加的，即当我们多次对原数组的不同区间施加不同的增量，
    // 我们只要按规则修改差分数组即可。

    class Solution {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] res = new int[n];
            // 构建差分数组
            for (int[] booking : bookings) {
                //  booking结构--{l, r, inc}
                // d[l-1]增加inc, d[r]减小inc
                int l = booking[0] - 1, r = booking[1], value = booking[2];
                res[l] += value;
                if (r < n) {
                    res[r] -= value;
                }
                System.out.println("l = " + l + "  r = " + r + "  value = " + value);
                System.out.println("res = " + Arrays.toString(res));
                System.out.println();
            }

            // 差分数组求前缀和--还原为原数组
            for (int i = 1; i < n; i++) {
                res[i] += res[i - 1];
            }
            return res;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)


}
