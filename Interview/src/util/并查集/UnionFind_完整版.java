package util.并查集;

/**
 * @author pxf
 * @create 2021-05-17 11:22
 */
public class UnionFind_完整版 {

    public class UnionFind {
        public int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数
        public int[] parent; // parent[i]表示第i个元素所指向的父节点
        public int size;    // 数据个数
        public int count;  // 连通分量个数
        public int[] sz;    // sz[i]表示以i为根的集合中元素个数

        // 构造函数
        public UnionFind(int size) {
            count = 0;
            rank = new int[size];
            parent = new int[size];
            this.size = size;
            sz = new int[count];
            // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
                sz[i] = 1;
                count++;
            }
        }

        // 查找过程, 查找元素p所对应的集合编号
        // O(h)复杂度, h为树的高度
        public int find(int p) {
            assert (p >= 0 && p < size);
            // 不断去查询自己的父亲节点, 直到到达根节点
            // 根节点的特点: parent[p] == p
/*        while (p != parent[p])
            p = parent[p];
        return p;*/

            // 第一种路径压缩算法
/*        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // 父节点设为爷爷节点
            p = parent[p];
        }
        return p;*/

            //  第二种路径压缩算法
            if (p != parent[p]) {
                parent[p] = find(parent[p]);// 父节点设为根节点
            }
            return parent[p];
        }

        // 查看元素p和元素q是否所属一个集合
        // O(h)复杂度, h为树的高度
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 合并元素p和元素q所属的集合
        // O(h)复杂度, h为树的高度

        /**
         * 层数rank少的合并到多的上，二者rank相等则p合并到q，且多一层--总rank加1
         */
        public void unionElements(int p, int q) {

            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot)
                return;

            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else if (rank[pRoot] > rank[qRoot]) {
                sz[pRoot] += sz[qRoot];
                parent[qRoot] = pRoot;
            } else { // rank[pRoot] == rank[qRoot]
                parent[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
                rank[qRoot] += 1;   // 维护rank的值
            }
            count--;
        }

        /**
         * 手动设置例子以后，更新此时的rank
         */
        public void updateRank() {
            //新改进--我不管你rank是啥(某个诡异的中间状态)，都先重置为1
            for (int i = 0; i < size; i++) {
                rank[i] = 1;
            }
            for (int i = 0; i < size; i++) {
                int height = 1;
                boolean isLeaf = true;
                for (int j = 0; j < size; j++) {
                    //有其他节点的根为i
                    if (parent[j] == i) {
                        isLeaf = false;
                        break;
                    }
                }
                // 取max逻辑---从不同路径更新上来的树高度height应取最大值
                if (isLeaf) { //从叶子往上更新rank
                    rank[i] = height;
                    int cur = parent[i]; //上一层为i的父亲
                    while (parent[cur] != cur) { //跳出循环时，cur为根
                        height++;
                        rank[cur] = Math.max(height, rank[cur]);
                        cur = parent[cur];
                    }
                    rank[cur] = Math.max(height + 1, rank[cur]);
                }
            }
        }

        //不完善--弃用
    /*public void updateRank2() {
        for (int i = 0; i < size; i++) {
            int height = 1;
            boolean isLeaf = true;
            for (int j = 0; j < size; j++) {
                //有其他节点的根为i
                if (parent[j] == i) {
                    isLeaf = false;
                    break;
                }
            }
            // 取min逻辑---每个节点rank只能变小--也有小问题
            if (isLeaf) { //从叶子往上更新rank
                rank[i] = height;
                int cur = parent[i]; //上一层为i的父亲
                while (parent[cur] != cur) { //跳出循环时，cur为根
                    height++;
                    rank[cur] = Math.min(height, rank[cur]);
                    cur = parent[cur];
                }
                rank[cur] = Math.min(height + 1, rank[cur]);
            }
        }
    }*/

        /**
         * @return 连通分量个数
         */
        public int getCount() {
            return count;
        }
    }
}
