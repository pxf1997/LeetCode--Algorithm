package 排列组合;

import leetcode.editor.cn.P216_CombinationSumIii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:40
 */
public class 组合求和4_有选取数量限制_不能重复使用 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> res = solution.combinationSum3(3, 9);
//        List<List<Integer>> res = solution.combinationSum3(2, 18);
        System.out.println("输出 => " + res);
    }

    private static class Solution {
        public List<List<Integer>> combinationSum3(int k, int target) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            int[] nums = new int[9];
            //不用排序nums数组
            for (int i = 0; i < 9; i++) {
                nums[i] = i + 1;
            }
            System.out.println("nums = " + Arrays.toString(nums));
            backtracking(nums, target, k, 0, res, path);
            return res;
        }

        private void backtracking(int[] nums, int target, int k, int begin, List<List<Integer>> res, List<Integer> path) {
            if (k == 0 && target == 0) {
                System.out.println("递归终止----成功找到一个path:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            if (k == 0 || target == 0) {
                System.out.println("递归终止----不成功");
                return;
            }
            for (int i = begin; i < nums.length; i++) {
                //剪枝
                if (target - nums[i] < 0) {
                    break;
                }
                path.add(nums[i]);
                System.out.println("递归之前 => " + path + "  剩余目标 = " + (target - nums[i]) + "  剩余个数 = " + (k - 1));

                //不能重复使用
                backtracking(nums, target - nums[i], k - 1, i + 1, res, path);
                //可以重复使用
//                backtracking(nums, target - nums[i], k - 1, i, res, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path + "  剩余个数 = " + k);
            }
        }
    }
}
