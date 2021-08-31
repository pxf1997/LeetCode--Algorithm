/**
 * 题目Id：981
 * 题目：基于时间的键值存储
 * 日期：2021-07-10 23:49:45
 */
//创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作： 
//
// 1. set(string key, string value, int timestamp) 
//
// 
// 存储键 key、值 value，以及给定的时间戳 timestamp。 
// 
//
// 2. get(string key, int timestamp) 
//
// 
// 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。 
//
// 如果有多个这样的值，则返回对应最大的 timestamp_prev 的那个值。 
// 如果没有值，则返回空字符串（""）。 
// 
//
// 
//
// 示例 1： 
//
// 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["f
//oo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
//输出：[null,null,"bar","bar",null,"bar2","bar2"]
//解释：  
//TimeMap kv;   
//kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
//kv.get("foo", 1);  // 输出 "bar"   
//kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即
// "bar"）   
//kv.set("foo", "bar2", 4);   
//kv.get("foo", 4); // 输出 "bar2"   
//kv.get("foo", 5); // 输出 "bar2"   
//
// 
//
// 示例 2： 
//
// 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [
//[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["lov
//e",20],["love",25]]
//输出：[null,null,null,"","high","high","low","low"]
// 
//
// 
//
// 提示： 
//
// 
// 所有的键/值字符串都是小写的。 
// 所有的键/值字符串长度都在 [1, 100] 范围内。 
// 所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的。 
// 1 <= timestamp <= 10^7 
// TimeMap.set 和 TimeMap.get 函数在每个测试用例中将（组合）调用总计 120000 次。 
// 
// Related Topics 设计 哈希表 字符串 二分查找 
// 👍 125 👎 0


package daily_2021_07;

//基于时间的键值存储

import java.util.*;

public class P981_TimeBasedKeyValueStore {
    public static void main(String[] args) {
        //测试代码
        TimeMap kv = new P981_TimeBasedKeyValueStore().new TimeMap();
        // case 1
//        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
//        kv.set("foo", "bar2", 2);
//        kv.set("foo", "bar3", 3);
//        kv.set("foo", "bar4", 4);
//        kv.set("foo", "bar5", 5);
//        kv.set("foo", "bar8", 8);
//        kv.set("foo", "bar10", 10);
//
//        // nums={1,2,3,4,5,8,10} 查找9
//        String t0 = kv.get("foo", 0);
//        System.out.println("t0 = " + t0);
//        System.out.println();
//
//        String t4 = kv.get("foo", 4);
//        System.out.println("t4 = " + t4);
//        System.out.println();
//
//        String t5 = kv.get("foo", 5);
//        System.out.println("t5 = " + t5);
//        System.out.println();
//
//        String t9 = kv.get("foo", 7);
//        System.out.println("t9 = " + t9);
//        System.out.println();


        // case 2
        kv.set("love", "high", 10); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
        kv.set("love", "low", 20);

        // nums={1,2,3,4,5,8,10} 查找9
        String t5 = kv.get("love", 5);
        System.out.println("t5 = " + t5);
        System.out.println();

        String t10 = kv.get("love", 10);
        System.out.println("t10 = " + t10);
        System.out.println();

        String t15 = kv.get("love", 15);
        System.out.println("t15 = " + t15);
        System.out.println();


    }


    // 参考答案--哈希表+数组
    class TimeMap1 {
        // key--键  value--Node构成的list
        // 逻辑--同一个key对应多个value及timestamp,例如("foo", "bar" ,1) ("foo", "bar2" ,4)
        Map<String, List<Node>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap1() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            list.add(new Node(key, value, timestamp));
            map.put(key, list);
        }

        public String get(String key, int timestamp) {
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            if (list.isEmpty()) return "";
            int len = list.size();
            int lo = 0, hi = len - 1;
            while (lo < hi) {
                int mid = lo + hi + 1 >> 1;
                if (list.get(mid).timestamp <= timestamp) {
                    lo = mid;
                }
                // mid时间戳 > timestamp
                else {
                    hi = mid - 1;
                }
            }
            return list.get(hi).timestamp <= timestamp ? list.get(hi).value : "";
        }

        // Node类,包含属性:key,value,timestamp
        class Node {
            String key;
            String value;
            int timestamp;

