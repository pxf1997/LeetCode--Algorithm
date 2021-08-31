/**
 * 题目Id：690
 * 题目：员工的重要性
 * 日期：2021-05-01 23:47:10
 */
//给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。 
//
// 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15,
// [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但
//是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。 
//
// 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。 
//
// 
//
// 示例： 
//
// 
//输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
//输出：11
//解释：
//员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 1
//1 。
// 
//
// 
//
// 提示： 
//
// 
// 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属 
// 员工数量不超过 2000 。 
// 
// Related Topics 深度优先搜索 广度优先搜索 哈希表 
// 👍 201 👎 0


//员工的重要性

import java.util.*;

public class P690_EmployeeImportance {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P690_EmployeeImportance().new Solution();
        Employee e1 = new Employee(1, 5, Arrays.asList(2, 3));
        Employee e2 = new Employee(2, 3, Collections.emptyList());
        Employee e3 = new Employee(3, 3, Collections.emptyList());
        List<Employee> eList = new ArrayList<>();
        eList.add(e1);
        eList.add(e2);
        eList.add(e3);
        int res = solution.getImportance(eList, 1);
        System.out.println("res = " + res);
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", importance=" + importance +
                    ", subordinates=" + subordinates +
                    '}';
        }
    }
//力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

    class Solution1 {

        //key--id  value--对应员工
        Map<Integer, Employee> map = new HashMap<>();

        public int getImportance(List<Employee> employees, int id) {
            // 题目给的形如:[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]] 别让误导了
            for (Employee employee : employees) {
                map.put(employee.id, employee);
            }
//            System.out.println("map = " + map);
            return dfs(id);
        }

        private int dfs(int id) {
            Employee cur = map.get(id);
            int total = cur.importance;
            List<Integer> subList = cur.subordinates;
            for (Integer sub : subList) {
                total += dfs(sub);
            }
//            System.out.println("cur=" + cur + "   total=" + total);
            return total;
        }
    }

    class Solution {
        int total2;
        Map<Integer, Employee> map = new HashMap<>();

        public int getImportance(List<Employee> employees, int id) {
            for (Employee employee : employees) {
                map.put(employee.id, employee);
            }
//            System.out.println("map = " + map);

            dfs2(id);
            return total2;

        }

        private void dfs2(int id) {
            Employee cur = map.get(id);

            total2 += cur.importance;
            //调试
//            System.out.println("cur = " + cur);
//            System.out.println("total2 = " + total2);
//            System.out.println();

            List<Integer> subList = cur.subordinates;
            if (subList == null) {
                return;
            }
            for (Integer subId : subList) {
                dfs2(subId);
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
