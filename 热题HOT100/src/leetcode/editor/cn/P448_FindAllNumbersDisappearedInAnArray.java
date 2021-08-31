/**
 * 题目Id：448
 * 题目：找到所有数组中消失的数字
 * 日期：2021-08-25 22:29:08
 */
//给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数
//字，并以数组的形式返回结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,3,2,7,8,2,3,1]
//输出：[5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i] <= n 
// 
//
// 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。 
// Related Topics 数组 哈希表 👍 796 👎 0


package leetcode.editor.cn;

//找到所有数组中消失的数字

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P448_FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P448_FindAllNumbersDisappearedInAnArray().new Solution();
//        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = new int[]{1, 1};
        List<Integer> res = solution.findDisappearedNumbers(nums);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // nums长度为len, 出现了[1,len]
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> res = new ArrayList<>();
            int len = nums.length;
            // 下标--num  值--出现次数
            int[] count = new int[len + 1]; // 0不统计,[1,len]
            for (int num : nums) {
                count[num]++;
            }
//            System.out.println("count = " + Arrays.toString(count));
            for (int i = 1; i <= len; i++) {
                if (count[i] == 0) res.add(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
