/**
 * 题目Id：274
 * 题目：H 指数
 * 日期：2021-07-12 11:13:22
 */
//给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。 
//
// h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引
//用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。 
//
// 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。 
//
// 
//
// 示例： 
//
// 
//输入：citations = [3,0,6,1,5]
//输出：3 
//解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
//     由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。 
//
// 
//
// 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。 
// Related Topics 数组 计数排序 排序 
// 👍 202 👎 0


package daily_2021_07;

//H 指数

import java.util.Arrays;

public class P274_HIndex {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P274_HIndex().new Solution();
        int[] citations = new int[]{3, 0, 6, 1, 5};


        int hIndex = solution.hIndex(citations);
        System.out.println("hIndex = " + hIndex);
    }


    // 暴力法--从高到低枚举 h指数
    class Solution_my {
        public int hIndex(int[] citations) {
            int max = Arrays.stream(citations).max().getAsInt();
            int res = 0;
            // i--候选的 h指数
            for (int i = max; i > 0; i--) {
                int cnt = 0;
                for (int citation : citations) {
                    if (citation >= i) cnt++;
                }
                //System.out.println("引用数大于等于 " + i + " 的论文篇数 = " + cnt);
                // 篇数cnt >= h指数
                if (cnt >= i) {
                    res = i;
                    break;
                }
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考题解--排序
    class Solution {
        public int hIndex1(int[] citations) {
            Arrays.sort(citations);
            int h = 0, i = citations.length - 1;
            // citations[i] > h 对应逻辑----
            // 找到了一篇被至少引用了 h+1 次的论文,现有h加1
            while (i >= 0 && citations[i] > h) {
                h++;
                i--;
            }
            return h;
        }

        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int h = 0;
            int len = citations.length;
            for (int i = 0; i < len; i++) {
                // citations[i] 当前论文引用数(从小到大有序)
                System.out.println("大于等于引用数 " + citations[i] + " 的篇数 : " + (len - i));
                if (citations[i] >= len - i) {
                    h = len - i;
                    break;
                }
            }
            return h;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
