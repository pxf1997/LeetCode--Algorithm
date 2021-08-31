/**
 * 题目Id：剑指 Offer 33
 * 题目：二叉搜索树的后序遍历序列
 * 日期：2021-06-17 10:24:20
 */
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。 
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// 👍 279 👎 0


package leetcode.editor.cn;

//二叉搜索树的后序遍历序列

import java.util.Arrays;

public class 剑指Offer_33_二叉搜索树的后序遍历序列 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_33_二叉搜索树的后序遍历序列().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 后序遍历--左 右 根
        public boolean verifyPostorder(int[] sequence) {
            // 讨论极端情况  5,4,3,2,1 链表
            int len = sequence.length;
            if (len == 0) {  // 比如左子树为空
                return true;
            }
            if (len == 1) {  // 单个节点
                return true;
            }
            int root = sequence[len - 1];

            int split = -1;
            for (int i = 0; i < len - 1; i++) {
                // [0, split] 是左子树
                if (sequence[i] < root) split++;
                else break;
            }
            for (int i = split + 1; i < len - 1; i++) {
                // [split+1, len-2] 是右子树 不应当出现比root小的值!
                if (sequence[i] < root) return false;
            }
            int[] left = Arrays.copyOfRange(sequence, 0, split + 1);
            int[] right = Arrays.copyOfRange(sequence, split + 1, len - 1);

            return verifyPostorder(left) && verifyPostorder(right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
