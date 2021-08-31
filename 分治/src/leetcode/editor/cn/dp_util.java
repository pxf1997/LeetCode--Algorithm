package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-04-27 10:15
 */
public class dp_util {

    public static void print_DP_2(int[][] data) {
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }

    public static void print_DP_2(boolean[][] data) {
        for (boolean[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }

    }

    public static void print_DP_2(ArrayList<Integer>[][] dp) {
        for (ArrayList<Integer>[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
    }
}
