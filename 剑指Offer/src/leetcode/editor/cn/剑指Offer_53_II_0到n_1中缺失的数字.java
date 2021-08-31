/**
 * 题目Id：剑指 Offer 53 - II
 * 题目：0～n-1中缺失的数字
 * 日期：2021-06-21 11:39:52
 */
//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出
//这个数字。
//
//
//
// 示例 1:
//
// 输入: [0,1,3]
//输出: 2
//
//
// 示例 2:
//
// 输入: [0,1,2,3,4,5,6,7,9]
//输出: 8
//
//
//
// 限制：
//
// 1 <= 数组长度 <= 10000
// Related Topics 数组 二分查找
// 👍 148 👎 0


package leetcode.editor.cn;

//0～n-1中缺失的数字

public class 剑指Offer_53_II_0到n_1中缺失的数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_53_II_0到n_1中缺失的数字().new Solution();
//        int missingNumber = solution.missingNumber(new int[]{0});
        int missingNumber = solution.missingNumber(new int[]{0, 1, 2, 3, 4, 6, 7, 8});
        System.out.println("missingNumber = " + missingNumber);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 以缺失的数字将nums分为左右两部分
    // 左--nums[i]=i 右--num[i]!=i
    class Solution {
        public int missingNumber(int[] nums) {
            int lo = 0, hi = nums.length - 1;
            // 跳出while时 lo=hi+1 交错
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] == mid) lo = mid + 1;
                else hi = mid - 1;
            }
            System.out.println("lo = " + lo + "  hi = " + hi);
            return lo;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
