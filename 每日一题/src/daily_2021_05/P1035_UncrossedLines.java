package daily_2021_05; /**
 * 题目Id：1035
 * 题目：不相交的线
 * 日期：2021-05-21 09:45:54
 */
//在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。 
//
// 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足： 
//
// 
// nums1[i] == nums2[j] 
// 且绘制的直线不与任何其他连线（非水平线）相交。 
// 
//
// 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。 
//
// 以这种方法绘制线条，并返回可以绘制的最大连线数。 
//
// 
//
// 示例 1： 
//
//
// 
//输入：nums1 = [1,4,2], nums2 = [1,2,4]
//输出：2
//解释：可以画出两条不交叉的线，如上图所示。 
//但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相
//交。
// 
//
// 
// 示例 2： 
//
// 
//输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
//输出：3
// 
//
// 
// 示例 3： 
//
// 
//输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
//输出：2 
//
// 
// 
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 500 
// 1 <= nums2.length <= 500 
// 1 <= nums1[i], nums2[i] <= 2000 
// 
//
// 
// Related Topics 数组 
// 👍 136 👎 0


//不相交的线

import util.dp_util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1035_UncrossedLines {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1035_UncrossedLines().new Solution();
        int maxUncrossedLines = solution.maxUncrossedLines(
                new int[]{2, 5, 1, 2, 5},
                new int[]{10, 5, 2, 1, 5, 2}
        );
        System.out.println("maxUncrossedLines = " + maxUncrossedLines);
    }

    // 直观法--找所有相交线,判断相交----复杂而且没法弄!
    class Solution_my {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums1[i] == nums2[j]) {
                        list.add(new int[]{i, j});
                    }
                }
            }
            dp_util.print_ListWithArrays(list);

            int[][] matrix = new int[list.size()][list.size()];
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    boolean cross = isCross(list.get(i), list.get(j));
                    if (cross) {
                        System.out.println(Arrays.toString(list.get(i)) + "与" + Arrays.toString(list.get(j)) + "相交");
                        matrix[i][j] = matrix[j][i] = 1;
                    }
//                    if (!cross) {
//                        System.out.println(Arrays.toString(list.get(i)) + "与" + Arrays.toString(list.get(j)) + "不相交");
//                    }
                }
            }
            dp_util.print_2D(matrix);
            return 0;
        }

        private boolean isCross(int[] line1, int[] line2) {
            if (line1[0] == line2[0] || line1[1] == line2[1]) {
                return true;
            }
            int e1, e2;
            if (line1[0] < line2[0]) {
                e1 = line1[1];
                e2 = line2[1];
            } else {
                e1 = line2[1];
                e2 = line1[1];
            }
            //  保证s1<s2 比较e1 e2
            return e1 > e2;
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // dp法
    class Solution {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length;
            //  分析:k条线 每条线值相等,不相交----相对顺序不变
            //  可转化为求最长公共子序列

            //  dp[i][j] 统计到 nums1[i] nums2[j]时,最长公共子序列长度
            int[][] dp = new int[len1 + 1][len2 + 1];
            //  边界条件--第一行第一列
            for (int i = 0; i < len1 + 1; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i < len2 + 1; i++) {
                dp[0][i] = 0;
            }
            StringBuilder sb = new StringBuilder(); // 需要记录最终值回溯的斜线部分
            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        sb.append(nums1[i - 1]);  // 记录所有斜线--记录得多了!
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            //  如何回溯出最长公共子序列


            System.out.println("sb = " + sb);
            dp_util.print_2D(dp);
            return dp[len1][len2];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
