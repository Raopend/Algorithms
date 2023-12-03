package Week1.Analysis;
import java.util.Collections;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class ThreeSum {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            a.add(StdIn.readInt());
        }
        Collections.sort(a);
        for (int i = 0; i < a.size() - 2; i++) {
            int j = i + 1;
            int k = a.size()-1;
            while (j < k) {
                int sum = a.get(i) + a.get(j) + a.get(k);
                if (sum == 0) {
                    StdOut.println(a.get(i) + " " + a.get(j) + " " + a.get(k));
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
    }
}
