/**
 * 题目Id：210
 * 题目：课程表 II
 * 日期：2021-08-27 12:58:04
 */
//现在你总共有 n 门课需要选，记为 0 到 n-1。 
//
// 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1] 
//
// 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。 
//
// 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。 
//
// 示例 1: 
//
// 输入: 2, [[1,0]] 
//输出: [0,1]
//解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。 
//
// 示例 2: 
//
// 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
//输出: [0,1,2,3] or [0,2,1,3]
//解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
// 
//
// 说明: 
//
// 
// 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。 
// 你可以假定输入的先决条件中没有重复的边。 
// 
//
// 提示: 
//
// 
// 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。 
// 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。 
// 
// 拓扑排序也可以通过 BFS 完成。 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 457 👎 0


package leetcode.editor.cn;

//课程表 II

import java.util.*;

public class P210_CourseScheduleIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P210_CourseScheduleIi().new Solution();
        int[] order = solution.findOrder(11, new int[][]{
                {1, 0}, {4, 0}, {6, 0},
                {2, 1},
                {3, 2},
                {10, 3},
                {5, 4},
                {9, 5},
                {7, 6},
                {8, 7},
                {9, 8},
                {10, 9},
        });
        System.out.println("order = " + Arrays.toString(order));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] indegrees = new int[numCourses];
            // 节点集--哪些节点需要拓扑排序
            List<Integer> vertices = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                vertices.add(i);
            }
            // 构建邻接矩阵adjacency 及入度indegrees
            List<List<Integer>> adjacency = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adjacency.add(new ArrayList<>());
            }
            for (int[] prerequisite : prerequisites) {
                int first = prerequisite[1], second = prerequisite[0];
                adjacency.get(first).add(second);
                indegrees[second]++;
            }

            List<Integer> res = topSort(indegrees, adjacency, vertices);
            return res.stream().mapToInt(Integer::valueOf).toArray();
        }

        /**
         * @param indegrees 入度
         * @param adjacency 邻接表
         * @param items     待排序的节点集
         * @return 拓扑排序
         */
        public List<Integer> topSort(int[] indegrees, List<List<Integer>> adjacency, List<Integer> items) {
            Queue<Integer> queue = new LinkedList<Integer>();
            for (int item : items) {
                if (indegrees[item] == 0) {
                    queue.offer(item);
                }
            }
            System.out.println("初始化队列:" + queue);
            List<Integer> res = new ArrayList<Integer>();
            while (!queue.isEmpty()) {
                int v = queue.poll();
                res.add(v);
                List<Integer> v_neis = adjacency.get(v);
                System.out.println("出队节点:" + v + " 对应邻接节点:" + v_neis);
                for (int nei : v_neis) {
                    if (--indegrees[nei] == 0) {
                        System.out.println("入队节点:" + nei);
                        queue.offer(nei);
                    }
                }
                System.out.println("当前队列:" + queue);
                System.out.println("更新后入度:" + Arrays.toString(indegrees));
                System.out.println();
            }
            System.out.println("排序结束");
            return res.size() == items.size() ? res : new ArrayList<>();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
