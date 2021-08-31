/**
 * 题目Id：815
 * 题目：公交路线
 * 日期：2021-06-28 09:33:01
 */
//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。 
//
// 
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 
//-> ... 这样的车站路线行驶。 
// 
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。 
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
// 
//
// 示例 2： 
//
// 
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= routes.length <= 500. 
// 1 <= routes[i].length <= 105 
// routes[i] 中的所有值 互不相同 
// sum(routes[i].length) <= 105 
// 0 <= routes[i][j] < 106 
// 0 <= source, target < 106 
// 
// Related Topics 广度优先搜索 数组 哈希表 
// 👍 148 👎 0


package daily_2021_06;

//公交路线

import java.util.*;

public class P815_BusRoutes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P815_BusRoutes().new Solution();
        int res = solution.numBusesToDestination(new int[][]{
                {1, 2, 7},
                {3, 6, 7},
        }, 1, 6);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // bfs最短路径----如何建图(抽象的节点和边)
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target) {
                return 0;
            }
            // 公交线路点做点
            int n = routes.length;
            // 公交线路之间能否换乘
            boolean[][] edge = new boolean[n][n];
            // key--车站 value--到达此车站的公交车
            Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
            for (int i = 0; i < n; i++) {
                for (int site : routes[i]) {
                    List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                    for (int j : list) {
                        edge[i][j] = edge[j][i] = true;
                    }
                    list.add(i);
                    rec.put(site, list);
                }
            }

            int[] dis = new int[n];
            Arrays.fill(dis, -1);
            Queue<Integer> que = new LinkedList<Integer>();
            for (int site : rec.getOrDefault(source, new ArrayList<Integer>())) {
                dis[site] = 1;
                que.offer(site);
            }
            while (!que.isEmpty()) {
                int x = que.poll();
                for (int y = 0; y < n; y++) {
                    if (edge[x][y] && dis[y] == -1) {
                        dis[y] = dis[x] + 1;
                        que.offer(y);
                    }
                }
            }

            int ret = Integer.MAX_VALUE;
            for (int site : rec.getOrDefault(target, new ArrayList<Integer>())) {
                if (dis[site] != -1) {
                    ret = Math.min(ret, dis[site]);
                }
            }
            return ret == Integer.MAX_VALUE ? -1 : ret;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
