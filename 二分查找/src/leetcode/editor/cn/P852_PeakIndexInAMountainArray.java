/**
 * 题目Id：852
 * 题目：山脉数组的峰顶索引
 * 日期：2021-06-15 09:22:17
 */
//符合下列属性的数组 arr 称为 山脉数组 ：
// 
// arr.length >= 3 
// 存在 i（0 < i < arr.length - 1）使得：
// 
// arr[0] < arr[1] < ... arr[i-1] < arr[i] 
// arr[i] > arr[i+1] > ... > arr[arr.length - 1] 
// 
// 
// 
//
// 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 
//1] > ... > arr[arr.length - 1] 的下标 i 。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [0,1,0]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：arr = [0,2,1,0]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：arr = [0,10,5,2]
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：arr = [3,4,5,1]
//输出：2
// 
//
// 示例 5： 
//
// 
//输入：arr = [24,69,100,99,79,78,67,36,26,19]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 3 <= arr.length <= 104 
// 0 <= arr[i] <= 106 
// 题目数据保证 arr 是一个山脉数组 
// 
//
// 
//
// 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？ 
// Related Topics 二分查找 
// 👍 167 👎 0


package leetcode.editor.cn;

//山脉数组的峰顶索引

public class P852_PeakIndexInAMountainArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P852_PeakIndexInAMountainArray().new Solution();
        int peakIndex = solution.peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19});
//        int peakIndex = solution.peakIndexInMountainArray(new int[]{5, 4, 3, 2, 1});
        System.out.println("peakIndex = " + peakIndex);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 写的不好, mid-1 会下标越界
        public int peakIndexInMountainArray1(int[] arr) {
            int lo = 0, hi = arr.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 左上坡
                if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                    lo = mid + 1;
                }
                // 右下坡
                else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }
            return lo;
        }

        // 参考--最小的满足 arr[i] > arr[i+1]的下标
        public int peakIndexInMountainArray(int[] arr) {
            int lo = 0, hi = arr.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // helper
                System.out.println("lo=" + lo + "  hi=" + hi + "  mid=" + mid);
                // target 在 [lo,mid]范围内,可能就是mid
                if (arr[mid] > arr[mid + 1]) {
                    hi = mid;
                }
                // target 在 [mid+1,hi]范围内,不可能是mid
                else {
                    lo = mid + 1;
                }
            }
            return lo;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
