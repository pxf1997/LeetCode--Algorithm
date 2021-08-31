/**
 * 题目Id：802
 * 题目：找到最终的安全状态
 * 日期：2021-08-05 09:53:31
 */
//在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。 
//
// 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。 
//
// 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。 
//
// 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
//满足 (i, j) 是图的一条有向边。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
// 
//
// 示例 2： 
//
// 
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 104 
// 0 <= graph[i].length <= n 
// graph[i] 按严格递增顺序排列。 
// 图中可能包含自环。 
// 图中边的数目在范围 [1, 4 * 104] 内。 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 175 👎 0


package daily_2021_08;

//找到最终的安全状态

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P802_FindEventualSafeStates {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P802_FindEventualSafeStates().new Solution();
        int[][] graph = new int[][]{
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
        };
        List<Integer> res = solution.eventualSafeNodes(graph);
        // 返回{2,4,5,6} 即{0,1,3}三者构成环
        System.out.println("res = " + res);

    }


    // 分析--什么叫"安全"???
    // 该节点不在"圈中",因此不会出现循环,可以到达"终点"!
    // "终点"是出度为0的节点
    // dfs + 三色标记(白0/灰1/黑2)
    class Solution1 {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int len = graph.length;
            int[] color = new int[len];
            List<Integer> res = new ArrayList<Integer>();
            for (int i = 0; i < len; i++) {
                if (safe(graph, color, i)) {
                    res.add(i);
                }
            }
            return res;
        }

        private boolean safe(int[][] graph, int[] color, int x) {
            if (color[x] > 0) {
                return color[x] == 2;
            }
            // 标记灰色--正在探索
            color[x] = 1;
            for (int y : graph[x]) {
                if (!safe(graph, color, y)) {
                    return false;
                }
            }
            color[x] = 2;
            return true;
        }

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 拓扑排序--原理:
    // 若一个节点出度为0,则它是安全的 --> 反图中它的入度为0
    class Solution {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int len = graph.length;
            // 构建反图
            List<List<Integer>> rg = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                rg.add(new ArrayList<>());
            }
            int[] indegrees = new int[len];
            for (int x = 0; x < len; x++) {
                for (int y : graph[x]) {
                    // 原图有向边 x-->y
                    // 反图的有向边 y-->x
                    rg.get(y).add(x);
                }
                // 反图x的入度 = 正图x的出度
                indegrees[x] = graph[x].length;
            }
            // 拓扑排序过程:入度为0节点先入队,用它更新其余节点入度
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                if (indegrees[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int y = queue.poll();
                // 有向边 y-->x
                for (int x : rg.get(y)) {
                    if (--indegrees[x] == 0) {
                        queue.offer(x);
                    }
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (indegrees[i] == 0) res.add(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    // 分析如下:
/*    回到本题，根据题目对「安全节点」的定义，我们知道如果一个节点无法进入「环」的话则是安全的，否则是不安全的。
    另外我们发现，如果想要判断某个节点数 x 是否安全，起始时将 x 进行入队，并跑一遍拓扑排序是不足够的。

    因为我们无法事先确保 x 满足入度为 0 的要求，所以当我们处理到与 x 相连的节点 y 时，可能会存在 y 节点入度无法减到 0 的情况，
    即我们无法输出真实拓扑序中，从 x 节点开始到结尾的完整部分。

    但是根据我们「证明」部分的启发，我们可以将所有边进行反向，这时候「入度」和「出度」翻转了。
    对于那些反向图中「入度」为 0 的点集 x，其实就是原图中「出度」为 0 的节点，
    它们「出度」为 0，根本没指向任何节点，必然无法进入环，是安全的；
    同时由它们在反向图中指向的节点（在原图中只指向它们的节点），必然也是无法进入环的，
    对应到反向图中，就是那些减去 x 对应的入度之后，入度为 0 的节点。

    算法流程!!!
    因此整个过程就是将图进行反向，
    再跑一遍拓扑排序，如果某个节点出现在拓扑序列，说明其进入过队列，说明其入度为 0，其是安全的，其余节点则是在环内非安全节点。
*/

}
