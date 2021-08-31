package exam_8_29_360笔试;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main2 {
    public static void main(String[] args) {
        //测试代码
//        Solution solution = new Solution();
//        Scanner sc = new Scanner(System.in);
//        List<String> list = new ArrayList<String>();
//        while (sc.hasNext()) {
//            String str = sc.nextLine();
//            list.add(str);
//        }
//        sc.close();
//        String line0 = list.get(0);
//        String line1 = list.get(1);
//
//        String[] str1 = line0.split(" ");
//        int n = Integer.parseInt(str1[0]);
//        int m = Integer.parseInt(str1[1]);
//        int x = Integer.parseInt(str1[2]);
//        int k = Integer.parseInt(str1[3]);
//
//        String[] str2 = line1.split(" ");
//        int[] nums = new int[str2.length];
//        for (int i = 0; i < str2.length; i++) {
//            nums[i] = Integer.parseInt(str2[i]);
//        }
//        int count = solution.func(n, m, x, k, nums);
//        System.out.println(count);

//         case1
        Solution solution = new Solution();
        int n = 5, m = 2, x = 1, k = 2;
        int[] nums = {4, 4, 2, 4, 4};

        int count = solution.func(n, m, x, k, nums);
        System.out.println(count);

    }

    // 暴力法 O(n^m)
    static class Solution {
        int max = Integer.MIN_VALUE;

        public int func(int n, int m, int x, int k, int[] nums) {
            int[] positions = new int[n];
            for (int i = 0; i < n; i++) {
                positions[i] = i;
            }
            // 可以重复的排列
            List<List<Integer>> Generous = permute(positions, m);

            for (List<Integer> list : Generous) {
                compute_helper(list, nums, x, k);
            }

            return max;
        }

        private void compute_helper(List<Integer> list, int[] nums, int x, int k) {
            int[] temp = Arrays.copyOf(nums, nums.length);
            for (Integer position : list) {
                for (int i = position - x; i <= position + x; i++) {
                    if (i >= 0 && i < temp.length) {
                        temp[i] += k;
                    }
                }
            }
            System.out.println("list = " + list);
            System.out.println("temp = " + Arrays.toString(temp));
            System.out.println();

            max = Math.max(max, Arrays.stream(temp).min().getAsInt());
        }

        public List<List<Integer>> permute(int[] nums, int limit) {
            // 准备工作
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtracking(nums, res, path, limit);
            return res;
        }

        // 用过的位置还可以再用
        private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, int limit) {
            if (path.size() == limit) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {

                path.add(nums[i]);

                backtracking(nums, res, path, limit); // 允许重复

                path.remove(path.size() - 1);
            }
        }
    }

}


