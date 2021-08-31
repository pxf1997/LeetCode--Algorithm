package daily_2021_06; /**
 * 题目Id：523
 * 题目：连续的子数组和
 * 日期：2021-06-02 10:17:32
 */
//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组： 
//
// 
// 子数组大小 至少为 2 ，且 
// 子数组元素总和为 k 的倍数。 
// 
//
// 如果存在，返回 true ；否则，返回 false 。 
//
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。 
//
// 示例 2： 
//
// 
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
// 
//
// 示例 3： 
//
// 
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 109 
// 0 <= sum(nums[i]) <= 231 - 1 
// 1 <= k <= 231 - 1 
// 
// Related Topics 数学 动态规划 
// 👍 267 👎 0


//连续的子数组和

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P523_ContinuousSubarraySum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P523_ContinuousSubarraySum().new Solution();
//        boolean res = solution.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6);
        boolean res = solution.checkSubarraySum(new int[]{23, 2, 4, 6, 6}, 7);
//        boolean res = solution.checkSubarraySum(new int[]{5, 0, 0, 0}, 3);
//        boolean res = solution.checkSubarraySum(new int[]{0, 1}, 2);
        System.out.println("res = " + res);
    }


    // 暴力--超时 没有用到已有结论
    class Solution1 {
        public boolean checkSubarraySum(int[] nums, int k) {
            if (nums.length < 2 || nums == null) {
                return false;
            }
            // memo = true 已经算过且无法找到
            boolean[] memo = new boolean[nums.length]; // i为结尾 不能得到结论

            // 子数组大小至少为2  0也算数
            for (int i = 0; i < nums.length; i++) {
                int sum = 0, len = 0;
                for (int j = i; j >= 0; j--) {
                    sum += nums[j];
                    len++;
                    if (sum % k == 0 && len >= 2) {
//                        System.out.println("找到--区间起始下标:" + j + "  结束下标:" + i + "  和:" + sum);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 前缀数组sum，sum[i]表示前i个元素的和。
     * 子数组nums[i..j]的和 subNum = sum[j+1]-sum[i];
     * ※同余定理： subNum % k == 0，等价于 sum[j+1] % k == sum[i] % k ！！！(就j>i)
     */
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            int[] sum = new int[nums.length + 1];
            for (int i = 1; i < sum.length; ++i) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
            System.out.println("sum = " + Arrays.toString(sum));

            HashMap<Integer, Integer> mod = new HashMap();  // 保存余数对应的下标
            for (int i = 0; i < sum.length; ++i) {
                int sumMod = sum[i] % k;
                if (mod.containsKey(sumMod) && i > mod.get(sumMod) + 1) {
                    System.out.println("起始:" + mod.get(sumMod) + "  终止:" + i + "  相同余数:" + sumMod);
                    return true;
                } else if (!mod.containsKey(sumMod)) { // 只在不存在key时更新，保证子数组长度尽可能大。
                    mod.put(sumMod, i);
                }
            }
            return false;
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        public boolean checkSubarraySum(int[] nums, int k) {
            int len = nums.length;
            if (len < 2) {
                return false;
            }
            int[] sum = new int[nums.length + 1];
            for (int i = 1; i < sum.length; ++i) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }

            //  key--余数  val--第一次出现下标
            Map<Integer, Integer> map = new HashMap<>();
            // 余数为0的处理
            map.put(0, 0);
            int remainder;
            for (int i = 1; i < len + 1; i++) {
                // 前缀和呢
                remainder = sum[i] % k;
                if (map.containsKey(remainder)) {
                    int prevIndex = map.get(remainder);
                    if (i - prevIndex >= 2) {
                        return true;
                    }
                } else {
                    map.put(remainder, i); // 前缀和i 对应原数组 i-1
                }
            }
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
