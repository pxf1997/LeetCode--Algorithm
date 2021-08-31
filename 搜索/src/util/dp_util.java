package util;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-04-29 16:46
 */
public class dp_util {
    public static void print_2D(int[][] dp) {
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void print_2D(char[][] dp) {
        for (char[] chars : dp) {
            System.out.println(Arrays.toString(chars));
        }
    }
    public static void print_2D(boolean[][] dp) {
        for (boolean[] bs : dp) {
            System.out.println(Arrays.toString(bs));
        }
    }
}
