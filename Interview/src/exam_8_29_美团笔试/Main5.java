package exam_8_29_美团笔试;

import util.TreeNode;
import util.dp_util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pxf
 * @create 2021-08-29 10:14
 */
public class Main5 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Solution();
        // case1
        int n = 6;
        int limit = 2;
        int[] stones = {1, 1, 1, 0, 1, 0};
        int[] temp = {1, 1, 2, 2, 3};

        int res = solution.func(6, 2, stones, temp);
        System.out.println(res);

        // 数据流
//        Scanner sc = new Scanner(System.in);
//        List<String> list = new ArrayList<String>();
//        while (sc.hasNext()) {
//            String str = sc.nextLine();
//            list.add(str);
//        }
//        sc.close();
//        System.out.println("list = " + list);
//        String s = list.get(0);
//        String target = list.get(1);
//        int res = solution.func(s, target);
//        System.out.println(res);

    }

    // 模拟法
    static class Solution {
        int n, limit;
        int[] stones;
        int cnt;
        int[][] adjacency;

        public int func(int n, int limit, int[] stones, int[] temp) {
            this.n = n;
            this.limit = limit;
            this.stones = stones;
            for (int i = 0; i < temp.length; i++) {
                temp[i]--;
            }
            adjacency = new int[n][n]; // 0号无意义
            for (int i = 0; i < temp.length; i++) {
                int fi = temp[i];
                adjacency[i + 1][fi] = adjacency[fi][i + 1] = 1;
            }
            boolean[] visited = new boolean[n];
            List<Integer> path = new ArrayList<>();
            backtracking(path, 0, 0, visited);
            return cnt;
        }

        private void backtracking(List<Integer> path, int begin, int cnt_stone, boolean[] visited) {
            // 在叶子判断
            if (isLeaf(begin)) {
                path.add(begin);
                visited[begin] = true;
                System.out.println("path = " + path);
                cnt++;
            }
            if (visited[begin]) return;
            if (cnt_stone > this.limit) {
                return;
            }
            path.add(begin);
            visited[begin] = true;
            cnt_stone += (stones[begin] == 1 ? 1 : 0);
            int[] next = adjacency[begin];
            for (int i = 0; i < next.length; i++) {
                if (next[i] == 1 && i > begin) {
                    backtracking(path, i, cnt_stone, visited);
                }
            }
            path.remove(path.size() - 1);
            visited[begin] = false;
        }

        // 如何判断叶子--用邻接矩阵, 编号比它大的节点都不相连
        private boolean isLeaf(int node) {
            int[] next = adjacency[node];
            for (int i = 0; i < next.length; i++) {
                if (i > node && next[i] == 1) return false;
            }
            return true;
        }

    }
}
// 第一行两个整数 n, m，意义如题目描述。
//
//第二行 n 个整数 pi（1 ≤ i ≤ n），pi 如果是 0，则代表 i 号地块没有石头，如果是 1，则代表有石头。
//
//第三行 n-1 个整数 fi（1 ≤ i ≤ n），表示 (i+1, fi) 之间有⼀条可双向通行的道路相连。
//
//对于 50% 的数据，n ≤ 500；
//
//对于 100% 的数据，1 ≤ m ≤ n ≤ 105, 0 ≤ pi ≤ 1, 1 ≤ fi ≤ i。