package Week3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Merge {
    private static Comparable[] aux;

    // is v < w?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + hi >> 1;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void show(Comparable[] a) {
        for (Comparable i: a) {
            StdOut.println(i);
        }
    }

    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();
        Integer[] integerArray = Arrays.stream(a)
                .boxed()
                .toArray(Integer[]::new);
        sort(integerArray);
        show(integerArray);
    }
}
