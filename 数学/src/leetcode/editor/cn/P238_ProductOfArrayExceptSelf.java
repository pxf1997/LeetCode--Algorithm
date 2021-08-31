/**
 * 题目Id：238
 * 题目：除自身以外数组的乘积
 * 日期：2021-05-12 15:13:18
 */
//给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之
//外其余各元素的乘积。 
//
// 
//
// 示例: 
//
// 输入: [1,2,3,4]
//输出: [24,12,8,6] 
//
// 
//
// 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。 
//
// 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 进阶： 
//你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。） 
// Related Topics 数组 
// 👍 816 👎 0


package leetcode.editor.cn;

//除自身以外数组的乘积

import java.util.Arrays;

public class P238_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P238_ProductOfArrayExceptSelf().new Solution();
//        int[] res = solution.productExceptSelf(new int[]{1, 2, 3, 4});
//        int[] res = solution.productExceptSelf(new int[]{-1, 1, 0, -3, 3});
        int[] res = solution.productExceptSelf(new int[]{4, 5, 1, 8, 2});
        System.out.println("res = " + Arrays.toString(res));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 不是一种思路----写新Solution
    // 一种思路代码不同(通常为改进优化)----在同一个Solution里写新方法
    // 用乘法 = 开挂  处理0的问题--挺恶心的
    class Solution1 {
        public int[] productExceptSelf(int[] nums) {
            int product = 1;
            int cntZero = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    product *= nums[i];
                } else {
                    cntZero++;
                }
            }

            System.out.println("非零元素乘积=" + product);
            int[] res = new int[nums.length];

            //全0的特例就很搞--CTMD
            if (cntZero == nums.length) {
                System.out.println("全是0玩你妈呢");
                return res;
            }

            for (int i = 0; i < nums.length; i++) {
                //没 0
                if (cntZero == 0) {
                    res[i] = product / nums[i];
                }
                //有 0
                else {
                    if (nums[i] == 0 && cntZero == 1) { //有0 && nums[i]==0 && 单独一个0
                        res[i] = product;
                    } else { //有0 && nums[i]!=0
                        res[i] = 0;
                    }
                }
            }
            return res;
        }
    }

    // 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    // 思路 左右乘积列表
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int len = nums.length;
            // L 和 R 分别表示左右两侧的乘积列表
            int[] L = new int[len];
            int[] R = new int[len];
            int[] res = new int[len];

            // L[i] 为索引 i 左侧所有元素的乘积
            // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
            L[0] = 1;
            for (int i = 1; i < len; i++) {
                L[i] = L[i - 1] * nums[i - 1];
            }

            // R[i] 为索引 i 右侧所有元素的乘积
            // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
            R[len - 1] = 1;
            for (int i = len - 2; i >= 0; i--) {
                R[i] = nums[i + 1] * R[i + 1];
            }
            // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
            for (int i = 0; i < len; i++) {
                res[i] = L[i] * R[i];
            }
            System.out.println("nums = " + Arrays.toString(nums));
            System.out.println("L =    " + Arrays.toString(L));
            System.out.println("R =    " + Arrays.toString(R));
            System.out.println("res =  " + Arrays.toString(res));
            return res;
        }

        public int[] productExceptSelf_1(int[] nums) {
            int length = nums.length;
            int[] answer = new int[length];

            // answer[i] 表示索引 i 左侧所有元素的乘积
            // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
            answer[0] = 1;
            for (int i = 1; i < length; i++) {
                answer[i] = nums[i - 1] * answer[i - 1];
            }

            // R 为右侧所有元素的乘积
            // 刚开始右边没有元素，所以 R = 1
            int R = 1;
            for (int i = length - 1; i >= 0; i--) {
                // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
                answer[i] = answer[i] * R;
                // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
                R *= nums[i];
            }
            return answer;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
