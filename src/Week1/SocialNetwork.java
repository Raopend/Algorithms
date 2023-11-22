package Week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetwork {
    private int[] id;
    private int[] sz;
    private int count;

    SocialNetwork(int N) {
        id = new int[N];
        sz = new int[N];
        count = N;

        for (int i = 0; i < N; i++) id[i] = i;
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
            id[p] = id[id[p]];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
           id[qRoot] = pRoot;
           sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SocialNetwork uf = new SocialNetwork(n);
        String date, time;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            date = StdIn.readString();
            time = StdIn.readString();
            uf.union(p, q);
            if (uf.count == 1) {
                StdOut.println("All members were connected at: " + date + time);
                break;
            }
        }
    }
}
