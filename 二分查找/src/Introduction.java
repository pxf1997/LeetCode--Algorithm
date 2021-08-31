import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-04-20 9:40
 */
public class Introduction {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 5, 7, 9, 12};
        Arrays.sort(data);
        int res = binarySearch(data, 12);
        int res2 = binarySearchForInsert(data, 13);
        System.out.println(res2);
    }

    private static int binarySearchForInsert(int[] nums, int key) {
        int lo = 0;
//        int hi = nums.length - 1;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // helper
            System.out.println("lo=" + lo + " mid=" + mid + " hi=" + hi);
            if (nums[mid] >= key) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

}
