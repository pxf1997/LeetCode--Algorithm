/**
 * 题目Id：462
 * 题目：最少移动次数使数组元素相等 II
 * 日期：2021-05-11 17:50:59
 */
//给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。 
//
// 例如: 
//
// 
//输入:
//[1,2,3]
//
//输出:
//2
//
//说明：
//只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）： 
//
//[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
// 
// Related Topics 数学 
// 👍 120 👎 0


package leetcode.editor.cn;

//最少移动次数使数组元素相等 II

import java.util.Arrays;

public class P462_MinimumMovesToEqualArrayElementsIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P462_MinimumMovesToEqualArrayElementsIi().new Solution();
//        int minMoves2 = solution.minMoves2(new int[]{1, 2, 3});
        int minMoves2 = solution.minMoves2(new int[]{1, 10, 2, 9, 28, 15});
        System.out.println("minMoves2 = " + minMoves2);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //思路----都移动到中位数
    class Solution {
        //朴素写法--不难发现规律----长度为偶数，中位数有两个，移动到这两个数的距离一样哦
        public int minMoves2(int[] nums) {
            Arrays.sort(nums);
            int cnt = 0;

            int mid = 0;
            if (nums.length % 2 == 1) {
                mid = nums.length / 2;
                int base = nums[mid];
                System.out.println("中位数:" + base);
                for (int num : nums) {
                    cnt += Math.abs(num - base);
                }
            } else {
                mid = nums.length / 2 - 1;
                int base1 = nums[mid];
                int cnt1 = 0;
                for (int num : nums) {
                    cnt1 += Math.abs(num - base1);
                }
                int base2 = nums[mid + 1];
                int cnt2 = 0;
                for (int num : nums) {
                    cnt2 += Math.abs(num - base2);
                }
                //helper
                System.out.println("中位数1:" + base1);
                System.out.println("中位数2:" + base2);
                //移动到这两个数的距离一样哦
                System.out.println("移动到中位数1所需步数:" + cnt1);
                System.out.println("移动到中位数2所需步数:" + cnt2);
                cnt = Math.min(cnt1, cnt2);
            }
            return cnt;

        }

        //优化
        public int minMoves2_1(int[] nums) {
            Arrays.sort(nums);
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                cnt += Math.abs(nums[nums.length / 2] - nums[i]);
            }
            return cnt;
        }
    }

    //快速选择找中位数--第k小的元素
    class Solution2 {
        public int minMoves2(int[] nums) {
            int move = 0;
            int median = findKthSmallest(nums, nums.length / 2);
            for (int num : nums) {
                move += Math.abs(num - median);
            }
            return move;
        }

        private int findKthSmallest(int[] nums, int k) {
            int lo = 0, hi = nums.length - 1;
            //二分法
            while (lo < hi) {
                int j = partition(nums, lo, hi);
                if (j == k) {
                    break;
                }
                if (j < k) {
                    lo = j + 1;
                } else {
                    hi = j - 1;
                }
            }
            return nums[k];
        }

        private int partition(int[] nums, int startIndex, int endIndex) {
            int low = startIndex, high = endIndex + 1;
            while (true) {
                while (nums[++low] < nums[startIndex] && low < endIndex) ;
                while (nums[--high] > nums[startIndex] && high > startIndex) ;
                if (low < high) {
                    swap(nums, low, high);
                } else {
                    break;
                }

            }
            swap(nums, startIndex, high);
            return high; //快排简化版---只定位base元素所在的下标
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
