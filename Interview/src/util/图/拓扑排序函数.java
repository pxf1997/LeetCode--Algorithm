package util.图;

import java.util.*;

/**
 * @author pxf
 * @create 2021-05-20 15:26
 */
public class 拓扑排序函数 {
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
