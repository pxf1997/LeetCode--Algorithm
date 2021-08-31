/**
 * 题目Id：31
 * 题目：下一个排列
 * 日期：2021-07-05 17:15:08
 */
//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
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
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1203 👎 0


package leetcode.editor.cn;

//下一个排列

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P31_NextPermutation {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P31_NextPermutation().new Solution();
        int[] nums = new int[]{4, 5, 2, 6, 3, 1};
        System.out.println("nums = " + Arrays.toString(nums));

        solution.nextPermutation(nums);

        System.out.println("nums = " + Arrays.toString(nums));
    }


    // 分析--题目要求:常数额外空间,原地修改
    // backtracking回溯记录path做法是否可行呢? 逻辑上可行!
    // 问题规模 1 <= nums.length <= 100 ,估计会超时!
    class Solution_my {
        public void nextPermutation(int[] nums) {
            List<List<Integer>> lists = permuteUnique(nums);
            System.out.println("lists = " + lists);
            // 结论--我们的lists是按字典序排序的!!!
            // 为啥呢?因为找排列的时候就对nums进行了排序
            for (int i = 0; i < lists.size(); i++) {
                boolean equal = true;
                List<Integer> list = lists.get(i);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) != nums[j]) {
                        equal = false;
                        break;
                    }
                }
                // 找到 nums 在lists中的位置
                if (equal) {
                    if (i != lists.size() - 1) {
                        // nums不是最后一个,将nums修改为下一个
                        nums = lists.get(i + 1).stream().mapToInt(Integer::valueOf).toArray();
                    } else {
                        // nums就是最后一个,返回第一个
                        nums = lists.get(0).stream().mapToInt(Integer::valueOf).toArray();
                    }
                    break;
                }
            }
            System.out.println("nums = " + Arrays.toString(nums));
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[len];
            backtracking(nums, res, path, visited);
            return res;

        }

        private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) {
                //System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                //排除重复0
                if (i != 0 && (nums[i] == nums[i - 1]) && !visited[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                visited[i] = true;
                //System.out.println("递归之前 => " + path);

                backtracking(nums, res, path, visited);//不可重复

                path.remove(path.size() - 1);
                visited[i] = false;
                //System.out.println("递归之后 => " + path);

            }

        }
    }

    // 参考题解1
    class Solution1 {
        public void nextPermutation(int[] nums) {
            //System.out.println("nums = " + Arrays.toString(nums));
            int i = nums.length - 2;
            // 1--找到「较小数」为 a[i], [i+1,len)为下降序列
            while (i >= 0 && nums[i] > nums[i + 1]) {
                i--;
            }

            // 2--在区间 [i+1,len) 中从后向前找到第一个元素j, 满足a[i] < a[j],
            // 「较大数」为 a[j],交换 i,j
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                //System.out.println("较小数a[" + i + "]=" + nums[i] + "  较大数a[" + j + "]=" + nums[j]);
                swap(nums, i, j);
                //System.out.println("nums = " + Arrays.toString(nums));
            }

            // 3--[i+1, n)为降序,用双指针反转区间使之升序
            // 考虑在第一步i=-1,即{3,2,1},全部反转,得到{1,2,3}
            reverse_helper(nums, i + 1);
            //System.out.println("nums = " + Arrays.toString(nums));
        }

        private void reverse_helper(int[] nums, int left) {
            int right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考题解2
    class Solution {
        public void nextPermutation1(int[] nums) {
            System.out.println("原始数组:" + Arrays.toString(nums));
            int len = nums.length;
            for (int i = len - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    Arrays.sort(nums, i, len);
                    System.out.println("较小数nums[" + (i - 1) + "]=" + nums[i - 1]);
                    System.out.println("较小数之后元素排序:" + Arrays.toString(nums));
                    for (int j = i; j < len; j++) {
                        if (nums[j] > nums[i - 1]) {
                            // 第一个比 较小数大的元素
                            System.out.println("较大数nums[" + j + "]=" + nums[j]);
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            System.out.println("交换较小数和较大数:" + Arrays.toString(nums));
                            return;
                        }
                    }
                }
            }
            Arrays.sort(nums);  // {3,2,1}重排为{1,2,3}
            return;
        }

        // 重新练习
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            // 从后往前,找不是降序排列的第一个元素,例如{4, 5, 2, 6, 3, 1}的2
            for (int i = len - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    Arrays.sort(nums, i + 1, len);
                    System.out.println("较小数nums[" + i + "]=" + nums[i]);
                    System.out.println("较小数之后元素排序:" + Arrays.toString(nums));
                    // [i,len) 变成升序,在这个范围内找第一个比nums[i]大的元素,进行交换即可
                    for (int j = i; j < len; j++) {
                        if (nums[j] > nums[i]) {
                            System.out.println("较大数nums[" + j + "]=" + nums[j]);
                            swap(nums, i, j);
                            System.out.println("交换较小数和较大数:" + Arrays.toString(nums));
                            return;
                        }
                    }
                }
            }
            Arrays.sort(nums);//原本是降序(最大排列),例如{3,2,1}-->{1,2,3}
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
