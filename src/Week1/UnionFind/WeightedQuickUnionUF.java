package Week1.UnionFind;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        count = 0;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            count++;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int find(int p) {
        while (id[p] != p) {
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
        if (sz[pRoot] < sz[qRoot]) {
            pRoot = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            qRoot = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
}
