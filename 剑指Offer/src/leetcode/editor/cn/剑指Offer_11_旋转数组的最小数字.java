/**
 * 题目Id：剑指 Offer 11
 * 题目：旋转数组的最小数字
 * 日期：2021-06-15 14:43:34
 */
//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
//
// 示例 1：
//
// 输入：[3,4,5,1,2]
//输出：1
//
//
// 示例 2：
//
// 输入：[2,2,2,0,1]
//输出：0
//
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/
// Related Topics 二分查找
// 👍 330 👎 0


package leetcode.editor.cn;

//旋转数组的最小数字

public class 剑指Offer_11_旋转数组的最小数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_11_旋转数组的最小数字().new Solution();
        int minArray = solution.minArray(new int[]{2, 2, 2, 0, 1});
        System.out.println("minArray = " + minArray);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 二分查找--根据数组元素分布规律
    class Solution {
        public int minArray(int[] numbers) {
            int len = numbers.length;
            int lo = 0, hi = len - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // helper
                System.out.println("lo=" + lo + "  hi=" + hi + "  mid=" + mid);
                // 目标在[lo,mid]范围内,可能为mid
                if (numbers[mid] < numbers[hi]) {
                    hi = mid;
                }
                // 目标在[mid+1,hi]范围内
                else if (numbers[mid] > numbers[hi]) {
                    lo = mid + 1;
                } else {
                    hi = hi - 1;
                }
            }
            return numbers[lo];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
