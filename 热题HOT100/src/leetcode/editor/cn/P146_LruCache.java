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

import java.util.*;

public class P146_LruCache {
    public static void main(String[] args) {
        //测试代码
        // case 1
        LRUCache lRUCache = new P146_LruCache().new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println("插入1,插入2 = " + lRUCache.printLinkedList());

        System.out.println("lRUCache.get(1) = " + lRUCache.get(1));// 返回1,注意,此处操作了key=1
        System.out.println("查询1 = " + lRUCache.printLinkedList());

        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println("lRUCache.get(2) = " + lRUCache.get(2)); // 返回 -1 (未找到)
        System.out.println("插入3,查询2 = " + lRUCache.printLinkedList());

        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println("插入4 = " + lRUCache.printLinkedList());
        System.out.println("lRUCache.get(1) = " + lRUCache.get(1)); // 返回 -1 (未找到)
        System.out.println("lRUCache.get(3) = " + lRUCache.get(3)); // 返回3
        System.out.println("lRUCache.get(4) = " + lRUCache.get(4)); // 返回4
    }

    // 哈希表+双向链表
    class LRUCache1 {
        // 存储数据的哈希表
        Map<Integer, DLinkedNode> map = new HashMap<>();
        int size;
        int capacity;
        DLinkedNode head, tail; // 头结点--最近使用,尾节点--最久使用

        public LRUCache1(int capacity) {
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


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 重新练习
    class LRUCache {
        // key--键 value--对应的链表节点
        Map<Integer, DLinkedNode> map;
        DLinkedNode head;
        DLinkedNode tail;
        int size;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            map = new HashMap<>();
            // 哑节点--头和尾
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = map.get(key);
            // map中没有对应的key
            if (node == null) {
                return -1;
            } else {
                moveToHead(node);
                return node.value;
            }
        }

        // 移动某个节点node到头部----移除 + 在头部添加
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        // 双向链表中移除某个节点
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 在头部添加
        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        public void put(int key, int value) {
            DLinkedNode node = map.get(key);
            // 不存在该key值
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);
                addToHead(newNode);
                map.put(key, newNode);
                size++;
                if (size > capacity) {
                    DLinkedNode remove = removeTail();
                    map.remove(remove.key);
                    size--;
                }
            }
            // 存在该key值
            else {
                node.value = value;
                moveToHead(node);
                map.put(key, node);
            }

        }

        private DLinkedNode removeTail() {
            DLinkedNode remove = tail.prev;
            removeNode(remove);
            return remove;
        }

        // helper--打印当前链表
        public List<Integer> printLinkedList() {
            List<Integer> res = new ArrayList<>();
            DLinkedNode cur = head;
            while (cur != null) {
                res.add(cur.key);
                cur = cur.next;
            }
            return res;
        }


        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
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
