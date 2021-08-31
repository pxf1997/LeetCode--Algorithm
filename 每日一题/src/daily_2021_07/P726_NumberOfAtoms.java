/**
 * 题目Id：726
 * 题目：原子的数量
 * 日期：2021-07-05 09:38:07
 */
//给定一个化学式formula（作为字符串），返回每种原子的数量。 
//
// 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
// 
//
// 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。 
//
// 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。 
//
// 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数
//量（如果数量大于 1），以此类推。 
//
// 示例 1: 
//
// 
//输入: 
//formula = "H2O"
//输出: "H2O"
//解释: 
//原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2: 
//
// 
//输入: 
//formula = "Mg(OH)2"
//输出: "H2MgO2"
//解释: 
//原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3: 
//
// 
//输入: 
//formula = "K4(ON(SO3)2)2"
//输出: "K4N2O14S4"
//解释: 
//原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 注意: 
//
// 
// 所有原子的第一个字母为大写，剩余字母都是小写。 
// formula的长度在[1, 1000]之间。 
// formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。 
// 
// Related Topics 栈 哈希表 字符串 
// 👍 140 👎 0


package daily_2021_07;

//原子的数量

import java.util.*;

public class P726_NumberOfAtoms {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P726_NumberOfAtoms().new Solution();
        String res = solution.countOfAtoms("K4(ON(SO3)2)2");
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--栈(递归)+哈希表
    class Solution {
        int i, n;
        String formula;

        public String countOfAtoms(String formula) {
            this.i = 0;
            this.n = formula.length();
            this.formula = formula;

            Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
            stack.push(new HashMap<String, Integer>());
            while (i < n) {
                char ch = formula.charAt(i);
                if (ch == '(') {
                    i++;
                    stack.push(new HashMap<String, Integer>()); // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
                } else if (ch == ')') {
                    i++;
                    int num = parseNum(); // 括号右侧数字
                    Map<String, Integer> popMap = stack.pop(); // 弹出括号内的原子数量
                    Map<String, Integer> topMap = stack.peek();
                    for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                        String atom = entry.getKey();
                        int v = entry.getValue();
                        topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num); // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                    }
                } else {
                    String atom = parseAtom();
                    int num = parseNum();
                    Map<String, Integer> topMap = stack.peek();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + num); // 统计原子数量
                }
            }

            Map<String, Integer> map = stack.pop();
            TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);

            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                String atom = entry.getKey();
                int count = entry.getValue();
                sb.append(atom);
                if (count > 1) {
                    sb.append(count);
                }
            }
            return sb.toString();
        }

        // 统计元素
        public String parseAtom() {
            StringBuffer sb = new StringBuffer();
            sb.append(formula.charAt(i++)); // 扫描首字母,例如Mg的M
            while (i < n && Character.isLowerCase(formula.charAt(i))) {
                sb.append(formula.charAt(i++)); // 扫描首字母后的小写字母,例如Mg的g
            }
            return sb.toString();
        }

        // 统计元素后面的数字
        public int parseNum() {
            if (i == n || !Character.isDigit(formula.charAt(i))) {
                return 1; // 不是数字，视作 1
            }
            int num = 0;
            while (i < n && Character.isDigit(formula.charAt(i))) {
                num = num * 10 + formula.charAt(i++) - '0'; // 扫描数字
            }
            return num;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
