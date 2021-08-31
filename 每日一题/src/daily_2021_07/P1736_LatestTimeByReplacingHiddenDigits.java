/**
 * 题目Id：1736
 * 题目：替换隐藏数字得到的最晚时间
 * 日期：2021-07-25 22:11:57
 */
//给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。 
//
// 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。 
//
// 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。 
//
// 
//
// 示例 1： 
//
// 
//输入：time = "2?:?0"
//输出："23:50"
//解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
// 
//
// 示例 2： 
//
// 
//输入：time = "0?:3?"
//输出："09:39"
// 
//
// 示例 3： 
//
// 
//输入：time = "1?:22"
//输出："19:22"
// 
//
// 
//
// 提示： 
//
// 
// time 的格式为 hh:mm 
// 题目数据保证你可以由输入的字符串生成有效的时间 
// 
// Related Topics 字符串 
// 👍 53 👎 0


package daily_2021_07;

//替换隐藏数字得到的最晚时间

public class P1736_LatestTimeByReplacingHiddenDigits {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1736_LatestTimeByReplacingHiddenDigits().new Solution();
        String res = solution.maximumTime("2?:?0");
//        String res = solution.maximumTime("??:??");
        System.out.println("res = " + res);
    }


    // 暴力法--疯狂 if else枚举,很傻而且还是错的!
    class Solution1 {
        public String maximumTime(String time) {
            String[] split = time.split(":");
            String hours = split[0];
            String minutes = split[1];
            String max_hours = helper_hours(hours);
            String max_minutes = helper_minutes(minutes);
            return max_hours + ":" + max_minutes;
        }

        private String helper_minutes(String minutes) {
            char[] chars = minutes.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '?') {
                    // '?x' 分钟的十位写5,个位写9
                    if (i == 0) chars[i] = '5';
                    else chars[i] = '9';
                }
            }
            return new String(chars);
        }

        private String helper_hours(String hours) {
            if (hours.equals("??")) return "23";
            char[] chars = hours.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '?') {
                    // '?x' 小时的个位写9/3, 十位写2
                    if (i == 0) {
                        if (chars[1] <= '3') chars[i] = '2';
                        else chars[i] = '1';
                    } else {
                        if (chars[0] == '0' || chars[0] == '1') chars[i] = '9';
                        else chars[i] = '3';
                    }
                }
            }
            return new String(chars);
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--贪心法
    class Solution {
        public String maximumTime(String time) {
            char[] chars = time.toCharArray();
            // chars[0]=='?'  若chars[1]∈[4,9] 则chars[0]=1,否则为2
            if (chars[0] == '?') {
                chars[0] = (chars[1] >= '4' && chars[1] <= '9') ? '1' : '2';
            }
            // chars[1]=='?'  若chars[0]==2 则chars[1]=3,否则为9
            if (chars[1] == '?') {
                chars[1] = (chars[0] == '2') ? '3' : '9';
            }
            if (chars[3] == '?') {
                chars[3] = '5';
            }
            if (chars[4] == '?') {
                chars[4] = '9';
            }
            return new String(chars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
