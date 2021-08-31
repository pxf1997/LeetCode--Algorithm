/**
 * 题目Id：剑指 Offer 62
 * 题目：圆圈中最后剩下的数字
 * 日期：2021-06-22 10:54:08
 */
//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
// 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 
//输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
// 👍 385 👎 0


package leetcode.editor.cn;

//圆圈中最后剩下的数字

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer_62_圆圈中最后剩下的数字 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_62_圆圈中最后剩下的数字().new Solution();
//        int lastRemaining = solution.lastRemaining(5, 3);
        int lastRemaining = solution.lastRemaining(4000, 997);
        System.out.println("lastRemaining = " + lastRemaining);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 队列模拟--超时
    // 也叫约瑟夫问题,热土豆问题,击鼓传花 等等
    class Solution {
        // n--人数 m--传递次数
        public int lastRemaining1(int n, int m) {
            if (n == 0 || m == 0) return -1; // 好没意思啊,输入个0搞啥玩意...

            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                queue.add(i);
            }
            int idx = 1;
            while (queue.size() > 1) {
                // 模拟传递过程
                System.out.println("模拟传递:");
                for (int i = 0; i < m - 1; i++) {
                    Integer out = queue.poll();
                    queue.add(out);
                    System.out.println(out + "出队并添加在队尾");
                }
                Integer out = queue.poll();
                System.out.println("第" + idx + "次移除:" + out);
                System.out.println();
                idx++;
            }
            return queue.poll();
        }

        // 简洁版
        public int lastRemaining(int n, int m) {
            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                queue.add(i);
            }
            while (queue.size() > 1) {
                // 模拟传递过程
                for (int i = 0; i < m - 1; i++) {
                    queue.offer(queue.poll());
                }
                queue.poll();
            }
            return queue.poll();
        }
    }

    // 数学
    class Solution2 {
        public int lastRemaining(int n, int m) {
            int ans = 0;
            // 最后一轮剩下2个人，所以从2开始反推
            for (int i = 2; i <= n; i++) {
                ans = (ans + m) % i;
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
