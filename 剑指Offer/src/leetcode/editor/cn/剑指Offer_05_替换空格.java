/**
 * 题目Id：剑指 Offer 05
 * 题目：替换空格
 * 日期：2021-06-09 16:31:36
 */
//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// 👍 122 👎 0


package leetcode.editor.cn;

//替换空格

public class 剑指Offer_05_替换空格 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_05_替换空格().new Solution();
        String res = solution.replaceSpace("We are happy.");
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String replaceSpace(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == ' ') {
                    sb.append("%20");
                } else{
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
