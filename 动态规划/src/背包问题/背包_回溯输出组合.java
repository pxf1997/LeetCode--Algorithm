package 背包问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static leetcode.editor.cn.dp_util.print_DP_2;

/**
 * @author pxf
 * @create 2021-05-21 15:33
 */
public class 背包_回溯输出组合 {
    public static void main(String[] args) {
//        int[] weights1 = {3, 9, 4, 5, 2};
//        int[] values1 = {4, 10, 8, 8, 3};
        int[] weights1 = {1, 2, 3, 4};
        int[] values1 = {15, 28, 36, 40};
//        int nLimit = weights1.length;  //只要重量W限制满足，你随便拿，对物品个数不做限制--完全背包

        //  拿了哪些物品
//        List<int[]> items = bag_items(20, weights1.length, weights1, values1);
        List<int[]> items = bag_items(7,  weights1.length, weights1, values1);

        //  helper
        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }

    }

    //  记录每次拿的是什么----itemlist
    public static List<int[]> bag_items(int WeightLimit, int N, int[] weights, int[] values) {
//        int[][] dp = new int[N][WeightLimit + 1];
        int[][] dp = new int[N + 1][WeightLimit + 1];
        //  初始化 0列 0行
        for (int i = 0; i < N + 1; i++) { //0列--没有容量
            dp[i][0] = 0;
        }
        for (int i = 0; i < WeightLimit + 1; i++) { //0行--没有物品
            dp[0][i] = 0;
        }
        for (int i = 1; i < N + 1; i++) {
            int w = weights[i - 1];
            int v = values[i - 1];
            for (int j = 1; j < WeightLimit + 1; j++) {
                dp[i][j] = dp[i - 1][j]; //默认不拿 下标i物品
                if (j >= w) {
                    // 如果拿不拿二者相等呢? 不拿
                    if (dp[i - 1][j] < dp[i - 1][j - w] + v) {
                        dp[i][j] = dp[i - 1][j - w] + v;
                        //  itemList.add(new Integer[]{w, v});//这句话--DP表中所有的斜线
                    }
                }
            }
        }

        System.out.println("打印dp表:");
        print_DP_2(dp);

        //回溯--从dp表最右下角往前面找
        List<int[]> path = new ArrayList<>();
        List<List<int[]>> pathList = new ArrayList<>();
        traceBack(dp, weights, values, N, WeightLimit, path, pathList); // 传入dp表右下角元素的下标
        return path;
    }

    //  回溯
    public static void traceBack(int[][] dp, int[] weights, int[] values, int i, int j, List<int[]> path, List<List<int[]>> pathlist) {
        // old
        /*while (i > 0 && j > 0) {
            //  讨论分类为--i=0 第一行是特例
            while ((i >= 1) && dp[i][j] == dp[i - 1][j]) { //与上一行相同--不拿weight[i]
                i--;
            }
            if ((i >= 1) && dp[i][j] > dp[i - 1][j]) {
                path.add(new int[]{weights[i], values[i]});
                //这两句话先后顺序--不能调换啊！！！！！
                j -= weights[i];
                i--;
            } else if (i == 0) {
                path.add(new int[]{weights[0], values[0]});
            }
        }*/

        //new
        while (j > 0 && i > 0) {
            if (dp[i][j] > dp[i - 1][j]) {
                System.out.println("i=" + i + " j=" + j + " 拿取物品:(" + weights[i - 1] + "," + values[i - 1] + ")");
                path.add(new int[]{weights[i - 1], values[i - 1]});
                //这两句话先后顺序--不能调换啊！！！！！
                j -= weights[i - 1];
                i--;
            } else {
                System.out.println("i=" + i + " j=" + j + " 不拿取物品:(" + weights[i - 1] + "," + values[i - 1] + ")");
                i--;
            }
        }
        pathlist.add(path);
        System.out.println("一条路径:" + path);
    }

}
