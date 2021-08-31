/**
 * 题目Id：435
 * 题目：无重叠区间
 * 日期：2021-04-15 11:25:55
 */
//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。 
//
// 注意: 
//
// 
// 可以认为区间的终点总是大于它的起点。 
// 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
// Related Topics 贪心算法 
// 👍 401 👎 0


package leetcode.editor.cn;

//无重叠区间

import java.util.Arrays;
import java.util.Comparator;

public class P435_NonOverlappingIntervals {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P435_NonOverlappingIntervals().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

/*      题目描述：计算让一组区间不重叠所需要移除的区间个数。
        先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
        在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
        按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。*/

        public int eraseOverlapIntervals2(int[][] intervals) {
            if (intervals.length == 0) return 0;
//            我们对按照右端点排好序的区间进行遍历
//            Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

//            不会lamda表达式，不会用API就老老实实的自己写！！！
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1]; //升序
                }
            });
            int count = intervals.length;
            int right = intervals[0][1]; //右界
            int ans = 1;
            for (int i = 1; i < count; i++) {
                if (intervals[i][0] >= right) {
                    ans++;
                    right = intervals[i][1];
                }
            }
            return count - ans;


        }

        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) return 0;
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
//                    return o1[1] - o2[1];
                    return (o1[1] < o2[1]) ? -1 :( (o1[1] == o2[1]) ? 0 : 1 );
                }
            });
            int len = intervals.length;
            int right = intervals[0][1];
            int ans = 1;
            for (int i = 1; i < len; i++) {
                int newleft = intervals[i][0];
                int newright = intervals[i][1];
                if (newleft >= right) { //新左界 >= 右界，不重叠 举例【1,2】 【2,3】
                    right = newright;
                    ans++;
                }
            }
            return len - ans;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
