/**
 * 题目Id：11
 * 题目：盛最多水的容器
 * 日期：2021-06-18 11:08:47
 */
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 2538 👎 0


package leetcode.editor.cn;

//盛最多水的容器

public class P11_ContainerWithMostWater {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P11_ContainerWithMostWater().new Solution();
        int maxArea = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println("maxArea = " + maxArea);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--两次循环O(n^2)必然超时
    // 2 <= n <= 3 * 104

    // 动态规划角度
    // 有重复的子问题--效率低下 O(n^2)
    class Solution1 {
        // 递归的底--dp最小子问题
        // maxArea(i, j) where j - i == 1 = min(h[i], h[j])
        // maxArea(i, j) = max(
        //.   [(j - i) * min(h[i], h[j]),
        //.    // We can omit one of below.
        //.    maxArea(i+1, j),
        //.    maxArea(i, j-1)])
        public int maxArea(int[] height) {
            return maxAreaWork(height, 0, height.length - 1);
        }

        int maxAreaWork(int[] height, int i, int j) {
            if (j - i == 1) return Math.min(height[i], height[j]);

            int primaryChoose = (j - i) * Math.min(height[i], height[j]);
            int secondaryChoose = height[i] > height[j]
                    ? maxAreaWork(height, i, j - 1)
                    : maxAreaWork(height, i + 1, j);
            return Math.max(primaryChoose, secondaryChoose);
        }


    }

    // 参考--双指针
    class Solution {
        // 每次向内移动高度较小的指针,使高度有可能变大
        public int maxArea(int[] height) {
            int max = 0, len = height.length;
            int left = 0, right = len - 1;
            while (left < right) {
                int area = Math.min(height[left], height[right]) * (right - left);
                max = Math.max(max, area);
                // helper
                System.out.println("left = " + left + "  right = " + right + "  area = " + area);
                if (height[left] <= height[right]) left++;
                else right--;
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
