package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pxf
 * @create 2021-04-20 9:21
 */
public class sort_exer_util {
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static int[] gennerateArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static int[] removeDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        //  版本3
        List<Integer> list = new ArrayList<Integer>(set);
        //  版本1
/*            int size = set.size();
            for (Integer x : set) {
                list.add(x);
            }*/
        //  版本2
        /*            list.addAll(set);*/
        // 梦寐以求的----List＜Integer＞ 转为 int[]
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
