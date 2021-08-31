/**
 * 题目Id：554
 * 题目：砖墙
 * 日期：2021-05-02 10:30:37
 */
//你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。 
//
// 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没
//有穿过一块砖的。 
//
// 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的
//砖块数量最少 ，并且返回 穿过的砖块数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：wall = [[1],[1],[1]]
//输出：3
// 
// 
//
// 提示： 
//
// 
// n == wall.length 
// 1 <= n <= 104 
// 1 <= wall[i].length <= 104 
// 1 <= sum(wall[i].length) <= 2 * 104 
// 对于每一行 i ，sum(wall[i]) 应当是相同的 
// 1 <= wall[i][j] <= 231 - 1 
// 
// Related Topics 哈希表 
// 👍 169 👎 0


//砖墙

import java.util.*;

public class P554_BrickWall {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P554_BrickWall().new Solution();
        List<Integer> r1 = new ArrayList<>(Arrays.asList(1, 2, 2, 1));
        List<Integer> r2 = new ArrayList<>(Arrays.asList(3, 1, 2));
        List<Integer> r3 = new ArrayList<>(

                Arrays.asList(1, 3, 2));
        List<Integer> r4 = new ArrayList<>(Arrays.asList(2, 4));
        List<Integer> r5 = new ArrayList<>(Arrays.asList(3, 1, 2));
        List<Integer> r6 = new ArrayList<>(Arrays.asList(1, 3, 1, 1));
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(r1);
        wall.add(r2);
        wall.add(r3);
        wall.add(r4);
        wall.add(r5);
        wall.add(r6);

//        List<Integer> r1 = new ArrayList<>(Arrays.asList(100000000));
//        List<Integer> r2 = new ArrayList<>(Arrays.asList(100000000));
//        List<Integer> r3 = new ArrayList<>(Arrays.asList(100000000));
//        List<List<Integer>> wall = new ArrayList<>();
//        wall.add(r1);
//        wall.add(r2);
//        wall.add(r3);

        System.out.println("原始wall = " + wall);
        int res = solution.leastBricks(wall);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {

        public int leastBricks(List<List<Integer>> wall) {
            //似贪心用箭射气球--思路不对
            //砖块 从小到大排序（交换层，不交换每层内顺序）
//            Collections.sort(wall, new Comparator<List<Integer>>() {
//                @Override
//                public int compare(List<Integer> o1, List<Integer> o2) {
//                    int i = 0;
//                    while (i < o1.size() && i < o2.size()) {
//                        if (!o1.get(i).equals(o2.get(i))) {
//                            return o1.get(i) - o2.get(i);
//                        } else {
//                            i++;
//                        }
//                    }
//                    return 0;
//                }
//            });
//            System.out.println("排序wall = " + wall);

            int width = 0;
            for (Integer i : wall.get(0)) {
                width += i;
            }

            int min = Integer.MAX_VALUE;
            List<List<Integer>> positions = new ArrayList<>();
            for (List<Integer> row : wall) {
                //前缀和--缝隙位置
                List<Integer> curp = find_position(row);
                positions.add(curp);
            }
//            System.out.println("positions = " + positions);

            for (int i = 0; i < width; i++) {
                int count = wall.size(); //有几层
                for (List<Integer> position : positions) {
                    if (position.contains(i)) {
                        count--;
                    }
                }
                min = Math.min(min, count);
//                System.out.println("插入位置=" + i +"  count=" + count);
            }

            return min;
        }

        //        前缀和
        private List<Integer> find_position(List<Integer> row) { //不要0和len
            List<Integer> res = new ArrayList<Integer>();
            int sum = 0;
            if (row.size() == 1) {
                return row;
            }
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                res.add(sum);
            }

            return res;
        }
    }

    class Solution {
        public int leastBricks(List<List<Integer>> wall) {
//            垂线穿过的砖块数量加上从边缘经过的砖块数量之和是一个定值，即砖墙的高度。
//            因此，问题可以转换成求「垂线穿过的砖块边缘数量的最大值」，用砖墙的高度减去该最大值即为答案。
//            细节--需要统计每行砖块中 '除了最右侧的砖块以外' 的其他砖块的'右边缘'即可

            //key--缝隙的可能位置  val--出现次数
            Map<Integer, Integer> cnt = new HashMap<>();
            for (List<Integer> row : wall) {
                //每行砖块数量--讨论每层只有一块砖：cnt为空，即只有两边缘，中间没缝隙
                int n = row.size();
                int sum = 0;
                for (int i = 0; i < n - 1; i++) {
                    sum += row.get(i);
                    cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
                }
            }
//            System.out.println("cnt = " + cnt);

            //讨论每层只有一块砖：cnt为空，即只有两边缘(中间没缝隙)max最终为0
            int max = 0;
            for (Integer value : cnt.values()) {
                max = Math.max(value, max);
            }

            return wall.size() - max;

        }

    }


//leetcode submit region end(Prohibit modification and deletion)

}
