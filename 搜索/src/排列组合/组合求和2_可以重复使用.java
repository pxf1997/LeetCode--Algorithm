package 排列组合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:31
 */
public class 组合求和2_可以重复使用 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2, 3, 6, 7};
//        int[] candidates = {2, 7, 6, 3, 5, 1};
        List<List<Integer>> res = solution.combinationSum(candidates, 9);
        System.out.println("输出 => " + res);
    }

    private static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            backtracking(candidates, target, 0, res, path);

            return res;
        }

        //做减法--不做加法
        private void backtracking(int[] candidates, int target, int begin, List<List<Integer>> res, List<Integer> path) {
            if (target == 0) {
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
            }
//            if (target < 0) {
//                System.out.println("递归终止:剩余小于0");
//                return;
//            }
            for (int i = begin; i < candidates.length; i++) {
                if (target - candidates[i] < 0) {
                    break;
                }
                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "  剩余:"+(target - candidates[i]));

                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i
                backtracking(candidates, target - candidates[i], i, res, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }

        }
    }
}
