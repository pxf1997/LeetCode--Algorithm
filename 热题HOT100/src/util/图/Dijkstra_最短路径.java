package util.图;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-08-03 10:22
 */
public class Dijkstra_最短路径 {
    // 注--节点编号从1开始
    public static int[] Dijkstra(int[][] edges, int n, int start) {
        final int INF = Integer.MAX_VALUE / 2; // 方便判断最后结果--是否都访问到

        // 构建邻接矩阵--有向图
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        // 节点编号减1,范围为[0,n-1]
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x][y] = g[y][x] = edge[2];
        }
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start - 1] = 0;
        boolean[] used = new boolean[n];

        // 算法流程
        for (int i = 0; i < n; i++) {
            int x = -1;
            // 1--每次从"未确定节点"中选一个与起点距离最短的点x
            for (int y = 0; y < n; y++) {
                // 细节--(x == -1 || dist[y] < dist[x])的逻辑:若x未确定(-1),不判断dist[-1]
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            // 2--将x归类为"已确定节点"
            used[x] = true;
            // 3--用x"更新"距离
            for (int y = 0; y < n; y++) {
                // 可能得到更新的节点,一定是"未确定的"
//                if(!used[y]){
//
//                }
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
            // helper
            System.out.println("选出的节点(未确定节点中距离最短): " + x + " 对应距离" + dist[x]);
            System.out.println("更新dist:" + Arrays.toString(dist));
            System.out.println();
        }
        return dist;
    }

    @Test
    public void test1() {
        // case2--无向图
        int[][] edges = new int[][]{
                {1, 2, 5},
                {1, 3, 15},
                {1, 4, 7},
                {2, 4, 8},
                {2, 5, 10},
                {3, 4, 3},
                {3, 6, 6},
                {4, 5, 9},
                {5, 6, 4},
        };
        int n = 6, start = 1;

        int[] res = Dijkstra(edges, n, start);
        System.out.println("res = " + Arrays.toString(res));
    }
}
