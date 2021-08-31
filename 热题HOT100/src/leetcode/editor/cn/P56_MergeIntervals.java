/**
 * 题目Id：56
 * 题目：合并区间
 * 日期：2021-08-23 22:10:12
 */
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics 数组 排序 👍 1069 👎 0


package leetcode.editor.cn;

//合并区间

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P56_MergeIntervals {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P56_MergeIntervals().new Solution();
        int[][] intervals = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18},
        };
//        int[][] intervals = new int[][]{
//                {1, 3},
//        };
        int[][] res = solution.merge(intervals);
        System.out.println("res = " + Arrays.deepToString(res));

    }


    // 评价--思路有问题,应按"左端点"排序而非"右端点"
    class Solution_wrong {
        public int[][] merge(int[][] intervals) {
            List<int[]> list = new ArrayList<>();
            // 按区间右端点升序排列
//            Arrays.sort(intervals, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    return o1[1] - o2[1];
//                }
//            });
            System.out.println("intervals = " + Arrays.deepToString(intervals));
            // lamda表达式--我不会!
//            Arrays.sort(intervals, ((o1, o2) -> o1[1] - o2[1]));
//            Arrays.sort(intervals, (Comparator.comparingInt(o -> o[1])));

            int right = intervals[0][1];
            int[] temp = new int[]{intervals[0][0], intervals[0][1]};

            for (int i = 0; i < intervals.length; i++) {
                int left = intervals[i][0];
                // 下一个的左边界>上一个的右边界 --不能合并
                if (left > right) {
                    System.out.println("添加区间:" + Arrays.toString(temp));
                    list.add(temp);
                    temp = new int[]{intervals[i][0], intervals[i][1]};
                }
                // 区间交叉 可以合并
                else {
                    right = intervals[i][1]; // 更新当前右边界
                    temp[1] = right;
                }
                // 注意逻辑--添加的都是"前一个",特殊情况
                if (i == intervals.length - 1) {
                    System.out.println("添加区间:" + Arrays.toString(temp));
                    list.add(temp);
                }
            }

            System.out.println("list = " + list);

            // 组装结果
            int len = list.size();
            int[][] res = new int[len][2];
            for (int i = 0; i < len; i++) {
                res[i] = list.get(i);
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) return new int[0][2];
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            List<int[]> merge = new ArrayList<>();
            // 算法流程描述--
            // 1--如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，
            //      那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
            // 2--否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，
            //      将其置为二者的较大值。
            for (int i = 0; i < intervals.length; i++) {
                int L = intervals[i][0], R = intervals[i][1];
                // 当前右界(上个区间的) < L  --即不能合并
                if (merge.size() == 0 || merge.get(merge.size() - 1)[1] < L) {
                    merge.add(new int[]{L, R});
                } else {
                    // 更新右界() --即可以合并
                    merge.get(merge.size() - 1)[1] = Math.max(merge.get(merge.size() - 1)[1], R);
                }
            }
            return merge.toArray(new int[merge.size()][2]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
