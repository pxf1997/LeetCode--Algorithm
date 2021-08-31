/**
 * 题目Id：540
 * 题目：有序数组中的单一元素
 * 日期：2021-04-20 13:52:22
 */
//给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。 
//
// 示例 1: 
//
// 
//输入: [1,1,2,3,3,4,4,8,8]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [3,3,7,7,10,11,11]
//输出: 10
// 
//
// 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。 
// Related Topics 二分查找 
// 👍 227 👎 0


package leetcode.editor.cn;

//有序数组中的单一元素

public class P540_SingleElementInASortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P540_SingleElementInASortedArray().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNonDuplicate1(int[] nums) {
/*		令 index 为 Single Element 在数组中的位置。在 index 之后，数组中原来存在的成对状态被改变。
		如果 m 为偶数，并且 m + 1 < index，那么 nums[m] == nums[m + 1]；
		m + 1 >= index，那么 nums[m] != nums[m + 1]。*/
/*
		从上面的规律可以知道，如果 nums[m] == nums[m + 1]，
		那么 index 所在的数组位置为 [m + 2, h]，此时令 l = m + 2；
		如果 nums[m] != nums[m + 1]，那么 index 所在的数组位置为 [l, m]，此时令 h = m。
		因为 h 的赋值表达式为 h = m，那么循环条件也就只能使用 l < h 这种形式。
*/
            int len = nums.length;
            int l = 0, h = len - 1;
            while (l < h) {
                int mid = l + (h - l) / 2;
                if (mid % 2 == 1) {
                    mid--; // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
                }
                if (nums[mid] == nums[mid + 1]) {
                    l = mid + 2;
                } else h = mid;
            }
            return nums[l];
        }

        public int singleNonDuplicate(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len - 1; i += 2) {
                if (nums[i] != nums[i + 1]) return nums[i];
            }
//            考虑遍历范围 index表示：len-1 不访问
            return nums[len - 1];
        }

    }

//    仅对偶数索引进行二分搜索
    class Solution1 {
        public int singleNonDuplicate(int[] nums) {
            int lo = 0;
            int hi = nums.length - 1;
//            while终止条件--lo=hi
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (mid % 2 == 1) mid--;
//               讨论：为什么lo=mid+2 而 hi=mid-----需要依据逻辑考虑mid处的性质

                //若单一数没出现，偶数下标的元素等于下个奇数下标的元素
                if (nums[mid] == nums[mid + 1]) {
                    lo = mid + 2;
                //若单一数已经出现，偶数下标的元素不等于下个奇数下标的元素
                } else {
                    hi = mid;
                }
            }
//            return nums[lo];
            return nums[hi];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
