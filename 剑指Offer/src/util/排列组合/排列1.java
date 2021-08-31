package util.排列组合;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:10
 */
public class 排列1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 2, 3, 4});
        System.out.println("permute = " + permute);
    }

    private static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            //  不考虑ints的极端情况，比如length=0这种特例----毫无意义！！！
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];
            backtracking(nums, res, path, visited);
            return res;
        }

        //排列不需要begin(1234、2134不同)  需要visited[]
        private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) {
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                path.add(nums[i]);
                visited[i] = true;
                System.out.println("递归之前 => " + path);

                backtracking(nums, res, path, visited);//不可重复

                path.remove(path.size() - 1);
                visited[i] = false;
                System.out.println("递归之后 => " + path);
            }
        }
    }
}
