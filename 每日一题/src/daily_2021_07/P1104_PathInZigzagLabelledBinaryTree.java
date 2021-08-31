/**
 * 题目Id：1104
 * 题目：二叉树寻路
 * 日期：2021-07-29 09:32:19
 */
//在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
//
// 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
//
// 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
//
// 
//
// 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
//
// 
//
// 示例 1： 
//
// 输入：label = 14
//输出：[1,3,4,14]
// 
//
// 示例 2： 
//
// 输入：label = 26
//输出：[1,2,6,10,26]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= label <= 10^6 
// 
// Related Topics 树 数学 二叉树 
// 👍 94 👎 0


package daily_2021_07;

//二叉树寻路

import java.util.*;

public class P1104_PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1104_PathInZigzagLabelledBinaryTree().new Solution();
        List<Integer> res = solution.pathInZigZagTree(14);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案
    class Solution {
        public List<Integer> pathInZigZagTree(int label) {
            int row = 1, rowStart = 1;
            while (rowStart * 2 <= label) {
                row++;
                rowStart *= 2;
            }
            if (row % 2 == 0) {
                label = getReverse(label, row);
            }
            List<Integer> path = new ArrayList<Integer>();
            while (row > 0) {
                if (row % 2 == 0) {
                    path.add(getReverse(label, row));
                } else {
                    path.add(label);
                }
                row--;
                label >>= 1;
            }
            Collections.reverse(path);
            return path;
        }

        public int getReverse(int label, int row) {
            return (1 << row - 1) + (1 << row) - 1 - label;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
