package 子集;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 15:19
 */
public class 子集1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2, 3});
        System.out.println("输出 => " + subsets);
    }

    private static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            //思路----不同的子集大小
            for (int size = 0; size <= nums.length; size++) {
                System.out.println("size = " + size);

                backtracking(nums, 0, size, res, path); //小性质--每次回溯后，path为空

                System.out.println();
            }
            return res;
        }

        private void backtracking(int[] nums, int begin, int size, List<List<Integer>> res, List<Integer> path) {
            if (path.size() == size) {
                System.out.println("递归结束:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < nums.length; i++) {
                //添加
                path.add(nums[i]);
                System.out.println("递归之前 => " + path + "  剩余个数:" + (size - path.size()));

                //不允许重复
                backtracking(nums, i + 1, size, res, path);

                //删除
                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);

            }
        }
    }
}
