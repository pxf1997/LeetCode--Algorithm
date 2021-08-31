/**
 * 题目Id：169
 * 题目：多数元素
 * 日期：2021-07-01 16:46:10
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
// Related Topics 数组 哈希表 分治 计数 排序 
// 👍 1044 👎 0


package leetcode.editor.cn;

//多数元素

import java.util.Arrays;

public class P169_MajorityElement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P169_MajorityElement().new Solution();
        int majorityElement = solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2});
        System.out.println("majorityElement = " + majorityElement);
    }


    class Solution1 {
        // 摩尔投票法
        public int majorityElement(int[] nums) {
            Integer candidate = null;
            int cnt = 0;
            for (int num : nums) {
                if (cnt == 0) {
                    candidate = num;
                }
                cnt += (num == candidate ? 1 : -1);
                //System.out.println("num = " + num + "  candidate = " + candidate + "  cnt = " + cnt);
            }
            return candidate;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            //System.out.println("nums = " + Arrays.toString(nums));
            return nums[nums.length / 2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
