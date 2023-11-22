package Week1.Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // the open status of a site
    private boolean[][] openStatus;
    // virtual top site and bottom site
    private final int top = 0;
    private final int bottom;
    private final WeightedQuickUnionUF uf1;
    private final WeightedQuickUnionUF uf2;
    // the length of the grid
    private final int length;
    // the number of open sites
    private int openNums;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }
        length = n;
        bottom = n * n + 1;
        openStatus = new boolean[n + 1][n + 1];
        uf1 = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
    }
    private boolean validate(int row, int col) {
        return row < 1 || row > length || col < 1 || col > length;
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (validate(row, col)) throw new IllegalArgumentException("row or col is not valid");
        return openStatus[row][col];
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (validate(row, col)) throw new IllegalArgumentException("row or col is not valid");
        if (!isOpen(row, col)) {
            openNums++;
            openStatus[row][col] = true;
            int unionIndex = length * row + col - length;
            // if the site is in the first row, connect it to the virtual top site
            if (row == 1) {
                uf1.union(unionIndex, top);
                uf2.union(unionIndex, top);
            }
            // if the site is in the last row, connect it to the virtual bottom site
            if (row == length) {
                uf1.union(unionIndex, bottom);
            }
            // connect the site to its open neighbors
            for (int[] dir : direction) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (!validate(newRow, newCol) && isOpen(newRow, newCol)) {
                    int newUnionIndex = length * newRow + newCol - length;
                    uf1.union(unionIndex, newUnionIndex);
                    uf2.union(unionIndex, newUnionIndex);
                }
            }
        }
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (validate(row, col)) throw new IllegalArgumentException("row or col is not valid");
        return isOpen(row, col) && uf2.find(length * row + col - length) == uf2.find(top);
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNums;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf1.find(top) == uf1.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation uf = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            if (!uf.isOpen(row, col)) {
                uf.open(row, col);
            }
            StdOut.println(row + " " + col);
        }
        if (uf.percolates()) {
            StdOut.println("percolating");
            StdOut.println(uf.numberOfOpenSites());
        }
        else StdOut.println("not percolating");
    }
}
