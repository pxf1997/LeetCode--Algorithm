/**
 * 题目Id：605
 * 题目：种花问题
 * 日期：2021-04-19 10:47:58
 */
//假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。 
//
// 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则
//的情况下种入 n 朵花？能则返回 true ，不能则返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：flowerbed = [1,0,0,0,1], n = 1
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：flowerbed = [1,0,0,0,1], n = 2
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= flowerbed.length <= 2 * 104 
// flowerbed[i] 为 0 或 1 
// flowerbed 中不存在相邻的两朵花 
// 0 <= n <= flowerbed.length 
// 
// Related Topics 贪心算法 数组 
// 👍 342 👎 0


package leetcode.editor.cn;

//种花问题

public class P605_CanPlaceFlowers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P605_CanPlaceFlowers().new Solution();

        int[] flowerbed = new int[]{1,0,0,0,1};
        int n = 1;

        boolean b = solution.canPlaceFlowers(flowerbed, n);
        System.out.println("b = " + b);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        防御式编程思想：在 flowerbed 数组两端各增加一个 0， 这样处理的好处在于不用考虑边界条件，
//        任意位置处只要连续出现三个 0 就可以栽上一棵花。
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int[] temp = new int[flowerbed.length + 2];
//            手动数组复制
//            for (int i = 1; i < flowerbed.length + 1; i++) {
//                temp[i] = flowerbed[i - 1];
//            }
            System.arraycopy(flowerbed, 0, temp, 1, flowerbed.length);

            for (int i = 1; i < temp.length - 1; i++) {
                if (temp[i] == 0 && temp[i - 1] == 0 && temp[i + 1] == 0) {
                    n--;
                    temp[i] = 1;
                }
            }
            return n <= 0;
        }
        public boolean canPlaceFlowers1(int[] flowerbed, int n) {
            int len = flowerbed.length;
            int cnt = 0;
            for (int i = 0; i < len && cnt < n; i++) {
                if (flowerbed[i] == 1) {
                    continue;
                }
                int pre = i == 0 ? 0 : flowerbed[i - 1]; //最左侧
                int next = i == len - 1 ? 0 : flowerbed[i + 1]; //最右侧
                if (pre == 0 && next == 0) {
                    cnt++;
                    flowerbed[i] = 1;
                }
            }
            return cnt >= n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
