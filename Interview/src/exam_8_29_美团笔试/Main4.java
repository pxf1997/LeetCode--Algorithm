package exam_8_29_美团笔试;

import util.dp_util;

import java.util.*;

/**
 * @author pxf
 * @create 2021-08-29 10:14
 */
public class Main4 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Solution();
        // case1
        int[][] matrix = new int[][]{
                {4, 4, 2},
                {3, 3, 2},
                {2, 4, 1},
        };
        int res = solution.func(6, 6, 3, matrix);
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
        int m, n;
        int[][] grass;

        public int func(int row, int col, int time, int[][] matrix) {
            m = row;
            n = col;
            grass = new int[row][col];
            for (int[] ints : matrix) {
                int x = ints[0], y = ints[1], r = ints[2];
                grow();
                cut(x, y, r);
            }

            dp_util.print_2D(grass);
            System.out.println();

            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    cnt += grass[i][j];
                }
            }
            return cnt;
        }

        private void grow() {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grass[i][j]++;
                }
            }
        }

        private int cut(int x, int y, int r) {
            int cnt = 0;
            // 暴力法--遍历每块草地
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 土地编号,从 0/1 开始
                    if (Math.pow(i + 1 - x, 2) + Math.pow(j + 1 - y, 2) <= r * r) {
                        grass[i][j] = 0;
                    }
                }
            }
            return cnt;
        }

        boolean inArea(int row, int col) {
            return row >= 0 && row < m && col >= 0 && col < n;
        }
    }
}
// 小团作为园艺部的一员，主要负责割草的工作。
//
//学校有一块 n×m 大小的草地，这块草地被平均划分为 n×m 个 1×1 的草地块。第 i 行第 j 列的草地块表示为 (i, j)。
//
//接下来 k 天的每天早上，小团都会去割草作为早锻炼项目。起初每个草地块都有一单位的草，每天早上小团都会用割草机将满足 (i - x)2 + (j - y)2≤ r2 的所有草地块 (i, j) 中的草全部割掉，也就是让这个草地块中的草数量变为 0。其中 (x, y) 是小团所处的草地块位置，r 对于每天割草是个常数。每过一天，每块草地中草的数量都增加 1。
//
//所有部员打算在第 k 天晚上一起把这片草地上的草全部割完。请问这天晚上会割掉多少单位的草？