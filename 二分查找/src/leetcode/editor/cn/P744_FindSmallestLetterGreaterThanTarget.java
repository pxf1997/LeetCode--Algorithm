/**
 * 题目Id：744
 * 题目：寻找比目标字母大的最小字母
 * 日期：2021-04-20 11:18:10
 */
//给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。 
//
// 在比较时，字母是依序循环出现的。举个例子： 
//
// 
// 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a' 
// 
//
// 
//
// 示例： 
//
// 输入:
//letters = ["c", "f", "j"]
//target = "a"
//输出: "c"
//
//输入:
//letters = ["c", "f", "j"]
//target = "c"
//输出: "f"
//
//输入:
//letters = ["c", "f", "j"]
//target = "d"
//输出: "f"
//
//输入:
//letters = ["c", "f", "j"]
//target = "g"
//输出: "j"
//
//输入:
//letters = ["c", "f", "j"]
//target = "j"
//输出: "c"
//
//输入:
//letters = ["c", "f", "j"]
//target = "k"
//输出: "c"
// 
//
// 
//
// 提示： 
//
// 
// letters长度范围在[2, 10000]区间内。 
// letters 仅由小写字母组成，最少包含两个不同的字母。 
// 目标字母target 是一个小写字母。 
// 
// Related Topics 二分查找 
// 👍 114 👎 0


package leetcode.editor.cn;

//寻找比目标字母大的最小字母

public class P744_FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P744_FindSmallestLetterGreaterThanTarget().new Solution();
        char[] test = {'b', 'c', 'f', 'g'};
        char res = solution.nextGreatestLetter(test, 'd');
        System.out.println(res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char nextGreatestLetter(char[] letters, char target) {
//            l、h、mid都是index
            int len = letters.length;
            int l = 0, h = len - 1;
            while (l <= h) {
                int mid = l + (h - l) / 2;
                if (letters[mid] <= target) l = mid + 1;
                else h = mid - 1;
            }
//          考虑如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
//           此时h一直不变，循环终止时 l=h+1=len

//            那么，为什么返回index=l处的字符呢？
//            使用二分查找：让我们找到最右边的位置将 target 插入 letters 中，以便它保持排序。

//            直接考虑最后一次进入while l=h=mid 然后判断if逻辑就好理解了

            return (l < len) ? letters[l] : letters[0];


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
