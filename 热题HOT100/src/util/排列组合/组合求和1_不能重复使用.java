package util.排列组合;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:20
 */
public class 组合求和1_不能重复使用 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] candidates = {2, 3, 6, 7};
        int[] candidates = {2, 7, 6, 3, 5, 1};
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
                System.out.println("递归之前 => " + path + "  剩余:" + (target - candidates[i]));

                // 不能重复使用,搜索起点为 i+1
                backtracking(candidates, target - candidates[i], i + 1, res, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }

        }
    }
}
