package Week1;

public class UFWithRootLargest {
    private int[] id;
    private int[] sz;
    private int[] largest;
    private int count;

    UFWithRootLargest(int N) {
        id = new int[N];
        sz = new int[N];
        largest = new int[N];
        count = N;

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int root(int p) {
        while (p != id[p]) {
            p = id[p];
            id[p] = id[id[p]];
        }
        return p;
    }

    public int find(int p) {
        return largest[root(p)];
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;
        int pLargest = largest[p];
        int qLargest = largest[q];
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            if (pLargest > qLargest) {
                largest[qRoot] = pLargest;
            }
        }
        else {
           id[qRoot] = pRoot;
           sz[pRoot] += sz[qRoot];
           if (pLargest < qLargest) {
                largest[pRoot] = qLargest;
            }
        }
        count--;
    }
}
