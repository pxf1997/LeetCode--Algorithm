/**
 * 题目Id：剑指 Offer 57 - II
 * 题目：和为s的连续正数序列
 * 日期：2021-06-21 17:00:04
 */
//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
//
//
// 示例 1：
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
//
//
// 示例 2：
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
//
//
//
//
// 限制：
//
//
// 1 <= target <= 10^5
//
//
//
// 👍 280 👎 0


package leetcode.editor.cn;

//和为s的连续正数序列

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 剑指Offer_57_II_和为s的连续正数序列 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_57_II_和为s的连续正数序列().new Solution();
        int[][] continuousSequence = solution.findContinuousSequence(15);
        System.out.println("continuousSequence = " + Arrays.deepToString(continuousSequence));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 枚举+暴力
    class Solution1 {
        // 这个返回值就很离谱,事先不能知道个数
        List<List<Integer>> res_list = new ArrayList<>();

        public int[][] findContinuousSequence(int target) {
            // 遍历可行起点
            // 考虑到至少两个数,起点范围可以缩小
            for (int i = 1; i <= target / 2; i++) {
                int sum = 0;
                List<Integer> path = new ArrayList<>();
                for (int j = i; sum < target; j++) { // 不对j做限制,对sum做限制
                    sum += j;
                    path.add(j);
                    if (sum == target) {
                        res_list.add(path);
                    } else if (sum > target) {
                        break;
                    }
                }
            }
            System.out.println("res = " + res_list);
            int size = res_list.size();
            int idx = 0;
            int[][] res = new int[size][]; // 声明行数
            for (List<Integer> path : res_list) {
                res[idx++] = path.stream().mapToInt(Integer::valueOf).toArray();
            }
            return res;
        }
    }

    // 双指针
    // 解释:对暴力法的优化,信息复用
    // [left,right]区间和若可行,[left+1,right]必然小于target,避免了重复枚举
    class Solution {
        public int[][] findContinuousSequence(int target) {
            List<int[]> res_list = new ArrayList<>();
            for (int left = 1, right = 2; left < right; ) {
                // 区间和
                int sum = (left + right) * (right - left + 1) / 2;
                // 得到合法解[left,right]
                if (sum == target) {
                    int[] res = new int[right - left + 1];
                    for (int i = left; i <= right; ++i) {
                        res[i - left] = i;
                    }
                    res_list.add(res);
                    left++;
                }
                // right右移使sum增大
                else if (sum < target) {
                    right++;
                }
                // sum>target 去枚举下一个起点
                else {
                    left++;
                }
            }
            return res_list.toArray(new int[res_list.size()][]);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
