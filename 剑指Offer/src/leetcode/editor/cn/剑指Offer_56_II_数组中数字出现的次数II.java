/**
 * 题目Id：剑指 Offer 56 - II
 * 题目：数组中数字出现的次数 II
 * 日期：2021-06-21 16:17:11
 */
//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// 👍 191 👎 0


package leetcode.editor.cn;

//数组中数字出现的次数 II

import java.util.Arrays;

public class 剑指Offer_56_II_数组中数字出现的次数II {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_56_II_数组中数字出现的次数II().new Solution();
        int singleNumber = solution.singleNumber(new int[]{3, 5, 5, 5, 3, 3, 4, 4, 4, -1, -1, -1, 16});
        System.out.println("singleNumber = " + singleNumber);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 哈希表统计次数就不写了,太简单
    // 位运算--思路:
    // 出现 3 次的数字--各二进制位出现的次数都是 3 的倍数。
    // 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字
    class Solution {
        public int singleNumber(int[] nums) {
            // 记录所有数字的各二进制位的 1 的出现次数
            int[] counts = new int[32];
            for (int num : nums) {
                for (int j = 0; j < 32; j++) {
                    counts[j] += num & 1; // 更新第 j 位
                    num >>>= 1; // 第 j 位 --> 第 j + 1 位
                }
            }
            System.out.println("counts = " + Arrays.toString(counts));
            int res = 0;
            // 或+左移 进行复原
            for (int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31 - i] % 3;
            }
            // 另一种写法
//            for (int i = 0; i < 32; i++) {
//                if (counts[i] % 3 != 0) {
//                    res += Math.pow(2, i);
//                }
//            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
