package Week2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n;
    private int first;
    private int last;

    public RandomizedQueue() {
        queue = (Item[]) new Object[10];
        n = 0;
        first = 0;
        last = -1;
    }
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int index = 0;
        for (int i = first; i <= last; i++) {
            temp[index++] = queue[i];
        }
        queue = temp;
        first = 0;
        last = n - 1;
    }

    public boolean isEmpty() {
        return first > last;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        queue[++last] = item;
        n++;
        if (n == queue.length) {
            resize(2 * queue.length);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int pos = StdRandom.uniformInt(first, last + 1);
        n--;
        Item current = queue[pos];
        queue[pos] = queue[last];
        queue[last--] = null;
        return current;
    }

     public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int pos = StdRandom.uniformInt(first, last + 1);
        return queue[pos];
     }
    private class ArrayListIterator implements Iterator<Item> {
        final Item[] items;
        private int index = 0;

        public ArrayListIterator() {
            int k = 0;
            items = (Item[]) new Object[n];
            for (int i = first; i <= last; i++) {
                items[k++] = queue[i];
            }
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return index >= first && index <= last;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

     public Iterator<Item> iterator() {
        return new ArrayListIterator();
     }

     public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();

        // adding 10 elements
        for (int i = 0; i < 10; i++) {
            test.enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + test.size() + "\n");

        }

        for (Integer i : test) StdOut.println(i);
    }
}
