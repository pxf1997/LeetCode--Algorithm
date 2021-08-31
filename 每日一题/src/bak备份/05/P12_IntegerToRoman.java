/**
 * 题目Id：12
 * 题目：整数转罗马数字
 * 日期：2021-05-14 09:57:37
 */
//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。 
//
// 
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给你一个整数，将其转为罗马数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 3
//输出: "III" 
//
// 示例 2: 
//
// 
//输入: num = 4
//输出: "IV" 
//
// 示例 3: 
//
// 
//输入: num = 9
//输出: "IX" 
//
// 示例 4: 
//
// 
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
// 
//
// 示例 5: 
//
// 
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 1 <= num <= 3999 
// 
// Related Topics 数学 字符串 
// 👍 571 👎 0


//整数转罗马数字

import java.util.*;

public class P12_IntegerToRoman {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P12_IntegerToRoman().new Solution();
//        String s = solution.intToRoman(23);
//        String s = solution.intToRoman(126);
//        String s = solution.intToRoman(14);
//        String s = solution.intToRoman(1994);
//        String s = solution.intToRoman(1900);
        String s = solution.intToRoman(1800);
        System.out.println("s = " + s);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            // MAP遍历不是按顺序的!

//            Map<Integer, Character> map = new HashMap<>();
//            map.put(1, 'I');
//            map.put(5, 'V');
//            map.put(10, 'X');
//            map.put(50, 'L');
//            map.put(100, 'C');
//            map.put(500, 'D');
//            map.put(1000, 'M');
//            System.out.println("map = " + map);

//            for (Integer key : map.keySet()) {
//                System.out.println("key = " + key);
//                System.out.println("map.get(key) = " + map.get(key));
//            }
//            List<Integer> list1 = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);
//            List<Character> list2 = Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M');

            //你小子真是个人才
            List<Integer> list1 = Arrays.asList(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000);
            List<String> list2 = Arrays.asList("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M");

            // 贪心--从大往小找
            for (int i = list1.size() - 1; i >= 0; i--) {
                int curNum = list1.get(i);
                if (num >= curNum) {
                    int cnt = num / curNum; // 有cnt个当前数字，例如有几个‘10’

                    System.out.println("curNum = " + curNum);
                    System.out.println("cnt = " + cnt);

                    for (int j = 0; j < cnt; j++) {
                        sb.append(list2.get(i)); // 加入cnt个对应字母
                    }

                    num = num % curNum;
                    System.out.println("sb = " + sb);
                    System.out.println();
                }

            }
            return sb.toString();

        }
    }

    class Solution {

        public String intToRoman(int num) {
            // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中，并且按照阿拉伯数字的大小降序排列
            int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder stringBuilder = new StringBuilder();
            int index = 0;
            while (index < nums.length) {
                // 特别注意：这里是等号
                while (num >= nums[index]) {
                    stringBuilder.append(romans[index]);
                    num -= nums[index];
                }
                index++;
            }
            return stringBuilder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
