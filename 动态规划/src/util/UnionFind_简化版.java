package util;

/**
 * @author pxf
 * @create 2021-05-17 11:21
 */
public class UnionFind_简化版 {
    //简化版
    class UnionFind {
        public int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数
        public int[] parent; // parent[i]表示第i个元素所指向的父节点
        public int[] sz;
        public int size;    // 数据个数
        public int count;  // 连通分量个数

        // 构造函数
        public UnionFind(int size) {
            count = 0;
            parent = new int[size];
            rank = new int[size];
            sz = new int[size];
            this.size = size;
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
            } else if (rank[qRoot] < rank[pRoot]) {
                parent[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else { // rank[pRoot] == rank[qRoot]
                parent[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
                rank[qRoot] += 1;   // 维护rank的值
            }
            count--;
        }

        /**
         * @return 连通分量个数
         */
        public int getCount() {
            return count;
        }
    }
}
