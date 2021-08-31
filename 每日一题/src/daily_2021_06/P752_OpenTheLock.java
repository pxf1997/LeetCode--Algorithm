/**
 * 题目Id：752
 * 题目：打开转盘锁
 * 日期：2021-06-25 09:26:40
 */
//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
// 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。 
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。 
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。 
//
// 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。 
//
// 
//
// 示例 1: 
//
// 
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
// 
//
// 示例 2: 
//
// 
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：
//把最后一位反向旋转一次即可 "0000" -> "0009"。
// 
//
// 示例 3: 
//
// 
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
//et = "8888"
//输出：-1
//解释：
//无法旋转到目标数字且不被锁定。
// 
//
// 示例 4: 
//
// 
//输入: deadends = ["0000"], target = "8888"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 死亡列表 deadends 的长度范围为 [1, 500]。 
// 目标数字 target 不会在 deadends 之中。 
// 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。 
// 
// Related Topics 广度优先搜索 数组 哈希表 字符串 
// 👍 284 👎 0


package daily_2021_06;

//打开转盘锁

import java.util.*;

public class P752_OpenTheLock {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P752_OpenTheLock().new Solution();
        int res = solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
//        int res = solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0009");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 思路--bfs
    class Solution {
        public int openLock(String[] deadends, String target) {
            if ("0000".equals(target)) return 0;

            // 死亡集合
            Set<String> dead = new HashSet<>(Arrays.asList(deadends));
            if (dead.contains("0000")) return -1;

            // bfs搜索,level--层数
            int level = 0;
            Queue<String> queue = new LinkedList<>();
            queue.offer("0000");
            Set<String> visited = new HashSet<>();
            visited.add("0000");

            while (!queue.isEmpty()) {
                // 需要记录层数,分批次出队
                level++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    List<String> next_list = get(cur);
                    System.out.println("level:" + level + "  出队:" + cur + "  候选入队(8个):" + next_list);
                    for (String next : next_list) {
                        if (!visited.contains(next) && !dead.contains(next)) {
                            // 你错的有多蠢!!!
                            // if (next.equals("target")) return level;
                            if (next.equals(target)) return level;
                            queue.offer(next);
                            visited.add(next);
                            System.out.println("入队:" + next);
                        }
                    }
                    System.out.println();
                }
            }
            return -1;
        }

        // 枚举 cur 通过一次旋转得到的数字, 共8种
        private List<String> get(String cur) {
            List<String> res = new ArrayList<>();
            char[] chars = cur.toCharArray();
            for (int i = 0; i < 4; i++) {
                // 向两个方向转动
                char c = chars[i];
                chars[i] = numPrev(c); // Java值传递,chars[i]改变,c不变
                res.add(new String(chars));

                chars[i] = numNext(c);
                res.add(new String(chars));

                chars[i] = c;
            }
            return res;
        }

        // 后一个数字
        private char numNext(char c) {
            return c == '9' ? '0' : (char) (c + 1);
        }

        // 前一个数字
        private char numPrev(char c) {
            return c == '0' ? '9' : (char) (c - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
