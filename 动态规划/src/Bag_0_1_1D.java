import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-04-22 15:10
 */
public class Bag_0_1_1D {
    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5, 9};
        int[] values = {3, 4, 8, 8, 10};
//        int[] weights = {1, 2, 3};
//        int[] values = {6, 10, 12};
        int nLimit = weights.length;  //只要重量W限制满足，你随便拿，对物品个数不做限制--完全背包

        int res = Bag_02_1(20, nLimit, weights, values);
        System.out.println(res);

    }

    /*  空间优化-- 类似一维dp的滚动数组？
        在程序实现时可以对 0-1 背包做优化。
        观察状态转移方程可以知道，前 i 件物品的状态仅与前 i-1 件物品的状态有关，
        因此可以将 dp 定义为一维数组，其中 dp[j] 既可以表示 dp[i-1][j] 也可以表示 dp[i][j]。
        此时， dp[j] = Math.max(dp[j], dp[j - w] + v);
        因为 dp[j-w] 表示 dp[i-1][j-w]，因此不能先求 dp[i][j-w]，防止将 dp[i-1][j-w] 覆盖。
        也就是说要先计算 dp[i][j] 再计算 dp[i][j-w]，在程序实现时需要按倒序来循环求解。
     */
    private static int Bag_02_1(int W, int nLimit, int[] weights1, int[] values1) { //nLimit为物品数
        int[] dp = new int[W + 1];
        System.out.println("dp = " + Arrays.toString(dp));
        System.out.println();
        //    物品（二维dp的一列）放在外循环，重量限制（W）在内循环，且内循环倒序；

        for (int i = 1; i <= nLimit; i++) { // 列循环 -- nLimit列压缩为 1 列
            int w = weights1[i - 1], v = values1[i - 1];
            for (int j = W; j >= 1; j--) { // 行循环
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
            System.out.println("i=" + i + " (" + w + "," + v + ")");
            System.out.println("dp=" + Arrays.toString(dp));
            System.out.println();
        }


        return dp[W];
    }


}
