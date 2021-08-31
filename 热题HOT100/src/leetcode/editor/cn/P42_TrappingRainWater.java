/**
 * 题目Id：42
 * 题目：接雨水
 * 日期：2021-07-15 09:49:27
 */
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2482 👎 0


package leetcode.editor.cn;

//接雨水

public class P42_TrappingRainWater {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P42_TrappingRainWater().new Solution();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res = solution.trap(height);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 双指针
    class Solution {
        public int trap(int[] height) {
            int res = 0;
            int len = height.length;
            int leftMax = 0, rightMax = 0;
            int left = 0, right = len - 1;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                int minOfMax = Math.min(leftMax, rightMax);
                // 左右指针,谁小移动谁,更新这个坐标的盛水量----
                // 宽度为1,高度为 minOfMax-height[下标]
                if (height[left] < height[right]) {
                    System.out.println("下标:" + left + "  盛水量:" + (minOfMax - height[left]));
                    res += minOfMax - height[left];
                    left++;
                } else {
                    System.out.println("下标:" + right + "  盛水量:" + (minOfMax - height[right]));
                    res += minOfMax - height[right];
                    right--;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
