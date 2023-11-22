package Week1;

import edu.princeton.cs.algs4.StdOut;

public class SuccessorWithDelete {
    private int[] id;
    private int[] sz;
    private int[] largest;
    private boolean[] isRemove;
    private int count;
    private int N;
    SuccessorWithDelete(int N) {
        id = new int[N];
        sz = new int[N];
        largest = new int[N];
        isRemove = new boolean[N];
        count = N;
        this.N = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
            isRemove[i] = false;
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
    public void remove(int x) {
        if (x > 0 && isRemove[x - 1]) union(x - 1, x);
        if (x < N - 1 && isRemove[x + 1]) union(x + 1, x);
        isRemove[x] = true;
    }
    public int getSuccessor(int x) {
        if (x < 0 || x > N - 1) {
            throw new IllegalArgumentException("Access out of bounds!");
        }
        else if (isRemove[x]) {
            if (find(x) + 1 > N - 1) return -1;
            else {
                return find(x) + 1;
            }
        }
        else if (x == N - 1) {
            return x;
        }
        else if (isRemove[x+1]) {
            return find(x + 1) + 1;
        }
        else {
            return x + 1;
        }
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

    public static void main(String[] args) {
        SuccessorWithDelete uf = new SuccessorWithDelete(10);
        uf.remove(2);
        uf.remove(4);
        uf.remove(3);
        StdOut.println("the successor is : " + uf.getSuccessor(3));
        uf.remove(7);
        StdOut.println("the successor is : " + uf.getSuccessor(0));
        StdOut.println("the successor is : " + uf.getSuccessor(1));
        StdOut.println("the successor is : " + uf.getSuccessor(9));
    }
}
