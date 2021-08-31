/**
 * 题目Id：剑指 Offer 56 - I
 * 题目：数组中数字出现的次数
 * 日期：2021-06-21 15:22:57
 */
//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2] 
//
// 
//
// 限制： 
//
// 
// 2 <= nums.length <= 10000 
// 
//
// 
// 👍 410 👎 0


package leetcode.editor.cn;

//数组中数字出现的次数

import java.util.Arrays;

public class 剑指Offer_56_I_数组中数字出现的次数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_56_I_数组中数字出现的次数().new Solution();
        int[] singleNumbers = solution.singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3});
        System.out.println("singleNumbers = " + Arrays.toString(singleNumbers));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        // 数组模拟哈希表
        public int[] singleNumbers(int[] nums) {
            int max = Arrays.stream(nums).max().getAsInt();
            int[] frequency = new int[max + 1];
            for (int num : nums) {
                frequency[num]++;
            }
            System.out.println("frequency = " + Arrays.toString(frequency));
            int[] res = new int[2];
            int idx = 0;
            for (int i = 0; i <= max; i++) {
                if (frequency[i] == 1) {
                    res[idx++] = i;
                }
            }
            return res;
        }
    }

    // 位运算--您搁那秀呢...
    // 分组异或
    // 1--两个只出现一次的数字在不同的组中
    // 2--相同的数字会被分到相同的组中
    class Solution {
        public int[] singleNumbers(int[] nums) {
            // 全部异或起来 c = a ^ b;
            int c = 0;
            for (int num : nums) {
                c = c ^ num;
            }
            // 分组--按c最低位的1划分
            //int div = c & (-c);
            int div = 1;
            while ((div & c) == 0) {
                div <<= 1;
            }
            int a = 0, b = 0;
            for (int num : nums) {
                // div位为1
                if ((div & num) != 0) {
                    a = a ^ num;
                }
                // div位为0
                else {
                    b = b ^ num;
                }
            }
            return new int[]{a, b};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
