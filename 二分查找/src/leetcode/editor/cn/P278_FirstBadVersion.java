/**
 * 题目Id：278
 * 题目：第一个错误的版本
 * 日期：2021-04-20 14:19:51
 */
//你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有
//版本都是错的。 
//
// 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。 
//
// 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误
//的版本。你应该尽量减少对调用 API 的次数。 
//
// 示例: 
//
// 给定 n = 5，并且 version = 4 是第一个错误的版本。
//
//调用 isBadVersion(3) -> false
//调用 isBadVersion(5) -> true
//调用 isBadVersion(4) -> true
//
//所以，4 是第一个错误的版本。  
// Related Topics 二分查找 
// 👍 272 👎 0


package leetcode.editor.cn;

//第一个错误的版本

public class P278_FirstBadVersion {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P278_FirstBadVersion().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public class VersionControl {
        public boolean isBadVersion(int i) {
            return false;
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
//            方法一：线性扫描 [超出时间限制]
//            for (int i = 1; i <= n; i++) {
//                if (isBadVersion(i)) return i;
//            }
//            return 0;

/*           如果第 m 个版本出错，则表示第一个错误的版本在 [l, m] 之间，
            令 h = m；否则第一个错误的版本在 [m + 1, h] 之间，令 l = m + 1。
            */
            int lo = 1, hi = n;
//            !!!这句话很重要 ： 因为 h 的赋值表达式为 h = m，因此循环条件为 l < h。
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
//              为什么是 hi=mid 而不是 mid-1 呢，根据题目逻辑去理解！！！
//              因为要找的错误版本在[l, m]之间，可能就是mid
                if (isBadVersion(mid)) hi = mid;
//                else即mid不是错误版本，
//                即错误版本在[m + 1, h] 之间，令 l = m + 1。
                else lo = mid + 1;
            }
//            return hi;
            return lo;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
