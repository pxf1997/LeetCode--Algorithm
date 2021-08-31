/**
 * 题目Id：137
 * 题目：只出现一次的数字 II
 * 日期：2021-04-30 10:06:35
 */
//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 
// 👍 592 👎 0


//只出现一次的数字 II

import java.util.HashMap;
import java.util.Map;

public class P137_SingleNumberIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P137_SingleNumberIi().new Solution();
        int res = solution.singleNumber(new int[]{2, 2, 3, 2});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int singleNumber(int[] nums) {
            int len = nums.length;
            Map<Integer, Integer> mapForFrequency = new HashMap<>();
            for (int num : nums) {
                mapForFrequency.put(num, mapForFrequency.getOrDefault(num, 0) + 1);
            }
//            for (Integer key : mapForFrequency.keySet()) {
//                if (mapForFrequency.get(key) == 1) {
//                    return key;
//                }
//            }
//            return 0;

            int ans = 0;
            for (Map.Entry<Integer, Integer> entry : mapForFrequency.entrySet()) {
                int key = entry.getKey(), occ = entry.getValue();
                if (occ == 1) {
                    ans = key;
                    break;
                }
            }
            return ans;
        }

    }

//    依次确定每一个二进制位--比较复杂了啊，二进制运算的玩意

//    答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
//    这样一来，对于数组中的每一个元素 x，我们使用位运算 (x >> i) & i 得到 x 的第 i 个二进制位，
//    并将它们相加再对 3 取余，得到的结果一定为 0 或 1，即为答案的第 i 个二进制位。
    class Solution {
        public int singleNumber(int[] nums) {
            int ans = 0;
            for (int i = 0; i < 32; ++i) {
                int total = 0;
                for (int num : nums) {
                    System.out.println("num = " + num);
                    System.out.println("total = " + total);
                    System.out.println();
                    total += ((num >> i) & 1);     // >>右移 除以2^i  <<左移 乘2^i
                }
                if (total % 3 != 0) {
                    ans |= (1 << i);
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
