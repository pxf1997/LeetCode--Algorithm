/**
 * 题目Id：1011
 * 题目：在 D 天内送达包裹的能力
 * 日期：2021-04-26 14:44:48
 */
//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。 
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。 
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= D <= weights.length <= 50000 
// 1 <= weights[i] <= 500 
// 
// Related Topics 数组 二分查找 
// 👍 264 👎 0


//在 D 天内送达包裹的能力

import java.util.Arrays;

public class P1011_CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1011_CapacityToShipPackagesWithinDDays().new Solution();
        int res = solution.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
//        int res = solution.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shipWithinDays_1(int[] weights, int D) {
            int total = 0;
            for (int weight : weights) {
                total += weight;
            }
            int maxWeight = Arrays.stream(weights).max().getAsInt();
            int avg = total / D; // 考虑单个货物重量大于平均运力avg
            int start = Math.max(maxWeight, avg);

            int res = 0;
            for (int i = avg; i <= total; i++) {//递增寻找运载能力 res
                res = i;
                int curD = 0; //所需工期
                int curW = 0; //当前装载量
                for (int j = 0; j < weights.length; j++) {
                    curW += weights[j];
                    if (curW > res) {
                        curD++;
                        if (curD > D) break;
                        else curW = weights[j]; //下一个桶，装第一个
                    }
                }
                curD++; //最后一个桶(虽未装满但也算数)
                if (curD <= D) return res;
            }

            return total;
        }

        public int shipWithinDays(int[] weights, int D) {
//            在二分查找的每一步中，我们实际上需要解决一个判定问题：给定船的运载能力 x，
//            我们是否可以在 D 天内运送完所有包裹呢？这个判定问题可以通过【贪心】的方法来解决：
//            由于我们必须按照数组weights 中包裹的顺序进行运送，因此我们从数组 weights 的首元素开始遍历，
//            将连续的包裹都安排在同一天进行运送。当这批包裹的重量大于运载能力 x 时，我们就需要将最后一个包裹拿出来，安排在新的一天，
//            并继续往下遍历。当我们遍历完整个数组后，就得到了最少需要运送的天数。
//            我们将「最少需要运送的天数」与 D 进行比较，就可以解决这个判定问题。当其小于等于 D 时，
//            我们就忽略二分的右半部分区间；当其大于 D 时，我们就忽略二分的左半部分区间。
            int left = Arrays.stream(weights).max().getAsInt();
            int right = Arrays.stream(weights).sum();
            while (left < right) { // 注意点1--终止left=right=mid
                int mid = left + (right - left) / 2;
                // need 为需要运送的天数
                // cur 为当前这一天已经运送的包裹重量之和
                int need = 1;
                int cur = 0;
                for (int weight : weights) {
                    if (cur + weight > mid) {
                        need++;
                        cur = 0;
                    }
                    cur += weight;
                }
                if (need <= D) { // 这里有等于号，下一行能取mid
                    right = mid; //注意点2--mid可取 不赋值为mid-1
                } else {
                    left = mid + 1;
                }
            }
            return left; //注意点3--返回left


        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
