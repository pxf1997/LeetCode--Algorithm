package 背包问题;

import org.junit.Test;

import java.util.*;

import static leetcode.editor.cn.dp_util.print_DP_2;

/**
 * @author pxf
 * @create 2021-04-22 11:10
 */
public class Bag_0_1_2D {
    public static void main(String[] args) {
//        int[] weights1 = {1, 2, 3};
//        int[] values1 = {6, 10, 12};

//        int[] weights1 = {3, 2, 1};
//        int[] values1 = {12, 10, 6};


//        int[] weights1 = {2, 3, 4, 5, 9};
//        int[] values1 = {3, 4, 8, 8, 10};
        int[] weights1 = {3, 9, 4, 5, 2};
        int[] values1 = {4, 10, 8, 8, 3};
        int nLimit = weights1.length;  //只要重量W限制满足，你随便拿，对物品个数不做限制--完全背包

//        int res = Bag_01_1(5, nLimit, weights1, values1);
//        System.out.println(res);

//        拿了哪些物品
        List<Integer[]> items = Bag_01_items(20, nLimit, weights1, values1);
        for (Integer[] item : items) {
            System.out.println(Arrays.toString(item));
        }
    }

//     W 为背包总体积 -- 限制的是重量
//     N 为物品数量 -- 简单起见就等于总物品个数（不做拿走物品数量的限制、仅根据背包体积做限制）
//     weights 数组存储 N 个物品的重量
//     values 数组存储 N 个物品的价值

//    定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。
//    设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
//        1--第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价值，
//           dp[i][j] = dp[i-1][j]。
//        2--第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。


/*    public static int knapsack(int W, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][W];
    }*/

    public static int Bag_01_1(int WeightLimit, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][WeightLimit + 1];
        print_DP_2(dp);
        System.out.println();
        //i行 j列
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1];
            int v = values[i - 1];
            for (int j = 1; j <= WeightLimit; j++) {
                dp[i][j] = dp[i - 1][j]; //默认
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                }
            }
        }
        print_DP_2(dp);
        return dp[N][WeightLimit];
    }

    //    如何记录每次拿的是什么----itemlist
    public static List<Integer[]> Bag_01_items(int WeightLimit, int N, int[] weights, int[] values) {
        int[][] dp = new int[N][WeightLimit + 1];
        ArrayList<Integer[]> itemList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= WeightLimit; i++) {
            if (i >= weights[0]) dp[0][i] = values[0];
        }
        print_DP_2(dp);
        System.out.println();
        //
        for (int i = 1; i < N; i++) {
            int w = weights[i];
            int v = values[i];
            for (int j = 1; j <= WeightLimit; j++) {
                dp[i][j] = dp[i - 1][j]; //默认
                if (j >= w) {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                    if (dp[i - 1][j] < dp[i - 1][j - w] + v) {
                        dp[i][j] = dp[i - 1][j - w] + v;
//                        itemList.add(new Integer[]{w, v});//这句话--DP表中所有的斜线
                    }
                }
            }
        }
        print_DP_2(dp);
        System.out.println();

        //回溯的感觉--从dp表最右下角往前面找
        int i = N - 1, j = WeightLimit;
        while (i > 0 && j > 0) {
//           讨论分类为--i=0 第一行是特例
            while ((i >= 1) && dp[i][j] == dp[i - 1][j]) { //与上一行相同--不拿weight[i]
                i--;
            }
            if ((i >= 1) && dp[i][j] > dp[i - 1][j]) {
                itemList.add(new Integer[]{weights[i], values[i]});
                //这两句话先后顺序--不能调换啊！！！！！
                j -= weights[i];
                i--;
            } else if (i == 0) {
                itemList.add(new Integer[]{weights[0], values[0]});
            }
        }
        return itemList;
    }


    //    ArrayList去除重复
    public static <T> ArrayList<T> getSingle(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();     //创建新集合
        Iterator<T> it = list.iterator();        //根据传入的集合(旧集合)获取迭代器
        while (it.hasNext()) {                  //遍历老集合
            T obj = it.next();                  //记录每一个元素
            if (!newList.contains(obj)) {      //如果新集合中不包含旧集合中的元素
                newList.add(obj);       //将元素添加
            }
        }
        return newList;
    }

    //去重测试
    @Test
    public void singleTest() {


        ArrayList list = new ArrayList();
        list.add("她说");
        list.add("晴天");
        list.add("晴天");
        list.add("茉莉雨");
        list.add("茉莉雨");
        list.add("最长的电影");
        ArrayList newList = getSingle(list);
        System.out.println(newList);

        ArrayList<String> newList2 = new ArrayList<String>(new HashSet<String>(list));
        System.out.println(newList2);

    }

}
