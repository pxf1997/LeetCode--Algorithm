/**
 * 题目Id：75
 * 题目：颜色分类
 * 日期：2021-08-24 12:05:43
 */
//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 👍 980 👎 0


package leetcode.editor.cn;

//颜色分类

import java.util.Arrays;

public class P75_SortColors_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P75_SortColors_old().new Solution();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        System.out.println("nums = " + Arrays.toString(nums));
        solution.sortColors(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }


    // 分析目标--0, 1, 2 按顺序分布
    // 解法一:单指针,先交换0到前面,再交换1到前面  两次遍历O(2n)
    class Solution1 {
        public void sortColors(int[] nums) {
            // 你在开挂
            // Arrays.sort(nums);
            int idx = 0;
            int len = nums.length;
            // 0交换到前面
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, idx);
                    idx++;
                }
            }
            // 1交换到前面
            for (int i = 0; i < len; i++) {
                if (nums[i] == 1) {
                    swap(nums, i, idx);
                    idx++;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 双指针 p0--指向0的边界(第一个1) p1--指向1的边界(第一个2)
    class Solution {
        public void sortColors(int[] nums) {
            int p0 = 0, p1 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    swap(nums, i, p1);
                    p1++;
                } else if (nums[i] == 0) {
                    swap(nums, i, p0);
                    // p0 < p1 时，我们已经将一些1连续地放在头部, 即p0指向的是1
                    // 此时一定会把一个1交换出去，导致答案错误。
                    if (p0 < p1) {
                        swap(nums, i, p1);
                    }
                    p0++;
                    p1++;

                }
            }
            // {2, 0, 2, 1, 1, 0} --> {0, 0, 1, 1, 2, 2}   p0=2 p1=4
            System.out.println("p0 = " + p0);
            System.out.println("p1 = " + p1);
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 另一种双指针
    class Solution3 {
        public void sortColors(int[] nums) {
            int n = nums.length;
            int p0 = 0, p2 = n - 1;
            for (int i = 0; i <= p2; ++i) {
                while (i <= p2 && nums[i] == 2) {
                    int temp = nums[i];
                    nums[i] = nums[p2];
                    nums[p2] = temp;
                    --p2;
                }
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    ++p0;
                }
            }
        }
    }

}