            public Node(String key, String value, int timestamp) {
                this.key = key;
                this.value = value;
                this.timestamp = timestamp;
            }
        }
    }


    // my--注意二分查找
    class TimeMap_my {
        Map<String, List<Node>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap_my() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            // 分析逻辑--每次插入timestamp都是递增的
            // 只有插入没有更新!!!
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            list.add(new Node(key, value, timestamp));
            map.put(key, list);
        }

        public String get_old(String key, int target) {
            // 二分查找--按list中的timestamp查找
            // 业务逻辑--目标timestamp < target,且为最接近的
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            if (list.isEmpty()) return "";
            int lo = 0, hi = list.size() - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                //System.out.println("lo = " + lo + "  hi = " + hi + "  mid=" + mid);
                if (list.get(mid).timestamp > target) {
                    hi = mid - 1;
                } else if (list.get(mid).timestamp < target) {
                    lo = mid + 1;
                } else {  // 下标mid处元素 = target, 不用继续找了就是你
                    lo = mid;
                    break;
                }
            }
            //System.out.println("lo = " + lo + "  hi = " + hi);

            int found = list.get(lo).timestamp;
            // 注意: found比target大----返回found之前下标值
            if (found > target) {
                if (lo > 0) {
                    return list.get(lo - 1).value;
                } else {
                    return "";
                }
            } else {
                return list.get(lo).value;
            }
        }

        public String get(String key, int target) {
            // 二分查找--按list中的timestamp查找
            // 业务逻辑--目标timestamp < target,且为最接近的
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            if (list.isEmpty()) return "";
            int lo = 0, hi = list.size() - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                //System.out.println("lo = " + lo + "  hi = " + hi + "  mid=" + mid);
                if (list.get(mid).timestamp > target) {
                    hi = mid - 1;
                } else if (list.get(mid).timestamp < target) {
                    lo = mid + 1;
                } else {  // 下标mid处元素 = target, 不用继续找了就是你
                    lo = mid;
                    break;
                }
            }
            //System.out.println("lo = " + lo + "  hi = " + hi);
            String prev = lo > 0 ? list.get(lo - 1).value : "";
            return (list.get(lo).timestamp <= target) ? list.get(lo).value : prev;
        }

        // 一个key--foo 对应多个Node
        class Node {
            String key;
            String value;
            int timestamp;

            public Node(String key, String value, int timestamp) {
                this.key = key;
                this.value = value;
                this.timestamp = timestamp;
            }
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--哈希表+树
    class TimeMap {
        Map<String, TreeMap<Integer, Node>> map = new HashMap<>();
        List<String> allInfo = new ArrayList<>();
        Random random = new Random();

        public void set(String k, String v, int t) {
            update(k, t);
            TreeMap<Integer, Node> ts = map.getOrDefault(k, new TreeMap<Integer, Node>());
            ts.put(t, new Node(k, v, t));
            map.put(k, ts);
        }

        Node _get(String k, int t) {
            TreeMap<Integer, Node> ts = map.get(k);
            if (ts == null) return null;
            Map.Entry<Integer, Node> entry = ts.floorEntry(t);
            if (entry == null) return null;
            Node node = entry.getValue();
            return node;
        }

        public String get(String k, int t) {
            randomDel();
            Node node = _get(k, t);
            return node != null && node.t <= t ? node.v : "";
        }

        public String del(String k, int t) {
            TreeMap<Integer, Node> ts = map.get(k);
            if (ts == null) return null;
            Map.Entry<Integer, Node> entry = ts.floorEntry(t);
            if (entry == null) return null;
            Node node = entry.getValue();
            if (node != null && node.t == t) {
                ts.remove(t);
                return node.v;
            }
            return "";
        }

        // 保存所有的 kt 信息
        void update(String k, int t) {
            String nk = k + "_" + t;
            allInfo.add(nk);
        }

        // 随机删除，再重新插入，验证代码正确性
        void randomDel() {
            int idx = random.nextInt(allInfo.size());
            String[] ss = allInfo.get(idx).split("_");
            String k = ss[0];
            int t = Integer.parseInt(ss[1]);
            Node node = _get(k, t);
            del(node.k, node.t);
            set(node.k, node.v, node.t);
        }

        class Node {
            String k, v;
            int t;

            Node(String _k, String _v, int _t) {
                k = _k;
                v = _v;
                t = _t;
            }
        }
    }


/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
