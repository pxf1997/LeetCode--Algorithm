/**
 * 题目Id：295
 * 题目：数据流的中位数
 * 日期：2021-08-27 11:25:11
 */
//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
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
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 487 👎 0


package leetcode.editor.cn;

//数据流的中位数

import java.util.Comparator;
import java.util.PriorityQueue;

public class P295_FindMedianFromDataStream {
    public static void main(String[] args) {
        //测试代码
        MedianFinder2 solution = new P295_FindMedianFromDataStream().new MedianFinder2();
        solution.addNum(1);
        solution.addNum(2);
        System.out.println("solution.findMedian() = " + solution.findMedian());
        solution.addNum(3);
        System.out.println("solution.findMedian() = " + solution.findMedian());
        solution.addNum(4);
        solution.addNum(5);
        System.out.println("solution.findMedian() = " + solution.findMedian());


    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        // 设计细节--
        // 奇数个--最大堆(3,1,2)比最小堆(4,5) 多一个元素,直接返回最大堆顶
        PriorityQueue<Integer> maxHeap; // 最大堆--小于中位数的数 (3,1,2)
        PriorityQueue<Integer> minHeap; // 最小堆--大于中位数的数 (4,5)


        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> (b - a));
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // 新数据<=最大堆顶  应加入最大堆
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                // 两个堆大小不平衡了 --> 最大堆移动一个去最小堆
                if (minHeap.size() + 1 < maxHeap.size()) {
                    minHeap.offer(maxHeap.poll());
                }
            }
            // 新数据>最大堆顶  应加入最小堆
            else {
                minHeap.offer(num);
                // 两个堆大小不平衡了 --> 最小堆移动一个去最大堆
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }



    // 重新练习
    class MedianFinder2 {
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        /**
         * initialize your data structure here.
         */
        public MedianFinder2() {
            maxHeap = new PriorityQueue<>(new Comparator<>() {
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // 先判断空 (短路或)
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.offer(maxHeap.poll());
                }
            } else {
                minHeap.offer(num);
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
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
