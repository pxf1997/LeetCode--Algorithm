package exam_8_29_美团笔试;

import java.util.*;

/**
 * @author pxf
 * @create 2021-08-29 10:14
 */

// 思路--1--找出全排列 2--验证是否合法
public class Main2 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Solution();
        // case1
//        int n = 4;
//        int[] books = {1, 2, 3, 4};
//        int[] widths = {2, 4, 3, 4};
//        int count = solution.func(n, books, widths);
//        System.out.println(count);
        // 数据流
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        while (sc.hasNext()) {
            String str = sc.nextLine();
            list.add(str);
        }
        sc.close();
        //System.out.println("list = " + list);
        String line0 = list.get(0);
        String line1 = list.get(1);
        String line2 = list.get(2);

        int n = line0.charAt(0) - '0';
        String[] str1 = line1.split(" ");
        int[] books = new int[str1.length];
        int idx=0;
        for (String s : str1) {
            books[idx++] = Integer.parseInt(s);
        }

        idx=0;
        String[] str2 = line2.split(" ");
        int[] widths = new int[str2.length];
        for (String s : str2) {
            widths[idx++] = Integer.parseInt(s);
        }

        int res = solution.func(n, books, widths);
        System.out.println(res);
    }

    // Solution类
    // 思路--1--找出全排列 2--验证是否合法
    static class Solution {
        final int MOD = (int) (1e9 + 7);
        int count = 0;

        public int func(int n, int[] books, int[] widths) {
            Arrays.sort(widths);
            // 1--获取所有书籍可能的排列
            List<List<Integer>> permutes = permute(books);
            // System.out.println("permutes = " + permutes);
            // 2--验证此排列是否合法
            for (List<Integer> permute : permutes) {
                if (check(permute, widths)) {
                    // 输出合法排列
                    System.out.println("permute = " + permute);
                    count = (count + 1) % MOD;
                }
            }
            return count;
        }

        private boolean check(List<Integer> permute, int[] widths) {
            for (int i = 0; i < widths.length; i++) {
                // 二者长度都为 n
                if (permute.get(i) > widths[i]) return false;
            }
            return true;
        }

        public List<List<Integer>> permute(int[] nums) {
            // 不考虑nums的极端情况，比如length=0这种特例----毫无意义！！！
            // 准备工作
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];

            backtracking(nums, res, path, visited);
            return res;
        }

        //排列不需要begin (1234、2134不同)  需要visited[]
        private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                path.add(nums[i]);
                visited[i] = true;

                backtracking(nums, res, path, visited);//不可重复

                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }

    }

    // 思路--动态规划?
}
// 第一行一个整数 n，表示有 n 本书，同时有 n 个位置。
//
//第二行 n 个整数，表示每本书的厚度 ai(1 ≤ ai ≤ 109)。
//
//第三行 n 个整数，表示每个可以放书位置的宽度 bj (1 ≤ bj ≤ 109)。
//
//对于 30% 的数据，n ≤ 20；
//
//对于 100% 的数据，1 ≤ n ≤ 105。
//
//数据保证所有书可以返回书架

