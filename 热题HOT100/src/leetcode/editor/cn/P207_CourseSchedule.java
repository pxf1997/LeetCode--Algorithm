/**
 * 题目Id：207
 * 题目：课程表
 * 日期：2021-08-27 12:50:41
 */
//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 914 👎 0


package leetcode.editor.cn;

//课程表

import util.dp_util;

import java.util.*;

public class P207_CourseSchedule {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P207_CourseSchedule().new Solution();
        // case1
        boolean canFinish = solution.canFinish(5, new int[][]{
                {1, 0},
                {3, 0},
                {2, 1},
                {3, 1},
                {4, 2},
                {4, 3}}
        );
        System.out.println("canFinish = " + canFinish);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 最经典的拓扑排序了
    class Solution {
        // {后, 前}
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 1--构建邻接表,入度矩阵
            List<List<Integer>> adjacency = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adjacency.add(new ArrayList<>());
            }
            int[] indegrees = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                int first = prerequisite[1], second = prerequisite[0];
                indegrees[second]++;
                adjacency.get(first).add(second);
            }
            // helper
//            dp_util.print_adjacency(adjacency);
//            System.out.println("indegrees = " + Arrays.toString(indegrees));
//            System.out.println();

            // 2--拓扑排序开始
            // 2.1--入度为0的节点入队
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < indegrees.length; i++) {
                if (indegrees[i] == 0) queue.add(i);
            }
            int visit = 0;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                visit++;
                // v的邻接节点入度-1, 更新后若入度为0就入队
                List<Integer> v_neis = adjacency.get(v);
                for (Integer nei : v_neis) {
                    if (--indegrees[nei] == 0) {
                        queue.add(nei);
                    }
                }
            }
            return visit == numCourses;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
