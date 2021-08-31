/**
 * 题目Id：452
 * 题目：用最少数量的箭引爆气球
 * 日期：2021-04-15 14:15:42
 */
//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横
//坐标就足够了。开始坐标总是小于结束坐标。 
//
// 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xsta
//rt ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的
//最小数量。 
//
// 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。 
// 
//
// 示例 1： 
//
// 
//输入：points = [[10,16],[2,8],[1,6],[7,12]]
//输出：2
//解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球 
//
// 示例 2： 
//
// 
//输入：points = [[1,2],[3,4],[5,6],[7,8]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：points = [[1,2],[2,3],[3,4],[4,5]]
//输出：2
// 
//
// 示例 4： 
//
// 
//输入：points = [[1,2]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：points = [[2,3],[2,3]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 0 <= points.length <= 104 
// points[i].length == 2 
// -231 <= xstart < xend <= 231 - 1 
// 
// Related Topics 贪心算法 排序 
// 👍 388 👎 0


package leetcode.editor.cn;

//用最少数量的箭引爆气球

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P452_MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P452_MinimumNumberOfArrowsToBurstBalloons().new Solution();
        int minArrowShots = solution.findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});
        System.out.println("minArrowShots = " + minArrowShots);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMinArrowShots1(int[][] points) {


            if (points.length == 0) return 0;

//            没有意义的讨论--特殊的测试用例导致减法溢出
//            由于重写Comparator中的compare方法需要返回一个int类型的值，
//            一般会直接使用两数相减来直接得出这两个数的相对顺序，
//            那么不难得出【-2147483646 -2147483646】这样就会溢出了
//            所以可以使用Integer.compare(int,int)
//            或者还有一个已经实现好的Comparator(这个有点忘了，好像是Comparators里面的)
//            也或者可以自己进行比较，使用三目运算符返回-1或1

//            右端点升序
//            Arrays.sort(points, (p1, p2) -> p1[1] < p2[1] ? -1 : 1);

            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
//                    return o1[1] - o2[1];
//                    return (o1[1] < o2[1]) ? -1 : ((o1[1] == o2[1]) ? 0 : 1);
                    return Integer.compare(o1[1], o2[1]);

                }
            });

            int right = points[0][1]; //每一箭位置
            int ans = 1;
            for (int i = 1; i < points.length; i++) {
                int newleft = points[i][0];
                int newright = points[i][1];
//                if (newleft <= right) continue; //能射到此气球，不处理
//                right = newright;
//                ans++;

                if (newleft > right) { //新左界 >右界，不重叠 举例【1,2】 【3,4】
                    ans++;
                    right = newright;
                }
            }
            return ans;
        }

        public int findMinArrowShots(int[][] points) {
            System.out.println("points = " + Arrays.deepToString(points));
            System.out.println();

            int len = points.length;
            if (len == 0) return 0;
            if (len == 1) return 1;

            Arrays.sort(points, new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[1], o2[1]);
                }
            });

            System.out.println("points = " + Arrays.deepToString(points));
            System.out.println();

            int ans = 1;
            //第一箭射的位置，每一箭射的位置，气球最右侧
            int right = points[0][1];

            List<Integer> location = new ArrayList<>();
            location.add(right);

            for (int i = 1; i < len; i++) {
                int newleft = points[i][0];
                if (newleft > right) {
                    right = points[i][1];
                    ans++;

                    location.add(right);
                }
            }

            System.out.println("location = " + location);
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}