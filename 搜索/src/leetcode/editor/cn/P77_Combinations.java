/**
 * 题目Id：77
 * 题目：组合
 * 日期：2021-05-07 19:37:44
 */
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 579 👎 0


package leetcode.editor.cn;

//组合

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P77_Combinations {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P77_Combinations().new Solution();
        List<List<Integer>> res = solution.combine(5, 3);
//        List<List<Integer>> res = solution.combine(4, 2);
//        List<List<Integer>> res = solution.combine(4, 4);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //my
    class Solution1 {
        //        List<List<Integer>> res = new ArrayList<>();//可以写在Solution类的属性里，也可以跟着dfs一起传参
        int depth = 0;

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            //特殊情况处理
            if (k <= 0 || n < k) {
                return res;
            }
            // 从 1 开始是题目的设定
            backtracking(n, 1, k, path, res);
            return res;

        }

        private void backtracking(int n, int start, int k, List<Integer> path, List<List<Integer>> res) {
            // 递归终止条件是：path 的长度等于 k
            if (path.size() == k) {
                System.out.println("递归终止path = " + path);
                res.add(new ArrayList<>(path));
                return;
            }
            // 遍历可能的搜索起点
            for (int i = start; i <= n; i++) {
                // 向路径变量里添加一个数
                path.add(i);
                depth++;
                System.out.print("depth = " + depth);
                System.out.println("  path = " + path + "  加入i = " + i);


                // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
                backtracking(n, i + 1, k, path, res);


                // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
                path.remove(path.size() - 1);
                depth--;
                System.out.print("depth = " + depth);
                System.out.print("  回溯一哈");
                System.out.println("  path = " + path + "  移除i = " + i);
                System.out.println();

            }

        }
    }


    //参考答案
    class Solution {

        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            if (k <= 0 || n < k) {
                return res;
            }
            // 从 1 开始是题目的设定
            List<Integer> path = new ArrayList<>();
            dfs(n, k, 1, path, res);
            return res;
        }

        private void dfs(int n, int k, int begin, List<Integer> path, List<List<Integer>> res) {
            // 递归终止条件是：path 的长度等于 k
            if (path.size() == k) {
                System.out.println("递归终止path = " + path);
                res.add(new ArrayList<>(path));
                return;
            }

            // 遍历可能的搜索起点
//            for (int i = begin; i <= n; i++) {

            // 剪枝--只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
            for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
                // 向路径变量里添加一个数
                path.add(i);
                System.out.println("递归之前 => " + path);

                // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
                dfs(n, k, i + 1, path, res);

                // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);

                System.out.println(); //每次回溯打一个空行
            }

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
