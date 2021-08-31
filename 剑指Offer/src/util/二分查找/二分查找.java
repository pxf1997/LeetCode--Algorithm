package util.二分查找;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-06-15 9:28
 */
public class 二分查找 {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 3, 5, 7, 7, 7, 9, 12};
        Arrays.sort(data);

//        System.out.println("data = " + Arrays.toString(data));
//        int res = binarySearch(data, 13);
//        System.out.println("res = " + res);
//        System.out.println();

        System.out.println("data = " + Arrays.toString(data));
//        int res2 = binarySearchForInsert(data, 0);
        int res2 = binarySearchForInsert(data, 25);
//        int res2 = binarySearchForInsert(data, 7);
//        int res2 = binarySearchForInsert(data, 11);
        System.out.println("res2 = " + res2);
    }

    /**
     * @return 查找key的插入点,
     * 若已有若干个相同元素则附加在右侧,
     * 若key是最大的则附加在最后
     */
    public static int binarySearchForInsert(int[] nums, int key) {
        int lo = 0;
        int hi = nums.length - 1;
//        int hi = nums.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // helper
            System.out.println("lo = " + lo + "  hi = " + hi + "  mid = " + mid);
            if (nums[mid] <= key) { // 已有相同元素,附加在右侧
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println("lo = " + lo + "  hi = " + hi);
        return lo;
    }

    // 查找某元素位置,失败返回-1
    public static int binarySearch(int[] nums, int key) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // helper
            System.out.println("lo = " + lo + "  hi = " + hi + "  mid=" + mid);
            if (nums[mid] == key) {
                return mid;
            } else if (nums[mid] > key) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

}
