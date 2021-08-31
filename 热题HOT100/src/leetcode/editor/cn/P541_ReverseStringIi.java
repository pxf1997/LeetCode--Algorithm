/**
 * 题目Id：541
 * 题目：反转字符串 II
 * 日期：2021-08-20 10:47:23
 */
//给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由小写英文组成 
// 1 <= k <= 104 
// 
// Related Topics 双指针 字符串 
// 👍 164 👎 0


package leetcode.editor.cn;

//反转字符串 II

public class P541_ReverseStringIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P541_ReverseStringIi().new Solution();
//        String res = solution.reverseStr("abcd", 2);
        String res = solution.reverseStr("abcdefg", 2);
        System.out.println("res = " + res);
    }


    // 暴力法
    class Solution_my {
        public String reverseStr(String s, int k) {
            int len = s.length();
            int start = 0;
            StringBuilder sb = new StringBuilder();
            // 2k个元素一组,前k个翻转,后k个复制不变
            while (start < len) {
                // [0,k] 翻转
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < k; i++) {
                    if (start + i < len) {
                        temp.append(s.charAt(start + i));
                    } else {
                        break;
                    }
                }
                temp.reverse();
                sb.append(temp);
                start += k;

                // [k+1, 2k] 保留
                for (int i = 0; i < k; i++) {
                    if (start + i < len) {
                        sb.append(s.charAt(start + i));
                    } else {
                        break;
                    }
                }
                start += k;
            }
            return sb.toString();

        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 模拟法--我们直接按题意进行模拟：
    // 反转每个下标从 2k2k 的倍数开始的，长度为 kk 的子串。若该子串长度不足 kk，则反转整个子串。
    class Solution {
        public String reverseStr(String s, int k) {
            int len = s.length();
            char[] chars = s.toCharArray();
            // 每 2k 一组处理
            for (int i = 0; i < len; i += 2 * k) {
                reverse(chars, i, Math.min(i + k, len) - 1);
            }
            return new String(chars);
        }

        // 翻转一段范围的字符串,不用判奇偶(类似判断回文串!)
        public void reverse(char[] arr, int left, int right) {
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
