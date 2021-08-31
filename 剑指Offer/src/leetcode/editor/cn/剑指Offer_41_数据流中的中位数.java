/**
 * 题目Id：剑指 Offer 41
 * 题目：数据流中的中位数
 * 日期：2021-06-17 15:29:20
 */
//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例 1： 
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
// 
//
// 示例 2： 
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000] 
//
// 
//
// 限制： 
//
// 
// 最多会对 addNum、findMedian 进行 50000 次调用。 
// 
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-strea
//m/ 
// Related Topics 堆 设计 
// 👍 153 👎 0


package leetcode.editor.cn;

//数据流中的中位数

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 剑指Offer_41_数据流中的中位数 {
    public static void main(String[] args) {
        //测试代码
        MedianFinder solution = new 剑指Offer_41_数据流中的中位数().new MedianFinder();
        solution.addNum(1);
        solution.addNum(2);
        System.out.println("solution.findMedian() = " + solution.findMedian());
        solution.addNum(3);
        System.out.println("solution.findMedian() = " + solution.findMedian());

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 参考1
    class MedianFinder1 {
        Queue<Integer> minHeap, maxHeap;

        /**
         * initialize your data structure here.
         */
        public MedianFinder1() {
            minHeap = new PriorityQueue<>(); // 小顶堆，保存较大的一半
            maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 大顶堆，保存较小的一半
        }

        public void addNum(int num) {
            if (minHeap.size() != maxHeap.size()) {
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
            } else {
                maxHeap.add(num);
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {
            if (minHeap.size() != maxHeap.size()) {
                return minHeap.peek();
            } else {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
        }

    }

    // 参考2
    class MedianFinder {
        // 思路： 看了题解，网上看了csdn学习了一个新的高级数据结构之后，决定使用优先队列解决中位数问题。
        // 思路：初始化一个大顶堆，一个小顶堆，小顶堆存的数永远大于大顶堆的数,而且大顶和小顶都是靠中间的两个数

        // 默认优先队列--最小堆--存放比中位数大的元素(右边)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 定制排序--最大堆--存放比中位数小的元素(左边)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }); // 不知为什么， lambda表达式会慢很多。。。。

        int count = 0;

        public void addNum(Integer num) {
            // 有一个 存进去再'吐出来'的过程,如何理解?
            if (count % 2 == 0) {
                // count为偶数,第1,3,5,7....个数,先加到最大堆,再将最大堆顶吐给最小堆
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
                count++;
            } else {
                // count为偶数,第2,4,6,8....个数,先加到最小堆,再将最小堆顶吐给最大堆
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
                count++;
            }
        }

        public Double findMedian() {
            if (count == 0) {
                return 0.0;
            }
            if (count % 2 == 0) {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            } else {
                return (double) minHeap.peek();
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
