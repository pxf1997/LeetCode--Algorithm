package leetcode.editor.cn;

import org.junit.Test;

import java.util.*;

/**
 * @author pxf
 * @create 2021-04-22 18:14
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

    //    public static <T> void print_DP_2(T[][] data) {
//        for (T[] datum : data) {
//            System.out.println(Arrays.toString(datum));
//        }
//    }
    public static void print_DP_1(int[] data) {
        System.out.println(Arrays.toString(data));
    }

    //    移除重复元素--原地修改
    public static int[] removeDuplicate(int[] nums) {
        int fast = 1, slow = 1;
        int len = nums.length;
        while (fast < len) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return Arrays.copyOfRange(nums, 0, slow);
    }

    //    移除重复元素--开辟一个list
    public static int[] removeDuplicate2(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            if (!list.contains(num)) list.add(num);
        }
        int size = list.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    //    移除指定元素--原地
    public static int[] removeKey(int[] nums, int key) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != key) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return Arrays.copyOfRange(nums, 0, slow);
    }

    //    移除指定元素--开辟一个list
    public static int[] removeKey2(int[] nums, int key) {
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            if (num != key) list.add(num);
        }
        int size = list.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    @Test
    public void testArrayList_add() {
        //创建一个数组
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        // 往数组插入元素
        primeNumbers.add(2);
        primeNumbers.add(3);
        primeNumbers.add(5);
        System.out.println("ArrayList: " + primeNumbers);

        ArrayList<String> sites = new ArrayList<>();
        // 在该数组末尾插入元素
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        System.out.println("ArrayList: " + sites);
        // 在第一个位置插入元素
        sites.add(1, "Weibo");
        System.out.println("更新 ArrayList: " + sites);
    }


    @Test
    public void testRemoveDuplicate() {
        int[] nums = {1, 2, 1, 2, 3, 3, 4};
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println();
        int[] res = removeDuplicate(nums);
        System.out.println("res = " + Arrays.toString(res));
        //值传递--nums的地址，因此原地修改的话--nums也改变了
        System.out.println("nums = " + Arrays.toString(nums));
    }

    @Test
    public void testRemoveDuplicate2() {
        int[] nums = {1, 2, 1, 2, 3, 3, 4};
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println();
        int[] res = removeDuplicate2(nums);
        System.out.println("res = " + Arrays.toString(res));
        System.out.println("nums = " + Arrays.toString(nums));
    }

    @Test
    public void testRemoveKey1() {
        int[] nums = {1, 2, 1, 2, 3, 3, 4};
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println();
        int[] res = removeKey(nums, 1);
        System.out.println("res = " + Arrays.toString(res));
        System.out.println("nums = " + Arrays.toString(nums));
    }

    @Test
    public void testRemoveKey2() {
        int[] nums = {1, 2, 1, 2, 3, 3, 4};
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println();
        int[] res = removeKey2(nums, 1);
        System.out.println("res = " + Arrays.toString(res));
        System.out.println("nums = " + Arrays.toString(nums));
    }

    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> mapForFrequency = new HashMap<>();
        for (int num : nums) {
            mapForFrequency.put(num, mapForFrequency.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (Integer key : mapForFrequency.keySet()) {
            if (mapForFrequency.get(key) > 1) {
                res.add(key);
            }
        }
        System.out.println("res = " + res);
        return res.get(0);
    }


    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return num;
        }
        return -1;
    }

    //    方法二：原地交换 仅仅适用于----在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内
    public int findRepeatNumber3(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    @Test
    public void testFindRepeatNumber() {
        int[] nums = {1, 2, 1, 2, 3, 3, 4, 25, 26, 25};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println("repeatNumber = " + repeatNumber);

        int[] nums2 = {5, 6, 7, 7, 9};
        int repeatNumber2 = findRepeatNumber2(nums2);
        System.out.println("repeatNumber2 = " + repeatNumber2);
    }


}
