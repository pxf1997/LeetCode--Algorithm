/**
 * 题目Id：443
 * 题目：压缩字符串
 * 日期：2021-08-21 23:57:55
 */
//给你一个字符数组 chars ，请使用下述算法压缩： 
//
// 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ： 
//
// 
// 如果这一组长度为 1 ，则将字符追加到 s 中。 
// 否则，需要向 s 追加字符，后跟这一组的长度。 
// 
//
// 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会
//被拆分为多个字符。 
//
// 请在 修改完输入数组后 ，返回该数组的新长度。 
//
// 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：chars = ["a","a","b","b","c","c","c"]
//输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
//解释：
//"aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
// 
//
// 示例 2： 
//
// 
//输入：chars = ["a"]
//输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
//解释：
//没有任何字符串被替代。
// 
//
// 示例 3： 
//
// 
//输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
//解释：
//由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
//注意每个数字在数组中都有它自己的位置。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= chars.length <= 2000 
// chars[i] 可以是小写英文字母、大写英文字母、数字或符号 
// 
// Related Topics 双指针 字符串 
// 👍 253 👎 0


package daily_2021_08;

//压缩字符串

public class P443_StringCompression {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P443_StringCompression().new Solution();
        // case1
//        int res = solution.compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'});
//        int res = solution.compress(new char[]{'a', 'b', 'c'});

        int res = solution.compress(new char[]{'a', 'a', 'b', 'b',
                'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 总结--细节不少,自作聪明去封装函数结果适得其反!
    class Solution_my {
        public int compress(char[] chars) {
            if (chars.length == 1) return 1;
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int i = 0; i < chars.length; i++) {
                // 第一个和最后一个特殊处理
                if (i == 0) continue;

                // 元素未改变 / 改变
                if (chars[i] == chars[i - 1]) {
                    count++;
                } else {
                    helper(sb, chars, i, count);
                    count = 1;
                }

                // 最后一个坐标,强制处理
                if (i == chars.length - 1) {
                    if (count == 1) {
                        sb.append(chars[i]);
                    } else {
                        sb.append(chars[i - 1]);
                        sb.append(count);
                    }
                }
            }
//            System.out.println("sb = " + sb);
            // 处理原来的数组
            // java的值传递,chars传进来的时候指向的堆中地址是不能改的!
//            chars = sb.toString().toCharArray();
            for (int i = 0; i < sb.length(); i++) {
                chars[i] = sb.charAt(i);
            }
            return sb.length();
        }

        // 处理元素改变
        private void helper(StringBuilder sb, char[] chars, int i, int count) {
            // a,a,b 添加 a,2
            char cur = chars[i - 1];
            if (count > 1) {
                sb.append(cur);
                sb.append(count);
            }
            // a,b  添加 a
            else {
                sb.append(chars[i - 1]);
            }
        }
    }

    // 参考答案
    class Solution {
        public int compress(char[] chars) {
            int n = chars.length;
            int write = 0, left = 0;
            for (int read = 0; read < n; read++) {
                // 最后一个 / 下一个改变了 --> 进行处理
                if (read == n - 1 || chars[read] != chars[read + 1]) {
                    chars[write++] = chars[read];
                    int num = read - left + 1; // 连续元素范围 [left, read]
                    if (num > 1) {
                        int anchor = write;
                        while (num > 0) {
                            chars[write++] = (char) (num % 10 + '0');
                            num /= 10;
                        }
                        reverse(chars, anchor, write - 1);
                    }
                    left = read + 1;
                }
            }
            return write;
        }

        public void reverse(char[] chars, int left, int right) {
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
