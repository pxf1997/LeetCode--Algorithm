/**
 * 题目Id：218
 * 题目：天际线问题
 * 日期：2021-07-13 09:58:34
 */
//城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。 
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示： 
//
//
// 
// lefti 是第 i 座建筑物左边缘的 x 坐标。 
// righti 是第 i 座建筑物右边缘的 x 坐标。 
// heighti 是第 i 座建筑物的高度。 
// 
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
//列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。 
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
//案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...] 
//
// 
//
// 示例 1： 
//
// 
//输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//解释：
//图 A 显示输入的所有建筑物的位置和高度，
//图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。 
//
// 示例 2： 
//
// 
//输入：buildings = [[0,2,3],[2,5,3]]
//输出：[[0,3],[5,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= buildings.length <= 104 
// 0 <= lefti < righti <= 231 - 1 
// 1 <= heighti <= 231 - 1 
// buildings 按 lefti 非递减排序 
// 
// Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列） 
// 👍 438 👎 0


package daily_2021_07;

//天际线问题

import java.util.*;

public class P218_TheSkylineProblem {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P218_TheSkylineProblem().new Solution();
        int[][] buildings = new int[][]{
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8},
        };
        List<List<Integer>> res = solution.getSkyline(buildings);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 扫描线 + 优先队列
    class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            // pq中元素结构--[横坐标值(边缘), 高度]
            // pq逻辑--最大堆,高度最大(按高度"排序")
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
            List<Integer> boundaries = new ArrayList<>();
            for (int[] building : buildings) {
                boundaries.add(building[0]);
                boundaries.add(building[1]);
            }
            Collections.sort(boundaries);
            System.out.println("boundaries = " + boundaries);
            System.out.println();

            List<List<Integer>> res = new ArrayList<>();
            int len = buildings.length, idx = 0;

            // 遍历 boundary--横坐标
            for (int boundary : boundaries) {
                System.out.println("boundary = " + boundary);
                while (idx < len && buildings[idx][0] <= boundary) {
                    int[] offer = {buildings[idx][1], buildings[idx][2]};
                    pq.offer(offer);
                    idx++;
                    System.out.println("入队:" + Arrays.toString(offer));
                    //System.out.println("入队横坐标:" + buildings[idx][1] + "  高度:" + buildings[idx][2]);
                }

                // 如果不「包含该横坐标」，我们就将该队首元素弹出队列
                while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                    int[] poll = pq.poll();
                    System.out.println("出队:" + Arrays.toString(poll));
                }

                int maxn = pq.isEmpty() ? 0 : pq.peek()[1]; // 队首元素高度,若队列空则为0
                System.out.println("maxn = " + maxn);
                // 如果当前关键点的纵坐标大小与前一个关键点的纵坐标大小相同，则说明当前关键点无效，我们跳过该关键点即可。
                if (res.size() == 0 || maxn != res.get(res.size() - 1).get(1)) {
                    List<Integer> temp = Arrays.asList(boundary, maxn);
                    res.add(temp);
                    System.out.println("添加结果:" + temp);
                }
                System.out.println();
            }
            return res;
            // 分析--优化
            // 每一座建筑的左边缘信息只被用作加入优先队列时的依据，
            // 当其加入优先队列后，我们只需要用到其高度信息（对最大高度有贡献）以及其右边缘信息（弹出优先队列的依据），
            // 因此只需要在优先队列中保存这两个元素即可。

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
