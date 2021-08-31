/**
 * 题目Id：1418
 * 题目：点菜展示表
 * 日期：2021-07-06 10:09:28
 */
//给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodIt
//emi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。 
//
// 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中
//的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。 
//
// 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。 
//
// 
//
// 示例 1： 
//
// 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David",
//"3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","
//Ceviche"]]
//输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1
//","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
//解释：
//点菜展示表如下所示：
//Table,Beef Burrito,Ceviche,Fried Chicken,Water
//3    ,0           ,2      ,1            ,0
//5    ,0           ,1      ,0            ,1
//10   ,1           ,0      ,0            ,0
//对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
//而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
//餐桌 10：Corina 点了 "Beef Burrito" 
// 
//
// 示例 2： 
//
// 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],[
//"Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","
//Canadian Waffles"]]
//输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
// 
//解释：
//对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
//而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
// 
//
// 示例 3： 
//
// 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melis
//sa","2","Soda"]]
//输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= orders.length <= 5 * 10^4 
// orders[i].length == 3 
// 1 <= customerNamei.length, foodItemi.length <= 20 
// customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。 
// tableNumberi 是 1 到 500 范围内的整数。 
// 
// Related Topics 数组 哈希表 字符串 有序集合 排序 
// 👍 37 👎 0


package daily_2021_07;

//点菜展示表

import java.util.*;

public class P1418_DisplayTableOfFoodOrdersInARestaurant {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1418_DisplayTableOfFoodOrdersInARestaurant().new Solution();
        List<List<String>> orders = new ArrayList<>();
        orders.add(Arrays.asList("David", "3", "Ceviche"));
        orders.add(Arrays.asList("Corina", "10", "Beef Burrito"));
        orders.add(Arrays.asList("David", "3", "Fried Chicken"));
        orders.add(Arrays.asList("Carla", "5", "Water"));
        orders.add(Arrays.asList("Carla", "5", "Ceviche"));
        orders.add(Arrays.asList("Rous", "3", "Ceviche"));

        List<List<String>> res = solution.displayTable(orders);
        for (List<String> row : res) {
            System.out.println(row);
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my--屎山代码的感觉!
    class Solution {
        List<List<String>> res = new ArrayList<>();

        public List<List<String>> displayTable(List<List<String>> orders) {
            // 1--记录每桌菜品,桌号集合,菜品集合
            // key--桌号, value--<k2,v2>
            // k2--菜品, v2--菜品数量
            Map<String, Map<String, Integer>> map = new HashMap<>();
            Set<Integer> table_set = new HashSet<>();
            Set<String> food_set = new HashSet<>();
            for (List<String> order : orders) {
                String table = order.get(1);
                String food = order.get(2);
                table_set.add(Integer.parseInt(table));
                food_set.add(food);
                // table桌号对应的cur, 有就取出来,没有就新造
                // map结构:<桌号, <菜品,菜品数量>>
                // cur结构:<菜品,菜品数量>
                Map<String, Integer> cur = map.getOrDefault(table, new HashMap<>());
                cur.put(food, cur.getOrDefault(food, 0) + 1);
                map.put(table, cur);
            }

            // 2--桌号set和食品set升序排列,取出来到List中进行排序!
            List<Integer> table_list = new ArrayList<>(table_set);
            List<String> food_list = new ArrayList<>(food_set);
            Collections.sort(table_list);
            Collections.sort(food_list);
            // helper
//            System.out.println("map = " + map);
//            System.out.println("table_list = " + table_list);
//            System.out.println("food_list = " + food_list);

            // 3--拼装成结果,注意格式
            // header为第一行(表头)
            List<String> header = new ArrayList<>();
            header.add("Table");
            header.addAll(food_list);
            res.add(header);

            for (int table : table_list) {
                // table,key--桌号
                List<String> row_cur = new ArrayList<>();
                String key = Integer.toString(table);
                row_cur.add(key);
                // cur--<食品,数量>集合
                Map<String, Integer> cur = map.get(key);
                // 遍历食品集合,本桌没点此食物就填0
                for (String food : food_list) {
                    int food_num = cur.getOrDefault(food, 0);
                    row_cur.add(Integer.toString(food_num));
                }
                res.add(row_cur);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
