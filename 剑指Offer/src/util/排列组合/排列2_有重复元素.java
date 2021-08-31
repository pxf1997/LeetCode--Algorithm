package util.排列组合;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:45
 */
public class 排列2_有重复元素 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //  直观理解--{1,1,2,3,4} 排列结果应该与 {1,2,3,4} 一致,即不能区分重复的元素 1
//        List<List<Integer>> permute = solution.permuteUnique(new int[]{1, 1, 2, 3, 4});
        List<List<Integer>> permute = solution.permuteUnique(new int[]{1, 1, 2});
        System.out.println("permute = " + permute);
    }

    private static class Solution {
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
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
            }
            for (int i = 0; i < nums.length; i++) {
                if(visited[i]){
                    continue;
                }
                //排除重复
                if (i != 0 && (nums[i] == nums[i - 1]) && !visited[i - 1]) {
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
