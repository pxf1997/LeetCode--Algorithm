/**
 * 题目Id：84
 * 题目：柱状图中最大的矩形
 * 日期：2021-08-25 10:38:23
 */
//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
// Related Topics 栈 数组 单调栈 👍 1499 👎 0


package leetcode.editor.cn;

//柱状图中最大的矩形

import java.util.ArrayDeque;
import java.util.Deque;

public class P84_LargestRectangleInHistogram {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P84_LargestRectangleInHistogram().new Solution();
        // case1
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        int res = solution.largestRectangleArea(heights);
        System.out.println("res = " + res);
    }


    // 暴力法--超时
    public class Solution_暴力法 {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            // 特判
            if (len == 0) {
                return 0;
            }
            int res = 0;
            for (int i = 0; i < len; i++) {
                // 找左边最后 1 个大于等于 heights[i] 的下标
                int left = i;
                int curHeight = heights[i];
                while (left > 0 && heights[left - 1] >= curHeight) {
                    left--;
                }

                // 找右边最后 1 个大于等于 heights[i] 的索引
                int right = i;
                while (right < len - 1 && heights[right + 1] >= curHeight) {
                    right++;
                }

                int width = right - left + 1;
                res = Math.max(res, width * curHeight);
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) {
                return 0;
            }
            if (len == 1) {
                return heights[0];
            }

            int res = 0;
            int[] newHeights = new int[len + 2];
            newHeights[0] = 0;
            System.arraycopy(heights, 0, newHeights, 1, len);
            newHeights[len + 1] = 0;
            len += 2;
            heights = newHeights;

            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);

            for (int i = 1; i < len; i++) {
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    res = Math.max(res, curHeight * curWidth);
                }
                stack.addLast(i);
            }
            return res;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
