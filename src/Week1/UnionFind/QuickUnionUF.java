package Week1.UnionFind;

public class QuickUnionUF {
    private int[] id;
    private int count;
    public QuickUnionUF(int N) {
        id = new int[N];
        count = 0;
        for (int i = 0; i< N; i++) {
            id[i] = i;
            count++;
        }
    }

    public int find(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }
}
