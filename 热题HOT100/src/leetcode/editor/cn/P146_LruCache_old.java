/**
 * 题目Id：146
 * 题目：LRU 缓存机制
 * 日期：2021-07-08 16:30:59
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
import java.util.LinkedHashMap;
import java.util.Map;

public class P146_LruCache_old {
    public static void main(String[] args) {
        //测试代码
        // case 1
        LRUCache lRUCache = new P146_LruCache_old().new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println("lRUCache.get(1) = " + lRUCache.get(1));// 返回1,注意,此处操作了key=1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println("lRUCache.get(2) = " + lRUCache.get(2)); // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println("lRUCache.get(1) = " + lRUCache.get(1)); // 返回 -1 (未找到)
        System.out.println("lRUCache.get(3) = " + lRUCache.get(3)); // 返回3
        System.out.println("lRUCache.get(4) = " + lRUCache.get(4)); // 返回4
    }


    // 直接使用LinkedHashMap数据结构--等于开挂
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        // 重写方法--移除最老元素
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 哈希表+双向链表
    class LRUCache {
        // 存储数据的哈希表
        Map<Integer, DLinkedNode> map = new HashMap<>();
        int size;
        int capacity;
        DLinkedNode head, tail; // 头结点--最近使用,尾节点--最久使用

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用哑结点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = map.get(key);
            if (node == null) {
                return -1;
            }
            // 如果key存在,则通过哈希表定位对应节点,将其移至头部(逻辑上等于--更新时间戳)
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = map.get(key);
            // 如果key不存在
            if (node == null) {
                // 创建新节点并添加进哈希表
                DLinkedNode newNode = new DLinkedNode(key, value);
                map.put(key, newNode);
                // 添加到链表头部
                addToHead(newNode);
                size++;
                // 容量满了,需要移除尾部,同时删除map对应项
                if (size > capacity) {
                    DLinkedNode remove = removeTail();
                    map.remove(remove.key);
                    size--;
                }
            }
            // 如果key存在,一定不会移除元素
            else {
                node.value = value;
                moveToHead(node);
            }
        }

        // 重点注意----代码的复用
        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        // 移至头部--移除+在头部添加
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        // 从双向链表中移除一个元素
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        // 双向链表节点
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
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
