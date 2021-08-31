/**
 * 题目Id：146
 * 题目：LRU 缓存机制
 * 日期：2021-07-08 15:26:17
 */
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 105 
// 最多调用 2 * 105 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1483 👎 0


package leetcode.editor.cn;

//LRU 缓存机制

import java.util.HashMap;
import java.util.Map;

public class P146_LruCache_my {
    public static void main(String[] args) {
        //测试代码
        // case 1
//        LRUCache lRUCache = new P146_LruCache().new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        System.out.println("lRUCache.get(1) = " + lRUCache.get(1));// 返回1,注意,此处操作了key=1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        System.out.println("lRUCache.get(2) = " + lRUCache.get(2)); // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        System.out.println("lRUCache.get(1) = " + lRUCache.get(1)); // 返回 -1 (未找到)
//        System.out.println("lRUCache.get(3) = " + lRUCache.get(3)); // 返回3
//        System.out.println("lRUCache.get(4) = " + lRUCache.get(4)); // 返回4

        // case2
        LRUCache lRUCache = new P146_LruCache_my().new LRUCache(1);
        lRUCache.put(2, 1);
        System.out.println("lRUCache.get(2) = " + lRUCache.get(2));
        lRUCache.put(3, 2); // 怎么没有移除 2-->1键值对???
        System.out.println("lRUCache.get(2) = " + lRUCache.get(2));
        System.out.println("lRUCache.get(3) = " + lRUCache.get(3));

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 评价--用数组会在内存中产生极其多的多余数组(并非原地修改,每次都是new新的数组)
    //    --每次put和get会更新时间戳,都需要遍历整张哈希表,复杂度O(n)
    class LRUCache {
        // key--键, val--{value, time(时间戳)}
        int capacity;
        Map<Integer, int[]> data;

        public LRUCache(int capacity) {
            data = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            // 有key返回 {value, time}中的 value
            update_time(key);
            if (data.containsKey(key)) {
                return data.get(key)[0];
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            // 思路--当前操作key的time为0,其余time加1
            // 若新增元素超过了限制,则移除一个time最大的键值对
            // 1--更新元素,不会发生删除操作!
            if (data.containsKey(key)) {
                int[] cur = new int[]{value, 0};
                data.put(key, cur);
            }
            // 2--新增元素,可能会发生删除操作
            else {
                // 当前已满,寻找时间最久的键--这部分复杂度很高!!!
                if (data.size() == capacity) {
                    int max_time = Integer.MIN_VALUE; // 保证能找到
                    int key_to_remove = 0;
                    for (Map.Entry<Integer, int[]> entry : data.entrySet()) {
                        int cur_time = entry.getValue()[1];
                        if (cur_time > max_time) {
                            max_time = cur_time;
                            key_to_remove = entry.getKey();
                        }
                    }
                    data.remove(key_to_remove);
                    //System.out.println("移除time最大的key值为:" + key_to_remove + "  对应time为:" + max_time);
                }
                // 当前没满--不用操作
                data.put(key, new int[]{value, 0});
            }
            update_time(key);
        }

        // 当前操作的键key时间time设为0,其余的键的time增加1
        public void update_time(int operating_key) {
            for (Map.Entry<Integer, int[]> entry : data.entrySet()) {
                int cur_key = entry.getKey();
                int val = entry.getValue()[0];
                int cur_time = entry.getValue()[1];
                if (cur_key != operating_key) {
                    data.put(cur_key, new int[]{val, cur_time + 1});
                }
                if (cur_key == operating_key) {
                    data.put(cur_key, new int[]{val, 0});
                }
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
