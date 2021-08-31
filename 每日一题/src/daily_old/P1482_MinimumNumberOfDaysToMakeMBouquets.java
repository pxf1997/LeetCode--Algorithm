package daily_old; /**
 * 题目Id：1482
 * 题目：制作 m 束花所需的最少天数
 * 日期：2021-05-09 20:59:44
 */
//给你一个整数数组 bloomDay，以及两个整数 m 和 k 。 
//
// 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。 
//
// 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。 
//
// 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
//输出：3
//解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
//现在需要制作 3 束花，每束只需要 1 朵。
//1 天后：[x, _, _, _, _]   // 只能制作 1 束花
//2 天后：[x, _, _, _, x]   // 只能制作 2 束花
//3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
// 
//
// 示例 2： 
//
// 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
//输出：-1
//解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
// 
//
// 示例 3： 
//
// 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
//输出：12
//解释：要制作 2 束花，每束需要 3 朵。
//花园在 7 天后和 12 天后的情况如下：
//7 天后：[x, x, x, x, _, x, x]
//可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
//12 天后：[x, x, x, x, x, x, x]
//显然，我们可以用不同的方式制作两束花。
// 
//
// 示例 4： 
//
// 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
//输出：1000000000
//解释：需要等 1000000000 天才能采到花来制作花束
// 
//
// 示例 5： 
//
// 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// bloomDay.length == n 
// 1 <= n <= 10^5 
// 1 <= bloomDay[i] <= 10^9 
// 1 <= m <= 10^6 
// 1 <= k <= n 
// 
// Related Topics 数组 二分查找 
// 👍 158 👎 0


//制作 m 束花所需的最少天数

import java.util.ArrayList;
import java.util.List;

public class P1482_MinimumNumberOfDaysToMakeMBouquets {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1482_MinimumNumberOfDaysToMakeMBouquets().new Solution();
        int minDays = solution.minDays(new int[]{1, 1, 10, 3, 3, 10, 2, 2}, 3, 2);
//        int minDays = solution.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3);
        System.out.println("minDays = " + minDays);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //canMake辅助函数 + 二分查找
    class Solution1 {
        /**
         * @param bloomDay 花坛数组
         * @param m        共m束
         * @param k        每束花k朵
         * @return 最少天数--把我制作的花献给我的母亲
         */
        public int minDays(int[] bloomDay, int m, int k) {
            //花朵数量少于 k×m
            if (m > bloomDay.length / k) {
                return -1;
            }

            //所需的最少天数一定不会小于数组 bloomDay 中的最小值，一定不会大于数组 bloomDay 中的最大值
            int low = Integer.MAX_VALUE, high = 0;
            int length = bloomDay.length;
            for (int i = 0; i < length; i++) {
                low = Math.min(low, bloomDay[i]);
                high = Math.max(high, bloomDay[i]);
            }

            while (low < high) {
                int days = (high - low) / 2 + low;
                boolean cur = canMake(bloomDay, days, m, k);
                //helper
                System.out.print("low=" + low + "  high=" + high);
                System.out.println("  当前天数:" + days + "  能否完成:" + cur);
                System.out.println();

                if (canMake(bloomDay, days, m, k)) {
                    high = days;
                } else {
                    low = days + 1;
                }
            }
            System.out.println("跳出循环时 low=" + low + "  high=" + high);
            return low;
        }

        //辅助函数用于判断在给定的天数内能否制作出指定数量的花束--多一个参数days--时间限制
        //遍历数组bloomDay，计算其中的长度为 k 且最大元素不超过 days 的不重合的连续子数组的数量，
        // 如果符合要求的不重合的连续子数组的数量大于或等于 m 则返回 true，否则返回 false。
        public boolean canMake(int[] bloomDay, int days, int m, int k) {
            int bouquets = 0;
            int flowers = 0;
            int length = bloomDay.length;
            for (int i = 0; i < length && bouquets < m; i++) {
                if (bloomDay[i] <= days) {
                    flowers++;
                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                    }
                } else {
                    flowers = 0;
                }
            }
            System.out.println("bouquets = " + bouquets);
            return bouquets >= m;
        }
    }

    //不用二分查找--超时咯
    class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            //花朵数量少于 k×m
            if (m * k > bloomDay.length) {
                return -1;
            }

            //所需的最少天数一定不会小于数组 bloomDay 中的最小值，一定不会大于数组 bloomDay 中的最大值
            int low = Integer.MAX_VALUE, high = 0;
            int len = bloomDay.length;
            for (int i = 0; i < len; i++) {
                low = Math.min(low, bloomDay[i]);
                high = Math.max(high, bloomDay[i]);

            }

            //不用二分查找--超时而已，不会错的！
            while (low < high) {
                int mid = low + (high - low) / 2;
                boolean cur = canMake(bloomDay, mid, m, k);
                //helper
                System.out.print("low=" + low + "  high=" + high);
                System.out.println("  当前天数:" + mid + "  能否完成:" + cur);
                System.out.println();

                if (cur) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            System.out.println("跳出循环时 low=" + low + "  high=" + high);
            return low;
        }


        //辅助函数用于判断在给定的天数内能否制作出指定数量的花束--多一个参数days--时间限制
        //遍历数组bloomDay，计算其中的长度为 k 且最大元素不超过 days 的不重合的连续子数组的数量，
        // 如果符合要求的不重合的连续子数组的数量大于或等于 m 则返回 true，否则返回 false。
        public boolean canMake(int[] bloomDay, int days, int m, int k) {
            int bouquets = 0;
            int flowers = 0;
            int length = bloomDay.length;
            List<Integer> cur = new ArrayList<Integer>();
            for (int i = 0; i < length; i++) {
                if (bloomDay[i] <= days) {
                    flowers++;
                    cur.add(i);
                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                        System.out.println("第" + bouquets + "束花:" + cur);
                        cur.clear();
                    }
                } else { //不连续--中断了 将现有花朵数重置为0
                    flowers = 0;
                }
            }
            return bouquets >= m;
        }


    }

//leetcode submit region end(Prohibit modification and deletion)

}
