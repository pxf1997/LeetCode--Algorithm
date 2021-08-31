/**
 * 题目Id：300
 * 题目：最长递增子序列
 * 日期：2021-04-21 15:26:55
 */
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 二分查找 动态规划 
// 👍 1543 👎 0


package leetcode.editor.cn;

//最长递增子序列


import java.util.Arrays;

public class P300_LongestIncreasingSubsequence_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P300_LongestIncreasingSubsequence_old().new Solution();
        int[] test = {10, 9, 2, 5, 3, 7, 21, 18};
        int res = solution.lengthOfLIS(test);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int lengthOfLIS(int[] nums) {
//			dp[i] 的值代表 nums 前 i 个数字的最长子序列长度。
//			转移方程：
//			1--当 nums[i]>nums[j] 时： nums[i] 可以接在 nums[j] 之后（此题要求严格递增），
//			   此情况下最长上升子序列长度为 dp[j]+1
//			2--当 nums[i]<=nums[j] 时： nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。

//			转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)

            int len = nums.length;
            int[] dp = new int[len];
            int res = 0;
            Arrays.fill(dp, 1);
            for (int i = 0; i < len; i++) {
                int max = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                    dp[i] = max;
                }
            }

//            for (int i = 0; i < len; i++) {
//                res = Math.max(res, dp[i]);
//            }
            res = Arrays.stream(dp).max().getAsInt();
            return res;

        }
    }

    class Solution {
/*        定义一个 tails 数组，其中 tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素。对于一个元素 x，

        如果它大于 tails 数组所有的值，那么把它添加到 tails 后面，表示最长递增子序列长度加 1；
        如果 tails[i-1] < x <= tails[i]，那么更新 tails[i] = x。*/

        public int lengthOfLIS(int[] nums) {
            int length = nums.length;
            int[] tails = new int[length];
            int len = 0;
            for (int num : nums) {
//               len = 当前所需tails数组长度
//               那么按说搜索，endIndex应该为 len-1 才对，为什么搜索len呢--考虑这种情况
//               key比所有元素都大，应该插入位置就是 len 即追加在最后一个之后！
                int index = binarySearch(tails, len, num);
                tails[index] = num;
                if (index == len) {
                    len++;
                }

            }
            return len;

        }

        private int binarySearch(int[] tails, int endIndex, int key) {

            int l = 0, h = endIndex;
            while (l < h) {
                int mid = l + (h - l) / 2;
                if (tails[mid] == key) {
                    return mid;
                } else if (tails[mid] > key) {
                    h = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
