/**
 * 题目Id：401
 * 题目：二进制手表
 * 日期：2021-06-21 09:49:42
 */
//二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
// 
//
// 
// 例如，下面的二进制手表读取 "3:25" 。 
// 
//
// 
//
// （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 
//Unported (CC BY-SA 3.0) ） 
//
// 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。 
//
// 小时不会以零开头： 
//
// 
// 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。 
// 
//
// 分钟必须由两位数组成，可能会以零开头： 
//
// 
// 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：turnedOn = 1
//输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
// 
//
// 示例 2： 
//
// 
//输入：turnedOn = 9
//输出：[]
// 
//
// 
//
// 解释： 
//
// 
// 0 <= turnedOn <= 10 
// 
// Related Topics 位运算 回溯算法 
// 👍 272 👎 0


package daily_2021_06;

//二进制手表

import java.util.ArrayList;
import java.util.List;

public class P401_BinaryWatch {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P401_BinaryWatch().new Solution();
        List<String> res = solution.readBinaryWatch(2);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        // my--分配给 hours 和 minutes 的灯数量,再组合求和--太复杂了!!!
        // 参考--枚举时分的二进制表示,统计1的个数之和为turnedOn即可
        public List<String> readBinaryWatch(int turnedOn) {
            List<String> res = new ArrayList<>();
            for (int hour = 0; hour < 12; hour++) {
                for (int minute = 0; minute < 60; minute++) {
                    if (Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {
                        res.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
                    }
                }
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
