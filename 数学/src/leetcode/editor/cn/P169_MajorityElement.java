/**
 * 题目Id：169
 * 题目：多数元素
 * 日期：2021-05-12 11:38:56
 */
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 位运算 数组 分治算法 
// 👍 977 👎 0


package leetcode.editor.cn;

//多数元素

import java.util.Arrays;

public class P169_MajorityElement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P169_MajorityElement().new Solution();
        int majorityElement = solution.majorityElement(new int[]{
                7, 7, 5, 7, 5, 1,
                5, 7,
                5, 5, 7, 7,
                7, 7, 7, 7
        });
        System.out.println("majorityElement = " + majorityElement);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //排序法
    class Solution1 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    //摩尔投票法
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (count == 0) {
                    System.out.println("众数改变咯");
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
                System.out.println("nums[" + i + "]=" + num + "  candidate=" + candidate + "  count=" + count);
                System.out.println();
            }

            return candidate;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
