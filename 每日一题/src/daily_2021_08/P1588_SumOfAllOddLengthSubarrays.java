/**
 * 题目Id：1588
 * 题目：所有奇数长度子数组的和
 * 日期：2021-08-29 00:18:44
 */
//给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。 
//
// 子数组 定义为原数组中的一个连续子序列。 
//
// 请你返回 arr 中 所有奇数长度子数组的和 。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,4,2,5,3]
//输出：58
//解释：所有奇数长度子数组和它们的和为：
//[1] = 1
//[4] = 4
//[2] = 2
//[5] = 5
//[3] = 3
//[1,4,2] = 7
//[4,2,5] = 11
//[2,5,3] = 10
//[1,4,2,5,3] = 15
//我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58 
//
// 示例 2： 
//
// 输入：arr = [1,2]
//输出：3
//解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。 
//
// 示例 3： 
//
// 输入：arr = [10,11,12]
//输出：66
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 100 
// 1 <= arr[i] <= 1000 
// 
// Related Topics 数组 前缀和 👍 70 👎 0


package daily_2021_08;

//所有奇数长度子数组的和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1588_SumOfAllOddLengthSubarrays {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1588_SumOfAllOddLengthSubarrays().new Solution();
        int[] arr = new int[]{1, 4, 2, 5, 3};
        int sum = solution.sumOddLengthSubarrays(arr);
        System.out.println("sum = " + sum);
    }
    // 思路--生成所有奇数长度的子集即可
    // 评价--反而搞复杂了,因为题目要求--原数组中的一个连续子序列
    class Solution_wrong {
        List<List<Integer>> res = new ArrayList<>();
        int sum = 0;

        public int sumOddLengthSubarrays(int[] arr) {
            for (int i = 1; i <= arr.length; i += 2) {
                // 生成长度为 i 的子数组--子数组是组合
                List<Integer> path = new ArrayList<>();
                // 回溯生成子集
                backtracking(arr, path, 0, i);
            }
            return sum;
        }

        /**
         * @param arr        数组
         * @param path       生成的子集
         * @param beginIndex 起始下标
         * @param limit      元素个数限制
         */
        private void backtracking(int[] arr, List<Integer> path, int beginIndex, int limit) {
            if (path.size() == limit) {
                System.out.println("path = " + path);
                sum += sum_helper(path);
                res.add(new ArrayList<>(path));
            }
            for (int i = beginIndex; i < arr.length; i++) {
                path.add(arr[i]);

                backtracking(arr, path, i + 1, limit);

                path.remove(path.size() - 1);
            }
        }

        private int sum_helper(List<Integer> path) {
            int n = 0;
            for (Integer x : path) {
                n += x;
            }
            return n;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)


    // 暴力法--复杂度O(n^2)
    class Solution {
        int sum = 0;

        public int sumOddLengthSubarrays(int[] arr) {
            // 所有长度为i的子数组
            for (int len = 1; len <= arr.length; len += 2) {
                System.out.println("len = " + len);
                for (int start = 0; start < arr.length; start++) {
                    // 下标范围[start, end)
                    // [start, end-1]共 end-start=len 个元素
                    int end = start + len;
                    if (end <= arr.length) {
                        int[] cur = Arrays.copyOfRange(arr, start, end);
//                        System.out.println("cur = " + Arrays.toString(cur));
                        sum += Arrays.stream(cur).sum();
                    }
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
