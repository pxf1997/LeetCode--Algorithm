package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pxf
 * @create 2021-04-15 15:31
 */
public class max_min {
    public int findmax(int[] data) {
        int max = Integer.MIN_VALUE;
        for (int x : data) {
            if (x > max) max = x;
        }
        return max;
    }
    public int findmin(int[] data) {
        int min = Integer.MAX_VALUE;
        for (int x : data) {
            if (x < min) min = x;
        }
        return min;
    }

//    public int findListMax(List<Integer> data) {
//        return (int) Collections.max(data);
//    }


}
