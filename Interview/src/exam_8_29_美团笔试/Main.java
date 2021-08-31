package exam_8_29_美团笔试;

import java.util.*;

/**
 * @author pxf
 * @create 2021-08-29 10:14
 */
public class Main {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Solution();
        // case1
        Scanner cin = new Scanner(System.in);
        int a;
        List<Integer> list = new ArrayList<Integer>();
        while(cin.hasNextInt())
        {
            a = cin.nextInt();
            list.add(a);
        }
        int[] nums = list.stream().mapToInt(Integer::valueOf).toArray();
        int res = solution.func(nums);
        System.out.println(res);
    }

    static class Solution {
        int sum = 0;

        public int func(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            int len = nums.length;
            for (int num : nums) {
                // 1--走逻辑
                sum += helper(set, num);
                // 2--加入set
                set.add(num);
            }
            return sum;
        }

        private int helper(Set<Integer> set, int num) {
            int cnt = 0;
            for (Integer x : set) {
                if (num > x) cnt++;
            }
            return cnt;
        }

    }
}
// 第一行一个正整数 n，为丁香树数目；
//
//第二行 n 个数 ai，第 i 个数代表第 i 棵丁香树的芳香度。
//
//对于 30% 的数据，n ≤ 100；
//
//对于 100% 的数据，1 ≤ n ≤ 105, 1 ≤ ai ≤ 30。

