package Week2.ElementarySort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Shell {
    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }

    // exchange a[i] and a[j]
    private static <T> void exch(T[] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is v > w?
    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    // is v > w?
    private static <T> boolean less(Comparator<T> comparator, T v, T w) {
        return comparator.compare(v, w) < 0;
    }

    // print array to standard output
    private static <T> void show(T[] a) {
        for (T i : a) {
            StdOut.println(i);
        }
    }

    // is the array a[] sorted?
    private static <T extends Comparable<T>> boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private static <T> boolean isSorted(T[] a, Comparator<T> comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static <T> boolean isSorted(T[] a, Comparator<T> comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i - 1])) return false;
        return true;
    }

    // is the array h-sorted?
    private static <T extends Comparable<T>> boolean isHsorted(T[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
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
