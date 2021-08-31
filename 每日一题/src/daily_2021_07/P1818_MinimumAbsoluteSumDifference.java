/**
 * 题目Id：1818
 * 题目：绝对差值和
 * 日期：2021-07-14 10:12:04
 */
//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。 
//
// 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始
//）。 
//
// 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。 
//
// 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。 
//
// |x| 定义为： 
//
// 
// 如果 x >= 0 ，值为 x ，或者 
// 如果 x <= 0 ，值为 -x 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
//输出：0
//解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
//输出：20
//解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
//绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
// 
//
// 
//
// 提示： 
//
// 
// n == nums1.length 
// n == nums2.length 
// 1 <= n <= 105 
// 1 <= nums1[i], nums2[i] <= 105 
// 
// Related Topics 贪心 数组 二分查找 有序集合 
// 👍 48 👎 0


package daily_2021_07;

//绝对差值和

import java.util.Arrays;

public class P1818_MinimumAbsoluteSumDifference {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1818_MinimumAbsoluteSumDifference().new Solution();
        int[] nums1 = new int[]{1, 18, 21};
        int[] nums2 = new int[]{9, 21, 20};
        int res = solution.minAbsoluteSumDiff(nums1, nums2);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--
    // 单个二元组 {nums1[i], nums2[i]} 对答案的贡献为 |nums1[i] - nums2[i]|
    // nums1[j]替换nums1[i]后对答案的贡献为 |nums1[j] - nums2[i]|,前后差值为:
    // |nums1[i] - nums2[i]| -  |nums1[j] - nums2[i]|, 需要最大化此差值!
    class Solution {
        public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
            final int MOD = 1000000007;
            int len = nums1.length;
            int[] rec = new int[len];
            System.arraycopy(nums1, 0, rec, 0, len);
            Arrays.sort(rec);

            // sum--所有差值和  maxn--最大的改变前后的差值 答案为 sum-maxn
            int sum = 0, maxn = 0;
            for (int i = 0; i < len; i++) {
                int diff = Math.abs(nums1[i] - nums2[i]);
                sum = (sum + diff) % MOD;
                // 二分查找 nums1 中尽可能接近 nums2[i] 的元素
                int j = binarySearch(rec, nums2[i]);
                if (j < len) {
                    maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
                }
                if (j > 0) {
                    maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
                }
            }
            return (sum - maxn + MOD) % MOD;
        }

        public int binarySearch(int[] rec, int target) {
            int lo = 0, hi = rec.length - 1;
            if (rec[hi] < target) {
                return hi + 1;
            }
            while (lo < hi) {
                int mid = (hi - lo) / 2 + lo;
                if (rec[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
