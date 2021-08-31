/**
 * 题目Id：275
 * 题目：H 指数 II
 * 日期：2021-07-12 12:03:50
 */
//给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。 
//
// h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别
//被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）" 
//
// 
//
// 示例: 
//
// 输入: citations = [0,1,3,5,6]
//输出: 3 
//解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
//     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。 
//
// 
//
// 说明: 
//
// 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。 
//
// 
//
// 进阶： 
//
// 
// 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。 
// 你可以优化你的算法到对数时间复杂度吗？ 
// 
// Related Topics 数组 二分查找 
// 👍 131 👎 0


package daily_2021_07;

//H 指数 II

public class P275_HIndexIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P275_HIndexIi().new Solution();
        int hIndex = solution.hIndex(new int[]{0, 1, 3, 5, 6});
        System.out.println("hIndex = " + hIndex);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考题解-- 有序就二分查找
    // 分析-- lo=0, hi=len-1, mid满足 len-mid篇论文被引用至少 citations[mid]次
    //       若满足 citations[mid] >= len - mid,右边界缩小,否则左边界缩小
    class Solution {
        public int hIndex(int[] citations) {
            int len = citations.length;
            int lo = 0, hi = len - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (citations[mid] >= len - mid) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            // 分析-- 包含论文的下标范围[lo, len-1] 共 len-1-lo+1 = len-lo
            // 例: {0,1,3,5,6} len=5,lo=2, 即{3,5,6}
            System.out.println("lo = " + lo);
            return len - lo;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        public int hIndex(int[] citations) {
            int h = 0;
            int len = citations.length;
            for (int i = 0; i < len; i++) {
                // citations[i] 当前论文引用数(从小到大有序)
                 System.out.println("大于等于引用数 " + citations[i] + " 的篇数 : " + (len - i));
                if (citations[i] >= len - i) {
                    h = len - i; // h是递减枚举的
                    break;
                }
            }
            return h;
        }
    }


}
