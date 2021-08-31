package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public static void print_coordinateQueue(LinkedList<int[]> queue) {
        for (int[] coordinate : queue) {
            System.out.print(Arrays.toString(coordinate) + ", ");
        }
        System.out.println();
    }

    public static void print_ListWithArrays(List<int[]> list) {
        for (int[] ints : list) {
            System.out.print(Arrays.toString(ints) + ", ");
        }
        System.out.println();
    }

    public static void print_adjacency(List<List<Integer>> adjacency) {
        for (int i = 0; i < adjacency.size(); i++) {
            System.out.println(i + ":" + adjacency.get(i));
        }

    }


}
