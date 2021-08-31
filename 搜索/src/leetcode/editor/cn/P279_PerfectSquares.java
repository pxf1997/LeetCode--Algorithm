/**
 * 题目Id：279
 * 题目：完全平方数
 * 日期：2021-04-28 16:02:23
 */
//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 846 👎 0


package leetcode.editor.cn;

//完全平方数


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P279_PerfectSquares {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P279_PerfectSquares().new Solution();
        int result = solution.numSquares(12);
        System.out.println("result = " + result);
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        可以将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边。
        //        要求解最小的平方数数量，就是求解从节点 n 到节点 0 的最短路径。
        //        本题也可以用动态规划求解，在之后动态规划部分中会再次出现。
        public int numSquares(int n) {
            List<Integer> squares = generateSquares_2(n);
            Queue<Integer> queue = new LinkedList<Integer>();
            boolean[] marked = new boolean[n + 1];
            queue.add(n);
            marked[n] = true;
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                while (size-- > 0) {
                    int cur = queue.poll();
                    for (Integer s : squares) {
                        int next = cur - s;
                        if (next < 0) {
                            break;
                        }
                        if (next == 0) {
                            return level;
                        }
                        if (marked[next]) {
                            continue;
                        }
                        marked[next] = true;
                        queue.add(next);
                    }
                }
            }
            return n;
        }

        /**
         * 生成小于n的平方数序列
         *
         * @return 1, 4, 9, ...
         */
        private List<Integer> generateSquares(int n) {
            List<Integer> res = new ArrayList<Integer>();
            for (int i = 0; i <= Math.sqrt(n); i++) {
                res.add(i * i);
            }
            return res;
        }

        /**
         * 生成小于 n 的平方数序列
         *
         * @return 1, 4, 9, ...
         */
        private List<Integer> generateSquares_2(int n) {
            //1,4,9,16,25,36  diff=3,5,7,9,11.....
            //相临两个完全平方数之差可以组成一个等差数列:1,3,5,7,9,11
            List<Integer> squares = new ArrayList<>();
            int square = 1;
            int diff = 3;
            while (square <= n) {
                squares.add(square);
                square += diff;
                diff += 2;
            }
            return squares;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
