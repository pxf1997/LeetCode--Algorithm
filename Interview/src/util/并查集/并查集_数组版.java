package util.并查集;

/**
 * @author pxf
 * @create 2021-05-13 11:59
 */
public class 并查集_数组版 {
    public static class UF {

        private final int[] id;

        UF(int N) {
            id = new int[N + 1];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
            }
        }

        //  id中所有uID改为vID
        //  意义为----u所在连通分量的所有节点的ID为uID，都改成vID即与v相连
        void union(int u, int v) {
            int uID = find(u);
            int vID = find(v);
            if (uID == vID) {
                return;
            }
            for (int i = 0; i < id.length; i++) {
                if (id[i] == uID) {
                    id[i] = vID;
                }
            }
        }

        int find(int p) {
            return id[p];
        }

        boolean connect(int u, int v) {
            return find(u) == find(v);
        }
    }
}
