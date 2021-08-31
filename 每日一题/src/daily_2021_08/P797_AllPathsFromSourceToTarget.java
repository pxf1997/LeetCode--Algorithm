/**
 * 题目Id：797
 * 题目：所有可能的路径
 * 日期：2021-08-25 10:29:35
 */
//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序） 
//
// 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。 
//
// 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// 示例 3： 
//
// 
//输入：graph = [[1],[]]
//输出：[[0,1]]
// 
//
// 示例 4： 
//
// 
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,2,3],[0,3]]
// 
//
// 示例 5： 
//
// 
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i（即，不存在自环） 
// graph[i] 中的所有元素 互不相同 
// 保证输入为 有向无环图（DAG） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 161 👎 0


package daily_2021_08;

//所有可能的路径

import java.util.ArrayList;
import java.util.List;

public class P797_AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P797_AllPathsFromSourceToTarget().new Solution();
        int[][] graph = new int[][]{
                {1, 2},
                {3},
                {3},
                {}
        };
        List<List<Integer>> res = solution.allPathsSourceTarget(graph);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析-- 有向无环图（DAG)意味着什么 ???
    // 按照第 x - 1 位置的值去决策第 x 位的内容，必然不会决策到已经在当前答案的数值，
    // 否则会与 graph 为有向无环图（拓扑图）的先决条件冲突。
    // 换句话说，与一般的爆搜不同的是，我们不再需要 visvis 数组来记录某个点是否已经在当前答案中。

    class Solution {
        int[][] g;
        int n;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            g = graph;
            n = g.length;
            path.add(0);
            backtracking(0);
            return ans;
        }

        void backtracking(int begin) {
            // 1--递归终止条件
            if (begin == n - 1) {
                System.out.println("递归终止,path = " + path);
                ans.add(new ArrayList<>(path));
                return;
            }
            // 2--深入探索
            for (int next : g[begin]) {
                path.add(next);
                backtracking(next);
                path.remove(path.size() - 1);
            }
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
