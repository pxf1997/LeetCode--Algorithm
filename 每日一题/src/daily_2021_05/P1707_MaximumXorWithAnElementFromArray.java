package daily_2021_05; /**
 * 题目Id：1707
 * 题目：与数组中元素的最大异或值
 * 日期：2021-05-23 20:47:50
 */
//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。 
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
// xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。 
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
//查询的答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
// 
//
// 示例 2： 
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length, queries.length <= 105 
// queries[i].length == 2 
// 0 <= nums[j], xi, mi <= 109 
// 
// Related Topics 位运算 字典树 
// 👍 95 👎 0


//与数组中元素的最大异或值

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P1707_MaximumXorWithAnElementFromArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1707_MaximumXorWithAnElementFromArray().new Solution();
//        int[] maximizeXor = solution.maximizeXor(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}});
        int[] maximizeXor = solution.maximizeXor(new int[]{5, 2, 4, 6, 6, 3}, new int[][]{{12, 4}, {8, 1}, {6, 3}});
        System.out.println("maximizeXor = " + Arrays.toString(maximizeXor));
    }


    //  my_暴力_超时
    class Solution_my {
        public int[] maximizeXor(int[] nums, int[][] queries) {
            Arrays.sort(nums);
            int[] res = new int[queries.length];
            // 遍历 queries
            for (int i = 0; i < queries.length; i++) {
                int xi = queries[i][0], mi = queries[i][1];
                List<Integer> list = new ArrayList<>();
                for (int num : nums) {
                    if (num <= mi) {
                        list.add(num);
                    }
                }
                res[i] = findMax(list, xi);

            }
            return res;
        }

        private int findMax(List<Integer> list, int xi) {
            if (list.size() == 0) {
                return -1;
            }
            int max = Integer.MIN_VALUE;
            for (Integer i : list) {
                max = Math.max(max, i ^ xi);
            }
            return max;
        }


    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //  字典树
    class Solution {
        public int[] maximizeXor(int[] nums, int[][] queries) {
            Arrays.sort(nums);
            int numQ = queries.length;
            int[][] newQueries = new int[numQ][3];
            for (int i = 0; i < numQ; ++i) {
                newQueries[i][0] = queries[i][0];
                newQueries[i][1] = queries[i][1];
                newQueries[i][2] = i;
            }
            // 查询按 mi 排序
            Arrays.sort(newQueries, new Comparator<int[]>() {
                public int compare(int[] query1, int[] query2) {
                    return query1[1] - query2[1];
                }
            });

            int[] ans = new int[numQ];
            Trie trie = new Trie();
            int idx = 0, n = nums.length;
            for (int[] query : newQueries) {
                int x = query[0], m = query[1], qid = query[2];
                while (idx < n && nums[idx] <= m) {
                    trie.insert(nums[idx]);
                    ++idx;
                }
                if (idx == 0) { // 字典树为空
                    ans[qid] = -1;
                } else {
                    ans[qid] = trie.getMaxXor(x);
                }
            }
            return ans;
        }
    }

    class Trie {
        static final int L = 30;
        Trie[] children = new Trie[2];

        public void insert(int val) {
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXor(int val) {
            int ans = 0;
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    ans |= 1 << i;
                    bit ^= 1;
                }
                node = node.children[bit];
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
