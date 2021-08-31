/**
 * 题目Id：646
 * 题目：最长数对链
 * 日期：2021-04-21 16:21:51
 */
//给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。 
//
// 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。 
//
// 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。 
//
// 
//
// 示例： 
//
// 
//输入：[[1,2], [2,3], [3,4]]
//输出：2
//解释：最长的数对链是 [1,2] -> [3,4]
// 
//
// 
//
// 提示： 
//
// 
// 给出数对的个数在 [1, 1000] 范围内。 
// 
// Related Topics 动态规划 
// 👍 156 👎 0


package leetcode.editor.cn;

//最长数对链

import java.util.Arrays;
import java.util.Comparator;

public class P646_MaximumLengthOfPairChain {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P646_MaximumLengthOfPairChain().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        // 贪心--构成长度类似不重叠区间
        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[1], o2[1]);
                }
            });
            int res = 1;
            int right = pairs[0][1];
            int len = pairs.length;
            for (int i = 1; i < len; i++) {
                int newleft = pairs[i][0];
                int newright = pairs[i][1];
                if (newleft > right) {
                    right = newright;
                    res++;
                }
            }
            return res;

        }
    }

    class Solution {
        // dp[i]意义 以pairs[i]为结尾的最长链长
        // 状态转移： 遍历 j∈[0,i) 如果左端点 pairs[i][0]> pairs[j][1]:
        // 则 dp[i]=dp[j] + 1 维护一个max，使得dp[i]=max
        // 结果res是 dp数组的最大值
        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]-o2[0];
                }
            });
            int len = pairs.length;
            int[] dp = new int[len];
            Arrays.fill(dp, 1);//dp初始化为1 因为最少可以组成单节点链
            for (int i = 0; i < len; i++) {
                int max = 1;
                for (int j = 0; j < i; j++) {
                    if (pairs[i][0] > pairs[j][1]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
            }
            return Arrays.stream(dp).max().getAsInt();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
