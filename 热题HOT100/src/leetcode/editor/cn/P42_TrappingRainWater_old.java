/**
 * 题目Id：42
 * 题目：接雨水
 * 日期：2021-07-14 11:38:07
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
// 👍 2477 👎 0


package leetcode.editor.cn;

//接雨水

import java.util.Stack;

public class P42_TrappingRainWater_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P42_TrappingRainWater_old().new Solution();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res = solution.trap(height);
        System.out.println("res = " + res);
    }


    // 暴力法 O(n^2)
    class Solution_暴力法 {
        public int trap(int[] height) {
            int res = 0;
            // 遍历每一列
            int len = height.length;
            for (int i = 0; i < len; i++) {
                // 下标0,len-1列无法积水
                if (i == 0 || i == len - 1) continue;
                // 下标i的水面高度= min(左侧最高,右侧最高)-i处高度
                int left_height = 0, right_height = 0;
                for (int j = i + 1; j < len; j++) {
                    right_height = Math.max(right_height, height[j]);
                }
                for (int j = 0; j < i; j++) {
                    left_height = Math.max(left_height, height[j]);
                }
                int cur_height = Math.min(left_height, right_height) - height[i];
                System.out.println("height[" + i + "] = " + height[i] +
                        "  左侧最高:" + left_height +
                        "  右侧最高:" + right_height +
                        "  盛水高度:" + cur_height);
                if (cur_height > 0) {
                    res += cur_height;
                }
            }
            return res;
        }
    }

    // 单调栈--分析看word文档
    class Solution_单调栈 {
        public int trap(int[] height) {
            int res = 0;
            // 单调栈--存储下标,从底到顶下标对应的height中元素递减
            Stack<Integer> stack = new Stack<>();
            int len = height.length;
            for (int i = 0; i < len; i++) {
                // 新来的 > 栈顶  形成"凹槽"
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peek();
                    // 凹槽下标范围 (left,i)=[left+1, i-1] 宽度为 i-left-1
                    int currWidth = i - left - 1;
                    int currHeight = Math.min(height[left], height[i]) - height[top];
                    res += currWidth * currHeight;

                    // helper
                    System.out.println("底下标top = " + top + "  左下标left = " + left + "  右下标i(right) = " + i);
                    System.out.print("currWidth = " + currWidth);
                    System.out.print("  currHeight = " + currHeight);
                    System.out.println("  雨水体积 = " + currWidth * currHeight);
                    System.out.println();
                }
                stack.push(i);
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 双指针--对暴力法的优化
    // O(n)
    class Solution {
        public int trap(int[] height) {
            int res = 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;// 左侧最大和右侧最大
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                // 若 height[left] < height[right],则必有 leftMax < rightMax
                // left 处能接到的雨水量为 leftMax-height[left] 并将 left 右移
                // 另一种情况同理

                // 也可以多设置一个变量 minOfMax--左最大/右最大二者中较小的
                int minOfMax = Math.min(leftMax, rightMax);
                if (height[left] < height[right]) {
                    // 更新left盛水 left右移
                    //res += leftMax - height[left];
                    res += minOfMax - height[left];
                    left++;
                } else {
                    // 更新right盛水 right左移
                    //res += rightMax - height[right];
                    res += minOfMax - height[right];
                    right--;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
