/**
 * 题目Id：78
 * 题目：子集
 * 日期：2021-05-08 15:14:18
 */
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1160 👎 0


package leetcode.editor.cn;

//子集

import java.util.*;

public class P78_Subsets {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P78_Subsets().new Solution();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2, 3});
        System.out.println("输出 => " + subsets);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //my
    class Solution {
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
                //剪枝
                if (size - 1 < 0) {
                    break;
                }
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

        private void backtracking_bad(int[] nums, int begin, List<List<Integer>> res, List<Integer> path) {
            if (begin == nums.length) { //逻辑不对啊---找到 nums最后一个元素才算结束，比如{1,2,3} 子集{2}无法找到！
                System.out.println("递归结束:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < nums.length; i++) {
                path.add(nums[i]);
                System.out.println("递归之前 => " + path);

                //不允许重复
                backtracking_bad(nums, i + 1, res, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);

            }


        }
    }

    //参考
    class Solution1 {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(0, nums);
            return ans;
        }

        public void dfs(int cur, int[] nums) {
            if (cur == nums.length) {
                System.out.println("递归结束:" + t);
                ans.add(new ArrayList<Integer>(t));
                return;
            }
            // 考虑选择当前位置
            t.add(nums[cur]);
            System.out.println("选择 nums[" + cur + "] => " + t);
            dfs(cur + 1, nums);

            // 考虑不选择当前位置
            t.remove(t.size() - 1);
            System.out.println("不选择 nums[" + cur + "] => " + t);
            dfs(cur + 1, nums);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
