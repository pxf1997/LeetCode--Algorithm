/**
 * 题目Id：1893
 * 题目：检查是否区域内所有整数都被覆盖
 * 日期：2021-07-23 09:48:41
 */
//给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 star
//ti 到 endi 的 闭区间 。 
//
// 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。 
//
// 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了
//。 
//
// 
//
// 示例 1： 
//
// 
//输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
//输出：true
//解释：2 到 5 的每个整数都被覆盖了：
//- 2 被第一个区间覆盖。
//- 3 和 4 被第二个区间覆盖。
//- 5 被第三个区间覆盖。
// 
//
// 示例 2： 
//
// 
//输入：ranges = [[1,10],[10,20]], left = 21, right = 21
//输出：false
//解释：21 没有被任何一个区间覆盖。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ranges.length <= 50 
// 1 <= starti <= endi <= 50 
// 1 <= left <= right <= 50 
// 
// Related Topics 数组 哈希表 前缀和 
// 👍 33 👎 0


package daily_2021_07;

//检查是否区域内所有整数都被覆盖

import java.util.Arrays;
import java.util.Comparator;

public class P1893_CheckIfAllTheIntegersInARangeAreCovered {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1893_CheckIfAllTheIntegersInARangeAreCovered().new Solution();
        int[][] range = new int[][]{
                {1, 2},
                {3, 4},
                {5, 6},
        };
        int left = 2, right = 5;
        solution.isCovered(range, left, right);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 暴力法
    // 复杂度分析 -- ranges长度为n, [left,right]长度为m, 复杂度O(n*m)
    class Solution {
        public boolean isCovered(int[][] ranges, int left, int right) {
            // 对ranges按左界排序
            Arrays.sort(ranges, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            // 枚举[left, right],依次检查每个数是否被"覆盖"
            for (int i = left; i <= right; i++) {
                if (!isCovered_helper(i, ranges)) {
                    return false;
                }
            }
            return true;
        }

        // 检查num是否被覆盖
        private boolean isCovered_helper(int num, int[][] ranges) {
            for (int[] range : ranges) {
                if (range[0] <= num && range[1] >= num) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
