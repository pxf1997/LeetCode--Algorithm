package 测试;

import org.junit.Test;

import java.util.*;

/**
 * @author pxf
 * @create 2021-06-07 17:54
 */
public class 随手测试2 {
    public static void main(String[] args) {
        System.out.println("(char)1 = " + (char) 1);
        System.out.println("'1'-'0' = " + ('1' - '0'));
    }

    @Test
    public void test1() {
        List<int[]> res_list = new ArrayList<>() {{
            add(new int[]{1, 2, 3, 4, 5});
            add(new int[]{4, 5, 6});
            add(new int[]{7, 8});
        }};
        // 转化为二维数组
        int[][] ints = res_list.toArray(new int[res_list.size()][]);
        System.out.println("ints = " + Arrays.deepToString(ints));

    }

    @Test
    public void test2() {
        List<List<Integer>> res_list = new ArrayList<>();
        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("a = " + a);
        res_list.add(a);
        res_list.add(Arrays.asList(4, 5, 6));
        res_list.add(Arrays.asList(7, 8));
        // 怎么转化为二维数组?
        int size = res_list.size();
        int idx = 0;
        int[][] array_2D = new int[size][]; // 声明行数
        for (int i = 0; i < size; i++) {
            array_2D[idx++] = res_list.get(i).stream().mapToInt(Integer::valueOf).toArray();
        }
        System.out.println("array_2D = " + Arrays.deepToString(array_2D));
    }





}
